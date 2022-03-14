public class CardTesting {
   public static void main(String[] args) {
	Card c1 = new Card(16);
	Card c2 = new Card(68);
	boolean b = c1.equals(c2);
	if (b) {
	  System.out.println("card 1: " + c1.toString() );
	  System.out.println("card 2: " + c2.toString() );
	  System.out.println("same card!");
      }
	else
	  System.out.println("something is wrong!");
   }      
}