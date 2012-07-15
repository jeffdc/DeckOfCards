package com.nothoo.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCard {

	private Card aceOfSpades;
	private Card queenOfHearts;
	
	@Before
	public void setup() {
		aceOfSpades = Card.valueOf(Card.Suite.SPADES, Card.Rank.ACE);
		queenOfHearts = Card.valueOf(Card.Suite.HEARTS, Card.Rank.QUEEN);
	}

	@Test
	public void testValueOf() {
		assertNotNull(aceOfSpades);
	}

	@Test
	public void testGetSuite() {
		assertEquals(aceOfSpades.getSuite(), Card.Suite.SPADES);
	}

	@Test
	public void testGetRank() {
		assertEquals(aceOfSpades.getRank(), Card.Rank.ACE);
	}

	@Test
	public void testToString() {
		assertTrue(aceOfSpades.toString().contains(Card.Suite.SPADES.toString()));
		assertTrue(aceOfSpades.toString().contains(Card.Rank.ACE.toString()));
	}

	@Test
	public void testEqualsObject() {
		Card anotherAceOfSpades = Card.valueOf(Card.Suite.SPADES, Card.Rank.ACE);
		assertNotSame(aceOfSpades, anotherAceOfSpades);
		assertEquals(aceOfSpades, anotherAceOfSpades);
		assertTrue(aceOfSpades.equals(anotherAceOfSpades));
		
		assertNotSame(aceOfSpades, queenOfHearts);
		assertFalse(aceOfSpades.equals(queenOfHearts));
	}

	@Test
	public void testHashCode() {
		Card anotherAceOfSpades = Card.valueOf(Card.Suite.SPADES, Card.Rank.ACE);
		assertEquals(aceOfSpades.hashCode(), anotherAceOfSpades.hashCode());
		
		assertFalse(aceOfSpades.hashCode() == queenOfHearts.hashCode());
	}
}
