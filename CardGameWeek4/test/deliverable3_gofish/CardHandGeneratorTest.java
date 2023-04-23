/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package deliverable3_gofish;

import java.util.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import org.junit.Test;

/**
 *
 * @author camwi
 */
public class CardHandGeneratorTest {

    /**
     * Test of generateHand method, of class CardHandGenerator.
     */
    @Test
    public void testGenerateHandGood() {
        System.out.println("generateHandGood");
        int handSize = 0;
        boolean expResult = true, result = false;
        List<Card> testHand = CardHandGenerator.generateHand(handSize),
                expResultHand = testHand,
                resultHand = CardHandGenerator.generateHand(handSize);
        if(expResultHand != resultHand) result = true;
        assertEquals(expResult, result);
    }
    
    //Once again boundary is not required as there is only one scenario where
    //  it ends up true since it's a binary check
    
    @Test
    public void testGenerateHandBad() {
        System.out.println("generateHandBad");
        int handSize = 0;
        boolean expResult = false, result = true;
        List<Card> testHand = CardHandGenerator.generateHand(handSize),
                expResultHand = testHand, resultHand = testHand;
        if(expResultHand == resultHand) result = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of checkUsedCard method, of class CardHandGenerator.
     */
    @Test
    public void testCheckUsedCard() {
        System.out.println("checkUsedCard");
        for(int i = 0; i < Card.usedCards.length; i++)
            for(int j = 0; j < Card.usedCards[i].length; j++)
                Card.usedCards[i][j] = false;
        int suitPos = 0;
        int valuePos = 0;
        boolean expResult = false;
        boolean result = CardHandGenerator.checkUsedCard(suitPos, valuePos);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckUsedCardBoundary() {
        System.out.println("checkUsedCardBoundary");
        int suitPos = 0;
        int valuePos = 0;
        for(int i = 0; i < Card.usedCards.length; i++)
            for(int j = 0; j < Card.usedCards[i].length; j++)
                if(i != 0 && j != 0) Card.usedCards[i][j] = true;
        boolean expResult = false;
        boolean result = CardHandGenerator.checkUsedCard(suitPos, valuePos);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckUsedCardBad() {
        System.out.println("checkUsedCardBad");
        int suitPos = 0;
        int valuePos = 0;
        for(int i = 0; i < Card.usedCards.length; i++)
            for(int j = 0; j < Card.usedCards[i].length; j++)
                Card.usedCards[i][j] = true;
        boolean expResult = true;
        boolean result = CardHandGenerator.checkUsedCard(suitPos, valuePos);
        assertEquals(expResult, result);
    }

    /**
     * Test of randomCardGenerator method, of class CardHandGenerator.
     */
    @Test
    public void testRandomCardGeneratorGood() {
        System.out.println("randomCardGeneratorGood");
        Card testCard = CardHandGenerator.randomCardGenerator(),
                expResultCard = testCard, resultCard = testCard;
        boolean expResult = true, result = false;
        if(expResultCard == resultCard) result = true;
        assertEquals(expResult, result);
    }
    
    //Once again boundary is not required as there is only one scenario where
    //  it ends up true since it's a binary check
    
    @Test
    public void testRandomCardGeneratorBad() {
        System.out.println("randomCardGeneratorBad");
        Card testCard = CardHandGenerator.randomCardGenerator(),
                expResultCard = testCard,
                resultCard = CardHandGenerator.randomCardGenerator();
        boolean expResult = false, result = false;
        if(expResultCard == resultCard) result = true;
        assertEquals(expResult, result);
    }
    
}
