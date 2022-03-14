import java.util.*;
public class CardHand {
   protected Card[] hand;
   private int num;
   private int size;
   public CardHand(int k) { 
	this.num = k; 
	hand = new Card[num]; 
	size=0; 
   }
   public boolean add(Card c) {
      for (int i=0; i<num; i++) {
         if (hand[i]== null) {hand[i]=c; size++; return true;}
      }
      return false;  
   }   
   
   public Card get(int k) {
	if (k < size) return hand[k];
	return null;
   }

   public int getSize() { return size;}

   public void sort() {
	Arrays.sort(hand);
   }

}