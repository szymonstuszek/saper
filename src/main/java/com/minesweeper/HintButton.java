package com.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

public class HintButton extends JButton {
	private static final long serialVersionUID = 8356900747292549830L;
	private Board board;
	public int clickCount;
	private Field[] referenceFields;
	private List<Integer> mineFields;
	
	Random r = new Random();

	public HintButton() {
		referenceFields = new Field[Commons.NUMBER_OF_FIELDS];
		mineFields = new ArrayList<Integer>();
	}

	public void initializeButton(Board board) {
		this.board = board;
		setText("Hint");

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.showingHint = true;
				clickCount++;
				board.repaint();

				// we are allowed to get 3 hints per game
				if (clickCount == 3) {
					setEnabled(false);
				}
			}
		});
	}

	public int showMineField() {
		//iterate through the fields, to check where the mines are
		//show to the player a mine field at random
		referenceFields = board.getFields();
		for (int i = 0; i < referenceFields.length; i++) {
			if (referenceFields[i].hasMine()) {
				mineFields.add(i);
			}
		}
		
		return mineFields.get(r.nextInt(mineFields.size()));
	}
}
