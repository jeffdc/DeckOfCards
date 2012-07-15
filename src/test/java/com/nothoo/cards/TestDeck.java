package com.nothoo.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestDeck {

	private Deck deck;
	private Deck deckAcesHigh;

	@Before
	public void setup() {		
		deck = new Deck();
		deckAcesHigh = new Deck(true);
	}
	
	@Test
	public void testDeck() {	
		assertNotNull(deck);
	}
	
	@Test
	public void testDeckAcesHigh() {
		assertNotNull(deckAcesHigh);
	}

	@Test
	public void testShuffle() {
		deck.shuffle();
		dealToExhaustion(deck);

		assertEquals(deck.cardCount(), 0);
		assertNull(deck.dealOneCard());
		assertEquals(deck.cardCount(), 0);

		deck.shuffle();
		assertEquals(deck.cardCount(), 52);
		
		// I don't see how it is really feasible to test the randomness of the shuffle other than a basic sanity check:
		// the probability of the first card being the same in a non shuffled and shuffled deck is 1:52, so if we
		// shuffle then check the 1st card for each shuffle the overall probability of the 1st card repeatedly being
		// the same card becomes very small very quickly. If we run 100 tries the probability of the same card being
		// 1st each time is 1:52^100. So yes our test might incorrectly fail but it is very, very, very, unlikely.
		
		Card last = null;
		boolean success = false;
		for (int i=0; i<100; ++i) {
			deck.shuffle();
			Card current = deck.dealOneCard();
			if (last != null && !(last.equals(current))) {
				success = true;
				break;
			}
			last = current;
		}
		assertTrue(success);
	}

	@Test
	public void testDealOneCard() {
		assertNotNull(deck.dealOneCard());
	}

	@Test
	public void testCardCount() {
		assertEquals(deck.cardCount(), 52);	
	}

	@Test
	public void testDealAndCount() {
		deck.dealOneCard();
		assertEquals(deck.cardCount(), 51);
	}
	
	@Test
	public void testDeckExhaustion() {
		dealToExhaustion(deck);
		
		assertEquals(deck.cardCount(), 0);
		assertNull(deck.dealOneCard());
		assertEquals(deck.cardCount(), 0);
	}
	
	@Test
	public void testCompareCards() {
		Card aceOfSpades = Card.valueOf(Card.Suite.SPADES, Card.Rank.ACE);
		Card queenOfHearts = Card.valueOf(Card.Suite.HEARTS, Card.Rank.QUEEN);
		
		assertTrue(deck.compareCards(aceOfSpades, queenOfHearts) < 0);
		assertTrue(deck.compareCards(queenOfHearts, aceOfSpades) > 0);
		assertTrue(deck.compareCards(aceOfSpades, aceOfSpades) == 0);
	}
	
	private void dealToExhaustion(Deck deck) {
		for (int i=0; i<52; ++i) {
			deck.dealOneCard();
		}
	}
}
