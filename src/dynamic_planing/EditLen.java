package dynamic_planing;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.text.Position.Bias;
import javax.xml.transform.stream.StreamSource;

import edu.princeton.cs.algs4.Out;

/**
 * 计算两个文本的编辑长度
 * @author just4liz
 *
 */
public class EditLen {

	public void f(String a, String b) {
		calcLen(a, b, 0, 0, 0);
	}
	
	public int minLen = Integer.MAX_VALUE;
	
	private void calcLen(String a, String b, int ia, int ib, int len) {
		System.out.println(ia + " : " + ib + " : " + len);
		if (ia == a.length() || ib == b.length()) {
			if (ib < b.length()) {
				len += Math.abs(ib - b.length());				
			}
			if (ia < a.length()) {
				len += Math.abs(ia - a.length());								
			}
			if (len < minLen) {
				minLen = len;
			}
			return;
		}
		if (a.charAt(ia) == b.charAt(ib)) {
			calcLen(a, b, ia + 1, ib + 1, len);
		}else {
			calcLen(a, b, ia + 1, ib, len + 1);
			calcLen(a, b, ia, ib + 1, len + 1);
			calcLen(a, b, ia + 1, ib + 1, len + 1);
		}
	}
	
	
	/**
	 * 动态规划方案
	 * @param a
	 * @param b
	 */
	public int dynamic0(String a, String b) {
		//动态规划状态表
		int[][] dp = new int[a.length() + 1][b.length() + 1];
		
		//TODO 是否初始化待定
		for(int ai = 0; ai < a.length(); ai++) {
			for( int bi = 0; bi < b.length(); bi++) {
				int dpai = ai + 1;
				int dpbi = bi + 1;
				if (a.charAt(ai) == b.charAt(bi)) {
					dp[dpai][dpbi] = maxInThree(dp[dpai - 1][dpbi],dp[dpai][dpbi - 1],dp[dpai - 1][dpbi - 1]);
				}else {
					dp[dpai][dpbi] = maxInThree(dp[dpai - 1][dpbi] + 1,dp[dpai][dpbi - 1] + 1,dp[dpai - 1][dpbi - 1] + 1);
				}
			}
		}
		for(int[] arr : dp) {
			Arrays.stream(arr).boxed().collect(Collectors.toList()).forEach(System.out::print);
			System.out.println();
		}
		return dp[a.length()][b.length()];
	}
	
	private int maxInThree(int a, int b, int c) {
		int temp = Integer.MAX_VALUE;
		temp = a < temp ? a : temp;
		temp = b < temp ? b : temp;
		temp = c < temp ? c : temp;
		return temp;
	}
	
	public static void main(String[] args) {
		String a = "mitcmne";
		String b = "mtacnue";
		EditLen editLen = new EditLen();
		int minLen = editLen.dynamic0(a, b);
		System.out.println(minLen);
		
	}
}
