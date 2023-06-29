package ca.sheridancollege.project;

/**
 * @author Group7: Jaesung Lim, Ly Trong Nhan, Mykyta Varnikov
 * @date Jun 27, 2023
 */

public class PokerCard extends Card {

  private Ranks rank;
  private Suits suit;

  public PokerCard(Ranks rank, Suits suit) {
    super(rank, suit);
    this.rank = rank;
    this.suit = suit;
  }

  @Override
  public Ranks getRank() {
    return rank;
  }

  @Override
  public Suits getSuit() {
    return suit;
  }

  @Override
  public String toString() {
    return rank + " of " + suit;
  }
}

