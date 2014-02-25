package a7;

import java.util.Observer;
import java.util.Observable;
import java.util.List;
import java.util.ArrayList;

public class ChessGame extends Observable implements Observer{

	private ChessBoard board;
	private ChessPlayer player1;
	private ChessPlayer player2;
	private List<ChessMove> move_log;
	
	public ChessGame(ChessPlayer player1, ChessPlayer player2) {
		this.player1 = player1;
		this.player2 = player2;
		board = new ChessBoard();
		
		move_log = new ArrayList<ChessMove>();
		
		new Rook(player1, this, new ChessPosition(0,0));
		new Knight(player1, this, new ChessPosition(1,0));
		new Bishop(player1, this, new ChessPosition(2,0));
		new Queen(player1, this, new ChessPosition(3,0));
		new King(player1, this, new ChessPosition(4,0));
		new Bishop(player1, this, new ChessPosition(5,0));
		new Knight(player1, this, new ChessPosition(6,0));
		new Rook(player1, this, new ChessPosition(7,0));

		for (int i=0; i<8; i++) {
			new Pawn(player1, this, new ChessPosition(i,1));
		}

		new Rook(player2, this, new ChessPosition(0,7));
		new Knight(player2, this, new ChessPosition(1,7));
		new Bishop(player2, this, new ChessPosition(2,7));
		new Queen(player2, this, new ChessPosition(3,7));
		new King(player2, this, new ChessPosition(4,7));
		new Bishop(player2, this, new ChessPosition(5,7));
		new Knight(player2, this, new ChessPosition(6,7));
		new Rook(player2, this, new ChessPosition(7,7));

		for (int i=0; i<8; i++) {
			new Pawn(player2, this, new ChessPosition(i,6));
		}		
	}
	
	public ChessPlayer getPlayer1() {
		return player1;
	}
	
	public ChessPlayer getPlayer2() {
		return player2;
	}

	public ChessBoard getBoard() {
		return board;
	}

	@Override
	public void update(Observable o, Object arg) {
		ChessMove move = (ChessMove) arg;
		
		move_log.add(move);
		
		setChanged();
		notifyObservers(null);
	}
	
	public int getLogSize(){
		return move_log.size();
	}
	
	public ChessMove[] getMoves(int n){
		
		ArrayList<ChessMove> last_log = new ArrayList<ChessMove>();
		
		if(move_log.size() == 0) return new ChessMove[0];
		else{
		
			if(n == 0 || Math.abs(n) > move_log.size()) return (ChessMove[])move_log.toArray(new ChessMove[move_log.size()]);
			else if(n > 0 && n <= move_log.size()) {
					ChessMove[] a = new ChessMove[n];
					for(int i=0;i<n;i++){
						a[i] = move_log.get(i);
					}
					return a;
			}
			else{
				
				for(int i = move_log.size() + n;i < move_log.size();i++){
					ChessMove move = move_log.get(i);
					last_log.add(move);
				}
				
				return (ChessMove[])last_log.toArray(new ChessMove[-n]);
			}
		}

	}
	
	/*
	 * Place the piece to the original position; Remove the piece;
	 * if there is a captured piece, place the captured piece on the board.
	 */
	public void undo(){
		if(move_log.size() == 0) return;
		
		else{
		
			ChessMove last_move = move_log.remove(move_log.size()-1);
			
			getBoard().removePieceFrom(last_move.getTo());
			getBoard().placePieceAt(last_move.getPiece(), last_move.getFrom());
		
			if(last_move.getCaptured() != null){
				getBoard().placePieceAt(last_move.getCaptured(), last_move.getTo());
			}

		}
		
		setChanged();
		notifyObservers(null);
	}
	
}
