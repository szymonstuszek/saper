package minesweeper;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
	private static final long serialVersionUID = -839366922172436939L;
	private final int FRAME_WIDTH = 332;
	private final int FRAME_HEIGHT = 400;
	
	private Board board;
	private JLabel statusLabel;
	private HintButton hintButton;
	
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Saper");
		setResizable(false);
		setVisible(true);
		setLayout(new BorderLayout());
		
		statusLabel = new JLabel("");
		hintButton = new HintButton();
		board = new Board(statusLabel, hintButton);
		hintButton.initializeButton(board);
		
		JPanel bottomPanel = new JPanel();
		
		bottomPanel.add(statusLabel);
		bottomPanel.add(hintButton);
		
		add(board, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}
}
