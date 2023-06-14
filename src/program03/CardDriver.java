package program03;

/*
 * Author: Peter Li
 * Date: 02/06/2023
 * Class: CompSci 251
 */

import java.util.*;
import java.util.stream.IntStream;
public class CardDriver {
    private Card[] deck;

    public CardDriver() {
        this.deck = new Card[52];
    }

    // Class method
    public static void fillDeck(Card[] deck) {
        // I chose this method to be static as this is a utility method that allows decks of any size to be filled.
        // Filling a deck is something that can be universally done, regardless of deck size.
        Card card = new Card();

        for (int i = 0; i < deck.length; i++) {
            Card drawnCard = card.drawCard();
            deck[i] = drawnCard;
        }
    }

    public void tranpose() {
        Card card = new Card();
        Random rando = new Random();
        int randomIndex1 = rando.nextInt(this.deck.length);
        int randomIndex2 = 0;

        // This do while loop ensures that the same index isn't selected twice in a row.
        do {
            randomIndex2 = rando.nextInt(this.deck.length);
        } while(randomIndex2 == randomIndex1);

        card.drawCard(randomIndex1, this.deck);
        Card clonedCard = card.clone();
        card.drawCard(randomIndex2, this.deck);
        this.deck[randomIndex1] = this.deck[randomIndex2];
        this.deck[randomIndex2] = clonedCard;
    }

    public static void main(String[] args) {
        Card comparisonCard = new Card();
        comparisonCard.queenOfClubsCard();
        int location = 0;

        CardDriver driver = new CardDriver();
        CardDriver.fillDeck(driver.deck);

        // Using this IntStream so I can use the .range function to run a specific command x amount of times.
        IntStream.range(0, 100).forEach(i -> driver.tranpose());

        System.out.println("Here is a shuffled deck of cards:");
        System.out.println();

        // Search for queen of clubs card and which index it's on
        for(int i = 0; i < driver.deck.length; i++) {
            System.out.println(driver.deck[i]);
            if(driver.deck[i].equals(comparisonCard)) {
                location = i;
            }
        }

        System.out.println("");
        System.out.print("The " + comparisonCard + " was found at location " + location);
    }
}

