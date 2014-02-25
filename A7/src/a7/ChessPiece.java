package a7;

import java.util.Observable;

public class ChessPiece extends Observable{

	private ChessPlayer owner;
	private ChessGame game;
	private ChessPosition position;
	protected char mark;

	protected ChessPiece(ChessPlayer owner, ChessGame game,
			ChessPosition init_position) {
		this.owner = owner;
		this.game = game;
		this.position = init_position;
		addObserver(game);

		game.getBoard().placePieceAt(this, init_position);
	}

	public ChessPlayer getOwner() {
		return owner;
	}

	public ChessGame getGame() {
		return game;
	}

	public ChessPosition getPosition() {
		return position;
	}

	public void setPosition(ChessPosition new_position) {
		position = new_position;
	}

	/**
	 * if a chess piece from this owner already at destination, it is illegal
	 * 
	 * @param destination
	 * @throws IllegalMove
	 */
	public void moveTo(ChessPosition destination) throws IllegalMove {

		ChessPiece p = game.getBoard().getPieceAt(destination);

		if (p != null) {
			if (getPosition().equals(destination)
					|| game.getBoard().getPieceAt(destination).getOwner() == this.owner)
				throw new IllegalMove(this, getPosition(), destination);
		} else
			return;

	}

	// pre-cond: oppent's piece is at the destination
	public void capture(ChessPosition destination) {
		ChessPiece p = game.getBoard().getPieceAt(destination);
		if (p != null) {
			if (game.getBoard().getPieceAt(destination).getOwner() != this.owner)
				game.getBoard().removePieceFrom(destination);
		}

	}

	public char getMark() {
		return mark;
	}

	/**
	 * Check: 1. if the destination is in the straight direction of the present
	 * position; 2. if there is at least one chess piece between present
	 * position and destination
	 * 
	 * @param destination
	 *            the destination of the chess piece
	 * @return true if both conditions are satisfied
	 */
	public boolean checkStraightPiece(ChessPosition destination) {
		// check if the destination is in the straight direction of the present
		// position
		if (destination.getX() != getPosition().getX()
				&& destination.getY() != getPosition().getY())
			return false;

		// check if it is at least one chess piece between present
		// position and destination
		else if (destination.getX() == getPosition().getX()) {

			int a = Math.min(getPosition().getY(), destination.getY());
			int b = Math.max(getPosition().getY(), destination.getY());

			for (int i = a + 1; i < b; i++) {
				if (game.getBoard().getPieceAt(
						new ChessPosition(getPosition().getX(), i)) != null)
					return false;

			}

		} else if (destination.getY() == getPosition().getY()) {
			int a = Math.min(getPosition().getX(), destination.getX());
			int b = Math.max(getPosition().getX(), destination.getX());

			for (int i = a + 1; i < b; i++) {
				if (game.getBoard().getPieceAt(
						new ChessPosition(i, getPosition().getY())) != null)
					return false;

			}

		}

		return true;
	}

	/**
	 * Check: 1.if the destination is in the diagonal direction of the present
	 * position; 2.if there is at least one chess piece between present position
	 * and destination
	 * 
	 * @param destination
	 *            the destination of the chess piece
	 * @return true if both conditions are satisfied.
	 */
	public boolean checkDiagonalPiece(ChessPosition destination) {

		// check if destination is at the diagonal direction of present position
		if (Math.abs(getPosition().getX() - destination.getX()) != Math.abs(getPosition()
				.getY() - destination.getY()))
			return false;

		// check if at least one piece between current position and destination
		int d = 1;
		while (d < Math.abs(getPosition().getX() - destination.getX())) {

			if (destination.getX() > getPosition().getX()
					&& destination.getY() > getPosition().getY()) {
				if (game.getBoard().getPieceAt(
						new ChessPosition(getPosition().getX() + d, getPosition().getY()
								+ d)) != null)
					return false;
				d++;
			}

			else if (destination.getX() < getPosition().getX()
					&& destination.getY() < getPosition().getY()) {
				if (game.getBoard().getPieceAt(
						new ChessPosition(getPosition().getX() - d, getPosition().getY()
								- d)) != null)
					return false;
				d++;
			}

			else if (destination.getX() < getPosition().getX()
					&& destination.getY() > getPosition().getY()) {
				if (game.getBoard().getPieceAt(
						new ChessPosition(getPosition().getX() - d, getPosition().getY()
								+ d)) != null)
					return false;
				d++;
			}

			else if (destination.getX() > getPosition().getX()
					&& destination.getY() < getPosition().getY()) {
				if (game.getBoard().getPieceAt(
						new ChessPosition(getPosition().getX() + d, getPosition().getY()
								- d)) != null)
					return false;
				d++;
			}

		}
		return true;

	}
}

