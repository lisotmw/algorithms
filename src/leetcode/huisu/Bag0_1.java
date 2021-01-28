package leetcode.huisu;

/**
 * 0-1背包问题
 * @author just4liz
 *
 */
public class Bag0_1 {
	// 背包载重上限
	public int maxW = Integer.MIN_VALUE;
	
	/**
	 * 
	 * @param i			背包中物品下标
	 * @param cw		已装进背包的物品重量总和，
	 * @param items		物品重量数组
	 * @param n			背包允许的物品个数
	 * @param w			背包重量
	 */
	public void f(int i, int cw, int[] items, int n, int w) {
		if (cw == w || i == n) {
			if (cw >maxW) {
				maxW = cw;
			}
			return;
		}
		f(i + 1, cw, items, n, w);
		if (cw + items[i] <= w) {
			f(i + 1, cw + items[i], items, n, w);
		}
	}
	
	
}
