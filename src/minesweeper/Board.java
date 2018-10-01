package minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {
	private static final long serialVersionUID = -6285691849122451748L;
	private SaperGraphics saperGraphics;
	private MineGenerator mineGenerator;
	private SaperAdapter saperAdapter;
	private HintButton hintButton;
	private Field[] fields;
	public JLabel statusBar;
	
	public int minesToMark = Commons.NUMBER_OF_MINES;
	public boolean playing;
	public boolean gameWon;
	public boolean showingHint;
	
	public Board(JLabel label, HintButton hintButton) {
		this.statusBar = label;
		this.hintButton = hintButton;
		saperGraphics = new SaperGraphics();
		setPreferredSize(new Dimension(260, 260));
		setDoubleBuffered(true);
		
		fields = new Field[Commons.NUMBER_OF_FIELDS];
		mineGenerator = new MineGenerator(this);
		newGame();
		
		saperAdapter = new SaperAdapter(this, statusBar);
		addMouseListener(saperAdapter);
	}
	
	public void newGame() {
		playing = true;
		gameWon = false;
		minesToMark = Commons.NUMBER_OF_MINES;
		hintButton.setEnabled(true);
		hintButton.clickCount = 0;
		
		for(int i = 0; i < Commons.NUMBER_OF_FIELDS; i++) {
			fields[i] = new Field();
		}		
		mineGenerator.placeMines();		
	}
	
	
	
	public void paintComponent(Graphics g) {
		int coveredFields = 0;
		int hint = -1;
		statusBar.setText("Mines to mark: " + String.valueOf(minesToMark));
		
		if(showingHint) {
			hint = hintButton.showMineField();
		}
		
		
		for(int i = 0; i < Commons.NUMBER_OF_ROWS; i++) {
			for(int j = 0; j < Commons.NUMBER_OF_COLUMNS; j++) {
				int referenceLocation = i * Commons.NUMBER_OF_COLUMNS + j;
				if(!playing) {
					//if not playing, show where the mines were
					setGameOver(g);
					if(fields[referenceLocation].hasMine()) {
						drawField(g, i, j, saperGraphics.getMine());
					} else if (fields[referenceLocation].hasMine() 
							&& fields[referenceLocation].hasFlag()) {
						drawField(g, i, j, saperGraphics.getFlag());
					} else if (!fields[referenceLocation].hasMine() 
							&& fields[referenceLocation].hasFlag()) {
							drawField(g, i, j, saperGraphics.getWrong());
					} else if (fields[referenceLocation].hasCover()) {
						drawField(g, i, j, saperGraphics.getCover());
					}
					
					
				} else {
					
					if(fields[referenceLocation].hasFlag()) {
						drawField(g, i, j, saperGraphics.getFlag());
					} else if(fields[referenceLocation].hasCover()) {
						drawField(g, i, j, saperGraphics.getCover());
						coveredFields++;
					} else if(!fields[referenceLocation].hasCover()) {
						drawField(g, i, j, 
						saperGraphics.getFieldContent()[fields[referenceLocation].getMinesAroundCount()]);
					} 
				}
				
				//display the hint
				if(showingHint)	 {
					if(referenceLocation == hint) {
						drawField(g, i, j, saperGraphics.getHint());
						showingHint = false;
					}
				}
				
			}
		}
		
		if(coveredFields == 0 && playing) {
			setGameWon(g);
		}
	}
	
	public void setGameOver(Graphics g) {
		statusBar.setText("Game Over!");
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(18.0f));
		g.drawString("Game over, double click to start", 50, 50);
		hintButton.setEnabled(false);
	}
	
	public void setGameWon(Graphics g) {
		statusBar.setText("You have won!");
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(18.0f));
		g.drawString("You win, double click to play again", 20, 50);
		gameWon = true;
		hintButton.setEnabled(false);
	}
	
	public void drawField(Graphics g, int i, int j, Image fieldImage) {
		g.drawImage(fieldImage, i*Commons.FIELD_SIZE, j*Commons.FIELD_SIZE, this);
	}
	
	public void setStatusLabel(JLabel label) {
		label.setText("Mines left: " + Integer.toString(minesToMark));
	}
	
	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}
}