class Rook extends ChessPiece {

	public Rook(ChessPlayer owner, ChessGame game, ChessPosition init_position) {
		super(owner, game, init_position);
		if (owner == game.getPlayer1()) {
			mark = 'r';
		} else {
			mark = 'R';
		}
		
	}
	
	public String toString(){
		return "This is a rook!";
	}

	@Override
	public void moveTo(ChessPosition destination) throws IllegalMove {
		ChessPiece p = this.getGame().getBoard().getPieceAt(destination);
		ChessPosition f = this.getPosition();
		
		super.moveTo(destination);

		if (!super.checkStraightPiece(destination))
			throw new IllegalMove(this, this.getPosition(), destination);

		else {

			ChessMove chess_move = new ChessMove(this,f,destination,p);
			super.capture(destination);
			this.getGame().getBoard().removePieceFrom(this.getPosition());
			this.getGame().getBoard().placePieceAt(this, destination);
			
			setChanged();
			notifyObservers(chess_move);
			
		}
	}
}

class Bishop extends ChessPiece {
	public Bishop(ChessPlayer owner, ChessGame game, ChessPosition init_position) {
		super(owner, game, init_position);
		if (owner == game.getPlayer1()) {
			mark = 'b';
		} else {
			mark = 'B';
		}
	}
	
	public String toString(){
		return "This is a Bishop!";
	}

	@Override
	public void moveTo(ChessPosition destination) throws IllegalMove {
		ChessPiece p = this.getGame().getBoard().getPieceAt(destination);
		ChessPosition f = this.getPosition();
		
		super.moveTo(destination);

		if (!super.checkDiagonalPiece(destination))
			throw new IllegalMove(this, this.getPosition(), destination);

		else {
			super.capture(destination);
			this.getGame().getBoard().removePieceFrom(this.getPosition());
			this.getGame().getBoard().placePieceAt(this, destination);
			
			ChessMove chess_move = new ChessMove(this,f,destination,p);
			setChanged();
			notifyObservers(chess_move);
		}
	}
}

class Knight extends ChessPiece {

	public Knight(ChessPlayer owner, ChessGame game, ChessPosition init_position) {
		super(owner, game, init_position);
		if (owner == game.getPlayer1()) {
			mark = 'n';
		} else {
			mark = 'N';
		}
	}
	
	public String toString(){
		return "This is a Knight!";
	}

	@Override
	public void moveTo(ChessPosition destination) throws IllegalMove {
		super.moveTo(destination);
		ChessPiece p = this.getGame().getBoard().getPieceAt(destination);
		ChessPosition f = this.getPosition();
		
		int cur_x = this.getPosition().getX();
		int cur_y = this.getPosition().getY();
		int des_x = destination.getX();
		int des_y = destination.getY();

		// if the destination is not a 2*3 square from current position,
		// it is an illegal move
		if (Math.abs((cur_x - des_x) * (cur_x - des_x) + (cur_y - des_y)
				* (cur_y - des_y)) != 5)
			throw new IllegalMove(this, this.getPosition(), destination);

		else {
			super.capture(destination);
			this.getGame().getBoard().removePieceFrom(this.getPosition());
			this.getGame().getBoard().placePieceAt(this, destination);
			
			ChessMove chess_move = new ChessMove(this,f,destination,p);
			setChanged();
			notifyObservers(chess_move);
		}
	}
}

class Queen extends ChessPiece {
	public Queen(ChessPlayer owner, ChessGame game, ChessPosition init_position) {
		super(owner, game, init_position);
		if (owner == game.getPlayer1()) {
			mark = 'q';
		} else {
			mark = 'Q';
		}
	}
	
	public String toString(){
		return "This is the Queen!";
	}

	@Override
	public void moveTo(ChessPosition destination) throws IllegalMove {

		if (super.checkStraightPiece(destination)
				|| super.checkDiagonalPiece(destination)) {
			
			ChessPiece p = this.getGame().getBoard().getPieceAt(destination);
			ChessPosition f = this.getPosition();
			
			super.moveTo(destination);
			super.capture(destination);
			this.getGame().getBoard().removePieceFrom(this.getPosition());
			this.getGame().getBoard().placePieceAt(this, destination);
			
			ChessMove chess_move = new ChessMove(this,f,destination,p);
			setChanged();
			notifyObservers(chess_move);
		}

		else
			throw new IllegalMove(this, this.getPosition(), destination);

	}
}

class King extends ChessPiece {
	public King(ChessPlayer owner, ChessGame game, ChessPosition init_position) {
		super(owner, game, init_position);
		if (owner == game.getPlayer1()) {
			mark = 'k';
		} else {
			mark = 'K';
		}
	}
	
	public String toString(){
		return "This is the King!";
	}

