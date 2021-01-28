package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import leetcode.Test01.ListNode;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * @author just4liz
 *
 */
public class T53 {
	/**
	 * 290ms
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		ListNode ret = null;
		int len = lists.length;
		int procs = len;
		List<Integer> sortList = new ArrayList<Integer>();
		while(procs > 0) {
			int minVal = Integer.MAX_VALUE;
			int minValIdx = 0;
			for(int i = 0; i < len; i++) {
				if (lists[i] != null && lists[i].val <= minVal) {
					minVal = lists[i].val;
					minValIdx = i;
				}
			}
			ListNode minValNode = lists[minValIdx];
			if (minValNode!= null) {
				sortList.add(minValNode.val);
				lists[minValIdx] = minValNode.next;				
				if (minValNode.next == null) {
					procs--;
				}
			}else {
				procs--;
			}
		}
		if (!sortList.isEmpty()) {
			ret = new ListNode(sortList.get(0));
			ListNode temp = ret;
			int i = 1;
			while(i < sortList.size()) {
				temp.next = new ListNode(sortList.get(i));
				temp = temp.next;
				i++;
			}
		}
		return ret;
    }
	
	/**
	 * 执行用时：9 ms, 在所有 Java 提交中击败了34.85%的用户
	 * 内存消耗：40.1 MB, 在所有 Java 提交中击败了88.55%的用户
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists0(ListNode[] lists) {
	       ListNode ret = null;
			int len = lists.length;
			List<Integer> sortList = new ArrayList<Integer>();
			for(int i = 0; i < len; i++) {
				ListNode temp0 = lists[i];
				while(temp0 != null) {
					sortList.add(temp0.val);
					temp0 = temp0.next;
				}
			}
			sortList.sort((a,b)->a.compareTo(b));
			if (!sortList.isEmpty()) {
				ret = new ListNode(sortList.get(0));
				ListNode temp = ret;
				int i = 1;
				while(i < sortList.size()) {
					temp.next = new ListNode(sortList.get(i));
					temp = temp.next;
					i++;
				}
			}
			return ret;
	    }
	
	/**
	 * 优先队列法
	 * 执行用时：5 ms, 在所有 Java 提交中击败了65.53%的用户
	 * 内存消耗：40.2 MB, 在所有 Java 提交中击败了85.61%的用户
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists1(ListNode[] lists) {
		Queue<ListNode> que = new PriorityQueue<>(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});
		for(ListNode node : lists)
			if (node != null) que.offer(node);
		
		ListNode head = new ListNode();
		ListNode tail = head;
		while (!que.isEmpty()) {
			ListNode temp0 = que.poll();
			tail.next = new ListNode(temp0.val);
			tail = tail.next;
			if (temp0.next != null) {
				que.offer(temp0.next);
			}
		}
		return head.next;
	}
	
	/**======================官方start==============================*/
	class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();
    /**
     * 执行用时：7 ms, 在所有 Java 提交中击败了43.00%的用户
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了89.42%的用户
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }
	/**======================官方end==============================*/
	
	public static void main(String[] args) {
		ListNode[] input = new ListNode[] {new ListNode(Arrays.asList(1,4,5)),
				new ListNode(Arrays.asList(1,3,4)),
				new ListNode(Arrays.asList(2,6))};
		ListNode[] input1 = new ListNode[0];
		System.out.println(new T53().mergeKLists1(input));
	}
}
