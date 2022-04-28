package view;

import model.DataBase;

import javax.swing.*;
import java.sql.SQLException;

public class DatabaseView {

    private DataBase db;
    DatabaseView()
    {
        db = new DataBase();
    }
    private String findname()
    {
        JTextField namefield = new JTextField(5);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Name:"));
        myPanel.add(namefield);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter your name", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if(!namefield.getText().equals("")){
                //&&!matchField.getText().equals("")) {
                String name = (namefield.getText());
                return name;
            }
            else{
                System.out.println("user name wasnt enter");

            }
        }
        return "non";
    }
    public void endgame(int score) throws SQLException {
        String name = findname();
        if(name.equals("non"))
        {
            System.out.println("the user entered a non regustered name the end using the default name default name");
            db.gameover("default",score);
        }
        else{
            db.gameover(name,score);
        }
    }
}
