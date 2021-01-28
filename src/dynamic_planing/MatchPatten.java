package dynamic_planing;

/**
 * 通用字符串匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

	'?' 可以匹配任何单个字符。
	'*' 可以匹配任意字符串（包括空字符串）。
	两个字符串完全匹配才算匹配成功。
	
	说明：
	s 可能为空，且只包含从 a-z 的小写字母。
	p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。

 * @author just4liz
 *
 */
public class MatchPatten {
	boolean match = false;
	public boolean isMatch(String s, String p) {
		traceBack(s,p,0,0);
		return match;
    }
	
	/**
	 * 下面是传统的回溯算法；
	 * 优化方向：
	 * 考虑到此例需要大量穷举状态，可以通过动态规划对状态进行缓存，
	 * 然后进行状态间的推导：
	 * 1）两个字符串之前的字串都可以完全匹配
	 * 2）当前字符可以匹配
	 * invoke: traceBack(s,p,0,0)
	 * @param s
	 * @param p
	 * @param is
	 * @param ip
	 */
	public void traceBack(String s, String p, int is, int ip) {
		if (ip >= p.length()) {
			if (ip == p.length() && is == s.length()) {
				match = true;
				return;
			}
			return;	
		}
		if (is >= s.length() && ip >= p.length() && is != 0) {
			return;
		}
		if (p.charAt(ip) == '*') {
			if (ip + 1 <= p.length()) {
				traceBack(s, p, is, ip + 1);//匹配0个
			}
			if (is + 1 <= s.length()) {
				traceBack(s, p, is + 1, ip);//匹配多个
			}
			if (ip + 1 <= p.length() && is + 1 <= s.length()) {
				traceBack(s, p, is + 1, ip + 1);//匹配1个				
			}
		}else if ((is < s.length() && ip < p.length()) && (s.charAt(is) == p.charAt(ip) || p.charAt(ip) == '?')) {
			traceBack(s, p, is + 1, ip + 1);
		}
	}
	
	/**
	 * 动态规划方案
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean dynamic0(String s, String p) {
		if (p.length() == 0) {
			if (s.length() == 0) {
				return true;
			}
			return false;
		}
		//状态转移表
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		//初始化状态
		dp[0][0] = true;
		for(int ip = 1; ip <= p.length(); ip++) {
			if(p.charAt(ip - 1) == '*') {
				dp[0][ip] = true;
			}else {
				break;
			}
		}
		// 遍历顺序也是一个问题！！！！！
		for(int is = 1; is <= s.length(); is++) {
			for(int ip = 1; ip <= p.length(); ip++) {
				if (p.charAt(ip - 1) == '*') {// 当前状态从左上，左，上 三个格子转换而来
					dp[is][ip] = dp[is][ip - 1] || dp[is - 1][ip] || dp[is - 1][ip - 1];
				}else if (p.charAt(ip - 1)== s.charAt(is - 1) || p.charAt(ip - 1)== '?') {//直接取左上格式的状态
					dp[is][ip] = dp[is - 1][ip - 1];
				}
			}
		}
		Util.printArr2(dp);
		return dp[s.length()][p.length()];
	}
	
	public boolean dynamic(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j] || dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        Util.printArr2(dp);
        return dp[m][n];
    }
	
	/**
	 * 贪心算法实现:
	 * 找出限制值和期望值：
	 * '*':s 和 p下标同时增加，直到下一个字符相同且不为通配符
	 * '?':下标同时 +1,判断下一个字符
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean greedy0(String s, String p) {
		int slen = s.length();
		int plen = p.length();
		int is = 0;
		int ip = 0;
		if (p.charAt(ip) == '*') {
			while(ip < plen
					&& p.charAt(ip) == '*') {//移动 p 指针
				++ip;
			}
			if (ip < plen) { // 移动 s 指针
				if(slen - is > plen -ip) {//s 的剩余字符更多
					while(is < slen &&
							s.charAt(is) != p.charAt(ip)) {
						
					}					
				}
			}
		}
		return false;
	}

	
	public static void main(String[] args) {
		/**
		 * "zacabz"
"*a?b*"
		 */
		String s = "zacabz";
		String p = "*a?b*";
		MatchPatten match = new MatchPatten();
		boolean match2 = match.dynamic0(s, p);
		match.dynamic(s, p);
		System.out.println(match2);
	}
}
