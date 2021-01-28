package dynamic_planing;

/**
 * 最短路径求取
 * 状态转移方程
 * 递归 + 备忘录
 * @author just4liz
 *
 */
public class StatTransferFunc {
	private int[][] matrix = {{1,3,5,9},{2,1,3,4},{5,2,6,7},{6,8,4,3}};
	private int n = 4;
	private int[][] mem = new int[4][4];
	
	/**
	 * minDist(n - 1, n - 1)
	 * @param i
	 * @param j
	 * @return
	 */
	public int minDist(int i, int j) {
		if(i == 0 && j ==0) return matrix[0][0];
		if(mem[i][j] > 0) return matrix[i][j];
		int minLeft = Integer.MAX_VALUE;
		if (j - 1 >= 0) {
			minLeft = minDist(i, j - 1);
		}
		int minUp = Integer.MAX_VALUE;
		if(i - 1 >= 0) {
			minUp = minDist(i - 1, j);
		}
		int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
		mem[i][j] = currMinDist;
		return currMinDist;
	}
}
