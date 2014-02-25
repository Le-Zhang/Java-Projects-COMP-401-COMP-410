package a7;

import javax.swing.JLabel;

public class BoardSpot extends JLabel{
	private ChessPosition position;
	private ChessPiece piece;
	
	public BoardSpot(ChessPosition position) {
		this.position = position;
		piece = null;
	}
	
	public ChessPosition getPosition(){
		return position;
	}
	
	public void setPiece(ChessPiece piece){
		this.piece = piece;
	}
	
	public ChessPiece getPiece(){
		return piece;
	}

}
