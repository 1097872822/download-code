import java.util.Vector;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class GameCanvas extends Canvas implements CommandListener {
	protected Game game;

	String color = "";

	protected int empty;// ��Ļ�Ҳ����Ŀռ�

	protected int x;// �������������

	private boolean myTurn = false;

	protected int cellWidth;// ÿ�����ı߳�

	protected int mapWidth, canvasW;// ���̵Ŀ�Ⱥͻ����Ŀ��

	protected int a, b, c, d;// ���ǻ���������Ǽ������ߣ�ûʲô��

	protected int chessR;// ���ӵİ뾶

	private int desknum = -1; // �������

	private int seatPos = -1; // ��λ���

	private boolean banker = false;

	protected int selectedX, selectedY;// ѡ��������̸���ϵ�x,yλ��

	protected static int i, j;

	protected int m, n, p;// ��ס��ʼ��selectedX,selectedY��point[selectedX][selectedY]

	protected String q;// ��סword[selectedX][selectedY]

	protected int guard, guard1, guard2, g, g1;// ���FIRE�����˶��ٴ�,g�������ж���ֱ��ʱǰ�������,�м��Ƿ����������ӵ��ۼ���

	protected static int g2, isRedWin, isWhiteWin;// g2��ʾ��˭���ˣ�������������˼����

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

	public GameCanvas(Game game, Client client)// ���캯��
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
		exitCmd = new Command("�˳�", Command.EXIT, 0);
		start = new Command("��ʼ", Command.OK, 1);
		addCommand(start);
		addCommand(exitCmd);
		setCommandListener(this);
	}

	public void initWord() {
		point = new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },// ��ʼ��INT����
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 10, 0, 0, 0, 0, 0, 11, 0 },
				{ 12, 0, 13, 0, 14, 0, 15, 0, 16 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 28, 0, 29, 0, 30, 0, 31, 0, 32 },
				{ 0, 26, 0, 0, 0, 0, 0, 27, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 17, 18, 19, 20, 21, 22, 23, 24, 25 } };

		for (i = 0; i < 10; i++)// ��ʼ���ַ�����
		{
			for (j = 0; j < 9; j++) {
				if (i == 0) {
					if (j == 0) {
						word[i][j] = "��";
					}
					if (j == 1) {
						word[i][j] = "��";
					}
					if (j == 2) {
						word[i][j] = "��";
					}
					if (j == 3) {
						word[i][j] = "ʿ";
					}
					if (j == 4) {
						word[i][j] = "˧";
					}
					if (j == 8) {
						word[i][j] = "��";
					}
					if (j == 7) {
						word[i][j] = "��";
					}
					if (j == 6) {
						word[i][j] = "��";
					}
					if (j == 5) {
						word[i][j] = "ʿ";
					}
				}
				if (i == 1) {
					word[i][j] = "��";
				}
				if (i == 2) {
					if ((j != 1) & (j != 7)) {
						word[i][j] = "��";
					}
					if (j == 1) {
						word[i][j] = "��";
					}
					if (j == 7) {
						word[i][j] = "��";
					}
				}
				if (i == 3) {
					if (j % 2 == 0) {
						word[i][j] = "��";
					}
					if (j % 2 == 1) {
						word[i][j] = "��";
					}
				}
				if (i == 4) {
					word[i][j] = "��";
				}
				if (i == 5) {
					word[i][j] = "��";
				}
				if (i == 6) {
					if (j % 2 == 0) {
						word[i][j] = "��";
					}
					if (j % 2 == 1) {
						word[i][j] = "��";
					}
				}
				if (i == 7) {
					if ((j != 1) & (j != 7)) {
						word[i][j] = "��";
					}
					if (j == 1) {
						word[i][j] = "��";
					}
					if (j == 7) {
						word[i][j] = "��";
					}
				}
				if (i == 8) {
					word[i][j] = "��";
				}
				if (i == 9) {
					if (j == 0) {
						word[i][j] = "��";
					}
					if (j == 1) {
						word[i][j] = "��";
					}
					if (j == 2) {
						word[i][j] = "��";
					}
					if (j == 3) {
						word[i][j] = "ʿ";
					}
					if (j == 4) {
						word[i][j] = "˧";
					}
					if (j == 8) {
						word[i][j] = "��";
					}
					if (j == 7) {
						word[i][j] = "��";
					}
					if (j == 6) {
						word[i][j] = "��";
					}
					if (j == 5) {
						word[i][j] = "ʿ";
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

	protected void paintMapa(Graphics g)// ���ӵ��ϰ벿�ֵ�����
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

		// �����Ϸ�����
		g.drawLine(x + d, x + cellWidth + c, x + d, x + cellWidth + d);// ������
		g.drawLine(x + c, x + cellWidth + d, x + d, x + cellWidth + d);// ���Ϻ�

		g.drawLine(x + d + 2 * b, x + cellWidth + c, x + d + 2 * b, x
				+ cellWidth + d);// ������
		g.drawLine(x + cellWidth + b, x + cellWidth + d, x + cellWidth + a, x
				+ cellWidth + d);// ���Ϻ�

		g.drawLine(x + d, x + 2 * cellWidth + b, x + d, x + 2 * cellWidth + a);// ������
		g.drawLine(x + c, x + cellWidth + d + 2 * b, x + d, x + cellWidth + d
				+ 2 * b);// ���º�

		g.drawLine(x + d + 2 * b, x + 2 * cellWidth + b, x + d + 2 * b, x + 2
				* cellWidth + a);// ������
		g.drawLine(x + cellWidth + b, x + cellWidth + d + 2 * b, x + cellWidth
				+ a, x + cellWidth + d + 2 * b);// ���º�

		// �����Ϸ�����

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

	protected void paintMapb(Graphics g)// ��������--���ӣ�����
	{
		g.setColor(128, 128, 128);
		g.drawRect(x, x + 4 * cellWidth, mapWidth, cellWidth);
		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		g.drawString("����       ����", getWidth() / 2, x + 4 * cellWidth
				+ cellWidth * 3 / 4, Graphics.HCENTER | Graphics.BASELINE);
	}

	protected void paintMapc(Graphics g)// ���ӵ��°벿�ֵ�����
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

		// �����Ϸ�����
		g.drawLine(x + d, x + 6 * cellWidth + c, x + d, x + 6 * cellWidth + d);// ������
		g.drawLine(x + c, x + 6 * cellWidth + d, x + d, x + 6 * cellWidth + d);// ���Ϻ�

		g.drawLine(x + d + 2 * b, x + 6 * cellWidth + c, x + d + 2 * b, x + 6
				* cellWidth + d);// ������
		g.drawLine(x + cellWidth + b, x + 6 * cellWidth + d, x + cellWidth + a,
				x + 6 * cellWidth + d);// ���Ϻ�

		g.drawLine(x + d, x + 7 * cellWidth + b, x + d, x + 7 * cellWidth + a);// ������
		g.drawLine(x + c, x + 6 * cellWidth + d + 2 * b, x + d, x + 6
				* cellWidth + d + 2 * b);// ���º�

		g.drawLine(x + d + 2 * b, x + 7 * cellWidth + b, x + d + 2 * b, x + 7
				* cellWidth + a);// ������
		g.drawLine(x + cellWidth + b, x + 6 * cellWidth + d + 2 * b, x
				+ cellWidth + a, x + 6 * cellWidth + d + 2 * b);// ���º�

		// �����Ϸ�����

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

	protected void paintAllChess(Graphics g)// �������е�����
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

	protected void chooseChess(Graphics g)// ѡ�����ӣ�ʵ�ֵ�ԭ��������ѡ���˾��ٰ���ָ������ɫ
	{
		m = guard1;
		n = guard2; // �����µ������һ������
		g.setColor(255, 255, 0);
		g.fillArc(x - chessR + guard1 * cellWidth, x - chessR + guard2
				* cellWidth, 2 * chessR, 2 * chessR, 0, 360);
		g.setColor(0x00000000);
		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		g.drawString(word[guard2][guard1], x + guard1 * cellWidth, x + chessR
				+ guard2 * cellWidth, Graphics.HCENTER | Graphics.BOTTOM);
	}

	protected void whoIsGoing(Graphics g)// �жϸ�˭����
	{

		g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
				Font.SIZE_LARGE));
		if (!myTurn) {
			g.setColor(255, 255, 255);
			g.drawString("�öԷ�����", x, x + chessR + 10 * cellWidth, Graphics.LEFT
					| Graphics.BOTTOM);

		} else {
			g.setColor(255, 0, 0);
			g.drawString("��������", x, x + chessR + 10 * cellWidth, Graphics.LEFT
					| Graphics.BOTTOM);
		}
	}

	protected void checkWin(String str)// �ж���Ӯ
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

	protected void paintSelected(Graphics g)// ��ѡ���
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
			boolean send)// �ı��������ӵ�ֵ
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
				word[n][m] = "��";
			}
		} else {
			myTurn = false;
			p = point[selectedY][selectedX];
			point[selectedY][selectedX] = point[n][m];

			point[n][m] = 0;
			q = word[selectedY][selectedX];
			word[selectedY][selectedX] = word[n][m];
			word[n][m] = "��";
		}
	}

	protected void theRuleOfChe(int m, int n, int selectedX, int selectedY)// ���Ĺ���
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

	protected void theRuleOfMa(int m, int n, int selectedX, int selectedY)// ��Ĺ���
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

	protected void theRuleOfPao(int m, int n, int selectedX, int selectedY,// �ڵĹ���
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

	protected void theRuleOfXiang(int m, int n, int selectedX, int selectedY)// ��Ĺ���
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

	protected void theRuleOfShi(int m, int n, int selectedX, int selectedY)// ʿ�Ĺ���
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

	protected void theRuleOfShuai(int m, int n, int selectedX, int selectedY)// ˧�Ĺ���
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

	protected void theRuleOfZu(int m, int n, int selectedX, int selectedY)// ��Ĺ���
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

	protected synchronized void keyPressed(int keyCode) // ������
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
								System.out.println("point[selectedY][selectedX] / 17)="+(point[selectedY][selectedX] / 17)+"(point[n][m] / 17)="+(point[n][m] / 17));
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


	public void receiveMessage(String str)
	{                                                                                  // TODO Auto-generated method stub
		if (str.startsWith("move")) {                              // ���˳�����Ϣ
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
				point = new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },// ��ʼ��INT����
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
				point = new int[][] { { 17, 18, 19, 20, 21, 22, 23, 24, 25 },// ��ʼ��INT����
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
