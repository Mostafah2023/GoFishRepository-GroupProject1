/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliverable3_gofish;

import java.util.*;

/**
 *
 * @author Cameron, Mostafa, David
 */
public class GamePlayer {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int numOfCards = 0, numOfPlayers = 0;
        boolean endFlag = false, winFlag = false;
        
        //Find the number of players
        while(numOfCards == 0){
            System.out.println("How many players will be playing (2-5): ");
            numOfPlayers = in.nextInt();
            
            //Check if it's a valid amount of players
            while(true){
                if(numOfPlayers > 0 && numOfPlayers < 6) break;
                System.out.println("Please enter a valid number of players (2-5): ");
                numOfPlayers = in.nextInt();
            }
            
            //Set the number of cards dealt to each player
            if(numOfPlayers < 4 && numOfPlayers > 0){
                numOfCards = 5;
            }else if(numOfPlayers >= 4)
                numOfCards = 7;
        }

        //Create the each player and their hands (their cards)
        List<List<Card>> playersHands = new ArrayList<List<Card>>(numOfPlayers);
        for(int i = 0; i < numOfPlayers; i++){
            List<Card> cardHand = CardHandGenerator.generateHand(numOfCards);
            playersHands.add(cardHand);
            playersHands.set(i, sortHand(playersHands.get(i)));
        }
        
        //Print out each players hand
        for (int i = 0; i < playersHands.size(); i++) {
            System.out.println("Player" + (i + 1) + ":");
            for(Card c : playersHands.get(i))
                if (c != null)
                    System.out.println(c.toString());
            System.out.print("\n");
        }
        
        //Play the game
        while (!endFlag){
            //Cycle through displaying the current players hand
            for (int i = 0; i < playersHands.size(); i++) {
                playersHands.set(i, sortHand(playersHands.get(i)));
                System.out.println("Player" + (i + 1) + ":");
                for(Card c : playersHands.get(i))
                    if(c != null && c.getValue() != null && c.getSuit() != null)
                    System.out.println(c.toString());
                
                System.out.println("1 = Hearts, 2 = Clubs, 3 = Spades, 4 = Dimaonds");
                System.out.print("Any (value, suit i.e: 2 1): ");
                int tempValue = in.nextInt(), tempSuit = in.nextInt();
                
                //Check if the requested card is a valid card
                while(true){
                    if((tempSuit > 0 && tempSuit < 5 ) && (tempValue > 0 && tempValue < 14)) break;
                    System.out.print("Please enter a valid value (Suit -> 1-4, Value -> 1-13): ");
                    tempValue = in.nextInt();
                    tempSuit = in.nextInt();
                }
                
                /*Create a card to use as a reference (don't use the generateCard
                 *  method as it would make the requested card impossible to get if it is not in play)
                 */
                Card requestCard = new Card(Card.Suit.CLUBS, Card.Value.ACE);
                        requestCard.setSuit(Card.Suit.values()[tempSuit - 1]);
                        requestCard.setValue(Card.Value.values()[tempValue - 1]);
                        
                //Search for the requested card, else go fish
                boolean cardFound = false;
                for(int j = 0; j < playersHands.size(); j++){
                    if(playersHands.get(j) != playersHands.get(i))
                        for(int k = 0; k < playersHands.get(j).size(); k++)
                            if(playersHands.get(j).get(k).getSuit() == requestCard.getSuit()
                                    && playersHands.get(j).get(k).getValue() == requestCard.getValue()){
                                playersHands.get(i).add(playersHands.get(j).get(k));
                                playersHands.get(j).remove(playersHands.get(j).get(k));
                                cardFound = true;
                                
                            }
                    if(j + 1 == numOfPlayers && !cardFound){
                        System.out.println("Go fish!");
                        Card tempCard = CardHandGenerator.randomCardGenerator();
                        while(CardHandGenerator.checkUsedCard(tempCard.getSuitOrdinal(),
                                tempCard.getValueOrdinal()))
                            tempCard = CardHandGenerator.randomCardGenerator();
                        playersHands.get(i).add(tempCard);
                    }
                    
                }
            
            }
            
            //Check if there are any cards left in the deck
            if(Card.countCardsLeft() == 0){
                //End the game
                endFlag = true;
            }
        }
        //Calculate and display points
        for(int i = 0; i < numOfPlayers; i++){
            System.out.println("Player" + (i + 1) + ": " + checkForSet(playersHands.get(i)));
        }
    }
    
    /*----------------------------------------------------------------*/
    
    /*Checks if there are any sets in a deck, returns the number of
     *  points accumulated for those sets
     */
    public static int checkForSet(List<Card> hand){
        int points = 0, quarterPoint = 0;
        for(int i = 0; i < 13; i++){
                quarterPoint = 0;
                
                for(int j = 0; j < 4; j++){
                    for(Card c : hand){
                        if(c.getSuit() == Card.Suit.values()[j] && c.getValue() == Card.Value.values()[i])
                            quarterPoint++;
                        }
                        if(quarterPoint == 4)
                            points++;
                }
        }
        System.out.println("Points: " + points);
        return points;
    }
    
    /*----------------------------------------------------------------*/
    
    //Sort a players hand
    public static List<Card> sortHand(List<Card> hand){
        Comparator<Card> cardValueComparator = Comparator.comparing(Card::getValueOrdinal);
        hand.sort(cardValueComparator);
        return hand;
    }
    
    /*----------------------------------------------------------------*/
    
    //Draw a new card
    public static Card drawCard(){
        return CardHandGenerator.randomCardGenerator();
    }
}
