package com.lzw;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Server extends HttpServlet {

	private Hashtable players = new Hashtable();

	private int NUM = 2;

	private int DESKNUM = 10; // ������������

	private Desk[] desks = new Desk[DESKNUM];

	private int counter = 1; // ��Ҽ�����

	private Player player;

	PrintWriter out;

	/*
	 * ��ʼ�� ��players(Hashtable)��desks(��������)��ʼ��
	 */
	public void init(ServletConfig config) throws ServletException { // ��ʼ��
		super.init(config);
		players.clear();
		for (int i = 0; i < DESKNUM; i++) {
			desks[i] = new Desk();
			desks[i].setID(i);
		}
	}

	/*
	 * ������Ϣ������ ���ܸ�����Ϣ��Ȼ�������Ӧ�Ĵ�����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) // ������Ϣ
			throws ServletException, IOException {
		out = response.getWriter();
		String message = request.getParameter("message");
		String port = request.getParameter("port");
		port = port.trim();
		if (!message.startsWith("hello"))
			System.out.println(message);
		if (message.startsWith("register")) {// ��½��Ϣ
			System.out.println("register is start");
			denglu(request);
			return;
		}
		if (!port.equals("-1"))
			player = (Player) players.get(request.getRemoteAddr() + ":" + port);
		else
			player = (Player) players.get(request.getRemoteAddr() + ":"
					+ request.getRemotePort());
		if(player==null)return;
		if (message.startsWith("take")) {// ������Ϣ
			// System.out.println("take info start:1");
			luozuo(message, player);
			return;
		} else if (message.startsWith("start")) {// ��ʼ��Ϣmove:
			tryStart(player);
			return;
		} else if (message.startsWith("move")) {// ������Ϣ
			Desk desk = player.getDesk();
			desk.moveChess(message);
		} else if (message.startsWith("hello")) {
			if (!player.data.empty())
				out.println((String) player.data.pop());
			else
				out.println("noData");
			return;
		} else if (message.startsWith("exitgame")) {// �˳���Ϣ
			tryExit(players, player, request);
			return;
		} else if (message.startsWith("exit")) {// �˳���Ϣ
			tryExit(players, player, request);
			return;
		} else {
			System.out.println("No desk exist");
		}
	}

	public void sendMessage(Player p, String str) { // ������Ϣ���ȴ��û���ȡ
		p.data.push(new String(str));
	}

	public void updateClientsDesk(int deskid) { // �������пͻ�����
		for (Enumeration en = players.elements(); en.hasMoreElements();) {
			Player player = (Player) en.nextElement();
			if (player != null)
				updateDesk(player, deskid);
		}
	}

	public void updateDesk(Player isa, int deskid) { // ���µ�������
		String message = "updatedesk," + deskid;
		String str = "";
		for (int i = 0; i < desks[deskid].getPlayersCounter(); i++) {
			if (i == 0) {
				if (desks[deskid].isEmpty(i))
					str = "0";
				else
					str = "1";
			} else {
				if (desks[deskid].isEmpty(i))
					str = str + ",0";
				else
					str = str + ",1";
			}
		}
		message = message + ":" + str;
		sendMessage(isa, message);
	}

	public void sendDeskList(Player player) { // ��������б�
		String message = "desks," + DESKNUM;
		for (int i = 0; i < DESKNUM; i++) {
			String str = "";
			for (int j = 0; j < desks[i].getPlayersCounter(); j++) {
				if (j == 0) {
					if (desks[i].isEmpty(j))
						str = "0";
					else
						str = "1";
				} else {
					if (desks[i].isEmpty(j))
						str = str + ",0";
					else
						str = str + ",1";
				}
			}
			message = message + ":" + str;
		}
		sendMessage(player, message);
	}

	public void denglu(HttpServletRequest request) { // �����½��Ϣ
		Player player = new Player(request.getRemoteAddr(), request
				.getRemotePort());
		player.setID(counter);

		counter++;
		players.put(request.getRemoteAddr() + ":" + request.getRemotePort(),
				player);

		sendMessage(player, "port=" + request.getRemotePort());
		sendDeskList(player); // ���ʹ�����Ϣ
	}

	public void luozuo(String message, Player player) {// ����������Ϣ
		try {
			int index1 = message.indexOf(",");
			int index2 = message.indexOf(",", index1 + 1);
			int dindex = Integer
					.parseInt(message.substring(index1 + 1, index2));
			int pindex = Integer.parseInt(message.substring(index2 + 1));
			if (dindex < DESKNUM && dindex >= 0) {
				if (desks[dindex].isEmpty(pindex)) {
					desks[dindex].setPlayer(pindex, player);
					player.setDesk(desks[dindex]);
					sendMessage(player, "takeseat");
					updateClientsDesk(dindex);
				}
			}
		} catch (Exception exc) {
		}
	}

	public void tryStart(Player player) {// ����ʼ��Ϣ
		player.start();
		Desk d1 = player.getDesk();
		if (d1.isReady()) {
			d1.start();
		}
	}

	public void tryExit(Hashtable players, Player player,
			HttpServletRequest request) {
		Desk de = player.getDesk();
		player.init();
		de.removePlayer(player);
		player=null;
		players.remove(request.getRemoteHost() + ":" + request.getRemotePort());
		updateClientsDesk(de.getID());
	}
}