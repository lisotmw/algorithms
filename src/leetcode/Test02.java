package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 给定一个字符串(a,b,c,a)，找出 不含重复子字符串 的 最长长度(3)
 * @author just4liz
 *
 */
public class Test02 {
	public int calcMaxSonLen(String str) {
		int len = str.length();
		int maxLen  = 0;
		Set<Character> cacheSet = new HashSet<>();
		for(int i = 0; i < len; i++) {
			for(int j = i; j <len ;j++) {
				if (!cacheSet.contains(str.charAt(j))) {
					cacheSet.add(str.charAt(j));
					if (cacheSet.size() > maxLen) {
						maxLen = cacheSet.size();
					}
				}else {
					cacheSet.clear();
					break;
				}
			}
		}
		return maxLen;
	}
	
	/**
	 * 滑动窗口解法
	 * @param str
	 * @return
	 */
	public int calcMaxSonLen0(String str) {
		Set<Character> cacheSet = new HashSet<>();
		int rk = -1, maxLen = 0;
		int len = str.length();
		
		for(int i = 0; i < len; i++) {
			if (i > 0) {
				cacheSet.remove(str.charAt(i - 1));
			}
			
			while(rk + 1 < len && !cacheSet.contains(str.charAt(rk + 1))) {
				cacheSet.add(str.charAt(rk + 1));
				rk++;
			}
			maxLen = Math.max(maxLen, rk + 1 - i);
		}
		return maxLen;
	}
	
	public int calcMaxSonLen1(String s) {
		 Set<Character> set = new HashSet<>();
	        int left = 0, right = 0, res = 0;
	        while(right < s.length()){
	            char c = s.charAt(right++);
	            //存在重复的字符，则移动左指针，直到滑动窗口中不含有该字符
	            while(set.contains(c)){
	                set.remove(s.charAt(left++));
	            }
	            set.add(c);
	            res = Math.max(res, right-left);
	        }
	        return res;
	}
	
	@Test
	public void test0() {
		assertEquals(19, new Test02().calcMaxSonLen("sssssqwertyuiopasdfghjkl"));
	}
	
	public static void main(String[] args) {
		String string = "sssssqwertyuiopasdfghjkl";
		
		System.out.println(new Test02().calcMaxSonLen0(string));
	}
}
