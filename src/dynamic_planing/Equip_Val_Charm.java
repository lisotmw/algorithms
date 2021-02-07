package dynamic_planing;

/**
 * 装备价值魅力问题（多维背包问题）：
 * 游戏仓库里有多种装备，每种装备各有若干件，装备有单价和魅力两种属性，
 * 要求在限定总价的情况下，所购买的装备魅力值最高
 * @author just4liz
 *
 */
public class Equip_Val_Charm {
	/**
	 * 第一行数据为[品类数量，价值总量]
	 * 后面数据为[单品类数量，单品类价值，单品类魅力值]
	 */
	static int[][] A = {{4,102},{1,50,50},{3,20,19},{3,13,32},{2,21,47}};
	/**
	 * 动态规划状态方程处理法
	 * @param leftP				剩余的可用价值
	 * @param idxs				A 数组下标
	 * @param nidx				单品类下标
	 * @param totalPro			总魅力值
	 * @return					最大魅力值
	 */
	int func(int leftP,int idx, int nidx, int totalPro) {
		// 当前价格
		int nowPri = A[idx][1];
		// 当前魅力值
		int nowPro = A[idx][2];
		// 剩余价值不够，遍历到最后一个道具 =》拦截返回
		if (leftP <= 0 || (idx <= 1 && nidx <= 1)) {
			if (leftP <= 0 || leftP - nowPri < 0) return totalPro;
			return nowPro + totalPro;
		}
		
		int newPro = 0;
		// 剩余价值小于当前品类价值， 直接往前跳一个品类
		if (leftP - nowPri < 0) {
			if (idx - 1 < 1) {
				return totalPro;
			}
			newPro = func(leftP, idx - 1, A[idx - 1][0], totalPro);
		}
		// 到了当前品类最后一个，往前跳一个品类
		else if (nidx <= 1) {
			// 选（剩余价值减少，总魅力值增加）或者不选
			newPro = Math.max(func(leftP - nowPri, idx - 1, A[idx -1][0],nowPro + totalPro), 
					func(leftP, idx - 1, A[idx - 1][0],totalPro));
		}else {
			newPro = Math.max(func(leftP - nowPri, idx, nidx - 1, nowPro + totalPro),
					func(leftP, idx, nidx - 1, totalPro));
		}
		return newPro;
	}
	
	/**
	 * 动态规划，状态转移表法
	 * @return
	 */
	int func1() {
		// 最大价格上限
		int maxPri = A[0][1];
		// 汇总所有装备的总个数
		int YLen = 0;
		// 遍历 A数组的初始下标
		int idx = 1;
		// 在某一种装备中的指针下标（A[x][0],x > 0）
		int nidx = 0;
		for(int i = 1; i < A.length; i++) {
			YLen += A[i][0];
		}
		// 动态规划二维状态转移表
		int[][] dp = new int[YLen][maxPri];
		// 初始化第一个道具 dp 值
		if (maxPri >= A[1][1]) {
			dp[0][A[1][1] - 1] = A[1][2];
			// 若第一个道具只有一个，则指针后移一位
			if (A[1][0] == 1) {
				idx++;
			}
		}
		for(int a = 1; a < YLen; a++) {
			if (idx >= A.length) {
				break;
			}
			for(int b = 0; b < maxPri; b++) {
				if (dp[a - 1][b] > 0 || b == 0) {
					dp[a][b] = dp[a - 1][b];
					// 不能超过最大价格
					if (b + A[idx][1] - 1 < maxPri) {
						dp[a][b + A[idx][1] - 1] = Math.max(dp[a - 1][b] + A[idx][2],
								dp[a][b + A[idx][1] - 1]);						
					}
				}
			}
			if(nidx < A[idx][0] - 1)
				nidx++;
			else {
				nidx = 0;
				idx++;
			}
		}
		Util.printArr2(dp);
		
		int charm = 0;
		for(int b1 = maxPri - 1; b1 > 0; b1--) {
			charm = Math.max(dp[YLen - 1][b1], charm);
		}
		return charm;
	}
	
	
	public static void main(String[] args) {
		// 从数组最后一个元素开始遍历
		Equip_Val_Charm inst = new Equip_Val_Charm();
		System.out.println(inst.func(A[0][1], A[0][0], A[A.length - 1][0], 0));
		System.out.println(inst.func1());
	}
}
