/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cardgameweek4;

import java.util.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import org.junit.Test;

/**
 *
 * @author camwi
 */
public class GamePlayerTest {

    /**
     * Test of checkForSet method, of class GamePlayer.
     */
    @Test
    public void testCheckForSetGood() {
        System.out.println("checkForSetGood");
        List<Card> hand = Arrays.asList(new Card(Card.Suit.values()[0], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[1], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[2], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[3], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[0], Card.Value.values()[1]),
                        new Card(Card.Suit.values()[1], Card.Value.values()[1]),
                        new Card(Card.Suit.values()[2], Card.Value.values()[1]),
                        new Card(Card.Suit.values()[3], Card.Value.values()[1]));
        int expResult = 2, result = GamePlayer.checkForSet(hand);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckForSetBoundary() {
        System.out.println("checkForSetBoundary");
        List<Card> hand = Arrays.asList(new Card(Card.Suit.values()[0], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[1], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[2], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[3], Card.Value.values()[0]));
        int expResult = 1, result = GamePlayer.checkForSet(hand);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckForSetBad() {
        System.out.println("checkForSetBad");
        List<Card> hand = Arrays.asList(new Card(Card.Suit.values()[0], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[1], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[2], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[3], Card.Value.values()[1]));
        int expResult = 0, result = GamePlayer.checkForSet(hand);
        assertEquals(expResult, result);
    }

    /**
     * Test of sortHand method, of class GamePlayer.
     */
    @Test
    public void testSortHandGood() {
        System.out.println("sortHandGood");
        List<Card> hand = Arrays.asList(new Card(Card.Suit.values()[0], Card.Value.values()[3]),
                        new Card(Card.Suit.values()[1], Card.Value.values()[2]),
                        new Card(Card.Suit.values()[2], Card.Value.values()[1]),
                        new Card(Card.Suit.values()[3], Card.Value.values()[0]));
        List<Card> expResultHand = Arrays.asList(new Card(Card.Suit.values()[3], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[2], Card.Value.values()[1]),
                        new Card(Card.Suit.values()[1], Card.Value.values()[2]),
                        new Card(Card.Suit.values()[0], Card.Value.values()[3]));
        boolean expResult = true, result = false;
        GamePlayer.sortHand(hand);
        int trueFound = 0;
        for(int i = 0; i < hand.size(); i++)
            if(hand.get(i).getSuitOrdinal() + hand.get(i).getValueOrdinal() ==
                    expResultHand.get(i).getSuitOrdinal() + expResultHand.get(i).getValueOrdinal())
                trueFound++;
        if(trueFound == 4) result = true;
        assertEquals(expResult, result);
    }
    
    //Once again boundary is not required as there is only one scenario where
    //  it ends up true since it's a binary check
    
    @Test
    public void testSortHandBad() {
        System.out.println("sortHandBad");
        List<Card> hand = Arrays.asList(new Card(Card.Suit.values()[0], Card.Value.values()[3]),
                        new Card(Card.Suit.values()[1], Card.Value.values()[2]),
                        new Card(Card.Suit.values()[2], Card.Value.values()[1]),
                        new Card(Card.Suit.values()[3], Card.Value.values()[0]));
        List<Card> expResultHand = Arrays.asList(new Card(Card.Suit.values()[3], Card.Value.values()[0]),
                        new Card(Card.Suit.values()[2], Card.Value.values()[1]),
                        new Card(Card.Suit.values()[1], Card.Value.values()[2]),
                        new Card(Card.Suit.values()[1], Card.Value.values()[3]));
        boolean expResult = false, result = false;
        GamePlayer.sortHand(hand);
        int trueFound = 0;
        for(int i = 0; i < hand.size(); i++)
            if(hand.get(i).getSuitOrdinal() + hand.get(i).getValueOrdinal() ==
                    expResultHand.get(i).getSuitOrdinal() + expResultHand.get(i).getValueOrdinal())
                trueFound++;
        if(trueFound == 4) result = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of drawCard method, of class GamePlayer.
     */
    @Test
    public void testDrawCardGood() {
        System.out.println("drawCardGood");
        boolean result = false, expResult = true;
        Card resultCard = GamePlayer.drawCard(),
                expResultCard = GamePlayer.drawCard();
        if(resultCard != expResultCard) result = true;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDrawCardBoundary() {
        System.out.println("drawCardBoundary");
        boolean result = true, expResult = true;
        Card testCard = GamePlayer.drawCard(), resultCard = testCard,
                expResultCard = testCard;
        if(resultCard != expResultCard) result = false;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDrawCardBad() {
        System.out.println("drawCardBad");
        boolean result = false, expResult = false;
        Card resultCard = GamePlayer.drawCard(), expResultCard = null;
        if(resultCard == expResultCard) result = true;
        assertEquals(expResult, result);
    }
    
}
