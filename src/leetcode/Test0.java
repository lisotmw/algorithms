package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 给定一个数组（inputArr），返回两个元素之和等于特定数字（target）的下标
 * @author just4liz
 *
 */
public class Test0 {
	public static int[] solution(int[] inputArr, int target) {
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>(inputArr.length);
		for(int i = 0; i < inputArr.length; i++) {
			if (cache.containsKey(target - inputArr[i])) {
				return new int[] {cache.get(target - inputArr[i]), i};
			}
			cache.put(inputArr[i], i);
		}
		return new int[0];
	}
	
	public static void main(String[] args) {
		int [] inputArr = new int[] {1,3,4,5,7,9};
		int [] result = solution(inputArr, 16);
		List<Integer> collect = Arrays.stream(result).boxed().collect(Collectors.toList());
		System.out.println(collect);
	}
}
