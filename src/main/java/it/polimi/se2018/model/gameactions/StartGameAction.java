package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.Game;

public class StartGameAction extends GameAction
{
    @Override
    public void execute(Game game)
    {
        game.startGame();
    }
}
