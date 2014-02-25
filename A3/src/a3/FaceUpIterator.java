package a3;

import java.util.Iterator;

public class FaceUpIterator implements DeckIterator {
	private Deck stdeck;

	FaceUpIterator(Deck stdeck) {
		this.stdeck = stdeck;
	}

	/*
	 * If the top card is not faced up, then rotate the deck until a faced-up
	 * card is on the top. Everytime the deck rotates, "a" increments itself. If
	 * "a" equals to the number of card in the deck minus 1, it indicates that
	 * it rotates around. There is no faced-up card. Then return false,
	 * otherwise, return true after rotating the deck back to the original
	 * order.
	 */
	@Override
	public boolean hasNext() {

		int a = 0;
		while (!stdeck.getTop().isFaceUp()) {
			stdeck.rotate();
			a++;
			if (a == stdeck.getSize() - 1)
				return false;
		}

		for (int i = a + 1; i <= stdeck.getSize(); i++) {
			stdeck.rotate();
		}

		return true;
	}

	/*
	 * The precondition is this.hasNext() is true
	 */
	@Override
	public Card next() {
		if (!hasNext()) {
			throw new RuntimeException(
					"There is no faced-up card in this deck.");
		}
		while (!stdeck.getTop().isFaceUp()) {
			stdeck.rotate();
		}
		return stdeck.takeTop();

	}

}
