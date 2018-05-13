package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.Game;

public class SelectDieFromRoundTrackAction extends GameAction
{
    int round;
    int numDie;

    public SelectDieFromRoundTrackAction(int round, int numDie)
    {
        this.round = round;
        this.numDie = numDie;
    }

    @Override
    public void execute(Game game)
    {
        if(game.selectDieFromCurrentPlayerBoard(round, numDie))
        {
            executed = true;
        }
        else
        {
            executed = false;
            errorMessage = "The selected round doesn't contain any die!";
        }
    }
}
