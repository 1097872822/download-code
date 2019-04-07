package com.lzw;
public class Umpire {
	private int huihe;
	private int bigID;
	private int score;
	protected static int i, j;
	protected static int isRedWin = 1, isWhiteWin = 1;// g2表示该谁走了，后面那俩顾名思义了
	private int point[][];
	public Umpire() {
		huihe = 0;
		score = 0;
		point = new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },// 初始化INT数组
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 10, 0, 0, 0, 0, 0, 11, 0 },
				{ 12, 0, 13, 0, 14, 0, 15, 0, 16 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 28, 0, 29, 0, 30, 0, 31, 0, 32 },
				{ 0, 26, 0, 0, 0, 0, 0, 27, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 17, 18, 19, 20, 21, 22, 23, 24, 25 } };
	}
	public void checkWin() {// 判断输赢
		isRedWin = 0;
		isWhiteWin = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				if (point[0 + i][3 + j] == 5) {
					isRedWin++;
				}
			}
		}
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				if (point[7 + i][3 + j] == 21) {
					isWhiteWin++;
				}
			}
		}
	}
	public void moveChess(int selectedY, int selectedX, int n, int m) {
		point[selectedY][selectedX] = point[n][m];
		point[n][m] = 0;
		checkWin();
	}
	public void logHuihe() {
		huihe++;
	}
	public int getHuihe() {
		return huihe;
	}
	public void init() {
		huihe = 0;
		score = 0;
		isRedWin = 1;
		isWhiteWin = 1;
		point = new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8, 9 },// 初始化INT数组
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 10, 0, 0, 0, 0, 0, 11, 0 },
				{ 12, 0, 13, 0, 14, 0, 15, 0, 16 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 28, 0, 29, 0, 30, 0, 31, 0, 32 },
				{ 0, 26, 0, 0, 0, 0, 0, 27, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 17, 18, 19, 20, 21, 22, 23, 24, 25 } };
	}
	public int score() {
		return score;
	}
	public void clearScore() {
		score = 0;
	}
	public void clearhuihe() {
		huihe = 0;
	}
}
