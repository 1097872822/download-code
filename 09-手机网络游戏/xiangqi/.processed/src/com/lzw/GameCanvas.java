package com.lzw;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class GameCanvas extends Canvas implements CommandListener {
	protected Game game;

	String color = "";

	protected int rightSpace;// ��Ļ�Ҳ����Ŀռ�

	protected int x;// �������������

	private boolean myTurn = false;

	protected int gridWidth;// ÿ�����ı߳�

	protected int mapWidth, canvasW;// ���̵Ŀ�Ⱥͻ����Ŀ��

	protected int a, b, c, d;

	protected int chessR;// ���ӵİ뾶

	private int desknum = -1; // �������

	private int seatPos = -1; // ��λ���

	private boolean banker = false;

	protected int selectedX, selectedY;// ѡ��������̸���ϵ�x,yλ��

	protected static int i, j;

	protected int m, n, p;// ��ס��ʼ��selectedX,selectedY��point[selectedX][selectedY]

	protected String q;// ��סword[selectedX][selectedY]

	protected int guard, guard1, guard2, g, g1;// ���FIRE�����˶��ٴ�,g�������ж���ֱ��ʱǰ�������,�м��Ƿ����������ӵ��ۼ���

	protected static int turnWho;// ��ʾ��˭����

	protected static int isRedWin; // ����ʤ��

	protected static int isWhiteWin; // ����ʤ��

	private Client client;

	protected Command exitCmd, start, ok;

	private int point[][]; // ����λ������

	protected String[][] chess; // ������������

	private int chessSelColor; // ѡ�����ӵ���ɫ

	private int backColor; // ���̱���ɫ

	private int charColor; // ���Ӻ�����ɫ

	private int lineColor; // �����ߵ���ɫ

	private int borderColor;// ���Ӻ������ɫ

	private int selBorderColor;// ѡ�����ӵı߿�ɫ

	private int blackChees; // ��������ɫ

	private int redChess;// ������ɫ

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

	public GameCanvas(Game game, Client client) {// ���캯��
		this.game = game;
		this.client = client;
		chessSelColor = 0x188312;// ��ʼ��ѡ�����ӵ���ɫ
		backColor = 0xEECD05; // ��ʼ������ɫ
		charColor = 0xFFFFFF; // ��ʼ��������ɫ
		lineColor = 0x5A5743; // ��ʼ�������ߵ���ɫ
		borderColor = 0x5A5743; // ��ʼ�����Ӻ������ɫ
		selBorderColor = 0x50FAFC; // ��ʼ��ѡ�����ӵı߿�ɫ
		blackChees = 0x000000; // ������ɫ
		redChess = 0xBF0404;// ������ɫ
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
		exitCmd = new Command("�˳�", Command.EXIT, 0);
		start = new Command("��ʼ", Command.OK, 1);
		addCommand(start);
		addCommand(exitCmd);
		setCommandListener(this);
	}

	public void initChess() {
		point = new int[][] {
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9 },// ��ʼ��INT����
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 10, 0, 0, 0, 0, 0, 11, 0 },
				{ 12, 0, 13, 0, 14, 0, 15, 0, 16 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 28, 0, 29, 0, 30, 0, 31, 0, 32 },
				{ 0, 26, 0, 0, 0, 0, 0, 27, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 17, 18, 19, 20, 21, 22, 23, 24, 25 } };

		chess = new String[][] {
				{ "��", "��", "��", "ʿ", "��", "ʿ", "��", "��", "��", },
				{ "��", "��", "��", "��", "��", "��", "��", "��", "��", },
				{ "��", "��", "��", "��", "��", "��", "��", "��", "��", },
				{ "��", "��", "��", "��", "��", "��", "��", "��", "��", },
				{ "��", "��", "��", "��", "��", "��", "��", "��", "��", },
				{ "��", "��", "��", "��", "��", "��", "��", "��", "��", },
				{ "��", "��", "��", "��", "��", "��", "��", "��", "��", },
				{ "��", "��", "��", "��", "��", "��", "��", "��", "��", },
				{ "��", "��", "��", "��", "��", "��", "��", "��", "��", },
				{ "��", "��", "��", "ʿ", "��", "ʿ", "��", "��", "��", }, };
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

	protected void paintChessboardUp(Graphics g) { // ���ϰ벿����
		for (int k = 0; k < 4; k++) {
			for (int l = 0; l < 8; l++) {
				g.setColor(lineColor);
				g.drawRect(x + l * gridWidth, x + k * gridWidth, gridWidth,
						gridWidth);
			}
		}
		g.setColor(lineColor);						// ���û������ߵ���ɫ
		g.drawLine(x + 3 * gridWidth, x, x + 5 * gridWidth, x + 2 * gridWidth);
		g.drawLine(x + 5 * gridWidth, x, x + 3 * gridWidth, x + 2 * gridWidth);
		// �����Ϸ�����
		{
			g.drawLine(x + d, x + gridWidth + c, x + d, x + gridWidth + d);// ������
			g.drawLine(x + c, x + gridWidth + d, x + d, x + gridWidth + d);// ���Ϻ�
			g.drawLine(x + d + 2 * b, x + gridWidth + c, x + d + 2 * b, x
					+ gridWidth + d);// ������
			g.drawLine(x + gridWidth + b, x + gridWidth + d, x + gridWidth + a,
					x + gridWidth + d);// ���Ϻ�
			g.drawLine(x + d, x + 2 * gridWidth + b, x + d, x + 2 * gridWidth
					+ a);// ������
			g.drawLine(x + c, x + gridWidth + d + 2 * b, x + d, x + gridWidth
					+ d + 2 * b);// ���º�
			g.drawLine(x + d + 2 * b, x + 2 * gridWidth + b, x + d + 2 * b, x
					+ 2 * gridWidth + a);// ������
			g.drawLine(x + gridWidth + b, x + gridWidth + d + 2 * b, x
					+ gridWidth + a, x + gridWidth + d + 2 * b);// ���º�
		}
		// �����Ϸ�����
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

	protected void paintRiver(Graphics g) {// ����
		g.setColor(lineColor);
		g.drawRect(x, x + 4 * gridWidth, mapWidth, gridWidth);
		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		g.setColor(borderColor);
		g.drawString("����       ����", getWidth() / 2, x + 4 * gridWidth
				+ gridWidth * 3 / 4, Graphics.HCENTER | Graphics.BASELINE);
	}

	protected void paintChessboardDown(Graphics g) {// ���°벿����
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
		// �����Ϸ�����
		{
			g.drawLine(x + d, x + 6 * gridWidth + c, x + d, x + 6 * gridWidth
					+ d);// ������
			g.drawLine(x + c, x + 6 * gridWidth + d, x + d, x + 6 * gridWidth
					+ d);// ���Ϻ�

			g.drawLine(x + d + 2 * b, x + 6 * gridWidth + c, x + d + 2 * b, x
					+ 6 * gridWidth + d);// ������
			g.drawLine(x + gridWidth + b, x + 6 * gridWidth + d, x + gridWidth
					+ a, x + 6 * gridWidth + d);// ���Ϻ�

			g.drawLine(x + d, x + 7 * gridWidth + b, x + d, x + 7 * gridWidth
					+ a);// ������
			g.drawLine(x + c, x + 6 * gridWidth + d + 2 * b, x + d, x + 6
					* gridWidth + d + 2 * b);// ���º�

			g.drawLine(x + d + 2 * b, x + 7 * gridWidth + b, x + d + 2 * b, x
					+ 7 * gridWidth + a);// ������
			g.drawLine(x + gridWidth + b, x + 6 * gridWidth + d + 2 * b, x
					+ gridWidth + a, x + 6 * gridWidth + d + 2 * b);// ���º�
		}
		// �����Ϸ�����
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

	protected void paintAllChess(Graphics g) {// ������������
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

	protected void paintSelectedChess(Graphics g) {// ��ѡ�������
		m = guard1;
		n = guard2; // �����µ������һ������
		g.setColor(chessSelColor);
		g.fillArc(x - chessR + guard1 * gridWidth, x - chessR + guard2
				* gridWidth, 2 * chessR, 2 * chessR, 0, 360);
		g.setColor(charColor);
		g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		g.drawString(chess[guard2][guard1], x + guard1 * gridWidth, x + chessR
				+ guard2 * gridWidth, Graphics.HCENTER | Graphics.BOTTOM);
	}

	protected void whoTurn(Graphics g) {// �жϸ�˭����
		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		if (!myTurn) {
			g.setColor(0xFE0000);
			g.drawString("�ֵ��Լ�����", x, x + chessR + 10 * gridWidth,
					Graphics.LEFT | Graphics.BOTTOM);

		} else {
			g.setColor(0X9F3202);
			g.drawString("�ֵ��Լ�����", x, x + chessR + 10 * gridWidth,
					Graphics.LEFT | Graphics.BOTTOM);
		}
	}

	protected void checkWin(String str) {// �ж���Ӯ
		int index = str.indexOf(",");
		String str2 = str.substring(index + 1);
		try {
			if (str2.equals("you")) {
				Alert alert;
				alert = new Alert("��ϲ!", null, Image.createImage(this
						.getClass().getResourceAsStream("win.gif")),
						AlertType.INFO);
				ok = new Command("OK", Command.OK, 1);
				alert.setTimeout(Alert.FOREVER);
				alert.addCommand(ok);
				alert.setCommandListener(this);
				Game.display.setCurrent(alert);
			} else {
				Alert alert = new Alert("�ź�!", null, Image.createImage(this
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

	protected void paintSelected(Graphics g) {// ��ѡ���
		g.setColor(selBorderColor);
		g.drawRect(x - chessR + selectedX * gridWidth, x - chessR + selectedY
				* gridWidth, 2 * chessR, 2 * chessR);

	}

	protected void paint(Graphics g) {
		g.setColor(backColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		paintChessboardUp(g); // ���û��ϰ벿���̵ķ���
		paintRiver(g); // ���û��ӵķ���
		paintChessboardDown(g); // ���û��°벿���̵ķ���
		paintAllChess(g); // ���û����ӵķ���
		if (guard % 2 == 1) {
			paintSelectedChess(g); // ���û�ѡ�����ӵķ���
		}
		paintSelected(g);
		whoTurn(g);
	}

	protected void changTwoChessNum(int m, int n, int selectedX, int selectedY,
			boolean send) {// �ı��������ӵ�ֵ
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
				chess[n][m] = "��";
			}
		} else {
			myTurn = false;
			p = point[selectedY][selectedX];
			point[selectedY][selectedX] = point[n][m];

			point[n][m] = 0;
			q = chess[selectedY][selectedX];
			chess[selectedY][selectedX] = chess[n][m];
			chess[n][m] = "��";
		}
	}

	private void theRuleOfChe(int m, int n, int sx, int sy) {// ���Ĺ���
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

	private void theRuleOfMa(int m, int n, int sx, int sy) {// ��Ĺ���
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

	private void theRuleOfPao(int m, int n, int sx, int sy, int g1) {// �ڵĹ���
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

	private void theRuleOfXiang(int m, int n, int sx, int sy) {// ��Ĺ���
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

	private void theRuleOfShi(int m, int n, int sx, int sy) {// ʿ�Ĺ���
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

	private void theRuleOfShuai(int m, int n, int sx, int sy) {// ˧�Ĺ���
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

	private void theRuleOfZu(int m, int n, int sx, int sy) {// ��Ĺ���
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

	public void commandAction(Command c, Displayable d) { // �����ť�¼�����
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

	protected void keyPressed(int keyCode) { // ������
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
			} else if (action == Canvas.FIRE)// �����FIRE���ҷֳ������������һ��ѡ�����ӣ�
			{ // ���ǵ�ѡ�������Ӻ��������ߵ�����ѡ���λ��

				guard = guard + 1;// ÿ����FIREһ�Σ�GUARD�ͼ�һ�������ж�FIRE�Ǳ�ѡ�ֻ���ѡ�ֺ����²���
				if (guard % 2 == 1) // ��ʱ�ǵ�ѡ��ĳһ������ʱ������choosChess������ѡ������
				{
					if (point[selectedY][selectedX] != 0) {
						guard1 = selectedX;
						guard2 = selectedY;
					}
				}

				if (guard % 2 == 0)// ��������ǵ����ӱ�ѡ�ֺ�
				{
					if (point[selectedY][selectedX] != point[n][m])// ���ߵ���һ����������Ҳ�������ѡ��
					{ // һ�����ӣ��ֲ���ѡ�ˣ���ֻ��ʲô������
						if ((point[n][m] == 1) | (point[n][m] == 9)
								| (point[n][m] == 17) | (point[n][m] == 25))// ��ѡ���������ǳ���ʱ��
						{ // repaint��OK��
							if (point[selectedY][selectedX] == 0)// ����һ���ߵ��ǿո���ı�ѡ�ֵĸ��Ӻ���һ����
							{ // �ߵĸ��ӵ�point[][]��word[][]��ֲ��Ȼ��repaint��OK
								theRuleOfChe(m, n, selectedX, selectedY);
							} else// ����һ������ԶԷ����ӵģ������һ�����ӵ�ֵ��Ϊ�ղ�ѡ���ĸ��ӵ�ֵ����
							{ // �ղ�ѡ���ĸ��ӵ�ֵ���Ϊ��
								System.out
										.println("point[selectedY][selectedX] / 17)="
												+ (point[selectedY][selectedX] / 17)
												+ "(point[n][m] / 17)="
												+ (point[n][m] / 17));
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// ��Ȼ����Ե��Ӳ������Լ���
								{
									theRuleOfChe(m, n, selectedX, selectedY);

								}
							}
						}
						if ((point[n][m] == 2) | (point[n][m] == 8)
								| (point[n][m] == 18) | (point[n][m] == 24))// ��ѡ�������������ʱ��
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfMa(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// ��Ȼ����Ե��Ӳ������Լ���
								{
									theRuleOfMa(m, n, selectedX, selectedY);

								}
							}
						}
						if ((point[n][m] == 10) | (point[n][m] == 11)
								| (point[n][m] == 26) | (point[n][m] == 27))// ��ѡ�����������ڵ�ʱ��
						{
							if (point[selectedY][selectedX] == 0) {
								g1 = 0;
								theRuleOfPao(m, n, selectedX, selectedY, g1);
							} else {
								g1 = 1;
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// ��Ȼ����Ե��Ӳ������Լ���
								{
									theRuleOfPao(m, n, selectedX, selectedY, g1);

								}
							}
						}
						if ((point[n][m] == 3) | (point[n][m] == 7)
								| (point[n][m] == 19) | (point[n][m] == 23))// ��ѡ�������������ʱ��
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfXiang(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// ��Ȼ����Ե��Ӳ������Լ���
								{
									theRuleOfXiang(m, n, selectedX, selectedY);

								}
							}
						}
						if ((point[n][m] == 4) | (point[n][m] == 6)
								| (point[n][m] == 20) | (point[n][m] == 22))// ��ѡ����������ʿ��ʱ��
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfShi(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// ��Ȼ����Ե��Ӳ������Լ���
								{
									theRuleOfShi(m, n, selectedX, selectedY);

								}
							}
						}
						if ((point[n][m] == 5) | (point[n][m] == 21))// ��ѡ����������˧��ʱ��
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfShuai(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// ��Ȼ����Ե��Ӳ������Լ���
								{
									theRuleOfShuai(m, n, selectedX, selectedY);

								}
							}
						}
						if ((point[n][m] > 11 & point[n][m] < 17))// ��ѡ���������Ǻ췽���ʱ��
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfZu(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// ��Ȼ����Ե��Ӳ������Լ���
								{
									theRuleOfZu(m, n, selectedX, selectedY);

								}
							}
						}
						if (point[n][m] > 27)// ��ѡ���������ǰ׷����ʱ��
						{
							if (point[selectedY][selectedX] == 0) {
								theRuleOfZu(m, n, selectedX, selectedY);
							} else {
								if ((point[selectedY][selectedX] / 17) != (point[n][m] / 17))// ��Ȼ����Ե��Ӳ������Լ���
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
		if (str.startsWith("move")) { // ���˳�����Ϣ
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
						{ 1, 2, 3, 4, 5, 6, 7, 8, 9 },// ��ʼ��INT����
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
						{ 17, 18, 19, 20, 21, 22, 23, 24, 25 },// ��ʼ��INT����
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
