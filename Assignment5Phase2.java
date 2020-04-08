import java.awt.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BoxLayout;

public class Assignment5Phase2
{
    static int NUM_CARDS_PER_HAND = 7;
    static int NUM_PLAYERS = 2;
    static JLabel[] computerLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] humanLabels = new JLabel[NUM_CARDS_PER_HAND];
    static JLabel[] playedCardLabels = new JLabel[NUM_PLAYERS];
    static JLabel[] playLabelText = new JLabel[NUM_PLAYERS];

    public static void main(String[] args)
    {
        int k;
        Icon tempIcon;
        GUICard guiCard = new GUICard();

        // establish main frame in which program will run
        CardTable myCardTable
                = new CardTable("CardTable", NUM_CARDS_PER_HAND, NUM_PLAYERS);
        myCardTable.setSize(800, 600);
        myCardTable.setLocationRelativeTo(null);
        myCardTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // show everything to the user
        myCardTable.setVisible(true);

        // CREATE LABELS ----------------------------------------------------
        for (int i = 0; i < myCardTable.getNumCardsPerHand(); i++)
        {
            computerLabels[i] = new JLabel();
            computerLabels[i].setIcon(new ImageIcon("images/BK.gif"));
            humanLabels[i] = new JLabel();
            humanLabels[i].setIcon(guiCard.getIcon(randomCardGenerator()));
        }

        // ADD LABELS TO PANELS -----------------------------------------
        for (int i = 0; i < myCardTable.getNumCardsPerHand(); i++)
        {
            myCardTable.pnlComputerHand.add(computerLabels[i], JLabel.CENTER);
        }

        for (int i = 0; i < myCardTable.getNumCardsPerHand(); i++)
        {
            myCardTable.pnlHumanHand.add(humanLabels[i], JLabel.CENTER);
        }
        // Text
        JLabel playerLabel = new JLabel( "Player", JLabel.CENTER );
        JLabel computerLabel = new JLabel( "Computer", JLabel.CENTER );

        // Card Labels
        JLabel playerCardLabel = new JLabel( "", JLabel.CENTER );
        JLabel computerCardLabel = new JLabel( "", JLabel.CENTER );

        playerCardLabel.setIcon(guiCard.getIcon(randomCardGenerator()));
        computerCardLabel.setIcon(guiCard.getBackCardIcon());

        // and two random cards in the play region (simulating a computer/hum ply)
        myCardTable.pnlPlayArea.add(playerCardLabel);
        myCardTable.pnlPlayArea.add(computerCardLabel);
        myCardTable.pnlPlayArea.add(playerLabel);
        myCardTable.pnlPlayArea.add(computerLabel);

        // show everything to the user
        myCardTable.setVisible(true);
    }

    static Card randomCardGenerator()
    {
        char[] values = new char[]
                {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'A', 'K', 'Q', 'J'};
        int value = new Random().nextInt(values.length);
        Random random = new Random();
        Card card = new Card(values[value],Card.Suit.values()[random.nextInt(Card.Suit.values().length)]);

        return card;
    }
}

class CardTable extends JFrame
{
    static int MAX_CARDS_PER_HAND = 56;
    static int MAX_PLAYERS = 2;  // for now, we only allow 2 person games

    public JPanel pnlComputerHand, pnlHumanHand, pnlPlayArea;

    private int numCardsPerHand;
    private int numPlayers;

    public CardTable(String title, int numCardsPerHand, int numPlayers)
    {
        super();
        this.setTitle(title);
        this.numCardsPerHand = numCardsPerHand;
        this.numPlayers = numPlayers;
        pnlComputerHand = new JPanel();
        pnlHumanHand = new JPanel();
        pnlPlayArea = new JPanel();

        TitledBorder playerBorderTitle = BorderFactory.createTitledBorder("Player Hand");
        TitledBorder playAreaBorderTitle = BorderFactory.createTitledBorder("Play Area");
        TitledBorder computerBorderTitle = BorderFactory.createTitledBorder("Computer Hand");

        FlowLayout plyHandLayout = new FlowLayout();
        FlowLayout cmpHandLayout = new FlowLayout();
        GridLayout playAreaLayout = new GridLayout(2, 2);

        pnlComputerHand.setLayout(cmpHandLayout);
        pnlHumanHand.setLayout(plyHandLayout);
        pnlPlayArea.setLayout(playAreaLayout);

        pnlPlayArea.setBorder(playAreaBorderTitle);
        pnlHumanHand.setBorder(playerBorderTitle);
        pnlComputerHand.setBorder(computerBorderTitle);

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        pnlComputerHand.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlComputerHand.setPreferredSize(new Dimension(50, 80));

        pnlHumanHand.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlHumanHand.setPreferredSize(new Dimension(50, 80));

        this.add(pnlComputerHand);
        this.add(pnlPlayArea);
        this.add(pnlHumanHand);

    }

    public int getNumCardsPerHand()
    {
        return numCardsPerHand;
    }

    public int getNumPlayers()
    {
        return numPlayers;
    }
}

class GUICard
{
    private static Icon[][] iconCards = new ImageIcon[14][4]; // 14 = A thru K + joker
    private static Icon iconBack;
    private static boolean iconsLoaded = false;

    public GUICard()
    {
        if (!iconsLoaded)
        {
            loadCardIcons();
        }
    }

