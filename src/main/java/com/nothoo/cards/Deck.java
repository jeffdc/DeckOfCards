package com.nothoo.cards;

import static com.nothoo.cards.Card.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple implementation of a Deck of 52 poker-style playing cards. 
 * 
 * N.B. - This class is not thread-safe and should not be used in a concurrent way. Further the class is declared as
 * final to prevent subclassing. This was done since all the complexities of a providing a safe super class were not
 * taken into account and thus the class is not designed for subclassing.
**/	
public final class Deck
{
	// Holds the cards
	private List<Card> cards;
	private boolean wantShuffled;
	private boolean acesHigh;
	private Random randoms;
	private static int CARD_COUNT = 52;
	
	/**
	* Constructor that creates a deck of 52 cards in some predetermined order with aces as low card.
	**/
	public Deck () {
		this(false);
	}
	
	/**
	* Constructor that creates a deck of 52 cards in some predetermined order.
	* If acesHigh is true, then the Ace will be treated as high card for sake of comparison, otherwise it will be
	* low card.
	**/
	public Deck(boolean acesHigh) {
		initializeDeck();
		wantShuffled = false;
		this.acesHigh = acesHigh;
		// Initialized with a bit of entropy, should be plenty for our purposes.
		// If more entropy is really needed (I doubt it!) java.security.SecureRandom could be used.
		randoms = new Random(System.nanoTime());
	}
	
	/**
	* Shuffles the deck into some random order. If the deck has been dealt from it will be re-created with all 
	* 52 cards. Thus the case of shuffling a partial deck is not considered.
	* 
	**/
	public void shuffle() {
		wantShuffled = true;
		if (cardCount() != CARD_COUNT) {
			initializeDeck();
		}
	}
	
	/**
	* Deals out a single card. If the deck is exhausted, i.e., all 52 cards have already been dealt, then null 
	* is returned.
	**/
	public Card dealOneCard() {
		Card card = null;
		if (cardCount() > 0) {
			int index = wantShuffled ? randoms.nextInt(cardCount()) : 0;
			card = cards.get(index);
			cards.remove(index);
		} 
		
		return card;
	}
	
	/**
	 * Returns the total number of cards in the deck. This number will go down each time a card is dealt from the
	 * deck.
	 * @return Total number of cards in the deck.
	 */
	public int cardCount() {
		return cards.size();
	}
	
	/**
     * Compares two cards based solely on Rank. Whether or not the aces are treated as high card or low card is
     * determined by how the deck was created. 
     * @return An int value with identical semantics to the Comparable interface.
     */
	public int compareCards(Card first, Card second) {
		// if acesHigh is set then we use the value stored with the enum Card.Rank to do the comparison
		// else we just use the enums natural ordering
		return acesHigh ? (first.getRank().value() - second.getRank().value()) : 
						   first.getRank().compareTo(second.getRank());
	}
	
	/**
	* Creates a deck of 52 cards in a predetermined (unshuffled) order. This order is not guaranteed and is thus not
	* part of the public interface for the class.
	**/
	private void initializeDeck() {		
		cards = new ArrayList<Card>(CARD_COUNT);
		
		for (Suite suite : Suite.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(Card.valueOf(suite, rank));
			}
		}
	}
}