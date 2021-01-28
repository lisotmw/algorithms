package leetcode;

/**
 * 
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 *
 */
public class T51 {
	/**
	 * 执行用时：3 ms, 在所有 Java 提交中击败了76.66%的用户
	 * 内存消耗：39.9 MB, 在所有 Java 提交中击败了51.56%的用户
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		int sumLen = len1 + len2;
		boolean isSingle = sumLen % 2 != 0;
		int idx1 = 0;
		int idx2 =0;
		int midleSum = 0;//表示中位数之和
		int exeCnt = 0;// 添加了几次中位数
		int tar = 0;//每次遍历取到的目标值
		for(int i = 0; i < sumLen; i++) {
			if (isSingle ? exeCnt == 1 : exeCnt == 2) {
				break;
			}
			if(idx1 == len1 && idx2 <len2){//nums1已经遍历完。nums2没有
				tar = nums2[idx2];
				idx2++;
			}else if(idx2 == len2 && idx1 < len1){//nums2已经遍历完。nums1没有
				tar = nums1[idx1];
				idx1++;
			}else if(idx1 < len1 && nums1[idx1] <= nums2[idx2]) {//nums1 i 下标对应的数小于nums2
				tar = nums1[idx1];
				idx1++;
			}else if(idx2 < len2 && nums1[idx1] >= nums2[idx2]){//nums2 i 下标对应的数小于nums1
				tar = nums2[idx2];
				idx2++;
			}
			if (isSingle) {
				if (i == sumLen / 2) {
					midleSum += tar;
					exeCnt++;
				}
			}else {
				if (i == sumLen / 2 - 1 || i == sumLen / 2) {
					midleSum += tar;
					exeCnt++;
				}
			}
		}
		return midleSum / (isSingle ? 1.0d : 2.0d);
    }
	
	/**
	 * 官方
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays0(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }
	
	public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            
            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

	
	
	public static void main(String[] args) {
		int[] nums1 = new int[] {1};
		int[] nums2 = new int[] {2,3,4};
		System.out.println(new T51().findMedianSortedArrays(nums1, nums2));
	}
}
