package com.lzw;
public class Player {
	private int ID = 1;
	private String IP = "";
	private int PORT = 9999;
	private Desk desk;
	public Queue data;
	private String Color = "";
	public boolean start = false;
	public Player(String ip, int p){
		IP = ip;
		PORT = p;
		desk = null;
		data = new Queue();
	}
	public void setDesk(Desk d) {
		desk = d;
	}
	public Desk getDesk() {
		return desk;
	}
	public boolean equals(String ip) {
		if (IP.equals(ip))
			return true;
		else
			return false;
	}
	public boolean equals(Player p) {
		if (IP.equals(p.getIP()) && PORT == p.getPort())
			return true;
		else
			return false;
	}
	public String getIP() {
		return IP;
	}
	public int getPort() {
		return PORT;
	}
	public void setID(int id) {
		ID = id;
	}
	public int getID() {
		return ID;
	}
	public boolean isStart() {
		return start;
	}
	public void init() {
		start = false;
		data.clear();
		IP="";
		PORT=0;
	}
	public void reset()	{
		Color = "";
		start = false;
	}

	public void start() {
		start = true;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
}