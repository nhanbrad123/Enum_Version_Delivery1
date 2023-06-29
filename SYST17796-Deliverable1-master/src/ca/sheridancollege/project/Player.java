/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 * 
 * @author Group7: Jaesung Lim, Ly Trong Nhan, Mykyta Varnikov
 * @date Jun 27, 2023
 */
package ca.sheridancollege.project;

import java.util.*;

public class Player {

  private final String name;
  private List<Card> hand;

  public Player(String name) {
    this.name = name;
    this.hand = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public List<Card> getHand() {
    return hand;
  }

  public void setHand(List<Card> hand) {
    this.hand = hand;
  }

  public void addCardToHand(Card card) {
    hand.add(card);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Player ").append(name).append(":\n");
    for (int i = 0; i < hand.size(); i++) {
      sb
        .append("[Card ")
        .append(i + 1)
        .append("]: ")
        .append(hand.get(i))
        .append("\n");
    }
    return sb.toString();
  }
}

