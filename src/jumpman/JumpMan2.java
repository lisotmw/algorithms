package jumpman;

public class JumpMan2 {
	static int decreaseTT(int n) {
		if(n==0) {
			return 0;
		}
		if(n==1) {
			return 1;
		}
		if(n==2) {
			return 2;
		}
		int t;
		return t=decreaseTT(n-1)+decreaseTT(n-2);
	}
	public static void main(String[] args) {
		System.out.println(decreaseTT(10));
	}
}
