package program04;

/*
 * Author: Peter Li
 * Date: 02/19/2023
 * Class: CompSci 251
 */

import java.util.*;
public class Card {
    private int rank;
    private String suit;
    private static List<Integer> availableRanks = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
    private static List<String> availableSuits = new ArrayList<String>(Arrays.asList("clubs", "diamonds", "hearts", "spades"));
    private static int currentRank = 0;
    private static int currentSuit = 0;

    // Constructor setting the default values of the card.
    public Card() {
        this.rank = 1;
        this.suit = "clubs";
    }

    // Set the newly created instance card's combined card string with the drawn card.
    public Card clone() {
        Card clonedCard = new Card();
        clonedCard.rank = this.rank;
        clonedCard.suit = this.suit;

        return clonedCard;
    }

    public boolean equals(Card guest) {
        boolean matchingCard = ((guest.getSuit() == this.getSuit()) && (guest.getRank() == this.getRank())) ? true : false;

        return matchingCard;
    }

    // Public accessors
    // Draws a card in order -> this is used primarily for filling decks
    public Card drawCard() {
        Card card = new Card();

        card.rank = availableRanks.get(currentRank);
        card.suit = availableSuits.get(currentSuit);

        this.setSuit(card.suit);
        this.setRank(card.rank);

        // Keep incrementing a card until it hits the king in that suit. Then reset the rank to 0, move to next suit.
        if(currentRank < 12) {
            ++currentRank;
        } else {
            currentRank = 0;
            ++currentSuit;
        }

        return card;
    }

    // Overloaded Method
    // This allows drawing of a card from a deck
    public Card drawCard(int index, Card[] deck) {
        Card drawnCard = deck[index];

        this.rank = drawnCard.rank;
        this.suit = drawnCard.suit;

        return drawnCard;
    }

    // public getters
    public int getRank() {
        return this.rank;
    }

    public String getSuit() {
        return this.suit;
    }

    // private mutators
    private void setSuit(String suit) {
        this.suit = suit;
    }

    private void setRank(int rank) {
        this.rank = rank;
    }

    // This method maps the correct sequencing to the correct output.
    // Override the Object.toString method.
    @Override
    public String toString() {
        String combinedRankandSuit = Integer.toString(getRank()) + getSuit();
        String output = "";

        switch (combinedRankandSuit) {
            case "1clubs": // Ace of clubs
                output = "Ace of clubs";
                break;
            case "2clubs":
                output = "2 of clubs";
                break;
            case "3clubs":
                output = "3 of clubs";
                break;
            case "4clubs":
                output = "4 of clubs";
                break;
            case "5clubs":
                output = "5 of clubs";
                break;
            case "6clubs":
                output = "6 of clubs";
                break;
            case "7clubs":
                output = "7 of clubs";
                break;
            case "8clubs":
                output = "8 of clubs";
                break;
            case "9clubs":
                output = "9 of clubs";
                break;
            case "10clubs":
                output = "10 of clubs";
                break;
            case "11clubs":
                output = "Jack of clubs";
                break;
            case "12clubs":
                output = "Queen of clubs";
                break;
            case "13clubs":
                output = "King of clubs";
                break;
            case "1diamonds": // Ace of Diamonds
                output = "Ace of diamonds";
                break;
            case "2diamonds":
                output = "2 of diamonds";
                break;
            case "3diamonds":
                output = "3 of diamonds";
                break;
            case "4diamonds":
                output = "4 of diamonds";
                break;
            case "5diamonds":
                output = "5 of diamonds";
                break;
            case "6diamonds":
                output = "6 of diamonds";
                break;
            case "7diamonds":
                output = "7 of diamonds";
                break;
            case "8diamonds":
                output = "8 of diamonds";
                break;
            case "9diamonds":
                output = "9 of diamonds";
                break;
            case "10diamonds":
                output = "10 of diamonds";
                break;
            case "11diamonds":
                output = "Jack of diamonds";
                break;
            case "12diamonds":
                output = "Queen of diamonds";
                break;
            case "13diamonds":
                output = "King of diamonds";
                break;
            case "1hearts": // Ace of hearts
                output = "Ace of hearts";
                break;
            case "2hearts":
                output = "2 of hearts";
                break;
            case "3hearts":
                output = "3 of hearts";
                break;
            case "4hearts":
                output = "4 of hearts";
                break;
            case "5hearts":
                output = "5 of hearts";
                break;
            case "6hearts":
                output = "6 of hearts";
                break;
            case "7hearts":
                output = "7 of herats";
                break;
            case "8hearts":
                output = "8 of hearts";
                break;
            case "9hearts":
                output = "9 of hearts";
                break;
            case "10hearts":
                output = "10 of hearts";
                break;
            case "11hearts":
                output = "Jack of hearts";
                break;
            case "12hearts":
                output = "Queen of hearts";
                break;
            case "13hearts":
                output = "King of hearts";
                break;
            case "1spades": // Ace of spades
                output = "Ace of spades";
                break;
            case "2spades":
                output = "2 of spades";
                break;
            case "3spades":
                output = "3 of spades";
                break;
            case "4spades":
                output = "4 of spades";
                break;
            case "5spades":
                output = "5 of spades";
                break;
            case "6spades":
                output = "6 of spades";
                break;
            case "7spades":
                output = "7 of spades";
                break;
            case "8spades":
                output = "8 of spades";
                break;
            case "9spades":
                output = "9 of spades";
                break;
            case "10spades":
                output = "10 of spades";
                break;
            case "11spades":
                output = "Jack of spades";
                break;
            case "12spades":
                output = "Queen of spades";
                break;
            case "13spades":
                output = "King of spades";
                break;
        }

        return output;
    }
}

