package leetcode;


/**
 * 给定一个字符串s，找到s中最长的回文子串
 * @author just4liz
 *
 */
public class Test03 {
	/**
	 * 动态规划
	 * @param s
	 * @return
	 */
	public String longABA(String s) {
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		String ans = "";
		
		for(int l = 0; l < n; ++l) {
			for(int i= 0; i + l<n; ++i) {
				int j = i + l;
				if (l == 0) {
					dp[i][j] = true;
				}else if (l == 1) {
					dp[i][j] = (s.charAt(i) == s.charAt(j)); 
				}else {
					dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]); 
				}
				
				if (dp[i][j] && l + 1 > ans.length()) {
					ans = s.substring(i, i + l + 1);
				}
			}
		}
		return ans;
	}
	
	/**
	 * 动态规划01
	 * 执行用时：358 ms, 在所有 Java 提交中击败了11.43%的用户
	 * 内存消耗：44.6 MB, 在所有 Java 提交中击败了6.32%的用户
	 * @param s
	 * @return
	 */
	public String longABA01(String s) {
		int len= s.length();
		String result = "";
		boolean dp[][] = new boolean[len][len];
		for(int le = 0; le < len; le++) {// 长度
			int end = len - le;
			for(int i = 0; i < end; i++) {// 首标
				int j = i + le;//尾标
				if (le == 0) {
					dp[i][j] = true;
				}else if (le == 1) {
					dp[i][j] = s.charAt(i) == s.charAt(j);
				} else {
					dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
				}
				if (dp[i][j] && le + 1 > result.length()) {
					result = s.substring(i, j + 1);
				}
			}
		}
		return result;
	}

	/**
	 * 中心扩展
	 * 执行用时：32 ms, 在所有 Java 提交中击败了85.18%的用户
	 * 内存消耗：38.7 MB, 在所有 Java 提交中击败了67.39%的用户
	 * @param s
	 * @return
	 */
	public String longABA1(String s) {
		if (s == null || s.length() ==0) {
			return "";
		}
		int start = 0,end = 0;
		for(int i = 0 ; i < s.length(); i++) {
			int len1 = expand1(s, i, i);
			int len2 = expand1(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}
	public int expand1(String s, int start, int end) {
		while(start >= 0 && end <s.length() && s.charAt(start) == s.charAt(end)) {
			--start;
			++end;
		}
		return end - start - 1;
	}
	
	/**
	 * Manacher算法
	 * @param s
	 * @return
	 */
	public String longABA2(String s) {
		return "";
		}
	public int expand2(String s, int start, int end) {
		return 0;
	}
	public static void main(String[] args) {
		String longABA = new Test03().longABA01("ac");
		System.out.println(longABA);
	}
	
	/**
	 * 执行用时：329 ms, 在所有 Java 提交中击败了19.65%的用户
	 * 内存消耗：44.5 MB, 在所有 Java 提交中击败了9.32%的用户
	 * @param s
	 * @return
	 */
	public static String dynamic0(String s) {
		int len = s.length();
		boolean[][] dp = new boolean[len][len];
		String result = "";
		for(int count = 1; count <= len; count++) {
			for(int idx = 0; idx + count <= len; idx++) {
				int countIdx = count - 1;
				if (count == 1) {
					dp[countIdx][idx] = true;
				}else if (count == 2) {
					dp[countIdx][idx] = s.charAt(idx) == s.charAt(idx + count - 1);
				} else {
					dp[countIdx][idx] = (s.charAt(idx) == s.charAt(idx + count - 1)) 
							&& dp[countIdx - 2][idx + 1];
				}
				if (dp[countIdx][idx] && count > result.length()) {
					result = s.substring(idx, idx + count);
				}
			}
		}
		return result;
	}
	
}
