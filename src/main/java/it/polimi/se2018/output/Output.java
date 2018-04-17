package it.polimi.se2018.output;

import it.polimi.se2018.game.Board;

public class Output
{
    protected Board brd;

    public Output(Board board) {brd = board;}

    //to be override
    public void refresh()
    {
    }
}
