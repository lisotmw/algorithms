package dynamic_planing;

/**
 * 凑单问题
 * 一个活动“满200元减50元”，有n个想要购买的商品，在凑够满减前提下，让选出来的商品价格总和最大程度接近满减条件，简称薅羊毛
 * @author just4liz
 *
 */
public class ValuableBuy {
	/**
	 * 
	 * @param items			商品价格
	 * @param n				商品个数
	 * @param w				满减条件-200
	 */
	public static void double11advance(int[] items,int n, int w) {
		boolean[][] status = new boolean[n][3*w+1];//上限，超过3倍就没有了薅羊毛的价值
		status[0][0] = true;
		if (items[0] <= 3*w) {
			status[0][items[0]] = true;
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j <= 3*w;j++)
				if(status[i -1][j]) status[i][j] = true;
			for(int j = 0; j <= 3*w -items[i];++j)
				if(status[i - 1][j]) status[i][j + items[i]] = true;
		}
		int j;
		for(j = w; j < 3*w + 1; ++j)
			if(status[n -1][j]) break;//输出结果>=w
		if (j == 3*w + 1) return;
		
		for(int i = n - 1; i >= 1; --i) {
			if (j - items[i]>=0 && status[i - 1][j-items[i]]) {// 表示购买了这个商品
				System.out.println(items[i] + " ");
				j -= items[i];
			}//没购买j不变
		}
		if(j != 0) System.out.println(items[0]);
	}
}
