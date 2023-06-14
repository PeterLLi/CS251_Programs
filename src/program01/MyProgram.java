package program01;

import java.util.*;
public class MyProgram {
    private static List<Integer> cards = new ArrayList<Integer>(); //Set to static as it only should be initialized once when called.

    //Constructor
    public MyProgram() {
        cards = new ArrayList<Integer>(52);
    }

    //Fill list of cards available 0 - 51
    public void fillCardsDeck(){
        for (int i = 0; i < 52; i++) {
            cards.add(i, i);
        }
    }

    // set the values of deck to indicate that they are all
    // present - not dealt yet.
    public static void initDeck(boolean[] deck) {
        Arrays.fill(deck, true); // Since we are initiating the deck, all cards are present, hence everything in the array is set to true.
    }

    // returns whether or not all the cards in the deck
    // have already been dealt.
    public static boolean emptyDeck(boolean[] deck) {
        boolean emptyDeck = true;

        for (boolean card : deck) {
            if (card) {
                emptyDeck = false;
                break;
            }
        }

        return emptyDeck;
    }

    // returns a card (an int in the range 0 to 51) at random
    // that has not been dealt since the deck was initialize
    // via intDeck. Also notes (in deck) that this card is
    // no longer available.
    public static int dealCard(boolean[] deck) {
        int drawnCard;
        int randomIndex;

        //Random number generator. This generates a random number from 0 to the lists size.
        //This then selects the index which the random number generator generated. I.E if random generated 0, select index 0
        //and get the value at that index.
        Random rando = new Random();
        randomIndex = rando.nextInt(cards.size());
        drawnCard = cards.get(randomIndex);
        cards.remove(randomIndex);

        //Drawn card is the index in the deck. Set this index to false as it is not longer available;
        deck[drawnCard] = false;
        return drawnCard;
    }

    // given a card (an int in the range 0 to 51) prints
    // an appropriate representation of this card based
    // on a 1-1 and onto mapping of the set [0, 51] to
    // the cards described above.
    public static void printCard(int card) {
        String cardWithSuit = "";

        switch (card) {
            case 0:
                cardWithSuit = "AC"; //Ace of clubs
                break;
            case 1:
                cardWithSuit = "2C";
                break;
            case 2:
                cardWithSuit = "3C";
                break;
            case 3:
                cardWithSuit = "4C";
                break;
            case 4:
                cardWithSuit = "5C";
                break;
            case 5:
                cardWithSuit = "6C";
                break;
            case 6:
                cardWithSuit = "7C";
                break;
            case 7:
                cardWithSuit = "8C";
                break;
            case 8:
                cardWithSuit = "9C";
                break;
            case 9:
                cardWithSuit = "10C";
                break;
            case 10:
                cardWithSuit = "JC";
                break;
            case 11:
                cardWithSuit = "QC";
                break;
            case 12:
                cardWithSuit = "KC";
                break;
            case 13:
                cardWithSuit = "AS"; //Ace of Spades
                break;
            case 14:
                cardWithSuit = "2S";
                break;
            case 15:
                cardWithSuit = "3S";
                break;
            case 16:
                cardWithSuit = "4S";
                break;
            case 17:
                cardWithSuit = "5S";
                break;
            case 18:
                cardWithSuit = "6S";
                break;
            case 19:
                cardWithSuit = "7S";
                break;
            case 20:
                cardWithSuit = "8S";
                break;
            case 21:
                cardWithSuit = "9S";
                break;
            case 22:
                cardWithSuit = "10S";
                break;
            case 23:
                cardWithSuit = "JS";
                break;
            case 24:
                cardWithSuit = "QS";
                break;
            case 25:
                cardWithSuit = "KS";
                break;
            case 26:
                cardWithSuit = "AH"; //Ace of hearts
                break;
            case 27:
                cardWithSuit = "2H";
                break;
            case 28:
                cardWithSuit = "3H";
                break;
            case 29:
                cardWithSuit = "4H";
                break;
            case 30:
                cardWithSuit = "5H";
                break;
            case 31:
                cardWithSuit = "6H";
                break;
            case 32:
                cardWithSuit = "7H";
                break;
            case 33:
                cardWithSuit = "8H";
                break;
            case 34:
                cardWithSuit = "9H";
                break;
            case 35:
                cardWithSuit = "10H";
                break;
            case 36:
                cardWithSuit = "JH";
                break;
            case 37:
                cardWithSuit = "QH";
                break;
            case 38:
                cardWithSuit = "KH";
                break;
            case 39:
                cardWithSuit = "AD"; //Ace of Diamonds
                break;
            case 40:
                cardWithSuit = "2D";
                break;
            case 41:
                cardWithSuit = "3D";
                break;
            case 42:
                cardWithSuit = "4D";
                break;
            case 43:
                cardWithSuit = "5D";
                break;
            case 44:
                cardWithSuit = "6D";
                break;
            case 45:
                cardWithSuit = "7D";
                break;
            case 46:
                cardWithSuit = "8D";
                break;
            case 47:
                cardWithSuit = "9D";
                break;
            case 48:
                cardWithSuit = "10D";
                break;
            case 49:
                cardWithSuit = "JD";
                break;
            case 50:
                cardWithSuit = "QD";
                break;
            case 51:
                cardWithSuit = "KD";
                break;
        }
        System.out.print(cardWithSuit);
    }

    public static void main (String[] args) {
        MyProgram myProgram = new MyProgram(); //Call constructor
        myProgram.fillCardsDeck();

        //Unmodified main ending
        boolean[] myDeck = new boolean[52];
        final int cardsPerRow = 8;
        int cardsThisRow = 0;
        int myCard;
        initDeck(myDeck);
        System.out.println("\nHere is a shuffled deck ...\n");
        while (!emptyDeck(myDeck))
        {
            myCard = dealCard(myDeck);
            ++cardsThisRow;
            if(cardsThisRow <= cardsPerRow)
            {
                printCard(myCard);
                System.out.print(" ");
            }
            else
            {
                System.out.println("");
                cardsThisRow = 1;
                printCard(myCard);
                System.out.print(" ");
            }
        }
        System.out .println('\n');
    }
}

