/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardgameweek4;

import deliverable3_gofish.Card.Suit;
import deliverable3_gofish.Card.Value;
import java.util.*;

/**
 * A class that models the Card Hand. A Card hand has an array of cards. 
 * @author Cameron, Mostafa, David
 */

public class CardHandGenerator {

    /**
     * Generates a hand of a given size
     */
    public static List<Card> generateHand(int handSize) {
        
        // declare and initialize a hand of cards
        List<Card> hand = new ArrayList<>(handSize);
        
        for (int i = 0; i < handSize; i++) {
            Card tempCard = randomCardGenerator();
            // get a random suit and value. Note we're not concerned about uniqueness
            // at this point (this has been changed in the newest implementation)
            
            // Add the new card to the hand   
            hand.add(tempCard);
        }
        
        return hand;
    }
    
    /*----------------------------------------------------------------*/
    
    public static boolean checkUsedCard(int suitPos, int valuePos){
        return Card.usedCards[suitPos][valuePos];
    }
    
    /*----------------------------------------------------------------*/
    
    public static Card randomCardGenerator(){
        // we'll use this to generate random numbers
        Random random = new Random();
        
        // let's get these lengths once
        int numValues = Card.Value.values().length, numSuits = Card.Suit.values().length,
                randSuit = random.nextInt(numSuits), randValue = random.nextInt(numValues);
        
        //Checks if the card is used or not
        while(checkUsedCard(randSuit, randValue)){
            randSuit = random.nextInt(numSuits);
            randValue = random.nextInt(numValues);
        }
        
        Card.setUsedCards(Card.Suit.values()[randSuit], Card.Value.values()[randValue], true);
        
        return new Card(Card.Suit.values()[randSuit],
                Card.Value.values()[randValue]);
    }
}
