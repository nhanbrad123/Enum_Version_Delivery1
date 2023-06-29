/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 * 
 * @author Group7: Jaesung Lim, Ly Trong Nhan, Mykyta Varnikov
 * @date Jun 27, 2023
 */
package ca.sheridancollege.project;

public class Card {

  public enum Suits
  {
      DIAMONDS,
      CLUBS,
      HEARTS,
      SPADES,
  }
  public enum Ranks{
      ACE,
      TWO, 
      THREE,
      FOUR,
      FIVE, 
      SIX,
      SEVEN, 
      EIGHT,
      NINE,
      TEN,
      JACK,
      QUEEN,
      KING,
  }
  
  private final Ranks rank;
  private final Suits suit;
  public Card(Ranks rank, Suits suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public Ranks getRank() {
    return rank;
  }

  public Suits getSuit() {
    return suit;
  }

  @Override
  public String toString() {
    return rank + " of " + suit;
  }
}
