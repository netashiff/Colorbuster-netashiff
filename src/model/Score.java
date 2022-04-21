// File:   Score.java
// Project: ColorbusterShiff
// Author:  Neta Shiff
// History: Version 1.1 April 5, 2022
// this class is the basic score class

package model;

public class Score {
    private int current;
    private int higetscore;
    private int Person_id;
    private boolean check = false;

    public Score()
    {
        current = 0;
    }
//    public Score(int id)
//    {
//        this.higetscore=Database.gethighest(id);
//        current =0;
//    }

    public int getCurrent() {
        return current;
    }

    /**
     *adding score according match size
     * @param matchsize
     */
    public void addscore(int matchsize)
    {
        if(matchsize == 3)
            this.current  += 5*matchsize;
        if(matchsize ==4)
            this.current += 10*matchsize;
        if(matchsize ==5)
            this.current += 15*matchsize;
        if(matchsize >= 6)
            this.current += 20*matchsize;

    }

    /**
     * which take off points from the score
     */
    public void reduceScore(){
        if (current > 20)
            this.current-= 10;
        else
            System.out.println("please try again for matching colors");
    }

    /**
     * adding or reducing score according to the tile
     * @param match if there were a match
     * @param tile_num the match size
     */
    public void updatescore(boolean match, int tile_num)
    {
        if(match)
        {
            addscore(tile_num);
        }
        else
            reduceScore();
    }
    public void setScore(int score){
        this.current = score;
    }


//    /**
//     *
//     */
//    public void check_highest()
//    {
//        if (this.higetscore< current) {
//            this.higetscore = current;
//            Database.checkhighest(id,higetscore);
//        }
//        if(!check)
//        {
//            System.out.println("New Highest Score!");
//            check = true;
//        }
//    }



}
