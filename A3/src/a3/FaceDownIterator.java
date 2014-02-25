package a3;

public class FaceDownIterator implements DeckIterator {

	private Deck stdeck;

	FaceDownIterator(Deck stdeck) {
		this.stdeck = stdeck;
	}

	/*
	 * If the top card is not faced down, then rotate the deck until a
	 * faced-down card is on the top. Everytime the deck rotates, "a" increments
	 * itself. If "a" equals to the number of card in the deck minus 1, it
	 * indicates that it rotates around. There is no faced-down card. Then
	 * return false, otherwise, return true after rotating back to the original
	 * order.
	 */

	@Override
	public boolean hasNext() {
		int a = 0;
		while (stdeck.getTop().isFaceUp()) {
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
					"There is no faced-down card in this deck.");
		}
		while (stdeck.getTop().isFaceUp()) {
			stdeck.rotate();
		}
		return stdeck.takeTop();
	}

}
