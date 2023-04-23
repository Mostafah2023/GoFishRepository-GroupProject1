/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardgameweek4;

/**
 *
 * @author Cameron, Mostafa, David
 */
public class Card {

    public enum Suit {
        HEARTS, CLUBS, SPADES, DIAMONDS
    }

    public enum Value {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }
    
    public static boolean[][] usedCards = new boolean[4][13];
    
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }
    
    public static void setUsedCards(Suit suit, Value value, boolean state){
        usedCards[suit.ordinal()][value.ordinal()] = state;
    }
    
    public static int countCardsLeft(){
        int unusedCards = 0;
        for(boolean[] uCA : usedCards)
            for(boolean uC : uCA)
                if(!uC) unusedCards++;
        return unusedCards;
    }
    
    public int getValueOrdinal(){
        return value.ordinal();
    }
    
    public int getSuitOrdinal(){
        return suit.ordinal();
    }
    
    public void setValue(Value value){
        this.value = value;
    }

    public Value getValue() {
        return value;
    }
    
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }
    
    @Override
    public String toString(){
        return value + " of " + suit;
    }
}