    static void loadCardIcons()
    {
        String path = new String();
        int i = 0;

        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 14; k++) {
                path += "images/" + turnIntIntoCardValue(k) + turnIntIntoCardSuit(j) + ".gif";
                iconCards[k][j] = new ImageIcon(path);
                path = "";
            }
        }

        iconBack = new ImageIcon("images/BK.gif");
        iconsLoaded = true;
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

    static private int valueAsInt(Card card)
    {
        char value = card.getValue();
        switch (value)
        {
            case '1':
                return 'X';
            case '0':
                return 'A';
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return Integer.parseInt(String.valueOf(value));
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            default:
                return 0;
        }
    }

    static private int suitAsInt(Card card)
    {
        Card.Suit suit = card.getSuit();

        switch (suit)
        {
            case clubs:
                return 0;
            case diamonds:
                return 1;
            case hearts:
                return 2;
            case spades:
                return 3;
            default:
                return 0;
        }
    }

    static public Icon getIcon(Card card)
    {
        return iconCards[valueAsInt(card)][suitAsInt(card)];
    }

    static public Icon getBackCardIcon()
    {
        return iconBack;
    }
}

class Card {

    public enum Suit {clubs, diamonds, hearts, spades}

    char value;
    Suit suit;
    boolean errorFlag;

    public static char[] valueRanks = new char[]{'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'X'};

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
            this.errorFlag = false;
            return true;
        }
        this.errorFlag = true;
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

        if(equalValues == false  || sameSuit == false) {
            this.errorFlag = false;
        }
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

    private boolean isValid(char value, Suit suit) {
        char[] validValues = new char[]
                {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'A', 'K', 'Q', 'J', 'X'};

        // search every valid value for a match - return true as soon as match found
        for (int i = 0; i < validValues.length; i++) {
            if (value == validValues[i]) {
                return true;
            }
        }
        return false;
    }

    //will sort the incoming array of cards using a bubble sort routine
    static void arraySort(Card[] cards, int arraySize){
        Card temp;

        for (int i = 0; i < arraySize; i++){
            for (int j = 0; j < arraySize -1; j++){

                for(char valueRank: valueRanks){
                    if(cards[j].getValue() == valueRank){
                        temp = cards[j];
                        cards[j] = cards[j+1];
                        cards[j+1] = temp;
                    }
                }
            }
        }

        for(int p = 0; p < arraySize; p++){
            System.out.print(cards[p] + ", ");
        }

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

        if (numCards <= 0)
        {
            return new Card('?', Card.Suit.spades);
        }

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
            card.errorFlag = true;
        } else {
            card.set(myCards[k].getValue(), myCards[k].getSuit());
        }

        return card;
    }

    public void sort(){
        Card.arraySort(myCards, numCards);
    }

    public Card playCard(int cardIndex)
    {
        if ( numCards == 0 ) //error
        {
            //Creates a card that does not work
            return new Card('M', Card.Suit.spades);
        }
        //Decreases numCards.
        Card card = myCards[cardIndex];

        numCards--;
        for(int i = cardIndex; i < numCards; i++)
        {
            myCards[i] = myCards[i+1];
        }

        myCards[numCards] = null;

        return card;
    }
}

class Deck {
    public static final int MAX_CARDS = 6 * 56;

    private static Card[] masterPack;
    private Card[] cards = new Card[MAX_CARDS];
    private int topCard;
    int numPacks;

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
        if(numPacks < 1 || numPacks > 6){
            this.numPacks = numPacks;
        }
        //Find total number of cards
        topCard = (56 * numPacks);
        if (topCard <= MAX_CARDS) {
            //Create number of cards required from how many packs needed
            cards = new Card[56 * numPacks];
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
            card.errorFlag = true;
        } else {
            card = cards[k];
        }

        return card;
    }

    //Add a card to the deck and reassigns top card
    public boolean addCard(Card card){
        int numOfInstance = 0;

        //check number of card instances
        for (int i = 0; i < cards.length-1; i++){
            if (cards[i].equals(card)){
                numOfInstance ++;
            }
        }
        if(numPacks > numOfInstance ){
            return false;
        }

        if (cards.length >= topCard) {
            topCard++;
            cards[topCard-1] = card;
            /****************
             TEST PRINT
             *****************/
            System.out.println("\n");
            System.out.println("ADD TEST");
            for(Card cardPrint : cards){
                System.out.println(cardPrint);
            }
            return true;
        }
        return false;
    }

    //Removes a card from a deck and reassign top card
    public boolean removeCard(Card card){
        Card temp;
        int numOfInstance = 0;

        //counts the number of instances of a card
        for (int i = 0; i < cards.length-1; i++){
            if (cards[i].equals(card)){
                numOfInstance ++;
            }
        }
        //returns false if the card isnt found in the deck
        if(numOfInstance == 0 ){
            return false;
        }

        //removes card from deck and reassign topCard
        for (int i = 0; i < cards.length; i++) {

            if (cards[i].equals(card)) {
                for(int j = i; j < cards.length-1; j++){
                    temp = cards[j];
                    cards[j] = cards[j+1];
                    cards[j+1] = temp;
                }
                cards[topCard-1] = null;
                topCard--;
                /****************
                 TEST PRINT
                 *****************/
                System.out.println("\n");
                System.out.println("REMOVE TEST");
                for(Card cardPrint : cards){
                    System.out.println(cardPrint);
                }
                return true;
            }
        }

        return false;
    }

    //put all of the cards in the deck back into the right order according to their values
    public void sort() {
        Card.arraySort(cards, topCard + 1);
    }

    //return the number of cards remaining in the deck
    public int getNumCards() {
        return topCard;
    }
    //Generating the deck
    private static void allocateMasterPack() {
        if (masterPack != null) {
            return;
        }

        masterPack = new Card[56];
        char[] valueArray = new char[]
                {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'X'};

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