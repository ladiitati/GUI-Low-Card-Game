import java.awt.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

public class Assign5
{
    // static for the 57 icons and their corresponding labels
    // normally we would not have a separate label for each card, but
    // if we want to display all at once using labels, we need to.

    static final int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 back-of-card image
    static Icon[] icon = new ImageIcon[NUM_CARD_IMAGES];


    static void loadCardIcons()
    {
        String path = new String();
        int i = 0;

        /*
        File folder = new File("images/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                path = "images/" + file.getName();
                icon[i] = new ImageIcon(path);
                i++;
            }
        }
        */

        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 14; k++) {
                path += "images/" + turnIntIntoCardValue(k) + turnIntIntoCardSuit(j) + ".gif";
                icon[i] = new ImageIcon(path);
                path = "";
                i++;
            }
        }
        icon[i] = new ImageIcon("images/BK.gif");

    }

    // turns 0 - 13 into "A", "2", "3", ... "Q", "K", "X"
    static String turnIntIntoCardValue(int k)
    {
        switch (k)
        {
            case 0:
                return "A";
            case 1:
                return "X";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "7";
            case 8:
                return "8";
            case 9:
                return "9";
            case 10:
                return "T";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
        }
        return "E";
    }

    // turns 0 - 3 into "C", "D", "H", "S"
    static String turnIntIntoCardSuit(int j)
    {
        switch (j)
        {
            case 0:
                return "C";
            case 1:
                return "D";
            case 2:
                return "H";
            case 3:
                return "S";
        }
        return "E";
    }

    // a simple main to throw all the JLabels out there for the world to see
    public static void main(String[] args)
    {
        int k;

        // prepare the image icon array
        loadCardIcons();

        // establish main frame in which program will run
        JFrame frmMyWindow = new JFrame("Card Room");
        frmMyWindow.setSize(1150, 650);
        frmMyWindow.setLocationRelativeTo(null);
        frmMyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set up layout which will control placement of buttons, etc.
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);
        frmMyWindow.setLayout(layout);

        // prepare the image label array
        JLabel[] labels = new JLabel[NUM_CARD_IMAGES];
        for (k = 0; k < NUM_CARD_IMAGES; k++)
            labels[k] = new JLabel(icon[k]);

        // place your 3 controls into frame
        for (k = 0; k < NUM_CARD_IMAGES; k++)
            frmMyWindow.add(labels[k]);

        // show everything to the user
        frmMyWindow.setVisible(true);
    }
    
    
	
	static class CardTable extends JFrame {
		   static int MAX_CARDS_PER_HAND = 56;
		   static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games
		   
		   private int numCardsPerHand;
		   private int numPlayers;

		   public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;
		   
		   public int getNumCardsPerHand() {
			   return numCardsPerHand;
		   }
		   
		   public int getNumPlayers() {
			   return numPlayers;
		   }
		   
		   CardTable(String title, int numCardsPerHand, int numPlayers){
			   super(title);

			   pnlComputerHand = new JPanel();
			   pnlHumanHand = new JPanel();
			   pnlPlayArea = new JPanel(new GridLayout(2, 2));
			   
			   setLayout(new BorderLayout(20, 10));
			   add(pnlComputerHand, BorderLayout.NORTH);
			   add(pnlHumanHand, BorderLayout.SOUTH);
			   add(pnlPlayArea, BorderLayout.CENTER);

			   pnlComputerHand.setBorder(new TitledBorder("Computer Hand"));
			   pnlHumanHand.setBorder(new TitledBorder("Your Hand"));  
			   pnlPlayArea.setBorder(new TitledBorder("Playing Area"));
		   }
	}

	static class GUICard
	{
		 private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
		 private static Icon iconBack;
		 static boolean iconsLoaded = false;
		 
	   static void loadCardIcons() {
		   if (iconsLoaded) {
			   return;
		   }
		   
	         for(int suit = 0; suit < 4; suit++)
	         {
	            for(int value = 0; value < 14; value++)
	            {
	               iconCards[value][suit] = new ImageIcon("images/" + turnIntIntoCardValue(value) 
	                     + turnIntIntoCardSuit(suit) + ".gif");
	            }        
	         }
		   
		   iconBack = new ImageIcon("images/BK.gif");
		   
		   iconsLoaded = true;
	   }
	   
	   static public Icon getIcon(Card card) {
		   return iconCards[valueAsInt(card)][suitAsInt(card)];
	   }
	   
	   public static int suitAsInt(Card card) {
		   for (int i = 0; i < 4; i++) {
			   if (i == card.getSuit().ordinal()) {
				   return i;
			   }
		   }
		   return -1;

	   }
	   
	   public static int valueAsInt(Card card) {
		   char[] valueArray = new char[]
	                {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'J'};
		   
		      for (int i = 0; i < valueArray.length; i++) {
		          if (valueArray[i] == card.getValue()) {
		             return i;
		             }
		          }
		      return -1;
		   
	   }
	   
	   static public Icon getBackCardIcon() {
		   return iconBack;
	   }
	   
	   
	   
	   
	}
	class Card {
	    public enum Suit {clubs, diamonds, hearts, spades}

	    char value;
	    Suit suit;
	    boolean errorFlag;

	    Card(char value, Suit suit) {
	        set(value, suit);
	    }

	    // overload for no parameters case
	    Card() {
	        set('A', Suit.spades);
	    }

	    public boolean set(char value, Suit suit) {
	        if (isValid(value, suit)) { // only set if input is valid
	            this.value = value;
	            this.suit = suit;
	            setErrorFlag(false);
	            return true;
	        }
	        setErrorFlag(true);
	        return false;
	    }

	    public String toString() {
	        if (errorFlag) {
	            return "[invalid]";
	        }
	        return value + " of " + suit;
	    }

	    public boolean equals(Card card) {
	        boolean equalValues = value == card.getValue();
	        boolean sameSuit = suit == card.getSuit();

	        return equalValues && sameSuit;
	    }

	    public char getValue() {
	        return value;
	    }

	    public Suit getSuit() {
	        return suit;
	    }

	    public boolean isErrorFlag() {
	        return errorFlag;
	    }

	    public void setErrorFlag(boolean errorFlag) {
	        this.errorFlag = errorFlag;
	    }

	    private boolean isValid(char value, Suit suit) {
	        char[] validValues = new char[]
	                {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'A', 'K', 'Q', 'J'};

	        // search every valid value for a match - return true as soon as match found
	        for (int i = 0; i < validValues.length; i++) {
	            if (value == validValues[i]) {
	                return true;
	            }
	        }
	        return false;
	    }
	}

	class Hand {
	    public int MAX_CARDS = 100;

	    private Card[] myCards;
	    private int numCards;

	    public Hand() {
	        myCards = new Card[MAX_CARDS];
	        numCards = 0;
	    }

	    public void resetHand() {
	        numCards = 0;
	    }

	    public boolean takeCard(Card card) {

	        Card newCard = new Card(card.getValue(), card.getSuit());
	        if (numCards > MAX_CARDS) {
	            return false;
	        } else {
	            myCards[numCards] = newCard;
	            numCards++;
	            return true;
	        }
	    }

	    public Card playCard() {
	        numCards--;
	        Card card = new Card(myCards[numCards].getValue(),
	                myCards[numCards].getSuit());
	        return card;
	    }

	    public String toString() {
	        String output = new String();
	        output = "Hand: ( ";
	        //add each card to output string
	        for (int i = 0; i < numCards; i++) {
	            output += myCards[i];
	            if (i + 1 != numCards) {
	                output += ", ";
	            }
	        }
	        output += " )\n";
	        //add newline every 100 characters
	        for (int i = 100; i <= output.length(); i += 100) {
	            output = output.substring(0, i) + "\n" + output.substring(i);
	        }
	        return output;
	    }

	    public int getNumCards() {
	        return numCards;
	    }

	    public Card inspectCard(int k) {
	        Card card = new Card();

	        if (k > numCards) {
	            card.setErrorFlag(true);
	        } else {
	            card.set(myCards[k].getValue(), myCards[k].getSuit());
	        }

	        return card;
	    }
	}

	class Deck {
	    public static final int MAX_CARDS = 6 * 52;

	    private static Card[] masterPack;
	    private Card[] cards = new Card[MAX_CARDS];
	    private int topCard;

	    //Constructor that populates the Card array
	    public Deck(int numPacks) {
	        allocateMasterPack();
	        init(numPacks);
	    }

	    //Overload when no parameters
	    public Deck() {
	        allocateMasterPack();
	        init(1);
	    }

	    //Re-populates cards[] with the designated number of packs of cards
	    public void init(int numPacks) {
	        //Find total number of cards
	        topCard = (52 * numPacks);
	        if (topCard <= MAX_CARDS) {
	            //Create number of cards required from how many packs needed
	            cards = new Card[52 * numPacks];
	            int j = 0;
	            //Loop for the amount of packs required
	            for (int i = 0; i < numPacks; i++) {
	                //Loop through every Card object of masterPack array to add to deck
	                for (Card card : masterPack) {
	                    cards[j] = card;
	                    j++;
	                }
	            }
	        }
	    }

	    //Shuffling the cards using random number generator
	    public void shuffle() {
	        Random rand = new Random();
	        for (int j = 0; j <= cards.length - 1; j++) {
	            //Find next random card position between 0 and total # of cards
	            int randIndex = rand.nextInt(cards.length);
	            //Swap selected card with current card
	            Card temp = cards[randIndex];
	            cards[randIndex] = cards[j];
	            cards[j] = temp;
	        }
	    }

	    //Returns and removes the card at top position of cards[]
	    public Card dealCard() {
	        //Check if cards are still available
	        if (topCard < 0) {
	            return null;
	        }
	        //Move onto next card
	        topCard--;
	        //Get card information
	        Card dealtCard = cards[topCard];
	        //Delete card info and return it
	        cards[topCard] = null;
	        return dealtCard;
	    }

	    //Accessor for topCard
	    public int getTopCard() {
	        return topCard;
	    }

	    //Access for an individual card
	    public Card inspectCard(int k) {
	        Card card = new Card();
	        if (k < 0 || k > topCard) {
	            card.setErrorFlag(true);
	        } else {
	            card = cards[k];
	        }

	        return card;
	    }

	    //Generating the deck
	    private static void allocateMasterPack() {
	        if (masterPack != null) {
	            return;
	        }

	        masterPack = new Card[52];
	        char[] valueArray = new char[]
	                {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};

	        int i = 0;
	        //Use for-each loop to go through all suits in the enum
	        for (Card.Suit suit : Card.Suit.values()) {
	            //Use for-each loop to assign a card with each value in the current suit
	            for (char value : valueArray) {
	                masterPack[i] = new Card(value, suit);
	                i++;
	            }
	        }

	    }
	}
}