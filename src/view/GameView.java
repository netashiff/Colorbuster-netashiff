// File:   Gameview.java
// Project: colorbuster
// Author:  Neta Shiff
// History: Version 1.1 April 15, 2022

package view;
import javax.swing.*;
import java.awt.*;

//import view.ScoreView;
//import view.ButtonView;
import model.Score;
import model.Tile;
import view.BoardView;
import java.awt.event.*;
import java.util.HashSet;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class GameView extends JFrame {
	// Create the HUD Panel
	// Create the Board
	
	
	private static final long serialVersionUID = 1L;
	private ScoreView scoreView;
	private ButtonView buttonView;
	private BoardView boardView;

	
	// Setting defaul to 8x8
	
	int rows = 8, cols = 8;
	int width, height;
	

	ActionListener newGameListener;
	ActionListener tileTouchedListener;
	ActionListener newlevellistener;
	
	/**
	 * @param title
	 * @throws HeadlessException
	 */
	
	
	public GameView(String title,Score score,  MouseListener listener, ActionListener newGameListener,
					ActionListener tileTouched,ActionListener levellistener) throws HeadlessException {
		super(title);
		setResizable(false);
		
		width = 400;
		height = 500;
//		this.rows = rows;
//		int rows, int cols,
//		this.cols = cols;
		newgamecheck();
		this.newGameListener = newGameListener;
		this.tileTouchedListener = tileTouched;
		this.newlevellistener = levellistener;
		
		// Set up some reasonable sizes for the gameview
		
		setBounds(100,50,width, height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		scoreView = new ScoreView();
		scoreView.displayscore(score);
    	add(scoreView, BorderLayout.NORTH);
		
    	buttonView = new ButtonView(newGameListener,newlevellistener);
    	add(buttonView, BorderLayout.SOUTH);
		
		boardView = new BoardView(rows,cols, tileTouchedListener);
		add(boardView, BorderLayout.CENTER);
		
		setVisible(true);
				
	}

	private void newgamecheck()
	{
		JTextField rowField = new JTextField(5);
		JTextField colField = new JTextField(5);
		JTextField matchField = new JTextField(5);
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("row:"));
		myPanel.add(rowField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("col:"));
		myPanel.add(colField);
//		myPanel.add(new JLabel("minmatch size:"));
//		myPanel.add(matchField);

		int result = JOptionPane.showConfirmDialog(null, myPanel,
				"Please Enter new sizes", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if(!rowField.getText().equals("")&&!colField.getText().equals("")){
					//&&!matchField.getText().equals("")) {
				int rows = (Integer.parseInt(rowField.getText()));
				int cols = (Integer.parseInt(colField.getText()));
//				int match = (Integer.parseInt(matchField.getText()));
				if (rows <= 12 && rows >= 5 && cols <= 12 && cols >= 5 ){
						//&& match >= 3 && match <= 6) {
					this.rows = rows;
					this.cols = cols;
//					boardView.setminmatch(3);
				}
				else {
					System.out.println("start new game-using defualt setting 3,6,6");
					this.rows = 6;
					this.cols = 6;
//					boardView.setminmatch(3);
				}
			}
			else{
				System.out.println("start new game-using defualt setting 3,6,6");
				this.rows= 6;
				this.cols= 6;
//				boardView.setminmatch(3);

			}

		}
	}

	// Delegate to boardView
	public boolean isMoveAvailable() {
		return boardView.isMoveAvailable();
	}
	
	// Call this method to start a new Game
	
	public void newGame(Score score) {
		// Recreate some components and update the GUI.

		Container c = getContentPane();
		c.remove(boardView);
		c.invalidate();
		pack();
		//newgamecheck();
		boardView = null;
		score.setScore(0);
		scoreView.displayscore(score);

		boardView = new BoardView(rows,cols, tileTouchedListener);
		
		add(boardView, BorderLayout.CENTER);
		pack();
		revalidate();
		setBounds(100,50,width, height);
		
		System.out.println(boardView);  // debug
	}


	public void newsize(Score score)
	{
		String level = buttonView.getLevelSelector().getSelectedItem().toString();
		System.out.println("selected is+" +level);
		setminmatch(Integer.parseInt(level));
		newGame(score);
	}
	public HashSet<Tile> processTouchedTile(TileView tv) {
		HashSet<Tile> match =  boardView.processTouchedTile(tv);

		// You need to implement this method. It is called when a tileview is touched
		System.out.println("GameView == processing tile touch");
		
		System.out.println(boardView); // debug

		return match;
	}

	public void setminmatch(int min)
	{
		boardView.setminmatch(min);
	}
	public int getminmatch()
	{
		return boardView.getminmatch();
	}

}
