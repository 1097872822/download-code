package com.lzw;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class GameCanvas extends Canvas implements CommandListener {
	protected Game game;

	String color = "";

	protected int rightSpace;// 屏幕右侧留的空间

	protected int x;// 棋盘输出的坐标

	private boolean myTurn = false;

	protected int gridWidth;// 每个棋格的边长

	protected int mapWidth, canvasW;// 棋盘的宽度和画布的宽度

	protected int a, b, c, d;

	protected int chessR;// 棋子的半径

	private int desknum = -1; // 桌子序号

	private int seatPos = -1; // 座位序号

	private boolean banker = false;

	protected int selectedX, selectedY;// 选择框在棋盘格局上的x,y位置

	protected static int i, j;

	protected int m, n, p;// 记住开始的selectedX,selectedY和point[selectedX][selectedY]

	protected String q;// 记住word[selectedX][selectedY]

	protected int guard, guard1, guard2, g, g1;// 标记FIRE被按了多少次,g是用来判断走直线时前后的棋子,中间是否有其他棋子的累加器

	protected static int turnWho;// 表示该谁走了

	protected static int isRedWin; // 红棋胜利

	protected static int isWhiteWin; // 白棋胜利

	private Client client;

	protected Command exitCmd, start, ok;

	private int point[][]; // 棋子位置数组

	protected String[][] chess; // 棋子名称数组

	private int chessSelColor; // 选择棋子的颜色

	private int backColor; // 棋盘背景色

	private int charColor; // 棋子汉字颜色

	private int lineColor; // 棋盘线的颜色

	private int borderColor;// 楚河汉界的颜色

	private int selBorderColor;// 选择棋子的边框色

	private int blackChees; // 黑棋棋颜色

	private int redChess;// 红棋颜色

	public GameCanvas() {
	};

	public void init() {
		// desknum = -1;
		// seatPos = -1;
		addCommand(start);
		addCommand(exitCmd);
		initChess();
		banker = false;
		myTurn = false;
	}

	public void reset() {
		initChess();
		addCommand(start);
		addCommand(exitCmd);
		banker = false;
		myTurn = false;
	}

	public GameCanvas(Game game, Client client) {// 构造函数
		this.game = game;
		this.client = client;
		chessSelColor = 0x188312;// 初始化选择棋子的颜色
		backColor = 0xEECD05; // 初始化背景色
		charColor = 0xFFFFFF; // 初始化汉字颜色
		lineColor = 0x5A5743; // 初始化棋盘线的颜色
		borderColor = 0x5A5743; // 初始化楚河汉界的颜色
		selBorderColor = 0x50FAFC; // 初始化选择棋子的边框色
		blackChees = 0x000000; // 绿棋颜色
		redChess = 0xBF0404;// 红棋颜色
		rightSpace = getWidth() / 6;
		x = rightSpace * 1 / 3;
		canvasW = getWidth() - rightSpace;
		mapWidth = canvasW - canvasW % 8;
		gridWidth = mapWidth / 8;
		a = gridWidth * 2 / 5;
		b = gridWidth / 8;
		c = gridWidth - a;
		d = gridWidth - b;
		chessR = gridWidth * 2 / 5;
		selectedX = 4;
		selectedY = 7;
		guard = 0;
		guard1 = selectedX;
		guard2 = selectedY;
		m = guard1;
		n = guard2;
		chess = new String[10][9];
		turnWho = 1;
		initChess();
		exitCmd = new Command("退出", Command.EXIT, 0);
		start = new Command("开始", Command.OK, 1);
		addCommand(start);
		addCommand(exitCmd);
		setCommandListener(this);
	}

	public void initChess() {
		point = new int[][] {
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 },// 初始化INT数组
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 10, 0, 0, 0, 0, 0, 11, 0 },
				{ 12, 0, 13, 0, 14, 0, 15, 0, 16 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 28, 0, 29, 0, 30, 0, 31, 0, 32 },
				{ 0, 26, 0, 0, 0, 0, 0, 27, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 17, 18, 19, 20, 21, 22, 23, 24, 25 } };

		chess = new String[][] {
				{ "车", "马", "象", "士", "将", "士", "象", "马", "车", },
				{ "空", "空", "空", "空", "空", "空", "空", "空", "空", },
				{ "空", "炮", "空", "空", "空", "空", "空", "炮", "空", },
				{ "卒", "空", "卒", "空", "卒", "空", "卒", "空", "卒", },
				{ "空", "空", "空", "空", "空", "空", "空", "空", "空", },
				{ "空", "空", "空", "空", "空", "空", "空", "空", "空", },
				{ "卒", "空", "卒", "空", "卒", "空", "卒", "空", "卒", },
				{ "空", "炮", "空", "空", "空", "空", "空", "炮", "空", },
				{ "空", "空", "空", "空", "空", "空", "空", "空", "空", },
				{ "车", "马", "象", "士", "将", "士", "象", "马", "车", }, };
	}

	public int getDeskIndex() {
		return desknum;
	}

	public void setDeskIndex(int i) {
		desknum = i;
	}

	public void setSeatPos(int i) {
		seatPos = i;
	}

	public int getSeatPos() {
		return seatPos;
	}

	protected void paintChessboardUp(Graphics g) { // 画上半部棋盘
		for (int k = 0; k < 4; k++) {
			for (int l = 0; l < 8; l++) {
				g.setColor(lineColor);
				g.drawRect(x + l * gridWidth, x + k * gridWidth, gridWidth,
						gridWidth);
			}
		}
		g.setColor(lineColor);						// 设置画棋盘线的颜色
		g.drawLine(x + 3 * gridWidth, x, x + 5 * gridWidth, x + 2 * gridWidth);
		g.drawLine(x + 5 * gridWidth, x, x + 3 * gridWidth, x + 2 * gridWidth);
		// 画左上方的炮
		{
			g.drawLine(x + d, x + gridWidth + c, x + d, x + gridWidth + d);// 左上竖
			g.drawLine(x + c, x + gridWidth + d, x + d, x + gridWidth + d);// 左上横
			g.drawLine(x + d + 2 * b, x + gridWidth + c, x + d + 2 * b, x
					+ gridWidth + d);// 右上竖
			g.drawLine(x + gridWidth + b, x + gridWidth + d, x + gridWidth + a,
					x + gridWidth + d);// 右上横
			g.drawLine(x + d, x + 2 * gridWidth + b, x + d, x + 2 * gridWidth
					+ a);// 左下竖
			g.drawLine(x + c, x + gridWidth + d + 2 * b, x + d, x + gridWidth
					+ d + 2 * b);// 左下横
			g.drawLine(x + d + 2 * b, x + 2 * gridWidth + b, x + d + 2 * b, x
					+ 2 * gridWidth + a);// 右下竖
			g.drawLine(x + gridWidth + b, x + gridWidth + d + 2 * b, x
					+ gridWidth + a, x + gridWidth + d + 2 * b);// 右下横
		}
		// 画右上方的炮
		{
			g.drawLine(x + d + 6 * gridWidth, x + gridWidth + c, x + d + 6
					* gridWidth, x + gridWidth + d);
			g.drawLine(x + c + 6 * gridWidth, x + gridWidth + d, x + d + 6
					* gridWidth, x + gridWidth + d);
			g.drawLine(x + d + 2 * b + 6 * gridWidth, x + gridWidth + c, x + d
					+ 2 * b + 6 * gridWidth, x + gridWidth + 13 + 9);
			g.drawLine(x + gridWidth + b + 6 * gridWidth, x + gridWidth + d, x
					+ gridWidth + a + 6 * gridWidth, x + gridWidth + d);
			g.drawLine(x + d + 6 * gridWidth, x + 2 * gridWidth + b, x + d + 6
					* gridWidth, x + 2 * gridWidth + a);
			g.drawLine(x + c + 6 * gridWidth, x + gridWidth + d + 2 * b, x + d
					+ 6 * gridWidth, x + gridWidth + d + 2 * b);
			g.drawLine(x + d + 2 * b + 6 * gridWidth, x + 2 * gridWidth + b, x
					+ d + 2 * b + 6 * gridWidth, x + 2 * gridWidth + a);
			g.drawLine(x + gridWidth + b + 6 * gridWidth, x + gridWidth + d + 2
					* b, x + gridWidth + a + 6 * gridWidth, x + gridWidth + d
					+ 2 * b);
		}
	}

	protected void paintRiver(Graphics g) {// 画河
		g.setColor(lineColor);
		g.drawRect(x, x + 4 * gridWidth, mapWidth, gridWidth);
		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		g.setColor(borderColor);
		g.drawString("楚河       汉界", getWidth() / 2, x + 4 * gridWidth
				+ gridWidth * 3 / 4, Graphics.HCENTER | Graphics.BASELINE);
	}

	protected void paintChessboardDown(Graphics g) {// 画下半部棋盘
		for (int q = 0; q < 4; q++) {
			for (int w = 0; w < 8; w++) {
				g.setColor(lineColor);
				g.drawRect(x + w * gridWidth, x + (q + 5) * gridWidth,
						gridWidth, gridWidth);
			}
		}
		g.setColor(lineColor);
		g.drawLine(x + 3 * gridWidth, x + 7 * gridWidth, x + 5 * gridWidth, x
				+ 9 * gridWidth);
		g.drawLine(x + 5 * gridWidth, x + 7 * gridWidth, x + 3 * gridWidth, x
				+ 9 * gridWidth);
		// 画左上方的炮
		{
			g.drawLine(x + d, x + 6 * gridWidth + c, x + d, x + 6 * gridWidth
					+ d);// 左上竖
			g.drawLine(x + c, x + 6 * gridWidth + d, x + d, x + 6 * gridWidth
					+ d);// 左上横

			g.drawLine(x + d + 2 * b, x + 6 * gridWidth + c, x + d + 2 * b, x
					+ 6 * gridWidth + d);// 右上竖
			g.drawLine(x + gridWidth + b, x + 6 * gridWidth + d, x + gridWidth
					+ a, x + 6 * gridWidth + d);// 右上横

			g.drawLine(x + d, x + 7 * gridWidth + b, x + d, x + 7 * gridWidth
					+ a);// 左下竖
			g.drawLine(x + c, x + 6 * gridWidth + d + 2 * b, x + d, x + 6
					* gridWidth + d + 2 * b);// 左下横

			g.drawLine(x + d + 2 * b, x + 7 * gridWidth + b, x + d + 2 * b, x
					+ 7 * gridWidth + a);// 右下竖
			g.drawLine(x + gridWidth + b, x + 6 * gridWidth + d + 2 * b, x
					+ gridWidth + a, x + 6 * gridWidth + d + 2 * b);// 右下横
		}
		// 画右上方的炮
		{
			g.drawLine(x + d + 6 * gridWidth, x + 6 * gridWidth + c, x + d + 6
					* gridWidth, x + 6 * gridWidth + d);
			g.drawLine(x + c + 6 * gridWidth, x + 6 * gridWidth + d, x + d + 6
					* gridWidth, x + 6 * gridWidth + d);

			g.drawLine(x + d + 2 * b + 6 * gridWidth, x + 6 * gridWidth + c, x
					+ d + 2 * b + 6 * gridWidth, x + 6 * gridWidth + d);
			g.drawLine(x + gridWidth + b + 6 * gridWidth,
					x + 6 * gridWidth + d, x + gridWidth + a + 6 * gridWidth, x
							+ 6 * gridWidth + d);

			g.drawLine(x + d + 6 * gridWidth, x + 7 * gridWidth + b, x + d + 6
					* gridWidth, x + 7 * gridWidth + a);
			g.drawLine(x + c + 6 * gridWidth, x + 6 * gridWidth + d + 2 * b, x
					+ d + 6 * gridWidth, x + 6 * gridWidth + d + 2 * b);

			g.drawLine(x + d + 2 * b + 6 * gridWidth, x + 7 * gridWidth + b, x
					+ d + 2 * b + 6 * gridWidth, x + 7 * gridWidth + a);
			g.drawLine(x + gridWidth + b + 6 * gridWidth, x + 6 * gridWidth + d
					+ 2 * b, x + gridWidth + a + 6 * gridWidth, x + 6
					* gridWidth + d + 2 * b);
		}
	}

	protected void paintAllChess(Graphics g) {// 画出所有棋子
		if (point == null)
			return;
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 9; j++) {
				if (point[i][j] != 0) {
					if (point[i][j] < 17) {
						g.setColor(redChess);
					} else {
						g.setColor(blackChees);
					}
					g.fillArc(x - chessR + j * gridWidth, x - chessR + i
							* gridWidth, 2 * chessR, 2 * chessR, 0, 360);
					g.setColor(charColor);
					g.setFont(Font.getFont(Font.FACE_PROPORTIONAL,
							Font.STYLE_BOLD, Font.SIZE_LARGE));
					g.drawString(chess[i][j], x + j * gridWidth, x + chessR + i
							* gridWidth, Graphics.HCENTER | Graphics.BOTTOM);
				}
			}
		}
	}

	protected void paintSelectedChess(Graphics g) {// 画选择的棋子
		m = guard1;
		n = guard2; // 再重新单独输出一个棋子
		g.setColor(chessSelColor);
		g.fillArc(x - chessR + guard1 * gridWidth, x - chessR + guard2
				* gridWidth, 2 * chessR, 2 * chessR, 0, 360);
		g.setColor(charColor);
		g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		g.drawString(chess[guard2][guard1], x + guard1 * gridWidth, x + chessR
				+ guard2 * gridWidth, Graphics.HCENTER | Graphics.BOTTOM);
	}

	protected void whoTurn(Graphics g) {// 判断该谁走了
		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		if (!myTurn) {
			g.setColor(0xFE0000);
			g.drawString("轮到对家走棋", x, x + chessR + 10 * gridWidth,
					Graphics.LEFT | Graphics.BOTTOM);

		} else {
			g.setColor(0X9F3202);
			g.drawString("轮到自己走棋", x, x + chessR + 10 * gridWidth,
					Graphics.LEFT | Graphics.BOTTOM);
		}
	}

	protected void checkWin(String str) {// 判断输赢
		int index = str.indexOf(",");
		String str2 = str.substring(index + 1);
		try {
			if (str2.equals("you")) {
				Alert alert;
				alert = new Alert("恭喜!", null, Image.createImage(this
						.getClass().getResourceAsStream("win.gif")),
						AlertType.INFO);
				ok = new Command("OK", Command.OK, 1);
				alert.setTimeout(Alert.FOREVER);
				alert.addCommand(ok);
				alert.setCommandListener(this);
				Game.display.setCurrent(alert);
			} else {
				Alert alert = new Alert("遗憾!", null, Image.createImage(this
						.getClass().getResourceAsStream("false.gif")),
						AlertType.INFO);
				ok = new Command("OK", Command.OK, 1);
				alert.setTimeout(Alert.FOREVER);
				alert.addCommand(ok);
				alert.setCommandListener(this);
				Game.display.setCurrent(alert);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void paintSelected(Graphics g) {// 画选择框
		g.setColor(selBorderColor);
		g.drawRect(x - chessR + selectedX * gridWidth, x - chessR + selectedY
				* gridWidth, 2 * chessR, 2 * chessR);

	}

	protected void paint(Graphics g) {
		g.setColor(backColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		paintChessboardUp(g); // 调用画上半部棋盘的方法
		paintRiver(g); // 调用画河的方法
		paintChessboardDown(g); // 调用画下半部棋盘的方法
		paintAllChess(g); // 调用画棋子的方法
		if (guard % 2 == 1) {
			paintSelectedChess(g); // 调用画选择棋子的方法
		}
		paintSelected(g);
		whoTurn(g);
	}

	protected void changTwoChessNum(int m, int n, int selectedX, int selectedY,
			boolean send) {// 改变两个格子的值
		if (send) {
			if ((color.equals("red") && point[n][m] < 17)
					|| (color.equals("white") && point[n][m] >= 17)) {
				if (!banker) {
					client.sendMessage("move;" + seatPos + ":" + selectedY
							+ "," + selectedX + "," + n + "," + m);
				} else {
					client.sendMessage("move;" + seatPos + ":"
							+ (9 - selectedY) + "," + (8 - selectedX) + ","
							+ (9 - n) + "," + (8 - m));
				}

				myTurn = false;
				p = point[selectedY][selectedX];
				point[selectedY][selectedX] = point[n][m];

				point[n][m] = 0;
				q = chess[selectedY][selectedX];
				chess[selectedY][selectedX] = chess[n][m];
				chess[n][m] = "空";
			}
		} else {
			myTurn = false;
			p = point[selectedY][selectedX];
			point[selectedY][selectedX] = point[n][m];

			point[n][m] = 0;
			q = chess[selectedY][selectedX];
			chess[selectedY][selectedX] = chess[n][m];
			chess[n][m] = "空";
		}
	}

	private void theRuleOfChe(int m, int n, int sx, int sy) {// 车的规则
		g = 0;
		if (m == sx) {
			if (n > sy) {
				for (i = 1; i < n - sy; i++) {
					if (point[sy + i][m] != 0) {
						g++;
					}
				}
			} else {
				for (i = 1; i < sy - n; i++) {
					if (point[n + i][m] != 0) {
						g++;
					}
				}
			}
			if (g == 0) {
				changTwoChessNum(m, n, sx, sy, true);
			}

		}
		if (n == sy) {
			if (m > sx) {
				for (i = 1; i < m - sx; i++) {
					if (point[n][i + sx] != 0) {
						g++;
					}
				}
			} else {
				for (i = 1; i < sx - m; i++) {
					if (point[n][m + i] != 0) {
						g++;
					}
				}
			}
			if (g == 0) {
				changTwoChessNum(m, n, sx, sy, true);
			}

		}
	}

	private void theRuleOfMa(int m, int n, int sx, int sy) {// 马的规则
		if (n < 9) {
			if (point[n + 1][m] == 0) {
				if (sx - m == 1) {
					if (sy - n == 2) {
						changTwoChessNum(m, n, sx, sy, true);
					}
				}
			}
		}
		if (n > 0) {
			if (point[n - 1][m] == 0) {
				if (m - sx == 1) {
					if (n - sy == 2) {
						changTwoChessNum(m, n, sx, sy, true);
					}
				}
			}
		}
		if (n < 9) {
			if (point[n + 1][m] == 0) {
				if (sx - m == -1) {
					if (sy - n == 2) {
						changTwoChessNum(m, n, sx, sy, true);
					}
				}
			}
		}
		if (n > 0) {
			if (point[n - 1][m] == 0) {
				if (m - sx == -1) {
					if (n - sy == 2) {
						changTwoChessNum(m, n, sx, sy, true);
					}
				}
			}
		}
		if (m < 8) {
			if (point[n][m + 1] == 0) {
				if (sx - m == 2) {
					if (sy - n == 1) {
						changTwoChessNum(m, n, sx, sy, true);
					}
				}
			}
		}
		if (m > 0) {
			if (point[n][m - 1] == 0) {
				if (m - sx == 2) {
					if (n - sy == 1) {
						changTwoChessNum(m, n, sx, sy, true);
					}
				}
			}
		}
		if (m < 8) {
			if (point[n][m + 1] == 0) {
				if (sx - m == 2) {
					if (sy - n == -1) {
						changTwoChessNum(m, n, sx, sy, true);
					}
				}
			}
		}
		if (m > 0) {
			if (point[n][m - 1] == 0) {
				if (m - sx == 2) {
					if (n - sy == -1) {
						changTwoChessNum(m, n, sx, sy, true);
					}
				}
			}
		}
	}

	private void theRuleOfPao(int m, int n, int sx, int sy, int g1) {// 炮的规则
		g = 0;
		if (m == sx) {
			if (n > sy) {
				for (i = 1; i < n - sy; i++) {
					if (point[sy + i][m] != 0) {
						g++;
					}
				}
			} else {
				for (i = 1; i < sy - n; i++) {
					if (point[n + i][m] != 0) {
						g++;
					}
				}
			}
			if (g == g1) {
				changTwoChessNum(m, n, sx, sy, true);
			}

		}
		if (n == sy) {
			if (m > sx) {
				for (i = 1; i < m - sx; i++) {
					if (point[n][i + sx] != 0) {
						g++;
					}
				}
			} else {
				for (i = 1; i < sx - m; i++) {
					if (point[n][m + i] != 0) {
						g++;
					}
				}
			}
			if (g == g1) {
				changTwoChessNum(m, n, sx, sy, true);
			}

		}
	}

	private void theRuleOfXiang(int m, int n, int sx, int sy) {// 相的规则
		if (color.equals("red"))
			if (sy < 5)
				return;
			else if (color.equals("while"))
				if (sy > 4)
					return;
		if (n < 9 & m < 8) {
			if (point[n + 1][m + 1] == 0) {
				if ((sx - m == 2) & (sy - n == 2)) {
					changTwoChessNum(m, n, sx, sy, true);
				}
			}
		}
		if (n > 0 & m < 8) {
			if (point[n - 1][m + 1] == 0) {
				if ((sx - m == 2) & (sy - n == -2)) {
					changTwoChessNum(m, n, sx, sy, true);
				}
			}
		}
		if (n < 9 & m > 0) {
			if (point[n + 1][m - 1] == 0) {
				if ((sx - m == -2) & (sy - n == 2)) {
					changTwoChessNum(m, n, sx, sy, true);
				}
			}
		}
		if (n > 0 & m > 0) {
			if (point[n - 1][m - 1] == 0) {
				if ((sx - m == -2) & (sy - n == -2)) {
					changTwoChessNum(m, n, sx, sy, true);
				}
			}
		}
	}

	private void theRuleOfShi(int m, int n, int sx, int sy) {// 士的规则
		if ((m > 2 & m < 6) & (sx > 2 & sx < 6) & (n >= 7 & n <= 9)
				& (sy >= 7 & sy <= 9)) {

			if ((sx - m == 1) & (sy - n == 1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}

			if ((sx - m == 1) & (sy - n == -1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == -1) & (sy - n == 1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == -1) & (sy - n == -1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
		}
		if ((m > 2 & m < 6) & (sx > 2 & sx < 6) & (n >= 0 & n < 3)
				& (sy >= 0 & sy < 3)) {
			if ((sx - m == 1) & (sy - n == 1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == 1) & (sy - n == -1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == -1) & (sy - n == 1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == -1) & (sy - n == -1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
		}
	}

	private void theRuleOfShuai(int m, int n, int sx, int sy) {// 帅的规则
		if ((m > 2 & m < 6) & (sx > 2 & sx < 6) & (n >= 7 & n <= 9)
				& (sy >= 7 & sy <= 9)) {
			if ((sx - m == 1) & (sy - n == 0)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == -1) & (sy - n == 0)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == 0) & (sy - n == 1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == 0) & (sy - n == -1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
		}
		if ((m > 2 & m < 6) & (sx > 2 & sx < 6) & (n >= 0 & n < 3)
				& (sy >= 0 & sy < 3)) {
			if ((sx - m == 1) & (sy - n == 0)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == -1) & (sy - n == 0)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == 0) & (sy - n == 1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
			if ((sx - m == 0) & (sy - n == -1)) {
				changTwoChessNum(m, n, sx, sy, true);
			}
		}
	}

	private void theRuleOfZu(int m, int n, int sx, int sy) {// 卒的规则
		if (sy <= n) {
			if (n > 4) {
				if ((sy - n == -1) & (sx - m == 0)) {
					changTwoChessNum(m, n, sx, sy, true);
				}
			} else {
				if ((sy - n == -1) & (sx - m == 0)) {
					changTwoChessNum(m, n, sx, sy, true);
				}
				if ((sy - n == 0) & (sx - m == 1)) {
					changTwoChessNum(m, n, sx, sy, true);
				}
				if ((sy - n == 0) & (sx - m == -1)) {
					changTwoChessNum(m, n, sx, sy, true);
				}
			}
		}
	}

	public void commandAction(Command c, Displayable d) { // 软键按钮事件处理
		if (c == exitCmd) {
			(new Thread() {
				public void run() {
					client.sendMessage("exitgame");
				}
			}).start();
			seatPos = -1;
			desknum = -1;
			Game.display.setCurrent(game.getPlayerList());
		} else if (c == ok) {
			reset();
			Game.display.setCurrent(this);

		} else if (c == start) {
			banker = false;
			client.sendMessage("start");
			this.removeCommand(start);
		}
	}

	protected void keyPressed(int keyCode) { // 处理按键
		int action = getGameAction(keyCode);
		if (myTurn) {
			if (action == Canvas.LEFT) {
				selectedX = (--selectedX + 8 + 1) % (8 + 1);
			} else if (action == Canvas.RIGHT) {
				selectedX = (++selectedX) % (8 + 1);
			} else if (action == Canvas.UP) {
				selectedY = (--selectedY + 9 + 1) % (9 + 1);
			} else if (action == Canvas.DOWN) {
				selectedY = (++selectedY) % (9 + 1);
			} else if (action == Canvas.FIRE)// 这里的FIRE键我分成了两种情况：一是选种棋子，
			{ // 二是当选择了棋子后，让棋子走到下面选择的位置

				guard = guard + 1;// 每按下FIRE一次，GUARD就加一，用来判断FIRE是被选种还是选种后走下不棋
				if (guard % 2 == 1) // 这时是当选种某一个棋子时，调用choosChess函数，选择棋子
				{
					if (point[selectedY][selectedX] != 0) {
						guard1 = selectedX;
						guard2 = selectedY;
					}
				}

				if (guard % 2 == 0)// 这种情况是当棋子被选种后
				{
					if (point[selectedY][selectedX] != point[n][m])// 当走的下一步不是自身，也就是玩家选过
					{ // 一个棋子，又不想选了，这只需什么都不做
						if ((point[n][m] == 1) | (point[n][m] == 9)
								| (point[n][m] == 17) | (point[n][m] == 25))// 当选定的棋子是车的时候
						{ // repaint就OK了
							if (point[selectedY][selectedX] == 0)// 当下一步走的是空格，则改变选种的格子和下一步所
							{ // 走的格子的point[][]和word[][]的植，然后repaint就OK
								theRuleOfChe(m, n, selectedX, selectedY);
							} else// 当下一步是想吃对方的子的，则把下一步格子的值变为刚才选定的格子的值，而
							{ // 刚才选定的格子的值则便为零
								System.out
										.println("point[selectedY][selectedX] / 17)="
												+ (point[selectedY][selectedX] / 17)
												+ "(point[n][m] / 17)="
												+ (point[n][m] / 17));
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// 当然，想吃的子不能是自己的
								{
									theRuleOfChe(m, n, selectedX, selectedY);

								}
							}
						}
						if ((point[n][m] == 2) | (point[n][m] == 8)
								| (point[n][m] == 18) | (point[n][m] == 24))// 当选定的棋子是马的时候
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfMa(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// 当然，想吃的子不能是自己的
								{
									theRuleOfMa(m, n, selectedX, selectedY);

								}
							}
						}
						if ((point[n][m] == 10) | (point[n][m] == 11)
								| (point[n][m] == 26) | (point[n][m] == 27))// 当选定的棋子是炮的时候
						{
							if (point[selectedY][selectedX] == 0) {
								g1 = 0;
								theRuleOfPao(m, n, selectedX, selectedY, g1);
							} else {
								g1 = 1;
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// 当然，想吃的子不能是自己的
								{
									theRuleOfPao(m, n, selectedX, selectedY, g1);

								}
							}
						}
						if ((point[n][m] == 3) | (point[n][m] == 7)
								| (point[n][m] == 19) | (point[n][m] == 23))// 当选定的棋子是相的时候
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfXiang(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// 当然，想吃的子不能是自己的
								{
									theRuleOfXiang(m, n, selectedX, selectedY);

								}
							}
						}
						if ((point[n][m] == 4) | (point[n][m] == 6)
								| (point[n][m] == 20) | (point[n][m] == 22))// 当选定的棋子是士的时候
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfShi(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// 当然，想吃的子不能是自己的
								{
									theRuleOfShi(m, n, selectedX, selectedY);

								}
							}
						}
						if ((point[n][m] == 5) | (point[n][m] == 21))// 当选定的棋子是帅的时候
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfShuai(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// 当然，想吃的子不能是自己的
								{
									theRuleOfShuai(m, n, selectedX, selectedY);

								}
							}
						}
						if ((point[n][m] > 11 & point[n][m] < 17))// 当选定的棋子是红方卒的时候
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfZu(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// 当然，想吃的子不能是自己的
								{
									theRuleOfZu(m, n, selectedX, selectedY);

								}
							}
						}
						if (point[n][m] > 27)// 当选定的棋子是白方卒的时候
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfZu(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// 当然，想吃的子不能是自己的
								{
									theRuleOfZu(m, n, selectedX, selectedY);

								}
							}
						}

					}
				}
			}
		}
		repaint();
	}

	public void receiveMessage(String str) { // TODO Auto-generated method
		// stub
		if (str.startsWith("move")) { // 别人出牌信息
			int index0 = str.indexOf(";");
			int index1 = str.indexOf(":");
			int index2 = str.indexOf(",", index1 + 1);
			int index3 = str.indexOf(",", index2 + 1);
			int index4 = str.indexOf(",", index3 + 1);

			int seat = Integer.parseInt(str.substring(index0 + 1, index1));
			int selectedY = Integer.parseInt(str.substring(index1 + 1, index2));
			int selectedX = Integer.parseInt(str.substring(index2 + 1, index3));
			int n = Integer.parseInt(str.substring(index3 + 1, index4));
			int m = Integer.parseInt(str.substring(index4 + 1));
			if (seat != seatPos) {
				if (banker) {
					changTwoChessNum(8 - m, 9 - n, 8 - selectedX,
							9 - selectedY, false);
					myTurn = true;
				} else {
					changTwoChessNum(m, n, selectedX, selectedY, false);
					myTurn = true;
				}
				repaint();
			}
		} else if (str.startsWith("color")) {
			int index = str.indexOf(":");
			color = str.substring(index + 1);
			if (color.equals("white")) {
				point = new int[][] {
						{ 1, 2, 3, 4, 5, 6, 7, 8, 9 },// 初始化INT数组
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 10, 0, 0, 0, 0, 0, 11, 0 },
						{ 12, 0, 13, 0, 14, 0, 15, 0, 16 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 28, 0, 29, 0, 30, 0, 31, 0, 32 },
						{ 0, 26, 0, 0, 0, 0, 0, 27, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 17, 18, 19, 20, 21, 22, 23, 24, 25 } };

			} else if (color.equals("red")) {
				point = new int[][] {
						{ 17, 18, 19, 20, 21, 22, 23, 24, 25 },// 初始化INT数组
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 26, 0, 0, 0, 0, 0, 27, 0 },
						{ 28, 0, 29, 0, 30, 0, 31, 0, 32 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 12, 0, 13, 0, 14, 0, 15, 0, 16 },
						{ 0, 10, 0, 0, 0, 0, 0, 11, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 1, 2, 3, 4, 5, 6, 7, 8, 9 } };
			}
			repaint();
		} else if (str.startsWith("turn")) {
			myTurn = true;
			banker = true;
			repaint();
		} else if (str.startsWith("win")) {
			checkWin(str);
		} else if (str.startsWith("exitgame")) {
			game.initialize();
			Game.display.setCurrent(game.getPlayerList());
		}
	}
}
