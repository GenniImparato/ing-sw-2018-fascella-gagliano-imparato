package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.Game;

public class SelectDieFromBoardAction extends GameAction
{
    private int row;
    private int col;

    public SelectDieFromBoardAction(int row, int column)
    {
        this.row = row;
        this.col = column;
    }

    @Override
    public void execute(Game game)
    {
        if(game.selectDieFromCurrentPlayerBoard(row, col))
        {
            executed = true;
        }
        else
        {
            executed = false;
            errorMessage = "The selected cell doesn't contain any die!";
        }
    }
}
