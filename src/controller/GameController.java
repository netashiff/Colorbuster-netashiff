/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JOptionPane;

import model.Score;
import model.Tile;
import view.GameView;
import view.ScoreView;
import view.TileView;

import static view.ScoreView.scoreLabel;

/**
 * @author Frank J. Mitropoulos
 *
 */
public class GameController {
	// These aren't used, but could be depending on your gme and what you want to do
	private int gameStatus;
	
	private int moveNumber = 0;
	
	private GameView gameView;
	private ScoreView scoreview;
	private Score scoreobject;
	
	

	/**
	 * Create the GameView and pass in the appropriate listeners
	 */
	public GameController() {
		super();
		scoreobject= new Score();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
			
				gameView = new GameView("Title",scoreobject,null,new NewGameListener(),
						new TileTouchedListener(), new LevelsListener());
				gameView.setVisible(true);	
				
			}
		});
		scoreview= new ScoreView();

		
	}
	
	// Listener used to process touches on TileView
	
	class TileTouchedListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			TileView tv = (TileView) event.getSource();
			System.out.println("Tile touched..." + tv.toString());
			// TODO -- you implement this
			// delegate to GameView!
			HashSet<Tile> matches = gameView.processTouchedTile(tv);
			if (matches.size() >= gameView.getminmatch()) {
				scoreobject.updatescore(true,matches.size());
				scoreview.displayscore(scoreobject);
			} else {
				scoreobject.updatescore(false,matches.size());
				scoreview.displayscore(scoreobject);
				//scoreLabel.setText("Sdcore " +scoreobject.getCurrent());
			}
			System.out.println("score: 10");
			
			
			// If no move is available display a message
			
			if (!gameView.isMoveAvailable()) {
				JOptionPane.showMessageDialog(gameView,
					    "No more moves... starting new game");
				gameView.newGame(scoreobject);
			}
		}
		
	}
	
	// Listener used to process click on New Game Button
	
	class NewGameListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			System.out.println("Starting new game...");
			System.out.println("Not yet implemented..");
			// TODO
			// You implement this method. Delegate to GameView!
			gameView.newGame(scoreobject);
		}
		
	}

	class LevelsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Starting new game...");
			System.out.println("newlevel.");
			gameView.newsize(scoreobject);
		}

	}
}
