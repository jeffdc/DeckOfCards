package com.nothoo.cards

import org.specs2._
import specification._

/**
 * 
 */
class CardUnitSpec extends Specification { def is =

  "This is a specification for the Card class".title	                	^
                                                                            p^
  "The Card class should:"			                                        ^ endp^
    "1) be correctly created via the factory method"						^ creation^
    "2) be equal based on Rank and Suite only"								^ equalsContract^
    "3) correctly expose Rank/Suite info"									^ rankSuite^
    "4) provide correct toString"											^ stringify^
                                                                            end
  
  def creation =															p^
    "correct creation"														! correctCreation^
     																		endbr
                                                                            
  def equalsContract =														p^
     "instances with same Rank/Suite should be equal"                   	! equality().e1^
     "instances with diff Rank/Suite should not be equal"					! equality().e2^
     "instances with same Rank/Suite should have same hashCode"				! equality().e3^
     "instances with diff Rank/Suite should have diff hashCode"				! equality().e4^
       																		endbr
       																		
  def rankSuite =															p^
    "suite set correctly"													! properties().e1^
    "rank set correctly"													! properties().e2^
     																		endbr

  def stringify =															p^
    "correct toString"														! correctToString^
    																		endbr
    																		
  def aceOfHearts = Card.valueOf(Card.Suite.HEARTS, Card.Rank.ACE)
  def kingOfSpades = Card.valueOf(Card.Suite.SPADES, Card.Rank.KING)
  
  def correctCreation = (aceOfHearts.getSuite() must_== Card.Suite.HEARTS) && (aceOfHearts.getRank() must_== Card.Rank.ACE)
  
  case class equality() {
	val card1 = aceOfHearts
	val card2 = aceOfHearts
	val card3 = kingOfSpades
	
	def e1 = card1.equals(card2)
	def e2 = !card1.equals(card3)
	def e3 = card1.hashCode == card2.hashCode
	def e4 = card1.hashCode != card3.hashCode
  }

  case class properties() {
    val card = aceOfHearts
    
    def e1 = card.getSuite must_== Card.Suite.HEARTS
    def e2 = card.getRank must_== Card.Rank.ACE
  }
  
  def correctToString = (aceOfHearts.toString() must contain("ACE")) && (aceOfHearts.toString() must contain("HEARTS"))
  
}