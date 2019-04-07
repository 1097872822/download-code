package com.lzw;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class Game extends MIDlet implements CommandListener {
	private Client client;
	public static Display display;
	private GameCanvas canvas;
	private List playerList;
	private int[][] desks;
	private int trySeat, tryDesk;
	private Command okCommand;
	public Game() {
		display = Display.getDisplay(this);
		playerList = new List("桌面列表", Choice.EXCLUSIVE);
		Command exitCommand = new Command("退出", Command.EXIT, 0);
		playerList.addCommand(exitCommand);
		okCommand = new Command("落座", Command.OK, 0);
		playerList.addCommand(okCommand);
		playerList.setCommandListener(this);
		display.setCurrent(playerList);
		client = new Client(this);
	}

	protected void startApp() throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

	protected void destroyApp(boolean p0) throws MIDletStateChangeException {
		client.sendMessage("exit");
		display.setCurrent(null);
	}

	public void commandAction(Command c, Displayable s) {
		if (c.getCommandType() == Command.EXIT) {
			client.sendMessage("exit");
			try {
				destroyApp(false);
				notifyDestroyed();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (c == okCommand) {
			if (playerList.getSelectedIndex() >= 0) {
				try {
					String info = playerList.getString(playerList
							.getSelectedIndex());
					int index1 = info.indexOf("-");
					int d = Integer.parseInt(info.substring(0, index1));
					int index2 = info.indexOf("-", index1 + 1);

					int d1 = Integer.parseInt(info
							.substring(index1 + 1, index2));
					int d2 = Integer.parseInt(info.substring(index2 + 1));
					if (d1 == 0 || d2 == 0) {
						if (d1 == 0)
							trySeat = 0;
						else
							trySeat = 1;

						tryDesk = d;
						if (canvas == null)
							canvas = new GameCanvas(this, client);
						else
							canvas.init();
						client.sendMessage("take," + d + "," + trySeat);
					}
				} catch (Exception exc) {
					System.out.println("Error parseInt");
					exc.printStackTrace();
				}
			}
		}
	}
	public GameCanvas getCanvas() {
		return canvas;
	}
	public void initialize() {
		canvas = null;
	}
	public void takeSeat() {				// 处理落座信息的方法
		if (canvas == null)					// 如果游戏界面对象未初始化
			canvas = new GameCanvas(this, client);	// 初始化游戏界面对象
		else
			canvas.init();					// 调用游戏界面的init()方法
		canvas.setSeatPos(trySeat);			// 设置玩家座位
		canvas.setDeskIndex(tryDesk);		// 设置玩家卓号
		display.setCurrent(canvas);			// 显示游戏界面
	}
	public void updateDesk(String str) {	// 更新桌面
		int index1 = str.indexOf(",");
		int index2 = str.indexOf(":", index1 + 1);
		int index3 = str.indexOf(",", index2 + 1);
		try {
			int d = Integer.parseInt(str.substring(index1 + 1, index2));
			desks[d][0] = Integer.parseInt(str.substring(index2 + 1, index3));
			desks[d][1] = Integer.parseInt(str.substring(index3 + 1));

			playerList.set(d, d + "-" + desks[d][0] + "-" + desks[d][1], null);
		} catch (Exception exc) {
		}
	}
	public List getPlayerList() {
		return playerList;
	}
	public void setDesks(String string) {
		for (int i = 0; i < playerList.size(); i++)
			playerList.delete(i);
		int index1, index2, index3, index4, index0;
		index1 = string.indexOf(",");
		index2 = string.indexOf(":", index1 + 1);
		int desknum = Integer.parseInt(string.substring(index1 + 1, index2));
		desks = new int[desknum][4];

		index0 = index2;
		int counter = 0;
		while (counter < desknum) {
			index1 = string.indexOf(",", index0 + 1);
			index4 = string.indexOf(":", index1 + 1);

			desks[counter][0] = Integer.parseInt(string.substring(index0 + 1,
					index1));
			if (index4 > 0)
				desks[counter][1] = Integer.parseInt(string.substring(
						index1 + 1, index4));
			else {
				string = string.trim();
				desks[counter][1] = Integer.parseInt(string
						.substring(index1 + 1));
			}
			playerList.append(counter + "-" + desks[counter][0] + "-"
					+ desks[counter][1], null);
			index0 = index4;
			counter++;
		}
	}
}
