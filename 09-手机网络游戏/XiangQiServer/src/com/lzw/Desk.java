package com.lzw;
import java.util.Vector;

public class Desk {
	private int ID;// ����ID
	private Player[] players;// �������
	private int NUM = 2;// �������
	private Player banker = null; // ׯ��
	private int bankerID = 0; // ׯ��ID
	private Umpire umpire;// �㷨
	private int game = 0; // ��Ϸ����
	private int score = 0;// ���� ��ʱû�õ�
	Server server;
	public Desk() {
		game = 0;
		server = new Server();
		umpire = new Umpire();
		players = new Player[NUM];
		banker = null;
		bankerID = 0;
		for (int i = 0; i < NUM; i++) {
			players[i] = null;
		}
	}
	/**
	 * ��ʼ��***********
	 */
	public void init() {
		banker = null; // ׯ��
		bankerID = 0;
		game = 0;
		umpire.init();
	}
	public void reset(){
		umpire.init();
		for(int i=0;i<NUM;i++)
			players[i].reset();
	}
	public void start() {
		players[bankerID].setColor("red");
		players[(bankerID+1)%NUM].setColor("white");
		sendMessage(players[bankerID], "color:"+players[bankerID].getColor());
		sendMessageToOther(players[bankerID],"color:"+players[(bankerID+1)%NUM].getColor());
		sendMessage(players[bankerID], "turn");
	}
	public void moveChess(String message) {
		int index0 = message.indexOf(";");
		int index1 = message.indexOf(":");
		int index2 = message.indexOf(",", index1 + 1);
		int index3 = message.indexOf(",", index2 + 1);
		int index4 = message.indexOf(",", index3 + 1);
		int seat = Integer.parseInt(message.substring(index0 + 1, index1));
		int selectedY = Integer.parseInt(message.substring(index1 + 1, index2));
		int selectedX = Integer.parseInt(message.substring(index2 + 1, index3));
		int n = Integer.parseInt(message.substring(index3 + 1, index4));
		int m = Integer.parseInt(message.substring(index4 + 1));
		umpire.moveChess(selectedY, selectedX, n, m);
		sendMessageToOther(players[seat], message);
		if (Umpire.isWhiteWin == 0) {
			sendMessage(players[bankerID], "win,you");
			sendMessageToOther(players[bankerID], "win");
			bankerID = (seat + 1) % NUM;
			reset();
		} else if (Umpire.isRedWin == 0) {
			sendMessage(players[(bankerID+1)%NUM], "win,you");
			sendMessageToOther(players[(bankerID+1)%NUM], "win");
			bankerID = (seat + 1) % NUM;
			reset();
		}
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public Player getBanker() {
		return banker;
	}
	public void setBanker(Player banker) {
		this.banker = banker;
	}
	public int getGame() {
		return game;
	}
	public boolean isReady() // �����Ƿ����������Ҷ���ʼ
	{
		for (int i = 0; i < NUM; i++) {
			if (players[i] == null)
				return false;
			else if (!players[i].isStart())
				return false;
		}
		umpire.init();
		return true;
	}
	public boolean isEmpty(int pos) {// ����pos�Ƿ��
		if (pos >= NUM)
			return false;
		return players[pos] == null;
	}
	public int getBankerID() {
		return bankerID;
	}
	public int getPlayersCounter() {// ����������
		return NUM;
	}
	public Player[] getPlayers() {
		return players;
	}
	public int getPlayerSeat(Player p) // ���������λ
	{
		for (int i = 0; i < NUM; i++) {
			if (players[i] == null)
				continue;
			if (players[i].equals(p))
				return i;
		}
		return -1;
	}
	public void setPlayer(int pos, Player n) {// �趨���n����pos��λ��
		if (pos >= NUM)
			return;
		players[pos] = n;
	}
	public void removePlayer(Player p) {// �Ƴ����p
		for (int i = 0; i < NUM; i++) {
			if (players[i] == null)
				continue;
			else if (players[i].equals(p))
				players[i] = null;
		}
	}
	public void sendMessageToAll(String mes) {
		for (int i = 0; i < NUM; i++)
			if (players[i] != null)
				sendMessage(players[i], mes);
	}
	public void sendMessageToOther(Player player, String message) {
		for (int i = 0; i < NUM; i++) {
			if (players[i] != null && !players[i].equals(player))
				sendMessage(players[i], message);
		}
	}
	public void sendMessage(Player p, String m) {
		server.sendMessage(p, m);
	}
	public void sendBankerInfo() {
		sendMessageToAll("bankerInfo:" + bankerID);
	}
	public void sendScoreInfo() {
		sendMessageToAll("scoreInfo:" + score);
	}
	public void overOneGame() {
		init();
	}
}
