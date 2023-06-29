package ca.sheridancollege.project;

/**
 * @author Group7: Jaesung Lim, Ly Trong Nhan, Mykyta Varnikov
 * @date Jun 27, 2023
 */

public class PokerCard extends Card {

  private String rank;
  private String suit;

  public PokerCard(String rank, String suit) {
    super(rank, suit);
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

