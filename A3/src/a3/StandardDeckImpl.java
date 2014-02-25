package a3;

import java.util.ArrayList;

import a3.StandardCard.Suit;

public class StandardDeckImpl implements Deck {

	private ArrayList<Card> cards;

	StandardDeckImpl() {
		cards = new ArrayList<Card>();

		for (int i = 0; i < 13; i++) {
			cards.add(new StandardCardImpl(Suit.SPADES, i + 1, this));
			cards.add(new StandardCardImpl(Suit.HEARTS, i + 1, this));
			cards.add(new StandardCardImpl(Suit.DIAMONDS, i + 1, this));
			cards.add(new StandardCardImpl(Suit.CLUBS, i + 1, this));
		}
	}

	@Override
	public int getSize() {
		return cards.size();
	}

	@Override
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	@Override
	public Card getTop() {
		if (cards.isEmpty()) {
			throw new RuntimeException("This deck is empty.");
		} else
			return cards.get(this.getSize() - 1);
	}

	@Override
	public Card takeTop() {
		if (cards.isEmpty()) {
			throw new RuntimeException("This deck is empty");
		}
		return cards.remove(getSize() - 1);
	}

	/*
	 * If the deck is empty, just place the Card c into this deck, at top. If
	 * the deck is not empty and Card c belongs to this deck, put Card c at top.
	 * Else, will not put Card c into this deck
	 */
	@Override
	public void placeOnTop(Card c) {
		if (this.isEmpty()) {
			cards.add(c);
		} else if(c.getDeck().equals(this)){
			cards.add(c);
		} else {
			throw new RuntimeException("This card does not belong to the deck.");
		}

	}

	@Override
	public void cut(int position) {

		if (position == 0 || position >= this.getSize())
			return;
		else {
			for (int i = 0; i < (this.getSize() - position); i++) {
				this.rotate();
			}
		}

	}

	@Override
	public void rotate() {
		cards.add(0, this.takeTop());

	}

	@Override
	public DeckIterator iterator() {
		DeckIterator iter =new DeckIteratorImpl(this);
		return iter;
	}

	@Override
	public DeckIterator faceUpIterator() {
		FaceUpIterator iter = new FaceUpIterator(this);
		return iter;
	}

	@Override
	public DeckIterator faceDownIterator() {
		FaceDownIterator iter = new FaceDownIterator(this);
		return iter;
	}

	@Override
	public void reset() {
		cards.removeAll(cards);
		
		for (int i = 0; i < 13; i++) {
			cards.add(new StandardCardImpl(Suit.SPADES, i + 1, this));
			cards.add(new StandardCardImpl(Suit.HEARTS, i + 1, this));
			cards.add(new StandardCardImpl(Suit.DIAMONDS, i + 1, this));
			cards.add(new StandardCardImpl(Suit.CLUBS, i + 1, this));
		}
		
	}


}