	@Override
	public void moveTo(ChessPosition destination) throws IllegalMove {
		super.moveTo(destination);
		ChessPiece p = this.getGame().getBoard().getPieceAt(destination);
		ChessPosition f = this.getPosition();

		// if King moves more than 1 step, it is illegal
		if (Math.abs(destination.getX() - this.getPosition().getX()) > 1
				|| Math.abs(destination.getY() - this.getPosition().getY()) > 1)
			throw new IllegalMove(this, this.getPosition(), destination);

		else {
			super.capture(destination);
			this.getGame().getBoard().removePieceFrom(this.getPosition());
			this.getGame().getBoard().placePieceAt(this, destination);
			
			ChessMove chess_move = new ChessMove(this,f,destination,p);
			setChanged();
			notifyObservers(chess_move);
		}
	}
}

class Pawn extends ChessPiece {
	private ChessPosition begin_position;

	public Pawn(ChessPlayer owner, ChessGame game, ChessPosition init_position) {
		super(owner, game, init_position);
		this.begin_position = init_position;

		if (owner == game.getPlayer1()) {
			mark = 'p';
		} else {
			mark = 'P';
		}
	}
	
	public String toString(){
		return "This is a Pawn!";
	}

	@Override
	public void moveTo(ChessPosition destination) throws IllegalMove {

		int cur_x = this.getPosition().getX();
		int cur_y = this.getPosition().getY();
		int des_x = destination.getX();
		int des_y = destination.getY();

		// if destination equals to present spot, illegal
		if (this.getPosition().equals(destination))
			throw new IllegalMove(this, this.getPosition(), destination);

		ChessPiece p = this.getGame().getBoard().getPieceAt(destination);
		ChessPosition f = this.getPosition();

		// if Pawn is moving backward, it is illegal
		if (this.mark == 'p') { // owner is P1
			if (des_y < cur_y)
				throw new IllegalMove(this, this.getPosition(), destination);
		}

		if (this.mark == 'P') { // owner is P2
			if (des_y > cur_y)
				throw new IllegalMove(this, this.getPosition(), destination);
		}

		if (p == null) {

			// check if Pawn is move up straight forward. If destination is
			// empty, it can only go straight forward
			if (destination.getX() != this.getPosition().getX())
				throw new IllegalMove(this, this.getPosition(), destination);

			// if present position is not beginning position, it can only move 1
			// step forward
			if (this.getPosition() != begin_position) {
				if (Math.abs(des_y - cur_y) > 1)
					throw new IllegalMove(this, this.getPosition(), destination);
			}

			// if present position is not beginning position, it is allowed
			// to move forward at most 2 steps
			if (this.getPosition() == begin_position) {
				if (Math.abs(des_y - cur_y) > 2)
					throw new IllegalMove(this, this.getPosition(), destination);
			}

			// check if pawn is blocked
			if (this.mark == 'p') { //P1
				if (this.getGame().getBoard()
						.getPieceAt(new ChessPosition(cur_x, cur_y + 1)) != null)
					throw new IllegalMove(this, this.getPosition(), destination);
			}

			if (this.mark == 'P') { //P2

				if (this.getGame().getBoard()
						.getPieceAt(new ChessPosition(cur_x, cur_y - 1)) != null)
					throw new IllegalMove(this, this.getPosition(), destination);

			}

		}
		if (p != null) {
			/*
			 * if destination is occupied by piece from owner, illegal; if
			 * destination is occupied, but in front of current position,
			 * illegal; if destination is at diagonal direction and occupied by
			 * an opponent's piece, remove opponent's
			 */
			if (Math.abs(des_y - cur_y) == 1 && (des_x == cur_x))
				throw new IllegalMove(this, this.getPosition(), destination);

			if (this.getGame().getBoard().getPieceAt(destination).getOwner() == this
					.getOwner())
				throw new IllegalMove(this, this.getPosition(), destination);

			else if (this.mark == 'p') { // P1

				if (this.getGame().getBoard().getPieceAt(destination)
						.getOwner() != this.getOwner()
						&& des_y - cur_y == 1 && Math.abs(cur_x - des_x) == 1)
					this.getGame().getBoard().removePieceFrom(destination);
			}

			else if (this.mark == 'P') { // P2

				if (this.getGame().getBoard().getPieceAt(destination)
						.getOwner() != this.getOwner()
						&& cur_y - des_y == 1 && Math.abs(cur_x - des_x) == 1)
					this.getGame().getBoard().removePieceFrom(destination);
			}

		}
		

		this.getGame().getBoard().removePieceFrom(this.getPosition());
		this.getGame().getBoard().placePieceAt(this, destination);
		
		ChessMove chess_move = new ChessMove(this,f,destination,p);
		
		setChanged();
		notifyObservers(chess_move);

	}

}
