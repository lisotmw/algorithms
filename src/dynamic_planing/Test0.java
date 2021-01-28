package dynamic_planing;

/**
 *  动态规划 demo0
 *  背包选择问题(回溯 + 备忘录解法)
 *  动态规划解法 参见 {@link Knapsack}
 * @author just4liz
 *
 */
public class Test0 {

	// 结果放在maxW 中
	private int maxW = Integer.MIN_VALUE;
	// 重量
	private int[] weight = {2,2,4,6,3};
	private int count = 3;// 物品个数
	private int loadWeight = 9;//背包承受的最大重量
	boolean[][] mem = new boolean[3][10];//备忘，默认false
	
	public void func(int i, int cw) {
		if (cw == loadWeight || i == count) {
			if (cw > maxW) {
				maxW = cw;
			}
			return;
		}
		if(mem[i][cw]) return;//重复状态
		mem[i][cw] = true;//记录状态
		func(i + 1, cw);// 选择不装第i个物品
		if (cw + weight[i] <= loadWeight ) {
			func(i + 1, cw + weight[i]);//选择装第i个物品
		}
	}
	
	public static void main(String[] args) {
		Test0 test0 = new Test0();
		test0.func(0, 0);
		boolean[][] mem = test0.mem;
		for(boolean[] mem1 : mem) {
			for(boolean mem2 : mem1) {
				System.out.print(mem2 ? 1 : 0);
			}
			System.out.println();			
		}
		
		System.out.println(test0.maxW);
	}
}
