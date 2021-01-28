package leetcode;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * @author just4liz
 *
 */
public class Test21 {
	/**
	 * 执行用时：890 ms, 在所有 Java 提交中击败了5.02%的用户
	 * 内存消耗：40.1 MB, 在所有 Java 提交中击败了11.32%的用户
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int len = height.length;
		if (len < 2) {
			return 0;
		}
		int maxArea = 0;
		for(int i = 0; i < len; i++) {
			int b = i + 1;
			while (b <len) {
				maxArea = Math.max(maxArea, (b - i) * Math.min(height[i], height[b]));
				b++;
			}
		}
		return maxArea;
    }
	
	/**
	 * 执行用时：309 ms, 在所有 Java 提交中击败了25.16%的用户
	 * 内存消耗：40.3 MB, 在所有 Java 提交中击败了8.53%的用户
	 * @param height
	 * @return
	 */
	public int maxArea0(int[] height) {
		int len = height.length;
		if (len < 2) {
			return 0;
		}
		int maxHigh = 0;
		int maxArea = 0;
		for(int i = 0; i < len; i++) {
			if (height[i] > maxHigh) {
				maxHigh = height[i];
				int b = i + 1;
				while (b <len) {
					maxArea = Math.max(maxArea, (b - i) * Math.min(maxHigh, height[b]));
					b++;
				}
			}
		}
		return maxArea;
    }
	
	/**
	 * 双指针（没有引出java静态包）
	 * 执行用时：3 ms, 在所有 Java 提交中击败了92.73%的用户
	 * 内存消耗：39.8 MB, 在所有 Java 提交中击败了18.00%的用户
	 * @param height
	 * @return
	 */
	public int maxArea1(int[] height) {
		int maxArea = 0;
		int left = 0,right = height.length - 1;
		while(right > left) {
			int area = (height[left] < height[right]? height[left]: height[right]) * (right - left);
			if (area > maxArea) {
				maxArea = area;
			}
			if (height[left] < height[right]) {
				left++;
			}else {
				right--;
			}
		}
		return maxArea;
    }
	
	/**
	 * 双指针解法
	 * 执行用时：4 ms, 在所有 Java 提交中击败了68.10%的用户
	 * 内存消耗：40.2 MB, 在所有 Java 提交中击败了9.45%的用户
	 * @param height
	 * @return
	 */
	public int maxArea2(int[] height) {
		int maxArea = 0;
		int left = 0,right = height.length - 1;
		while(right > left) {
			boolean b = height[left] < height[right];
			int area = (b? height[left]: height[right]) * (right - left);
			maxArea = Math.max(maxArea, area);
			if (b) {
				left++;
			}else {
				right--;
			}
		}
		return maxArea;
    }
	
	public static void main(String[] args) {
		int[] input = new int[] {1,8,6,2,5,4,8,3,7};
		System.out.println(new Test21().maxArea0(input));
	}
}
