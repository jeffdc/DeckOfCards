package com.nothoo.cards;

/**
* An implementation of a classic poker-style playing card. The standard 4 suites and 14 cards are all available.
* Cards are created by the static factory valueOf() methods. 
**/	
public final class Card 
{
	public static enum Suite { HEARTS, DIAMONDS, SPADES, CLUBS };
	public static enum Rank {
		ACE(20), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), 
		QUEEN(12), KING(13);
		
		private final int value;
		
		Rank(int value) {
			this.value = value;
		}
		
		/**
		 * Returns the 
		 * @return
		 */
		public int value() {			
			return value;
		}
	};

	private Suite suite;
	private Rank rank;

	/** no external construction allowed - use static factory method valueOf **/
	private Card(Suite suite, Rank rank) {
		this.suite = suite;
		this.rank = rank;
	}
	
	/** 
	 * Factory for creation - creates a card from the given Rank and Suite. 
	 * 
	 **/
	public static Card valueOf(Suite suite, Rank rank) {		
		return new Card(suite, rank);
	}
	
	public Suite getSuite() {
		return this.suite;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	/**
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
		return this.rank + " of " + this.suite; 
	}
	
	/**
     * Equality is NOT, always, reference based, it is based on the Suite and Rank matching.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
    	// same instance - shortcut out of here
    	if (this == other) 
    		return true;
    	// wrong type
        if (!(other instanceof Card)) 
        	return false;
        
        // do our downcast as we know it is safe
        Card otherCard = (Card)other;
	
		return otherCard.suite == suite && otherCard.rank == rank;
    }
    
    /**
     * See Joshua Bloch's Effective Java (1st (pp 36) or 2nd edition (pp 45)) for explanation of hashCode algorithm
     * @return hashcode
     */
    public int hashCode() {
        int code = 17;
		code = 31 * code + suite.hashCode();
		code = 31 * code + rank.hashCode();
        
        return code;
    }    
}