/**************************************************************
 Tatiana Adams, Ryan Barrett, Matthew Taylor, Rowena Terrado
 7th April 2020
 CST 338 Software Design
 Assignment 5: Low Card Game - Phase 1
 This program demonstrates use of the swing class to display a card
 panel.  Within the card table panel, 52 standard cards plus 5
 jacks and a card back are displayed.  Each card is displayed as
 and ImageIcon.  The array of Icons is built using a loop and then
 loaded into the panel using the FlowLayout layout.  The building
 of the icon array serves as the basis for creating an array of
 Icons for phase 2.
 ***************************************************************/

import javax.swing.*;
import java.awt.*;

public class Assignment5Phase1
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
}