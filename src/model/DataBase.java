package model;

import java.sql.*;
// File:   Database.java
// Project: ColorbusterShiff
// Author:  Neta Shiff
// History: Version 1.1 April 20, 2022
// this class is the database class 0.1

public class DataBase {
    private final String DB_Name = "test1.db";

    private int Id = 000;
    protected int highestscore;

    public DataBase() {
        createtables();

    }


    /**
     * create the table for the create table
     */
    private void createtables() {
        // Open a connection
        try (
                // Class.forName("org.sqlite.JBDC");
                Connection conn = DriverManager.getConnection(this.DB_Name);
             Statement stmt = conn.createStatement();) {
            String sql2 = "CREATE TABLE Highscores " +
                    "name VARCHAR(255) NOT NULL," +
                    "score INTEGER NOT NULL" +
                    "id INTEGER not NULL," +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql2);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int ranking_calc(int score) {
        try {
            Connection conn = DriverManager.getConnection(this.DB_Name);
            Statement stat = conn.createStatement();
            String sql = "SELECT id, name, RANK() OVER(ORDER BY score) as 'Rank' from Highscore;";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                if ((rs.getInt("Rank") <= 10)) {
                    System.out.println("the rank is:"+ rs.getInt("Rank")+"the score is "+ score);
                    return rs.getInt("Rank");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 50;
    }


    public int topscore(int score) {
        try {
            Connection conn = DriverManager.getConnection(this.DB_Name);
            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM Highscore;";
            ResultSet rs = stat.executeQuery(sql);
            boolean check = false;
            int place = 11;
            while (rs.next()) {
                if (rs.getInt("score") < score) {
                    place--;
                    check = true;
                }
            }
            return place;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 50;
    }

    /**
     * @param score
     * @return
     * @throws SQLException
     */
    public int gameover(String name, int score) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(this.DB_Name);
            Statement stat = conn.createStatement();
            this.Id++;
//            stat.executeUpdate("INSERT INTO Highscores(name,score,id)" + "VALUES('IL',score)");
            // inserting the new score into the table
            String sql = "";
            stat.executeUpdate("INSERT INTO Highscores VALUES(?,?,?)");
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, String.valueOf(score));
            pstmt.setString(3, String.valueOf(this.Id));
            int num = ranking_calc(score);
            return num;
        } catch (SQLException e) {
            System.out.println("gameover didnt work");
            return 0;
        }
    }
}


