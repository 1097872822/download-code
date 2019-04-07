import java.util.Vector;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class GameCanvas extends Canvas implements CommandListener {
	protected Game game;

	String color = "";

	protected int empty;// 屏幕右侧留的空间

	protected int x;// 棋盘输出的坐标

	private boolean myTurn = false;

	protected int cellWidth;// 每个棋格的边长

	protected int mapWidth, canvasW;// 棋盘的宽度和画布的宽度

	protected int a, b, c, d;// 这是画炮下面的那几个折线，没什么用

	protected int chessR;// 棋子的半径

	private int desknum = -1; // 桌子序号

	private int seatPos = -1; // 座位序号

	private boolean banker = false;

	protected int selectedX, selectedY;// 选择框在棋盘格局上的x,y位置

	protected static int i, j;

	protected int m, n, p;// 记住开始的selectedX,selectedY和point[selectedX][selectedY]

	protected String q;// 记住word[selectedX][selectedY]

	protected int guard, guard1, guard2, g, g1;// 标记FIRE被按了多少次,g是用来判断走直线时前后的棋子,中间是否有其他棋子的累加器

	protected static int g2, isRedWin, isWhiteWin;// g2表示该谁走了，后面那俩顾名思义了

	private Client client;

	protected Command exitCmd, start, ok;

	private int point[][];

	protected String[][] word;

	public GameCanvas() {
	};

	public void init() {
		// desknum = -1;
		// seatPos = -1;
		addCommand(start);
		addCommand(exitCmd);
		initWord();
		banker = false;
		myTurn = false;
	}

	public void reset() {
		initWord();
		addCommand(start);
		addCommand(exitCmd);
		banker = false;
		myTurn = false;
	}

	public GameCanvas(Game game, Client client)// 构造函数
	{
		this.game = game;
		this.client = client;

		empty = getWidth() / 6;
		x = empty * 1 / 3;
		canvasW = getWidth() - empty;
		mapWidth = canvasW - canvasW % 8;
		cellWidth = mapWidth / 8;
		a = cellWidth * 2 / 5;
		b = cellWidth / 8;
		c = cellWidth - a;
		d = cellWidth - b;
		chessR = cellWidth * 2 / 5;
		selectedX = 4;
		selectedY = 7;
		guard = 0;
		guard1 = selectedX;
		guard2 = selectedY;
		m = guard1;
		n = guard2;
		word = new String[10][9];
		g2 = 1;
		initWord();
		exitCmd = new Command("退出", Command.EXIT, 0);
		start = new Command("开始", Command.OK, 1);
		addCommand(start);
		addCommand(exitCmd);
		setCommandListener(this);
	}

	public void initWord() {
		point = new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },// 初始化INT数组
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 10, 0, 0, 0, 0, 0, 11, 0 },
				{ 12, 0, 13, 0, 14, 0, 15, 0, 16 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 28, 0, 29, 0, 30, 0, 31, 0, 32 },
				{ 0, 26, 0, 0, 0, 0, 0, 27, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 17, 18, 19, 20, 21, 22, 23, 24, 25 } };

		for (i = 0; i < 10; i++)// 初始化字符数组
		{
			for (j = 0; j < 9; j++) {
				if (i == 0) {
					if (j == 0) {
						word[i][j] = "车";
					}
					if (j == 1) {
						word[i][j] = "马";
					}
					if (j == 2) {
						word[i][j] = "相";
					}
					if (j == 3) {
						word[i][j] = "士";
					}
					if (j == 4) {
						word[i][j] = "帅";
					}
					if (j == 8) {
						word[i][j] = "车";
					}
					if (j == 7) {
						word[i][j] = "马";
					}
					if (j == 6) {
						word[i][j] = "相";
					}
					if (j == 5) {
						word[i][j] = "士";
					}
				}
				if (i == 1) {
					word[i][j] = "空";
				}
				if (i == 2) {
					if ((j != 1) & (j != 7)) {
						word[i][j] = "空";
					}
					if (j == 1) {
						word[i][j] = "炮";
					}
					if (j == 7) {
						word[i][j] = "炮";
					}
				}
				if (i == 3) {
					if (j % 2 == 0) {
						word[i][j] = "卒";
					}
					if (j % 2 == 1) {
						word[i][j] = "空";
					}
				}
				if (i == 4) {
					word[i][j] = "空";
				}
				if (i == 5) {
					word[i][j] = "空";
				}
				if (i == 6) {
					if (j % 2 == 0) {
						word[i][j] = "卒";
					}
					if (j % 2 == 1) {
						word[i][j] = "空";
					}
				}
				if (i == 7) {
					if ((j != 1) & (j != 7)) {
						word[i][j] = "空";
					}
					if (j == 1) {
						word[i][j] = "炮";
					}
					if (j == 7) {
						word[i][j] = "炮";
					}
				}
				if (i == 8) {
					word[i][j] = "空";
				}
				if (i == 9) {
					if (j == 0) {
						word[i][j] = "车";
					}
					if (j == 1) {
						word[i][j] = "马";
					}
					if (j == 2) {
						word[i][j] = "相";
					}
					if (j == 3) {
						word[i][j] = "士";
					}
					if (j == 4) {
						word[i][j] = "帅";
					}
					if (j == 8) {
						word[i][j] = "车";
					}
					if (j == 7) {
						word[i][j] = "马";
					}
					if (j == 6) {
						word[i][j] = "相";
					}
					if (j == 5) {
						word[i][j] = "士";
					}
				}

			}
		}
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

	protected void paintMapa(Graphics g)// 画河的上半部分的棋盘
	{
		for (int q = 0; q < 4; q++) {
			for (int w = 0; w < 8; w++) {
				g.setColor(128, 128, 128);
				g.drawRect(x + w * cellWidth, x + q * cellWidth, cellWidth,
						cellWidth);
			}
		}
		g.setColor(128, 128, 128);
		g.drawLine(x + 3 * cellWidth, x, x + 5 * cellWidth, x + 2 * cellWidth);
		g.drawLine(x + 5 * cellWidth, x, x + 3 * cellWidth, x + 2 * cellWidth);

		// 画左上方的炮
		g.drawLine(x + d, x + cellWidth + c, x + d, x + cellWidth + d);// 左上竖
		g.drawLine(x + c, x + cellWidth + d, x + d, x + cellWidth + d);// 左上横

		g.drawLine(x + d + 2 * b, x + cellWidth + c, x + d + 2 * b, x
				+ cellWidth + d);// 右上竖
		g.drawLine(x + cellWidth + b, x + cellWidth + d, x + cellWidth + a, x
				+ cellWidth + d);// 右上横

		g.drawLine(x + d, x + 2 * cellWidth + b, x + d, x + 2 * cellWidth + a);// 左下竖
		g.drawLine(x + c, x + cellWidth + d + 2 * b, x + d, x + cellWidth + d
				+ 2 * b);// 左下横

		g.drawLine(x + d + 2 * b, x + 2 * cellWidth + b, x + d + 2 * b, x + 2
				* cellWidth + a);// 右下竖
		g.drawLine(x + cellWidth + b, x + cellWidth + d + 2 * b, x + cellWidth
				+ a, x + cellWidth + d + 2 * b);// 右下横

		// 画右上方的炮

		g.drawLine(x + d + 6 * cellWidth, x + cellWidth + c, x + d + 6
				* cellWidth, x + cellWidth + d);
		g.drawLine(x + c + 6 * cellWidth, x + cellWidth + d, x + d + 6
				* cellWidth, x + cellWidth + d);

		g.drawLine(x + d + 2 * b + 6 * cellWidth, x + cellWidth + c, x + d + 2
				* b + 6 * cellWidth, x + cellWidth + 13 + 9);
		g.drawLine(x + cellWidth + b + 6 * cellWidth, x + cellWidth + d, x
				+ cellWidth + a + 6 * cellWidth, x + cellWidth + d);

		g.drawLine(x + d + 6 * cellWidth, x + 2 * cellWidth + b, x + d + 6
				* cellWidth, x + 2 * cellWidth + a);
		g.drawLine(x + c + 6 * cellWidth, x + cellWidth + d + 2 * b, x + d + 6
				* cellWidth, x + cellWidth + d + 2 * b);

		g.drawLine(x + d + 2 * b + 6 * cellWidth, x + 2 * cellWidth + b, x + d
				+ 2 * b + 6 * cellWidth, x + 2 * cellWidth + a);
		g.drawLine(x + cellWidth + b + 6 * cellWidth,
				x + cellWidth + d + 2 * b, x + cellWidth + a + 6 * cellWidth, x
						+ cellWidth + d + 2 * b);
	}

	protected void paintMapb(Graphics g)// 画那条河--楚河，哈哈
	{
		g.setColor(128, 128, 128);
		g.drawRect(x, x + 4 * cellWidth, mapWidth, cellWidth);
		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		g.drawString("楚河       汉界", getWidth() / 2, x + 4 * cellWidth
				+ cellWidth * 3 / 4, Graphics.HCENTER | Graphics.BASELINE);
	}

	protected void paintMapc(Graphics g)// 画河的下半部分的棋盘
	{
		for (int q = 0; q < 4; q++) {
			for (int w = 0; w < 8; w++) {
				g.setColor(128, 128, 128);
				g.drawRect(x + w * cellWidth, x + (q + 5) * cellWidth,
						cellWidth, cellWidth);
			}
		}
		g.setColor(128, 128, 128);
		g.drawLine(x + 3 * cellWidth, x + 7 * cellWidth, x + 5 * cellWidth, x
				+ 9 * cellWidth);
		g.drawLine(x + 5 * cellWidth, x + 7 * cellWidth, x + 3 * cellWidth, x
				+ 9 * cellWidth);

		// 画左上方的炮
		g.drawLine(x + d, x + 6 * cellWidth + c, x + d, x + 6 * cellWidth + d);// 左上竖
		g.drawLine(x + c, x + 6 * cellWidth + d, x + d, x + 6 * cellWidth + d);// 左上横

		g.drawLine(x + d + 2 * b, x + 6 * cellWidth + c, x + d + 2 * b, x + 6
				* cellWidth + d);// 右上竖
		g.drawLine(x + cellWidth + b, x + 6 * cellWidth + d, x + cellWidth + a,
				x + 6 * cellWidth + d);// 右上横

		g.drawLine(x + d, x + 7 * cellWidth + b, x + d, x + 7 * cellWidth + a);// 左下竖
		g.drawLine(x + c, x + 6 * cellWidth + d + 2 * b, x + d, x + 6
				* cellWidth + d + 2 * b);// 左下横

		g.drawLine(x + d + 2 * b, x + 7 * cellWidth + b, x + d + 2 * b, x + 7
				* cellWidth + a);// 右下竖
		g.drawLine(x + cellWidth + b, x + 6 * cellWidth + d + 2 * b, x
				+ cellWidth + a, x + 6 * cellWidth + d + 2 * b);// 右下横

		// 画右上方的炮

		g.drawLine(x + d + 6 * cellWidth, x + 6 * cellWidth + c, x + d + 6
				* cellWidth, x + 6 * cellWidth + d);
		g.drawLine(x + c + 6 * cellWidth, x + 6 * cellWidth + d, x + d + 6
				* cellWidth, x + 6 * cellWidth + d);

		g.drawLine(x + d + 2 * b + 6 * cellWidth, x + 6 * cellWidth + c, x + d
				+ 2 * b + 6 * cellWidth, x + 6 * cellWidth + d);
		g.drawLine(x + cellWidth + b + 6 * cellWidth, x + 6 * cellWidth + d, x
				+ cellWidth + a + 6 * cellWidth, x + 6 * cellWidth + d);

		g.drawLine(x + d + 6 * cellWidth, x + 7 * cellWidth + b, x + d + 6
				* cellWidth, x + 7 * cellWidth + a);
		g.drawLine(x + c + 6 * cellWidth, x + 6 * cellWidth + d + 2 * b, x + d
				+ 6 * cellWidth, x + 6 * cellWidth + d + 2 * b);

		g.drawLine(x + d + 2 * b + 6 * cellWidth, x + 7 * cellWidth + b, x + d
				+ 2 * b + 6 * cellWidth, x + 7 * cellWidth + a);
		g.drawLine(x + cellWidth + b + 6 * cellWidth, x + 6 * cellWidth + d + 2
				* b, x + cellWidth + a + 6 * cellWidth, x + 6 * cellWidth + d
				+ 2 * b);
	}

	protected void paintAllChess(Graphics g)// 画出所有的棋子
	{
		// if (color.equals("white")) {
		if (point == null)
			return;

		for (i = 0; i < 10; i++) {
			for (j = 0; j < 9; j++) {
				if (point[i][j] != 0) {
					if (point[i][j] < 17) {
						g.setColor(255, 0, 0);
					} else {
						g.setColor(255, 255, 255);
					}
					g.fillArc(x - chessR + j * cellWidth, x - chessR + i
							* cellWidth, 2 * chessR, 2 * chessR, 0, 360);
					g.setColor(0x00000000);
					g.setFont(Font.getFont(Font.FACE_PROPORTIONAL,
							Font.STYLE_BOLD, Font.SIZE_LARGE));
					g.drawString(word[i][j], x + j * cellWidth, x + chessR + i
							* cellWidth, Graphics.HCENTER | Graphics.BOTTOM);
				}

			}
		}
	}

	protected void chooseChess(Graphics g)// 选定棋子，实现的原理就是如果选择了就再按照指定的颜色
	{
		m = guard1;
		n = guard2; // 再重新单独输出一个棋子
		g.setColor(255, 255, 0);
		g.fillArc(x - chessR + guard1 * cellWidth, x - chessR + guard2
				* cellWidth, 2 * chessR, 2 * chessR, 0, 360);
		g.setColor(0x00000000);
		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		g.drawString(word[guard2][guard1], x + guard1 * cellWidth, x + chessR
				+ guard2 * cellWidth, Graphics.HCENTER | Graphics.BOTTOM);
	}

	protected void whoIsGoing(Graphics g)// 判断该谁走了
	{

		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		if (!myTurn) {
			g.setColor(255, 255, 255);
			g.drawString("该对方走了", x, x + chessR + 10 * cellWidth, Graphics.LEFT
					| Graphics.BOTTOM);

		} else {
			g.setColor(255, 0, 0);
			g.drawString("到你走棋", x, x + chessR + 10 * cellWidth, Graphics.LEFT
					| Graphics.BOTTOM);
		}
	}

	protected void checkWin(String str)// 判断输赢
	{
		int index = str.indexOf(",");
		String str2 = str.substring(index + 1);
		if (str2.equals("you")) {
			Alert alert = new Alert("Congradulations!", "You win!", null,
					AlertType.INFO);
			ok = new Command("OK", Command.OK, 1);
			alert.setTimeout(Alert.FOREVER);
			alert.addCommand(ok);
			alert.setCommandListener(this);
			Game.display.setCurrent(alert);
		} else {
			Alert alert = new Alert("Sorry!", "You Lose!", null, AlertType.INFO);
			ok = new Command("OK", Command.OK, 1);
			alert.setTimeout(Alert.FOREVER);
			alert.addCommand(ok);
			alert.setCommandListener(this);
			Game.display.setCurrent(alert);
			// this.addCommand(start);
		}
	}

	protected void paintSelected(Graphics g)// 画选择框
	{
		g.setColor(0, 0, 255);
		g.drawRect(x - chessR + selectedX * cellWidth, x - chessR + selectedY
				* cellWidth, 2 * chessR, 2 * chessR);

	}

	protected void paint(Graphics g) {
		g.setColor(0x00000000);

		g.fillRect(0, 0, getWidth(), getHeight());

		paintMapa(g);
		paintMapb(g);
		paintMapc(g);
		paintAllChess(g);

		if (guard % 2 == 1) {
			chooseChess(g);
		}

		paintSelected(g);
		whoIsGoing(g);
	}

	protected void changTwoChessNum(int m, int n, int selectedX, int selectedY,
			boolean send)// 改变两个格子的值
	{
		// g2++;

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
				q = word[selectedY][selectedX];
				word[selectedY][selectedX] = word[n][m];
				word[n][m] = "空";
			}
		} else {
			myTurn = false;
			p = point[selectedY][selectedX];
			point[selectedY][selectedX] = point[n][m];

			point[n][m] = 0;
			q = word[selectedY][selectedX];
			word[selectedY][selectedX] = word[n][m];
			word[n][m] = "空";
		}
	}

	protected void theRuleOfChe(int m, int n, int selectedX, int selectedY)// 车的规则
	{
		g = 0;
		if (m == selectedX) {
			if (n > selectedY) {
				for (i = 1; i < n - selectedY; i++) {
					if (point[selectedY + i][m] != 0) {
						g++;
					}
				}
			} else {
				for (i = 1; i < selectedY - n; i++) {
					if (point[n + i][m] != 0) {
						g++;
					}
				}
			}
			if (g == 0) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}

		}
		if (n == selectedY) {
			if (m > selectedX) {
				for (i = 1; i < m - selectedX; i++) {
					if (point[n][i + selectedX] != 0) {
						g++;
					}
				}
			} else {
				for (i = 1; i < selectedX - m; i++) {
					if (point[n][m + i] != 0) {
						g++;
					}
				}
			}
			if (g == 0) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}

		}
	}

	protected void theRuleOfMa(int m, int n, int selectedX, int selectedY)// 马的规则
	{
		if (n < 9) {
			if (point[n + 1][m] == 0) {
				if (selectedX - m == 1) {
					if (selectedY - n == 2) {
						changTwoChessNum(m, n, selectedX, selectedY, true);
					}
				}
			}
		}
		if (n > 0) {
			if (point[n - 1][m] == 0) {
				if (m - selectedX == 1) {
					if (n - selectedY == 2) {
						changTwoChessNum(m, n, selectedX, selectedY, true);
					}
				}
			}
		}
		if (n < 9) {
			if (point[n + 1][m] == 0) {
				if (selectedX - m == -1) {
					if (selectedY - n == 2) {
						changTwoChessNum(m, n, selectedX, selectedY, true);
					}
				}
			}
		}
		if (n > 0) {
			if (point[n - 1][m] == 0) {
				if (m - selectedX == -1) {
					if (n - selectedY == 2) {
						changTwoChessNum(m, n, selectedX, selectedY, true);
					}
				}
			}
		}
		if (m < 8) {
			if (point[n][m + 1] == 0) {
				if (selectedX - m == 2) {
					if (selectedY - n == 1) {
						changTwoChessNum(m, n, selectedX, selectedY, true);
					}
				}
			}
		}
		if (m > 0) {
			if (point[n][m - 1] == 0) {
				if (m - selectedX == 2) {
					if (n - selectedY == 1) {
						changTwoChessNum(m, n, selectedX, selectedY, true);
					}
				}
			}
		}
		if (m < 8) {
			if (point[n][m + 1] == 0) {
				if (selectedX - m == 2) {
					if (selectedY - n == -1) {
						changTwoChessNum(m, n, selectedX, selectedY, true);
					}
				}
			}
		}
		if (m > 0) {
			if (point[n][m - 1] == 0) {
				if (m - selectedX == 2) {
					if (n - selectedY == -1) {
						changTwoChessNum(m, n, selectedX, selectedY, true);
					}
				}
			}
		}
	}

	protected void theRuleOfPao(int m, int n, int selectedX, int selectedY,// 炮的规则
			int g1) {
		g = 0;
		if (m == selectedX) {
			if (n > selectedY) {
				for (i = 1; i < n - selectedY; i++) {
					if (point[selectedY + i][m] != 0) {
						g++;
					}
				}
			} else {
				for (i = 1; i < selectedY - n; i++) {
					if (point[n + i][m] != 0) {
						g++;
					}
				}
			}
			if (g == g1) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}

		}
		if (n == selectedY) {
			if (m > selectedX) {
				for (i = 1; i < m - selectedX; i++) {
					if (point[n][i + selectedX] != 0) {
						g++;
					}
				}
			} else {
				for (i = 1; i < selectedX - m; i++) {
					if (point[n][m + i] != 0) {
						g++;
					}
				}
			}
			if (g == g1) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}

		}
	}

	protected void theRuleOfXiang(int m, int n, int selectedX, int selectedY)// 相的规则
	{
		if(color.equals("red"))
			if(selectedY<5)
				return;
		else if(color.equals("while"))
			if(selectedY>4)
				return;
		if (n < 9 & m < 8) {
			if (point[n + 1][m + 1] == 0) {
				if ((selectedX - m == 2) & (selectedY - n == 2)) {
					changTwoChessNum(m, n, selectedX, selectedY, true);
				}
			}
		}
		if (n > 0 & m < 8) {
			if (point[n - 1][m + 1] == 0) {
				if ((selectedX - m == 2) & (selectedY - n == -2)) {
					changTwoChessNum(m, n, selectedX, selectedY, true);
				}
			}
		}
		if (n < 9 & m > 0) {
			if (point[n + 1][m - 1] == 0) {
				if ((selectedX - m == -2) & (selectedY - n == 2)) {
					changTwoChessNum(m, n, selectedX, selectedY, true);
				}
			}
		}
		if (n > 0 & m > 0) {
			if (point[n - 1][m - 1] == 0) {
				if ((selectedX - m == -2) & (selectedY - n == -2)) {
					changTwoChessNum(m, n, selectedX, selectedY, true);
				}
			}
		}
	}

	protected void theRuleOfShi(int m, int n, int selectedX, int selectedY)// 士的规则
	{
		if ((m > 2 & m < 6) & (selectedX > 2 & selectedX < 6)
				& (n >= 7 & n <= 9) & (selectedY >= 7 & selectedY <= 9)) {

			if ((selectedX - m == 1) & (selectedY - n == 1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}

			if ((selectedX - m == 1) & (selectedY - n == -1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == -1) & (selectedY - n == 1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == -1) & (selectedY - n == -1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
		}
		if ((m > 2 & m < 6) & (selectedX > 2 & selectedX < 6)
				& (n >= 0 & n < 3) & (selectedY >= 0 & selectedY < 3)) {
			if ((selectedX - m == 1) & (selectedY - n == 1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == 1) & (selectedY - n == -1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == -1) & (selectedY - n == 1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == -1) & (selectedY - n == -1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
		}
	}

	protected void theRuleOfShuai(int m, int n, int selectedX, int selectedY)// 帅的规则
	{
		if ((m > 2 & m < 6) & (selectedX > 2 & selectedX < 6)
				& (n >= 7 & n <= 9) & (selectedY >= 7 & selectedY <= 9)) {
			if ((selectedX - m == 1) & (selectedY - n == 0)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == -1) & (selectedY - n == 0)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == 0) & (selectedY - n == 1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == 0) & (selectedY - n == -1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
		}
		if ((m > 2 & m < 6) & (selectedX > 2 & selectedX < 6)
				& (n >= 0 & n < 3) & (selectedY >= 0 & selectedY < 3)) {
			if ((selectedX - m == 1) & (selectedY - n == 0)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == -1) & (selectedY - n == 0)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == 0) & (selectedY - n == 1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
			if ((selectedX - m == 0) & (selectedY - n == -1)) {
				changTwoChessNum(m, n, selectedX, selectedY, true);
			}
		}
	}

	protected void theRuleOfZu(int m, int n, int selectedX, int selectedY)// 卒的规则
	{
		// if (point[n][m] < 17) {
		// if (selectedY >= n) {
		// if (n < 5) {
		// if ((selectedY - n == 1) & (selectedX - m == 0)) {
		// changTwoChessNum(m, n, selectedX, selectedY, true);
		// }
		// } else {
		// if ((selectedY - n == 1) & (selectedX - m == 0)) {
		// changTwoChessNum(m, n, selectedX, selectedY, true);
		// }
		// if ((selectedY - n == 0) & (selectedX - m == 1)) {
		// changTwoChessNum(m, n, selectedX, selectedY, true);
		// }
		// if ((selectedY - n == 0) & (selectedX - m == -1)) {
		// changTwoChessNum(m, n, selectedX, selectedY, true);
		// }
		// }
		// }
		// } else {
		if (selectedY <= n) {
			if (n > 4) {
				if ((selectedY - n == -1) & (selectedX - m == 0)) {
					changTwoChessNum(m, n, selectedX, selectedY, true);
				}
			} else {
				if ((selectedY - n == -1) & (selectedX - m == 0)) {
					changTwoChessNum(m, n, selectedX, selectedY, true);
				}
				if ((selectedY - n == 0) & (selectedX - m == 1)) {
					changTwoChessNum(m, n, selectedX, selectedY, true);
				}
				if ((selectedY - n == 0) & (selectedX - m == -1)) {
					changTwoChessNum(m, n, selectedX, selectedY, true);
				}
			}
		}
		// }

	}

	public void commandAction(Command c, Displayable d) {
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
			// game.initialize();
			reset();
			Game.display.setCurrent(this);

		} else if (c == start) {
			banker = false;
			client.sendMessage("start");
			this.removeCommand(start);
		}
	}

	protected synchronized void keyPressed(int keyCode) // 处理按键
	{
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
								System.out.println("point[selectedY][selectedX] / 17)="+(point[selectedY][selectedX] / 17)+"(point[n][m] / 17)="+(point[n][m] / 17));
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


	public void receiveMessage(String str)
	{                                                                                  // TODO Auto-generated method stub
		if (str.startsWith("move")) {                              // 别人出牌信息
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
			System.out.println("read -" + str);
			if (color.equals("white")) {
				point = new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },// 初始化INT数组
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
				point = new int[][] { { 17, 18, 19, 20, 21, 22, 23, 24, 25 },// 初始化INT数组
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
