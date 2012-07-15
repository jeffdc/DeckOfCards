package com.nothoo.cards

import org.specs2._
import specification._

/**
 * 
 */
class DeckUnitSpec extends Specification { def is =

  "This is a specification for the Deck class".title	                	^
                                                                            p^
  "The Deck class should:"			                                        ^ endp^
    "1) be correctly created via its constructor"							^ checkCreation^
    "2) without shuffling return, cards in same order"						^ noShuffle^
    "3) when shuffled, return cards randomly"								^ shuffle^
    "4) deal cards"															^ checkDeal^
                                                                            end
  
  def checkCreation =														p^
    "correct creation"														! creation().e1^
    "contain 52 cards"														! creation().e2^
     																		endbr
                                                                            
  def noShuffle =															p^
     "in the same order"								                   	! notShuffled().e1^
       																		endbr
       																		
  def shuffle =																p^
    "in random order"														! shuffled().e1^
     																		endbr

  def checkDeal =															p^
    "deal 52 cards shuffled"												! deal().e1^
    "deal null after 52 shuffled"											! deal().e2^
    "deal 52 cards not shuffled"											! deal().e3^
    "deal null after 52 not shuffled"										! deal().e4^
    																		endbr
  
  case class creation() {
	def deck = new Deck()
  
	def e1 = deck must not beNull
	def e2 = deck.cardCount must_== 52
  }  																		
  
  case class notShuffled() {
	val deck1 = new Deck()
	val deck2 = new Deck()
	
	def e1 = {
	  for (i <- 1 to 52)
	    deck1.dealOneCard() must_== deck2.dealOneCard()
      deck1.cardCount must_== 0
	}
  }

  case class shuffled() {
	val deck1 = new Deck()
	val deck2 = new Deck()
	deck2.shuffle()
	
	def e1 = {
	  for (i <- 1 to 52)
	    deck1.dealOneCard() must_!= deck2.dealOneCard()
      deck1.cardCount must_== 0
	}
  }

  case class deal() {
    val deckShuffled = new Deck()
    val deckNotShuffled = new Deck()
    deckShuffled.shuffle
    
    def e1 = {
      pending
//      for (i <- 1 to 52)
//        deckShuffled.dealOneCard() must not	beNull
//       deckShuffled.cardCount must_== 0
    }
    
    def e2 = {
      pending
//      for (i <- 1 to 52)
//        deckShuffled.dealOneCard()
//        
//       deckShuffled.dealOneCard() must beNull
    }
    
    def e3 = {
      pending
//      for (i <- 1 to 52)
//        deckNotShuffled.dealOneCard() must not beNull
//       deckNotShuffled.cardCount must_== 0
    }
    
    def e4 = {
      pending
//      for (i <- 1 to 52)
//        deckNotShuffled.dealOneCard()
//        
//       deckNotShuffled.dealOneCard() must beNull
    }
  }
   
}