package dynamic_planing;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 关于拼写纠错-动态规划实现
 * @author just4liz
 *
 */
public class SpellCorrection1 {
	public int lwstDp(char[] a,int n,char[] b,int m) {
		int[][] minDist = new int[n][m];
		for(int j = 0; j < m; ++j) {
			if(a[0] == b[j]) minDist[0][j] = j;
			else if(j != 0)	minDist[0][j] = minDist[0][j - 1] + 1;
			else minDist[0][j] = 1;
		}
		for(int i = 0; i < n; ++i) {
			if(a[i]== b[0])	minDist[i][0] = i;
			else if(i != 0) minDist[i][0] = minDist[i - 1][0] + 1;
			else minDist[i][0] = 1;
		}
		
		for(int i = 1; i < n; ++i) {
			for(int j = 1; j < m; ++j) {
				if(a[i] == b[i]) minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
				else minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
			}
		}
		for(int[] arr : minDist) {
			Arrays.stream(arr).boxed().collect(Collectors.toList()).forEach(System.out::print);
			System.out.println();
		}
		return minDist[n - 1][m - 1];
	}
	
	private int min(int x, int y, int z) {
		int minv = Integer.MAX_VALUE;
		if(x < minv) minv = x;
		if(y < minv) minv = y;
		if(z < minv) minv = z;
		return minv;
	}
	
	public static void main(String[] args) {
		String a = "mitcmu";
		String b = "mtacnu";
		SpellCorrection1 sc = new SpellCorrection1();
		int dp = sc.lwstDp(a.toCharArray(), 6, b.toCharArray(), 6);
		System.out.println("最少替换次数： " + dp);
	}
}
