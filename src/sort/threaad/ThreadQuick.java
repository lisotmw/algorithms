package sort.threaad;

import static util.SortUtil.exch;
import static util.SortUtil.less;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import util.SortUtil;

public class ThreadQuick {
	static class MyTask extends RecursiveAction{

		int start,end;
		Comparable[] a;
		MyTask(int start,int end,Comparable[] a){
			this.start = start;
			this.end = end;
			this.a = a;
		}
		@Override
		protected void compute() {
			if(end-start<=1) {
				if(less(a[end],a[start]))
					exch(a,start,end);
			}else {
				int middle = start + (end-start)/2;
				MyTask task1 = new MyTask(start,middle,a);
				MyTask task2 = new MyTask(middle,end,a);
				task1.fork();
				task2.fork();
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		Comparable[] a = SortUtil.dou;
		ForkJoinPool fjp = new ForkJoinPool();
		MyTask task = new MyTask(0,a.length,a);
		fjp.execute(task);
		
		System.in.read();
		
		for(Comparable s:a) {
			System.out.println(s);
		}
	}
}
