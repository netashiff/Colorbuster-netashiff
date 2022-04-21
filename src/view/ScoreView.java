package view;

import model.Score;

import javax.swing.*;

public class ScoreView extends JPanel {

	private static final long serialVersionUID = 1L;

	public static JLabel scoreLabel;

	public ScoreView() {
		scoreLabel = new JLabel("Score: 0");
		add(scoreLabel);

	}

//	public void updateScore(boolean check ,int matchsize)
//	{
//
//		System.out.println("current score"+ this.score.getCurrent());
//		scoreLabel.setText("Score: " + this.score.getCurrent());
//		add(scoreLabel);
//
//	}
//	public void updateScore(int score)
//	{
//		this.score.setScore(score);
//		scoreLabel.setText("Score: " + this.score.getCurrent());
//	}
	public void displayscore(Score score)
	{
		String s = "Score " + Integer.toString(score.getCurrent());

		System.out.println("current score"+ score.getCurrent()+ "s:"+s);
		scoreLabel.setText(s);

	}
}
