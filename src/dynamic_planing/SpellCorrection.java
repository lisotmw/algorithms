package dynamic_planing;

/**
 * 关于拼写纠错-简单回溯实现
 * 处理使两个字符串相同的最少编辑次数
 * @author just4liz
 *
 */
public class SpellCorrection {
	private char[] a = "mitcmu".toCharArray();
	private char[] b = "mtacnu".toCharArray();
	private int n = 6;
	private int m = 6;
	
	public int minDist = Integer.MAX_VALUE;
	
	/**
	 * invoke:lwstBT(0,0,0);
	 * @param i
	 * @param j
	 * @param edist
	 */
	public void lwstBT(int i, int j, int edist) {
		if (i == n || j == m) {
			if(i < n) edist += (n - i);
			if(j < m) edist += (m - j);
			if(edist < minDist) minDist = edist;
			return;
		}
		if(a[i]== b[i]) {// 两字符匹配
			lwstBT(i + 1, j + 1, edist);
		}else {//两字符不匹配
			lwstBT(i + 1, j, edist + 1);
			lwstBT(i, j + 1, edist + 1);
			lwstBT(i + 1, j + 1, edist + 1);
		}
	}
	
	public static void main(String[] args) {
		SpellCorrection sc = new SpellCorrection();
		sc.lwstBT(0, 0, 0);
		System.out.println(sc.minDist);
	}
}
