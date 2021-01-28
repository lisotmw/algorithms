package leetcode.greedy;
/**
 * 贪心算法实例
 * 区间覆盖问题，给定一组区间，找出其中两两不相交区间
 * @author just4liz
 *
 */

import java.util.ArrayList;
import java.util.List;

public class T61 {
	public List<List<Integer>> findNuOverlapStage(List<List<Integer>> allStage){
		allStage.sort((a,b)->a.get(0).compareTo(b.get(0)));
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		for(int i = 0; i < allStage.size(); i++) {
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println((int)('5' - '0'));
	}
}
