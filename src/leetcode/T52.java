package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * @author just4liz
 *
 */
public class T52 {
	/**
	 * 执行用时：4 ms, 在所有 Java 提交中击败了88.93%的用户
	 * 内存消耗：38.5 MB, 在所有 Java 提交中击败了98.82%的用户
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		if (nums.length < 4) {
			return ret;
		}
		Arrays.sort(nums);
		int len = nums.length;
		for(int i1 = 0; i1 < len - 3; i1++) {
			if (i1 >0 && nums[i1] == nums[i1 -1]) {
				continue;
			}
			if (nums[i1] + nums[i1 + 1] + nums[i1 + 2] + nums[i1 + 3] > target) {
				break;
			}
			if (nums[i1] + nums[len- 1] + nums[len - 2] + nums[len - 3] < target) {
				continue;
			}
			for (int i2 = i1 + 1; i2 < len - 2; i2++) {
				if (i2 > i1 + 1 && nums[i2] == nums[i2 - 1]) {
					continue;
				}
				if (nums[i1] + nums[i2] + nums[i2 + 1] + nums[i2 + 2] > target) {
					break;
				}
				if (nums[i1] + nums[i2] + nums[len- 1] + nums[len - 2] < target) {
					continue;
				}
				int left = i2 + 1,right = len - 1;
				while(left < right) {
					int sum = nums[i1] + nums[i2] + nums[left] + nums[right];
					if (sum == target) {
						ret.add(Arrays.asList(nums[i1], nums[i2], nums[left], nums[right]));
						while(left < right && nums[left] == nums[left + 1]) {
							left++;
						}
						while (left < right && nums[right] == nums[right - 1]) {
							right--;
						}
						right--;
					}
					if (sum > target) {
						right--;
					}else {
						left++;
					}
				}
			}
		}
		return ret;
    }
	
	/**
	 * 递归版求n数之和
	 * 执行用时：5 ms, 在所有 Java 提交中击败了73.23%的用户
	 * 内存消耗：38.6 MB, 在所有 Java 提交中击败了97.51%的用户
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum0(int[] nums, int target) {
        Arrays.sort(nums);
        return nSumTarget(nums,4,0,target);
    }
    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
       int len=nums.length-n+1;
       List<List<Integer>> ans=new ArrayList<>();
       if(n>2){
           for(int i=start;i<len;i++){
                if(i>start&&nums[i-1]==nums[i]){
                    continue;
                }
                if(isBigValue(nums,n,i,target)){
                    break;
                }
                if(isSmallValue(nums,n,i,target)){
                    continue;
                }
                List<List<Integer>> ans2=nSumTarget(nums,n-1,i+1,target-nums[i]);
                for (int j = 0; j < ans2.size(); j++) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.addAll(ans2.get(j));
                    ans.add(temp);
                }
            }
       }else{
           int l=start,r=nums.length-1;
           while(l<r){
               int sum=nums[l]+nums[r];
               if(sum==target){
                   ans.add(Arrays.asList(nums[l],nums[r]));
                   while(l<r&&nums[l]==nums[l+1])l++;
                   while(l<r&&nums[r]==nums[r-1])r--;
                   l++;
                   r--;
               }else if(sum>target){
                   r--;
               }else{
                   l++;
               }
           }
       }
       return ans;
    }
    public boolean isBigValue(int[] nums,int n,int index,int target){
        int sum=0;
        while(n-->0){
            sum+=nums[index+n];
        }
        return sum>target;
    }
    public boolean isSmallValue(int[] nums,int n,int index,int target){
        int sum=0;
        while(--n>0){
            sum+=nums[nums.length-n];
        }
        return sum+nums[index]<target;
    }
	
	public static void main(String[] args) {
		int[] input = new int[] {-3,-2,-1,0,0,1,2,3};
		System.out.println(new T52().fourSum(input, 0));
	}
}
