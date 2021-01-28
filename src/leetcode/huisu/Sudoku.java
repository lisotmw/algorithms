package leetcode.huisu;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * 利用回溯算法解决数独问题
 * @author just4liz
 *
 */
public class Sudoku {
	
	/**
	 * 执行用时：13 ms, 在所有 Java 提交中击败了24.27%的用户
	 * 内存消耗：37.7 MB, 在所有 Java 提交中击败了12.81%的用户
	 * @param board
	 */
	public void solveSudoku(char[][] board) {
		solve(0, 0, board);
    }
	
	public boolean solve(int Ix, int Iy, char[][] board) {
		if (Ix > 8 && Iy > 8) {
			return true;
		}
		for(int x = Ix; x < 9; ++x) {
			for(int y = Iy; y < 9; ++y) {
				// 只要执行过一次Iy从0开始算
				Iy = 0;
				if (board[x][y] != '.') {
					continue;
				}
				int takeIn = 1;
				boolean succss = false;
				whi:
				while(takeIn <= 9 && !succss) {
					int gridX = (x) / 3;
					int gridY = (y) / 3;
					// 检测大格子内的数字
					for(int a = gridX * 3; a < gridX * 3 + 3; a++) {
						for(int b = gridY * 3; b < gridY * 3 + 3; b++) {
							if (board[a][b] - '0' == takeIn) {
								takeIn++;
								continue whi;
							}
						}						
					}
					//横向
					for(int y1 = 0; y1 < 9; y1++) {
						if (board[x][y1] - '0' == takeIn) {
							takeIn++;
							continue whi;
						}
					}
					//纵向
					for(int x1 = 0; x1 < 9; x1++) {
						if (board[x1][y] - '0' == takeIn) {
							takeIn++;
							continue whi;
						}
					}
					succss = true;
					board[x][y] = String.valueOf(takeIn).toCharArray()[0];
					if (y < 8) {
						if(!solve(x, y + 1,board)) {
							board[x][y] = '.';
							takeIn++;
							succss = false;
						}
					}else {
						if(!solve(x + 1, 0,board)) {
							board[x][y] = '.';
							takeIn++;
							succss = false;
						}
					}
				}
				if (!succss) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean solve(int Ix, int Iy, List<List<Integer>> sudoku) {
		if (Ix > 8 && Iy > 8) {
			return true;
		}
		for(int x = Ix; x < 9; ++x) {
			for(int y = Iy; y < 9; ++y) {
				// 只要执行过一次Iy从0开始算
				Iy = 0;
				if (sudoku.get(x).get(y) != 0) {
					continue;
				}
				int takeIn = 1;
				boolean succss = false;
				whi:
				while(takeIn <= 9 && !succss) {
					int gridX = (x) / 3;
					int gridY = (y) / 3;
					// 检测大格子内的数字
					for(int a = gridX * 3; a < gridX * 3 + 3; a++) {
						for(int b = gridY * 3; b < gridY * 3 + 3; b++) {
							if (sudoku.get(a).get(b) == takeIn) {
								takeIn++;
								continue whi;
							}
						}						
					}
					//横向
					for(int y1 = 0; y1 < 9; y1++) {
						if (sudoku.get(x).get(y1) == takeIn) {
							takeIn++;
							continue whi;
						}
					}
					//纵向
					for(int x1 = 0; x1 < 9; x1++) {
						if (sudoku.get(x1).get(y) == takeIn) {
							takeIn++;
							continue whi;
						}
					}
					succss = true;
					List<Integer> temp = sudoku.get(x);
					temp.set(y, takeIn);
					sudoku.set(x,temp);
					if (y < 8) {
						if(!solve(x, y + 1,sudoku)) {
							List<Integer> temp0 = sudoku.get(x);
							temp0.set(y, 0);
							sudoku.set(x,temp0);
							takeIn++;
							succss = false;
						}
					}else {
						if(!solve(x + 1, 0,sudoku)) {
							List<Integer> temp0 = sudoku.get(x);
							temp0.set(y, 0);
							sudoku.set(x,temp0);
							takeIn++;
							succss = false;
						}
					}
				}
				if (!succss) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void printSudo(List<List<Integer>> sudo) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < sudo.size(); i++) {
			for(int j = 0; j < sudo.size(); j++) {
				sb.append(sudo.get(i).get(j) + " ");
				if ((j + 1) % 3 == 0) {
					sb.append("|");
				}
			}
			sb.append("\n");
			if ((i + 1) % 3 == 0) {
				sb.append("--------------------\n");
			}
		}
		System.out.println("///////////////////////////");
		System.out.println(sb.toString());
	}
	public static void main(String[] args) {
		List<List<Integer>> sudo = Arrays.asList(
				Arrays.asList(0,0,0,4,0,0,6,7,0),
				Arrays.asList(0,0,7,0,0,0,0,9,4),
				Arrays.asList(2,0,0,8,0,7,1,0,0),
				Arrays.asList(8,0,5,2,0,9,0,0,0),
				Arrays.asList(0,3,0,0,0,6,0,0,0),
				Arrays.asList(7,2,0,0,8,0,0,0,0),
				Arrays.asList(0,0,2,0,0,0,0,0,7),
				Arrays.asList(0,0,0,6,0,0,5,2,0),
				Arrays.asList(4,0,1,0,2,8,0,0,6)
				);
		printSudo(sudo);
		new Sudoku().solve(0, 0, sudo);
		printSudo(sudo);
	}
	
	
}
