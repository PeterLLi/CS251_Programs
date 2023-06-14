package program04;

/*
 * Author: Peter Li
 * Date: 02/19/2023
 * Class: CompSci 251
 */

public class DeckDriver {
    public static void main(String[] args) {
        Deck deck = new Deck();

        //Shuffle the deck using the standard shuffleDeck method
        deck.shuffleDeck();

        System.out.println("Dealing every card in a shuffled deck.");
        while(!deck.isEmptyDeck()) {
            System.out.println(deck.dealCard());
        }

        // recollect all the cards and reshuffle the deck a certain amount of times
        deck.collectAllCards();
        deck.shuffleDeck(75);

        // Deal the first 26 cards
        System.out.println();
        System.out.println("Dealing the first 26 cards in a shuffled deck.");
        for(int i = 0; i < 26; i++) {
            System.out.println(deck.dealCard());
        }

        // Shuffle the deck 50 times again
        deck.shuffleDeck(50);

        // Deal the reamining 26 cards
        System.out.println();
        System.out.println("Reshuffle the remaining cards in the shuffled deck and deal them.");

        for(int i = 0; i < 26; i++) {
            System.out.println(deck.dealCard());
        }
    }
}

