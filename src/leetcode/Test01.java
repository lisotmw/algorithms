package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出两个逆序链表（listNode1，listNode2），返回两链表各位数字之和的新逆序链表（listNode3）
 * @author just4liz
 *
 */
public class Test01 {
	public static LinkedList<Integer> sumLinked(LinkedList<Integer> l1, LinkedList<Integer> l2){
		boolean carry = false;
		LinkedList< Integer> result = new LinkedList<>();
		int v1 = 0;
		int v2 = 0;
		while((!l1.isEmpty()  && l1 != null) || (!l2.isEmpty()&& l2 != null) || carry) {
			if (!l1 .isEmpty()) {
				v1 = l1.poll();
			}
			if (!l2.isEmpty()) {
				v2 = l2.poll();
			}
			int sum = v1 + v2 + (carry ? 1 : 0);
			result.add(sum % 10);
			if (sum >= 10) {
				carry = true;
			}else {
				carry = false;
			}
			v1 = 0;
			v2 = 0;
		}
		return result;
	}
	
	/**
	 * 普通内部类保有对外部类的引用，所以普通静态类不能独立存在，初始化的时候必须通过外部类的实例。
	 * @author just4liz
	 *
	 */
	public static class ListNode {
     int val;
     ListNode next;
     public ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     public ListNode(List<Integer> list){
    	 ListNode temp = this;
    	 int idx = 0;
    	 for(int val : list) {
    		 if (idx == 0) {
				this.val = val;
			}else {
				temp.next = new ListNode(val);
				temp = temp.next;
			}
    		idx++;
    	 }
     }
     @Override
    	public String toString() {
    	 StringBuilder sBuilder = new StringBuilder();
    	 ListNode temp = this;
    	 while (temp != null) {
			sBuilder.append(temp.val + ":");
			temp = temp.next;
		}
    		return sBuilder.toString();
    	}
	}
	
	/**
	 * 执行用时：2 ms, 在所有 Java 提交中击败了99.92%的用户
	 * 内存消耗：39.1 MB, 在所有 Java 提交中击败了22.48%的用户
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		boolean up = false;
		ListNode result = null;
		while((l1!= null) || (l2 != null) || up) {
			int v1 = 0,v2 = 0;
			if (l1 != null) {
				v1 = l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				v2 = l2.val;
				l2 = l2.next;
			}
			int sum1 = v1 + v2  + (up ? 1 : 0);
			int num = sum1 % 10;
			if (sum1 / 10 >= 1) {
				up = true;
			}else {
				up = false;
			}
			if (result == null) {
				result = new ListNode(num);
			}else {
				ListNode temp = result;
				while (temp.next != null) {
					temp = temp.next;
				}
				temp.next = new ListNode(num);
			}
		}
        return result;
    }
	
	public void test0() {
		ListNode l1 = new ListNode(Arrays.asList(9,9,9,4,9,7,9));
		ListNode l2 = new ListNode(Arrays.asList(9,9,4,9,8));
		System.out.println(addTwoNumbers(l1, l2).toString());
	}
	
	public static void main(String[] args) {
//		LinkedList<Integer> l1 = new LinkedList<>(Arrays.asList(2,3,5)), 
//				l2 = new LinkedList<>(Arrays.asList(2,3,6,7));
//		System.out.println(sumLinked(l1, l2));
		new Test01().test0();
		
	}
}
