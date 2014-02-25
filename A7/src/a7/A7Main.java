package a7;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.JFrame;

public class A7Main {

	public static void main(String[] args) {

		ChessPlayer p1 = new ChessPlayer("P1");
		ChessPlayer p2 = new ChessPlayer("P2");

		ChessGame game = new ChessGame(p1, p2);
		
		CommandLoop cmd_loop = new CommandLoop(game);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Chess Game");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChessGameView game_view = new ChessGameView(game);
		main_frame.setContentPane(game_view);
		main_frame.pack();
		main_frame.setVisible(true);
		
		game_view.addMoveListener(cmd_loop);
		cmd_loop.run();
	}
}

class CommandLoop implements MoveListener {

	private ChessGame game;

	public CommandLoop(ChessGame game) {
		this.game = game;
	}

	public void run() {
		ChessBoard board = game.getBoard();

		Scanner s = new Scanner(System.in);

		// This pattern matches valid move command coordinates
		Pattern move_pattern = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\\s*->\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");

		while (true) {
			// Print input cursor
			System.out.print("> ");

			// Get next command from input
			String cmd = s.next();

			// Quit if "quit"
			if (cmd.equals("quit")) {
				break;
			}

			if (cmd.equals("move")) {
				// Process "move" command.
				// Match rest of line against expected form of move command
				// (i.e., something that looks like: (x,y)->(X,Y) where x/X and y/Y are digits)

				Matcher matcher = move_pattern.matcher(s.nextLine());
				if (matcher.find()) {
					// Matched correctly. Create positions from matched data
					try {
						ChessPosition from = new ChessPosition(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
						ChessPosition to = new ChessPosition(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

						// See if there is a piece at the first coordinate...
						ChessPiece p = board.getPieceAt(from);
						if (p != null) {
							// ... and if so, then move to the second coordinate.
							p.moveTo(to);
						}
					} catch (IllegalArgumentException e) {
						// Catch exception when coordinates are out of range
						System.out.println(e.getMessage());
					} catch (IllegalMove e) {
						// Catch exception when move is illegal
						System.out.println(e.getMessage());
					}
				} else {
					// Rest of line does not match expected form for move command.
					// Report this fact.
					System.out.println("Badly formed move command");
				}
			} else if (cmd.equals("log")) {
				int num = 0;
				String rest_of_line = s.nextLine().trim();
				try {
					if (!rest_of_line.equals("")) {
						num = Integer.parseInt(rest_of_line);
					}
					ChessMove[] moves = game.getMoves(num);
					for (ChessMove m : moves) {
						System.out.println(m.toString());
					}
				}
				catch (NumberFormatException e) {
					System.out.println("Badly formed move command");
				}
			} else if (cmd.equals("undo")) {
				game.undo();
			} else {
				// Report unknown command.
				System.out.println("Unknown command");
			}
		}

		// Print goodbye message.
		System.out.println("Game Over");

	}

	@Override
	public void moveAttempted(ChessMove m) {
		try {
			ChessPiece p = game.getBoard().getPieceAt(m.getFrom());
			if (p != null) {
				p.moveTo(m.getTo());
			}
		} catch (IllegalMove e) {
			System.out.println(e.toString());
		}
	}
}
