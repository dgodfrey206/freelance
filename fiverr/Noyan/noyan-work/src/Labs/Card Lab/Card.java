//file BasicCard.java
import javax.swing.ImageIcon;
public class Card 
{
  private int num;
  private String suit;
  private String face;
  private ImageIcon image;
  public Card() {}
  public Card(int n) {
    num = n%52;
    setCard();
  }
  
  public String getSuit(){ return suit;}
  public String getFace() { return face;}
  public ImageIcon getImage() { return image;}

  private void setCard() {     
    int q = num/13;
    int r = num % 13;
    switch(q) {
      case 0: { suit="spade"; break;}
      case 1: { suit="heart"; break;}
      case 2: { suit="diamond"; break;}
      case 3: { suit="club"; break;} }
    if (2 <= r && r <= 10) face = "" + r;
    else
    switch(r) {
	case 11: { face="J"; break;}
	case 12: { face="Q"; break;}
	case 0: { face="K"; break;}
	case 1: { face="A"; break;} 
    }
   String fn = "cards\\200px-Playing_card_" + suit + "_" + face + ".svg.png";
   image = new ImageIcon(fn);
  }

  public String toString(){
    return suit + " " + face;
  }
  
   
}
  
 
