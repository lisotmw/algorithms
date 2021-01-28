package dynamic_planing;

/**
 * 背包问题
 * 动态规划解法
 * @author just4liz
 *
 */
public class Knapsack {
	/**
	 * 二位数组状态表解法
	 * @param weight		物品重量
	 * @param n				物品个数
	 * @param w				背包可承载重量
	 * @return
	 */
	public int knapsack(int[] weight,int n, int w) {
		boolean[][] status = new boolean[n][w + 1];
		status[0][0] = true;// 特殊处理一下
		if (weight[0] < w) {
			status[0][weight[0]] = true;
		}
		for(int i = 1; i < n; i++) {// 动态规划状态转移
			for(int j = 0; j <= w;++j) {
				if(status[i - 1][j] == true) status[i][j] = status[i -1][j];// 不把第i个放进背包，这个时候直接取前一次的所有状态
			}
			for(int j = 0;j <= w - weight[i]; ++j) {
				if(status[i - 1][j] == true) status[i][j + weight[i]] = true;// 把第i个放进背包
			}
		}
		for(int i = w; i >= 0; --i) {
			if(status[n - 1][i]==true) return i;
		}
		return 0;
	}
	
	/**
	 * 一维数组解法
	 * @param items
	 * @param n
	 * @param w
	 * @return
	 */
	public int knapsack2(int[] items, int n, int w) {
		boolean[] status = new boolean[w + 1];
		status[0] = true;
		
		if (items[0] < w) {
			status[items[0]] = true;
		}
		
		for(int i = 1; i < n; ++i) {
			for(int j = w - items[i]; j >= 0; --j) {
				if(status[j] == true) {
					status[j + items[i]]= true;
				}
			}
		}
		
		for(int i = w;i >= 0; --i) {
			if (status[i]) return i;
		}
		return 0;
	}
}
