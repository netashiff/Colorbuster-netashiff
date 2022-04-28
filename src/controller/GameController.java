/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashSet;

import javax.swing.JOptionPane;

import model.Score;
import model.Tile;
import view.GameView;
import view.ScoreView;
import view.TileView;
import view.DatabaseView;

import static view.ScoreView.scoreLabel;

/**
 * @author neta shiff &Frank J. Mitropoulos
 *
 */
public class GameController {
	// These aren't used, but could be depending on your gme and what you want to do
	private int gameStatus;
	
	private int moveNumber = 0;
	
	private GameView gameView;
	private ScoreView scoreview;
	private Score scoreobject;
	private DatabaseView db;
	
	

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
				// using the database view to call the database to create a list of top 10
//				try {
//					db.endgame(scoreobject.getCurrent());
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
				gameView.newGame(scoreobject);
			}
		}
		
	}
	
	// Listener used to process click on New Game Button
	
	class NewGameListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			System.out.println("Starting new game...");
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
