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

	private int DESKNUM = 10; // 定义桌子数量

	private Desk[] desks = new Desk[DESKNUM];

	private int counter = 1; // 玩家计数器

	private Player player;

	PrintWriter out;

	/*
	 * 初始化 对players(Hashtable)、desks(所有桌子)初始化
	 */
	public void init(ServletConfig config) throws ServletException { // 初始化
		super.init(config);
		players.clear();
		for (int i = 0; i < DESKNUM; i++) {
			desks[i] = new Desk();
			desks[i].setID(i);
		}
	}

	/*
	 * 接受信息主方法 接受各种信息，然后调用相应的处理方法
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) // 接受信息
			throws ServletException, IOException {
		out = response.getWriter();
		String message = request.getParameter("message");
		String port = request.getParameter("port");
		port = port.trim();
		if (!message.startsWith("hello"))
			System.out.println(message);
		if (message.startsWith("register")) {// 登陆信息
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
		if (message.startsWith("take")) {// 落座信息
			// System.out.println("take info start:1");
			luozuo(message, player);
			return;
		} else if (message.startsWith("start")) {// 开始信息move:
			tryStart(player);
			return;
		} else if (message.startsWith("move")) {// 走棋信息
			Desk desk = player.getDesk();
			desk.moveChess(message);
		} else if (message.startsWith("hello")) {
			if (!player.data.empty())
				out.println((String) player.data.pop());
			else
				out.println("noData");
			return;
		} else if (message.startsWith("exitgame")) {// 退出信息
			tryExit(players, player, request);
			return;
		} else if (message.startsWith("exit")) {// 退出信息
			tryExit(players, player, request);
			return;
		} else {
			System.out.println("No desk exist");
		}
	}

	public void sendMessage(Player p, String str) { // 发送信息，等待用户读取
		p.data.push(new String(str));
	}

	public void updateClientsDesk(int deskid) { // 更新所有客户桌面
		for (Enumeration en = players.elements(); en.hasMoreElements();) {
			Player player = (Player) en.nextElement();
			if (player != null)
				updateDesk(player, deskid);
		}
	}

	public void updateDesk(Player isa, int deskid) { // 更新单个桌面
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

	public void sendDeskList(Player player) { // 获得桌面列表
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

	public void denglu(HttpServletRequest request) { // 处理登陆信息
		Player player = new Player(request.getRemoteAddr(), request
				.getRemotePort());
		player.setID(counter);

		counter++;
		players.put(request.getRemoteAddr() + ":" + request.getRemotePort(),
				player);

		sendMessage(player, "port=" + request.getRemotePort());
		sendDeskList(player); // 发送大厅信息
	}

	public void luozuo(String message, Player player) {// 处理落座信息
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

	public void tryStart(Player player) {// 处理开始信息
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