/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 * 
 * @author Group7: Jaesung Lim, Ly Trong Nhan, Mykyta Varnikov
 * @date Jun 27, 2023
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {

  //The group of cards, stored in an ArrayList
  private ArrayList<Card> cards;
  private int size; //the size of the grouping

  public GroupOfCards(int size) {
    this.size = size;
    this.cards = new ArrayList<>();
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
