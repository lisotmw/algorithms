package jumpman;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class Node{
	private int v;
	private Node left;
	private Node right;
	
	public Node(int v) {
		this.v = v;
	}
	
	public void setLeft(Node n) {
		this.left = n;
	}
	
	public void setRight(Node n) {
		this.right = n;
	}
	
	public int getV() {
		return v;
	}
	
	public Node getL() {
		return left;
	}
	
	public Node getR() {
		return right;
	}
	
	public boolean hasSon() {
		return right!=null||left!=null;
	}
}

class SumLeaf{
	private static AtomicInteger id=new AtomicInteger(0);
	public SumLeaf() {
		id.getAndIncrement();
	}
	public static AtomicInteger getId() {
		return id;
	}
}

public class JumpMan {
	
	static void treeGe(Node tree) {
		Random r = new Random();
		tree.setLeft(new Node(r.nextInt(2)+1));
		tree.setRight(new Node(r.nextInt(2)+1));
	}
	
	static void sumTree(Node posi,int sum) {
			treeGe(posi);
			if(posi.getL()!=null) {
				Node temp = posi.getL();
				int sum1 = sum + temp.getV();
				if(sum1>10) {
					posi.setLeft(null);
					return;
				}
				else if(sum1<10)
				    sumTree(posi.getL(),sum1);
				else if(sum1==10) {
					new SumLeaf();
					return;
				}
			}
			if(posi.getR()!=null) {
				Node temp = posi.getR();
				int sum2 = sum + temp.getV();
				if(sum2>10) {
					posi.setRight(null);
					return;
				}else if(sum2 <10)
				    sumTree(posi.getR(),sum2);
				else if(sum2==10) {
					new SumLeaf();
					return;
				}
			}
			return;
	}
	
	
	public static void main(String[] args) {
		Node tree = new Node(0);
		sumTree(tree,0);
		System.out.println(SumLeaf.getId());
//		while(tree.getR()!=null) {
//			tree = tree.getR();
//			System.out.print(tree.getV());
//		}
		System.out.println("***********");
//		while(tree.getL()!=null) {
//			tree = tree.getL();
//			System.out.print(tree.getV());
//		}
		
	}
}
