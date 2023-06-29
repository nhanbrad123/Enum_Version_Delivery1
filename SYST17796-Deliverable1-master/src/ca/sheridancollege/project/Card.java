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

  private final String rank;
  private final String suit;

  public Card(String rank, String suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public String getRank() {
    return rank;
  }

  public String getSuit() {
    return suit;
  }

  @Override
  public String toString() {
    return rank + " of " + suit;
  }
}
