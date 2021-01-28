package sort.quick;

import static util.SortUtil.*;

import edu.princeton.cs.algs4.StdRandom;
import util.SortUtil;

public class Quick {
	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a,0,a.length-1);
	}
	public static void sort(Comparable[] a,int lo,int hi) {
		if(hi<=lo) return;
		int j=devide(a,lo,hi);
		sort(a,lo,j-1);//?j-1
		sort(a,j+1,hi);
	}
	
	public static int devide(Comparable[] a,int lo,int hi) {
		int i=lo,j=hi+1;//?hi+1
		Comparable va = a[lo];
		
		while(true) {
			while(less(a[++i],va))
				if(i==hi)
					break;
			while(less(va,a[--j]))
				if(j==lo)//?==
					break;
			if(i>=j)//?>=
				break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
	}
	public static void main(String[] args) {
		Double[] arr = SortUtil.dou;
		sort(SortUtil.dou);
		for(double a:SortUtil.dou) {
			System.out.println(a);
		}
	}
}
