package a3;

public class DeckIteratorImpl implements DeckIterator {

	private StandardDeckImpl stdeck;

	DeckIteratorImpl(StandardDeckImpl stdeck) {
		this.stdeck = stdeck;

	}

	/*
	 * if the size of deck is not 0, then the deck has next card, which is at
	 * the top of the deck
	 */

	@Override
	public boolean hasNext() {
		if (stdeck.getSize() == 0)
			return false;
		else
			return true;
	}

	/*
	 * return the card at top associated with the deck, then remove it.
	 * The precondition is this.hasNext() is true.
	 */
	@Override
	public Card next() {
		if(!hasNext()) 
			throw new RuntimeException("This deck is empty and has no next card.");
		else
			return stdeck.takeTop();
	}

}
