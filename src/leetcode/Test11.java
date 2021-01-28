package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 例如，输入"LEETCODEISHIRING",行数为4，则输出"LDREOEIIECIHNTSG"
 * 	L     D     R
	E   O E   I I
	E C   I H   N
	T     S     G
 * @author just4liz
 *
 */
public class Test11 {
	
	/**
	 * 暴力解法
	 * @param s
	 * @param line
	 * @return
	 */
	public String convert(String s, int line) {
		if (s == null || s.length() == 0 || line == 0) {
			return "";
		}
		if (line == 1) {
			return s;
		}
		//将"Z"分成可循环的序列，以计算二维数组的列数
		int loopLen = line + line - 2;
		int a1 = s.length() / loopLen;
		int a2 = s.length() % loopLen;
		// 二维数组列数
		int arrayColumn = a1 * (line - 1) + (a2 <= line ? 1 : (a2 - line + 1));
		
		char[][] dp = new char[line][arrayColumn];
		for(int i = 0; i < s.length(); i++) {
			int procs = i + 1;
			int b1 = procs / loopLen;
			int b2 = procs % loopLen;
			int column1 = line - 1;
			int x, y;
			if (b2 != 0 && b2 <= line) {
				y = b1 * column1;
				x = b2 - 1;
			} else {
				boolean b = b2 == 0;
				b2 = b? loopLen : b2;
				b1 = b ? b1 - 1 : b1;
				y = b1 * column1 + b2 - line;
				x = line - 1 - b2 % line;
			}
			dp[x][y] = s.charAt(i);
		}
		String result = "";
		String graphic = "";
		//'/uoooo'
		for(int r = 0; r < line; r ++) {
			for(int c = 0; c < arrayColumn; c ++) {
				String s1 = " ";
				if (dp[r][c] != (char)0) {
					result += String.valueOf(dp[r][c]);
					s1 = String.valueOf(dp[r][c]);
				}
				graphic += s1;
			}
			graphic += "\n";
		}
		System.out.println(graphic);
		return result;
	}
	
	/**
	 * 	a   s   r 
		b  ae  fe 
		w f t f t 
		ea  ad  ew
		r   g   q 

		asrbaefewftfteaadewrgq
	 * @param s
	 * @param line
	 * @return
	 */
	public String convert02(String s,int line) {
		if (line == 1) {
			return s;
		}
		//将"Z"分成可循环的序列，以计算二维数组的列数
		int loopLen = line + line - 2;
		/**
		 * 
		 * int multi = tl / loopLen;
		 * int reminder = tl % loopLen;
		 * 
		 * int multi1 = i / loopLen;
		 * int reminder1 = i % loopLen;
		 * 
		 * int idx;
		 * if(reminder1 == 1) idx = multi1;
		 * if(reminder1 == line) idx = multi
		 *  + (line -2)*multi 
		 *  + reminder <= 4 ? reminder : reminder - 1
		 *  + multi1
		 * else{
		 * 	idx = multi
		 *  + (multi1 -2)*multi 
		 * }
		 * 
		 * 
		 * 
		 */
		return "";
	}
	
	public String convert0(String s, int line) {
		if (s == null || s.length() == 0 || line == 0) {
			return "";
		}
		if (line == 1) {
			return s;
		}
		//将"Z"分成可循环的序列，以计算二维数组的列数
		int loopLen = line + line - 2;
		
		List<StringBuilder> sarr = new ArrayList<>(line);
		for(int i = 0; i < s.length(); i++) {
			int procs = i + 1;
			int b1 = procs / loopLen;
			int b2 = procs % loopLen;
			int x;
			if (b2 != 0 && b2 <= line) {
				x = b2 - 1;
			} else {
				boolean b = b2 == 0;
				b2 = b? loopLen : b2;
				b1 = b ? b1 - 1 : b1;
				x = line - 1 - b2 % line;
			}
			if (x > sarr.size() - 1) {
				sarr.add(new StringBuilder(""));
			}
			sarr.get(x) .append(String.valueOf(s.charAt(i)));
		}
		String result = "";
		//'/uoooo'
		for(int r = 0; r < sarr.size(); r ++) {
			result += sarr.get(r);
		}
		return result;
	}
	
	/**
	 * TODO best
	 * 执行用时：6 ms, 在所有 Java 提交中击败了79.47%的用户
	 *内存消耗：39.1 MB, 在所有 Java 提交中击败了75.23%的用户
	 * @param s
	 * @param line
	 * @return
	 */
	public String convert01(String s, int line) {
		if (line == 1) {
			return s;
		}
		int len = s.length();
		List<StringBuilder> sarr = new ArrayList<>();
		int size = Math.min(line, len);
		 for (int i = 0; i < size; i++)
			 sarr.add(new StringBuilder());
		int x = 0;
		boolean add = false;
		for(int i = 0; i < len; i++) {
			if (x == 0 || x == line - 1) {
				add = !add;
			}
			sarr.get(x) .append(s.charAt(i));
			x += add ? 1 : - 1;
		}
		StringBuilder ret = new StringBuilder();;
        for (StringBuilder row : sarr) ret.append(row);
        return ret.toString();
	}
	
	public String convert1(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
	
	public static void main(String[] args) {
		String input = "abwerafasetagdffreteqw";
		System.out.println(new Test11().convert(input, 5));
	}
}
