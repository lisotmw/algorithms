package dynamic_planing;

public class StatTransferGraphic {
	
	/**
	 * 最短路径求取
	 * 状态转移表法
	 * @param matrix
	 * @param n
	 * @return
	 */
	public int minDistDp(int[][] matrix, int n) {
		int[][] states = new int[n][n];
		int sum = 0;
		for(int j = 0; j < n; j++) {
			sum += matrix[0][j];
			states[0][j] = sum;
		}
		sum = 0;
		for(int i = 0; i < n; i++) {
			sum += matrix[i][0];
			states[i][0] = sum;
		}
		for(int i = 1; i < n; ++i) {
			for(int j = 1; j < n; ++j) {
				states[i][j] = matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
			}
		}
		return states[n - 1][n - 1];
	}
	
	

}
