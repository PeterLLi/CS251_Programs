package program02;

public class DeckDriver {
    public static void main(String[] args) {
        Deck myDeck = new Deck();
//		SmartDeck myDeck = new SmartDeck();
        myDeck.initDeck();

        final int cardsPerRow = 8;
        int cardsThisRow = 0;
        int myCard;

        System.out.println("\nHere is a shuffled deck ...\n");

        // Main logic
        while (!myDeck.emptyDeck())
        {
            myCard = myDeck.dealCard();
            ++cardsThisRow;
            if (cardsThisRow <= cardsPerRow)
            {
                System.out.print(Deck.cardToString(myCard));
                System.out.print(" ");
            }
            else
            {
                System.out.println("");
                cardsThisRow = 1;
                System.out.print(Deck.cardToString(myCard));
                System.out.print(" ");
            }
        }
        System.out .println('\n');
    }
}


