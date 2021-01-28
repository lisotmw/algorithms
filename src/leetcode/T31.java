package leetcode;

/**
 * 整数转罗马数字
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * @author just4liz
 *
 */
public class T31 {

	/**
	 * 执行用时：4 ms, 在所有 Java 提交中击败了99.97%%的用户
	 * 内存消耗：38 MB, 在所有 Java 提交中击败了94.92%的用户
	 * @param num
	 * @return
	 */
	public String int2Roman(int num) {
		char[] roman = new char[] {'I','V','X','L','C','D','M'};
		int[] digital = new int[] {1,5,10,50,100,500,1000};
		int idx = digital.length - 1;
		StringBuilder ret = new StringBuilder();
		while(idx >= 0 && num != 0) {
			int a = num / digital[idx];
			int b = num % digital[idx];
			if (a > 0 && !(a==1 && checkHighNum(num, 9))) {
				if (a == 4) {
					ret.append(roman[idx]).append(roman[idx + 1]);
				}else if (a == 9) {
					ret.append(roman[idx]).append(roman[idx + 2]);
				}else {
					for(int i = 0; i < a; i++)
						ret.append(roman[idx]);
				}
				num = b;
			}
			idx--;
		}
		return ret.toString();
	}
	
	public boolean checkHighNum(int tar,int highNum) {
		do {
			if (tar < 10) {
				return tar % 10 == highNum;
			}
			tar = tar / 10;
		} while (tar > 0);
		return false;
	}
	
	/**
	 * 执行用时：5 ms, 在所有 Java 提交中击败了93.19%的用户
	 * 内存消耗：38.1 MB, 在所有 Java 提交中击败了93.16%的用户 
	 * @param num
	 * @return
	 */
	public String intToRoman(int num) {
		int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};    
		String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

	    StringBuilder sb = new StringBuilder();
	    // Loop through each symbol, stopping if num becomes 0.
	    for (int i = 0; i < values.length && num >= 0; i++) {
	        // Repeat while the current symbol still fits into num.
	        while (values[i] <= num) {
	            num -= values[i];
	            sb.append(symbols[i]);
	        }
	    }
	    return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new T31().int2Roman(99));
	}
}
