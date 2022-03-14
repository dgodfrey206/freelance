package treeAssign;

import java.util.Iterator;



import java.util.List;

import treeAssign.GenericTree;

/**
* <h1>The Driver class for the GenericArrayList</h1>
*
* @author sarwars1
* @version 1.0
* @since   2021-04-14 
*/
public class Driver {

  public static void main(String[] args) {
    Driver d = new Driver();
    d.run();
  }

	public void run() {
		GenericTree<Integer> gTree = new GenericTree<Integer>();
		intTreeProcessing(gTree);
	}

	@SuppressWarnings("unchecked")
	public <T> void intTreeProcessing(GenericTree<T> gTree) {

		System.out.printf("Tree Length = %d", gTree.getSize());
	
		gTree.add(10);
		gTree.add(8);
		gTree.add(14);
		gTree.add(11);
		gTree.add(15);
		gTree.add(20);
		gTree.add(20);
		gTree.add(5);
		gTree.add(9);
		gTree.add(7);
		gTree.add(35);
		gTree.add(18);
		gTree.add(3);
		gTree.add(17);
		
		System.out.println();
		System.out.printf("Tree Length = %d", gTree.getSize());
		System.out.println();
		System.out.println("\n\nPrinting Values");
		
		Iterator<T> iterator = gTree.iterator();
		while(iterator.hasNext()) {
			T data = iterator.next();
			System.out.println(data);
		}
		
		System.out.println("\n\nPrinting Values again");
		iterator = gTree.iterator();
		while(iterator.hasNext()) {
			T data = iterator.next();
			System.out.println(data);
		}

		System.out.println("\n\nPrinting Values once again");
		
		Function<T> func = (x)->System.out.println(x.getData());
		gTree.forEach(func);

    Node<Integer> n = gTree.Search(18);
	}
	
}
