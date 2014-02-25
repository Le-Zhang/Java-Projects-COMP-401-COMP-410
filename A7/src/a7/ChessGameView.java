package a7;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class ChessGameView extends JPanel implements Observer,MouseListener {
	private ChessGame game;
	private BoardSpot board[][];
	private JPanel square_panel;
	private List<MoveListener> moveListeners;
	private boolean isFirstClick;
	private ChessPosition from;
	private ChessPosition to;
	private ChessPiece piece;
	private ChessMove move;

	public ChessGameView(ChessGame game) {
		this.game = game;
		board = new BoardSpot[8][8];
		moveListeners = new ArrayList<MoveListener>();
		isFirstClick = true;

		setLayout(new BorderLayout());

		JPanel p1_panel = new JPanel();
		p1_panel.setLayout(new BorderLayout());
		JLabel p1 = new JLabel("P1");
		p1.setHorizontalAlignment(SwingConstants.CENTER);
		p1_panel.add(p1);

		JPanel p2_panel = new JPanel();
		p2_panel.setLayout(new BorderLayout());
		JLabel p2 = new JLabel("P2");
		p2.setHorizontalAlignment(SwingConstants.CENTER);
		p2_panel.add(p2);

		square_panel = new JPanel();
		buildLabel();
		square_panel.setLayout(new GridLayout(8, 8));
		
		add(square_panel,BorderLayout.CENTER);
		add(p1_panel,BorderLayout.NORTH);
		add(p2_panel,BorderLayout.SOUTH);
		
		game.addObserver(this);

	}

	private void buildLabel() {
		for (int j = 7; j>=0; j--) {
			for (int i = 0; i<8; i++) {
				board[i][j] = new BoardSpot(new ChessPosition(i, j));

				if ((i + j) % 2 == 0) {
					board[i][j].setBackground(Color.WHITE);
				} else {
					board[i][j].setBackground(Color.GRAY);
				}

				if (game.getBoard().getPieceAt(board[i][j].getPosition()) != null) {
					String newMark = Character.toString(game.getBoard()
							.getPieceAt(board[i][j].getPosition()).getMark());
					board[i][j].setText(newMark);
				} else {
					board[i][j].setText(" ");
				}
				
				board[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				
				square_panel.add(board[i][j]);
				board[i][j].setOpaque(true);
				
				board[i][j].addMouseListener(this);

			}
		}
	}
	

	public void addMoveListener(MoveListener l) {
		moveListeners.add(l);

	}

	public void removeMoveListener(MoveListener l) {
		moveListeners.remove(l);
	}

	@Override
	public void update(Observable o, Object arg) {
		square_panel.removeAll();
		buildLabel();
		square_panel.revalidate();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		BoardSpot b = (BoardSpot)e.getSource();
		
		if(isFirstClick && b.getPosition() != null) {
			from = b.getPosition();
			piece = b.getPiece();
			b.setText(" ");
			isFirstClick = false;
		} 
		else if(!isFirstClick){
				to = b.getPosition();
				move = new ChessMove(piece, from, to, game.getBoard().getPieceAt(to));
					
				for(MoveListener l: moveListeners){
						l.moveAttempted(move);
				}
					
				isFirstClick = true;
				
		}
		
	
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
