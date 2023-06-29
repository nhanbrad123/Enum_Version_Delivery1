package ca.sheridancollege.project;

/**
 * @author Group7: Jaesung Lim, Ly Trong Nhan, Mykyta Varnikov
 * @date Jun 27, 2023
 */

import java.util.ArrayList;
import java.util.Scanner;

public class PokerGame extends Game {

  private GroupOfCards deck;
  private int numberOfPlayers;
  private Scanner scanner;

  public PokerGame(String name) {
    super(name);
    deck = new GroupOfCards(52);
    new ArrayList<>();
    scanner = new Scanner(System.in);
  }

  @Override
  public void play() {
    System.out.println("Welcome to the Five-Card Draw Poker Game!\n");

    System.out.println("Hand Rankings:");
    System.out.println("1. Royal Flush");
    System.out.println("2. Straight Flush");
    System.out.println("3. Four of a Kind");
    System.out.println("4. Full House");
    System.out.println("5. Flush");
    System.out.println("6. Straight");
    System.out.println("7. Three of a Kind");
    System.out.println("8. Two Pair");
    System.out.println("9. One Pair");
    System.out.println("10. High Card\n");

    System.out.print("Please enter the number of players (2-4): ");
    numberOfPlayers = scanner.nextInt();
    scanner.nextLine();

    ArrayList<Player> players = new ArrayList<>();
    for (int i = 1; i <= numberOfPlayers; i++) {
      System.out.print("Enter Player " + i + " username: ");
      String name = scanner.nextLine();
      players.add(new PokerPlayer(name));
    }
    setPlayers(players);

    while (true) {
      System.out.println("\nShuffling the deck...");
      initializeDeck();
      deck.shuffle();

      System.out.println("Dealing the cards...");
      dealCards();

      System.out.println();

      System.out.println("Each player receives 5 cards:\n");

      for (Player player : getPlayers()) {
        PokerPlayer pokerPlayer = (PokerPlayer) player;
        System.out.println("Player " + pokerPlayer.getName() + ":");
        pokerPlayer.showHand();
        System.out.println("");
      }

      System.out.println();

      for (Player player : getPlayers()) {
        System.out.println("It's " + player.getName() + "'s turn.");

        PokerPlayer pokerPlayer = (PokerPlayer) player;
        System.out.println(
          "Do you want to change any cards? Enter the numbers of the cards you want to change:"
        );
        pokerPlayer.showHandWithIndices();
        System.out.println(
          "e.g.\n1 2 3 4 5 -> Replace all cards\n1 2 -> Replace Card 1 and 2"
        );

        String input = scanner.nextLine();
        if (!input.isEmpty()) {
          pokerPlayer.changeCards(input, deck);
        }

        System.out.println(
          "\n" + player.getName() + "'s cards after changing:"
        );
        pokerPlayer.showHand();

        HandRanking handRanking = pokerPlayer.evaluateHand();
        System.out.println(
          "\n" + player.getName() + "'s highest hand rank: " + handRanking
        );
        System.out.println();
      }

      declareWinner();
      break; // Exit the loop after one round of the game
    }

    scanner.close();
    System.out.println("Thank you for playing!");
  }

  @Override
  public void declareWinner() {
    PokerPlayer winner = (PokerPlayer) getPlayers().get(0);
    HandRanking highestRank = winner.evaluateHand();

    for (int i = 1; i < getPlayers().size(); i++) {
      PokerPlayer player = (PokerPlayer) getPlayers().get(i);
      HandRanking rank = player.evaluateHand();
      if (rank.compareTo(highestRank) > 0) {
        winner = player;
        highestRank = rank;
      }
    }

    System.out.println("The winner is " + winner.getName() + "!");
  }

  private void initializeDeck() {
    deck.getCards().clear();

    String[] suits = { "♢", "♧", "♡", "♤" };
    String[] ranks = {
      "2",
      "3",
      "4",
      "5",
      "6",
      "7",
      "8",
      "9",
      "10",
      "Jack",
      "Queen",
      "King",
      "Ace",
    };

    for (String suit : suits) {
      for (String rank : ranks) {
        deck.getCards().add(new PokerCard(rank, suit));
      }
    }
  }

  private void dealCards() {
    int cardsPerPlayer = 5;

    for (int i = 0; i < cardsPerPlayer; i++) {
      for (Player player : getPlayers()) {
        PokerPlayer pokerPlayer = (PokerPlayer) player;
        pokerPlayer.addCard(deck.getCards().remove(0));
      }
    }
  }

  public static void main(String[] args) {
    PokerGame pokerGame = new PokerGame("Five-Card Draw Poker");
    pokerGame.play();
  }
}

