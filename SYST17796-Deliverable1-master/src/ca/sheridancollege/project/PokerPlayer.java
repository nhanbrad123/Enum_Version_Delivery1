package ca.sheridancollege.project;

/**
 * @author Group7: Jaesung Lim, Ly Trong Nhan, Mykyta Varnikov
 * @date Jun 27, 2023
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerPlayer extends Player {

  private ArrayList<Card> hand;

  public PokerPlayer(String name) {
    super(name);
    hand = new ArrayList<>();
  }

  public void addCard(Card card) {
    hand.add(card);
  }

  public void removeCard(int index) {
    hand.remove(index);
  }

  public void changeCards(String indices, GroupOfCards deck) {
    String[] indexArray = indices.split("[,\\s]+");
    for (String indexStr : indexArray) {
      int index = Integer.parseInt(indexStr) - 1;
      if (index >= 0 && index < hand.size()) {
        Card newCard = deck.getCards().remove(0);
        deck.setSize(deck.getSize() + 1);
        Card discardedCard = hand.set(index, newCard);
        deck.getCards().add(discardedCard);
      }
    }
  }

  public void showHand() {
    for (int i = 0; i < hand.size(); i++) {
      System.out.println("[Card " + (i + 1) + "]: " + hand.get(i));
    }
  }

  public void showHandWithIndices() {
    for (int i = 0; i < hand.size(); i++) {
      System.out.println("[Card " + (i + 1) + "]: " + hand.get(i));
    }
  }

  public HandRanking evaluateHand() {
    if (hasRoyalFlush()) {
      return HandRanking.ROYAL_FLUSH;
    } else if (hasStraightFlush()) {
      return HandRanking.STRAIGHT_FLUSH;
    } else if (hasFourOfAKind()) {
      return HandRanking.FOUR_OF_A_KIND;
    } else if (hasFullHouse()) {
      return HandRanking.FULL_HOUSE;
    } else if (hasFlush()) {
      return HandRanking.FLUSH;
    } else if (hasStraight()) {
      return HandRanking.STRAIGHT;
    } else if (hasThreeOfAKind()) {
      return HandRanking.THREE_OF_A_KIND;
    } else if (hasTwoPair()) {
      return HandRanking.TWO_PAIR;
    } else if (hasOnePair()) {
      return HandRanking.ONE_PAIR;
    } else {
      return HandRanking.HIGH_CARD;
    }
  }

  private boolean hasRoyalFlush() {
    Map<Card.Ranks, Integer> rankCounts = getRankCounts();
    boolean hasAce = rankCounts.containsKey("Ace");
    boolean hasKing = rankCounts.containsKey("King");
    boolean hasQueen = rankCounts.containsKey("Queen");
    boolean hasJack = rankCounts.containsKey("Jack");
    boolean hasTen = rankCounts.containsKey("10");
    boolean hasSameSuit = hasSameSuit();

    return hasAce && hasKing && hasQueen && hasJack && hasTen && hasSameSuit;
  }

  private boolean hasStraightFlush() {
    return hasStraight() && hasSameSuit();
  }

  private boolean hasFourOfAKind() {
    Map<Card.Ranks, Integer> rankCounts = getRankCounts();
    for (int count : rankCounts.values()) {
      if (count == 4) {
        return true;
      }
    }
    return false;
  }

  private boolean hasFullHouse() {
    Map<Card.Ranks, Integer> rankCounts = getRankCounts();
    boolean hasThreeOfAKind = false;
    boolean hasPair = false;
    for (int count : rankCounts.values()) {
      if (count == 3) {
        hasThreeOfAKind = true;
      } else if (count == 2) {
        hasPair = true;
      }
    }
    return hasThreeOfAKind && hasPair;
  }

  private boolean hasFlush() {
    return hasSameSuit();
  }

  private boolean hasStraight() {
    List<Integer> rankValues = getSortedRankValues();
    for (int i = 0; i < rankValues.size() - 1; i++) {
      if (rankValues.get(i) + 1 != rankValues.get(i + 1)) {
        return false;
      }
    }
    return true;
  }

  private boolean hasThreeOfAKind() {
    Map<Card.Ranks, Integer> rankCounts = getRankCounts();
    for (int count : rankCounts.values()) {
      if (count == 3) {
        return true;
      }
    }
    return false;
  }

  private boolean hasTwoPair() {
    Map<Card.Ranks, Integer> rankCounts = getRankCounts();
    int pairCount = 0;
    for (int count : rankCounts.values()) {
      if (count == 2) {
        pairCount++;
      }
    }
    return pairCount == 2;
  }

  private boolean hasOnePair() {
    Map<Card.Ranks, Integer> rankCounts = getRankCounts();
    for (int count : rankCounts.values()) {
      if (count == 2) {
        return true;
      }
    }
    return false;
  }

  private boolean hasSameSuit() {
    Card.Suits suit = hand.get(0).getSuit();
    for (Card card : hand) {
      if (!card.getSuit().equals(suit)) {
        return false;
      }
    }
    return true;
  }

  private Map<Card.Ranks, Integer> getRankCounts() {
    Map<Card.Ranks, Integer> rankCounts = new HashMap<>();
    for (Card card : hand) {
      Card.Ranks rank = ((PokerCard) card).getRank();
      rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
    }
    return rankCounts;
  }

  private List<Integer> getSortedRankValues() {
    List<Integer> rankValues = new ArrayList<>();
    for (Card card : hand) {
      Card.Ranks rank = ((PokerCard) card).getRank();
      rankValues.add(getRankValue(rank));
    }
    Collections.sort(rankValues);
    return rankValues;
  }

  private int getRankValue(Card.Ranks rank) {
    return 1+Card.Ranks.valueOf(rank.toString()).ordinal();
  }
}

