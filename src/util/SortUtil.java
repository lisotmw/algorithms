package util;

public class SortUtil {
	
	public static Double[] dou= {1.2,34.0,454.0,56.7,3.1415926,2147483647.0,1024.0,2048.512,735.34,87.0,51.0};
	
	public static boolean less(Comparable a,Comparable b) {
		return a.compareTo(b)<0;
	}
	
	public static void exch(Comparable[] a,int b,int c) {
		Comparable t = a[b];
		a[b]=a[c];
		a[c]=t;
	}
}
