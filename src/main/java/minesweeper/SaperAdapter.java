package minesweeper;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class SaperAdapter extends MouseAdapter {
	private Board board;
	private JLabel statusBar;

	public SaperAdapter(Board board, JLabel statusBar) {
		this.board = board;
		this.statusBar = statusBar;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int clicks = e.getClickCount();
		int x = e.getX() / Commons.FIELD_SIZE;
		int y = e.getY() / Commons.FIELD_SIZE;
		int location = x * Commons.NUMBER_OF_COLUMNS + y;

		// if a hint is being displayed, hide it
		board.showingHint = false;

		// if game has ended, start a new round with a double click
		if (!board.playing || board.gameWon) {
			if (clicks > 1) {
				board.newGame();
				board.repaint();
			}

			// otherwise check which button is pressed
		} else {

			// check if we're clicking on the board
			if (x < Commons.NUMBER_OF_COLUMNS && y < Commons.NUMBER_OF_ROWS) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					pressLeftButton(e, location);
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					pressRightButton(e, location);
				}
			}
			board.repaint();
		}
	}
	
	public void pressLeftButton(MouseEvent e, int location) {
		if (board.getFields()[location].hasCover()) {
			uncoverField(location);
		}
	}

	public void pressRightButton(MouseEvent e, int location) {
		// check if there are still mines to find
			if (!board.getFields()[location].hasFlag() && board.minesToMark > 0) {
				board.getFields()[location].setFlag();
				board.minesToMark--;
				board.setStatusLabel(statusBar);

			} else if(board.getFields()[location].hasFlag()) {
				board.getFields()[location].removeFlag();
				board.minesToMark++;
			}
	}

	public void uncoverField(int location) {
		board.getFields()[location].removeCover();
		if (board.getFields()[location].hasMine()) {
			board.playing = false;
		}
		if (board.getFields()[location].getMinesAroundCount() == 0)
			showEmptyFields(location);
	}
	
	public void checkField(int currentLocation) {
		if (board.getFields()[currentLocation].hasCover()) {
			board.getFields()[currentLocation].removeCover();
			if (board.getFields()[currentLocation].getMinesAroundCount() == 0) {
				showEmptyFields(currentLocation);
			}
		}
	}

	public void showEmptyFields(int location) {
		int currentLocation;
		int currentRow = location % Commons.NUMBER_OF_ROWS;

		currentLocation = location - Commons.NUMBER_OF_ROWS - 1;
		if (currentLocation >= 0 && currentRow > 0) {
			checkField(currentLocation);
		}

		currentLocation = location - Commons.NUMBER_OF_ROWS;
		if (currentLocation >= 0) {
			checkField(currentLocation);
		}

		currentLocation = location - Commons.NUMBER_OF_ROWS + 1;
		if (currentLocation >= 0 && currentRow < (Commons.NUMBER_OF_ROWS - 1)) {
			checkField(currentLocation);
		}

		currentLocation = location - 1;
		if (currentLocation >= 0 && currentRow > 0) {
			checkField(currentLocation);
		}

		currentLocation = location + 1;
		if (currentLocation < Commons.NUMBER_OF_FIELDS && currentRow < (Commons.NUMBER_OF_ROWS - 1)) {
			checkField(currentLocation);
		}

		currentLocation = location + Commons.NUMBER_OF_ROWS - 1;
		if (currentLocation < Commons.NUMBER_OF_FIELDS
				&& currentRow > 0) {
			checkField(currentLocation);
		}

		currentLocation = location + Commons.NUMBER_OF_ROWS;
		if (currentLocation < Commons.NUMBER_OF_FIELDS) {
			checkField(currentLocation);
		}

		currentLocation = location + Commons.NUMBER_OF_ROWS + 1;
		if (currentLocation < Commons.NUMBER_OF_FIELDS && currentRow < (Commons.NUMBER_OF_ROWS - 1)) {
			checkField(currentLocation);
		}
	}
}
