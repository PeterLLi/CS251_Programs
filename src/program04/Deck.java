package program04;

/*
 * Author: Peter Li
 * Date: 02/19/2023
 * Class: CompSci 251
 */

import java.util.*;
public class Deck {
    private Card[] deck = new Card[52];
    private int cardsDealt;

    // constructor
    // set each element of deck to a unique Card object,
    // and sets cardsDealt to zero.
    public Deck() {
        this.fillDeck();
        this.cardsDealt = 0;
    }

    private void fillDeck() {
        Card card = new Card();
        for(int i = 0; i < deck.length; i++) {
            deck[i] = card.drawCard();
        }
    }

    // accessor
    // return the value of cardsDealt.
    public int getCardsDealt() {
        return this.cardsDealt;
    }

    // mutator
    // sets cardsDealt specified value (cardsDealt)
    private void setCardsDealt(int cardsDealt) {
        this.cardsDealt = cardsDealt;
    }

    // returns whether or not all the cards in deck
    // have already been dealt (cardsDealt == 52).
    public boolean isEmptyDeck() {
        boolean isEmpty = this.cardsDealt == 52 ? true : false;
        return isEmpty;
    }

    // set cardsDealt to zero.
    public void collectAllCards() {
        this.setCardsDealt(0);
    }

    // if emptyDeck() is false ...
    // returns the card at location cardsDealt in deck,
    // and increments cardsDealt by 1.
    // else ...
    // returns null
    public Card dealCard() {
        int counter = this.getCardsDealt();
        Card card = new Card();

        if(this.isEmptyDeck() == false) {
            card = this.deck[cardsDealt];
            this.setCardsDealt(++counter);
        } else {
            card = null;
        }

        return card;
    }

    // apply 100 random card swaps within deck
    public void shuffleDeck() {
        Card card = new Card();
        Random rando = new Random();
        int randomIndex1 = 0;
        int randomIndex2 = 0;
        int counter = 0;

        while(counter < 100) {
            // Shuffle cards that haven't been dealt yet
            randomIndex1 = rando.nextInt(this.deck.length - this.getCardsDealt()) + this.cardsDealt;

            // This do while loop ensures that the same index isn't selected twice in a row.
            do {
                randomIndex2 = rando.nextInt(this.deck.length - this.getCardsDealt()) + this.cardsDealt;
            } while(randomIndex2 == randomIndex1);

            card.drawCard(randomIndex1, this.deck);
            Card clonedCard = card.clone();
            card.drawCard(randomIndex2, this.deck);

            this.deck[randomIndex1] = this.deck[randomIndex2];
            this.deck[randomIndex2] = clonedCard;

            counter++;
        }
    }

    // apply swapCnt random card swaps within deck
    public void shuffleDeck(int swapCnt) {
        Card card = new Card();
        Random rando = new Random();
        int randomIndex1 = 0;
        int randomIndex2 = 0;
        int counter = 0;

        while(counter < swapCnt) {
            // Shuffle cards that haven't been dealt yet
            randomIndex1 = rando.nextInt(this.deck.length - this.getCardsDealt()) + this.cardsDealt;

            // This do while loop ensures that the same index isn't selected twice in a row.
            do {
                randomIndex2 = rando.nextInt(this.deck.length - this.getCardsDealt()) + this.getCardsDealt();
            } while(randomIndex2 == randomIndex1);

            card.drawCard(randomIndex1, this.deck);
            Card clonedCard = card.clone();
            card.drawCard(randomIndex2, this.deck);

            this.deck[randomIndex1] = this.deck[randomIndex2];
            this.deck[randomIndex2] = clonedCard;

            counter++;
        }
    }
}

