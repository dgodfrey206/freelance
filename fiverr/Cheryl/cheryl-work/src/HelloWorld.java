
public class HelloWorld {

	public static void main(String[] args) {
		int n = 100000000;
		try {
			while (true) {
				int[] test = new int[n];
				n += 10000000;
			}
		} catch(OutOfMemoryError e) {
			System.out.print("Killed. memory = " + (4 * n) + " bytes.");
		}
	}

}
