package leetcode;

import java.util.Arrays;


/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与
 *target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * @author just4liz
 *
 */
public class T41 {
	/**
	 * 第一版
	 * 执行用时：7 ms, 在所有 Java 提交中击败了44.86%的用户
	 * 内存消耗：38.2 MB, 在所有 Java 提交中击败了83.60%的用户
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest(int[] nums, int target) {
		int abs = Integer.MAX_VALUE;
		int sum = 0;
		int len = nums.length;
		Arrays.sort(nums);
		for (int i = 0; i < len; ++i) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int tar2 = target - nums[i];
			int third = len - 1;// 这行代码，下移一行，执行就会超时！！！！！！
			for (int j = i + 1; j < len; ++j) {
				if (j > i + 1 && nums[j] == nums[j - 1] || third == j) {
					continue;
				}
				int abs0 = Math.abs(target - (nums[i]+ nums[j] + nums[third]));
				if (abs0 < abs) {
					abs = abs0;
                	sum = nums[i]+ nums[j] + nums[third];
				}
                while (third > j && nums[j] + nums[third] > tar2) {
                	--third;
				}
                int abs1 = Integer.MAX_VALUE;
                if (third > j) {
                	abs1 = Math.abs(target - (nums[i]+ nums[j] + nums[third]));
				}
                int abs2 = Integer.MAX_VALUE;
                if (third < len - 2) {
                	abs2 = Math.abs(target - (nums[i] + nums[j] + nums[third + 1]));					
				}
                if (abs1 <= abs2 && abs1 < abs) {
                	abs = abs1;
                	sum = nums[i]+ nums[j] + nums[third];
				}else if (abs2 < abs) {
					abs = abs2;
					sum = nums[i] + nums[j] + nums[third + 1];
				}
                if (abs == 0) {
					return sum;
				}
			}
		}
		return sum;
    }
	
	public static void main(String[] args) {
		T41 t41 = new T41();
		int[] input = new int[] {-1,2,1,-4};
		System.out.println(t41.threeSumClosest(input, 1));
	}
}
