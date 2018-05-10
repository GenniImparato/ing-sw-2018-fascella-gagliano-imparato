package it.polimi.se2018.controller.gameactions;

import it.polimi.se2018.model.CannotAddPlayerException;
import it.polimi.se2018.model.Game;

public class AddNewPlayerAction extends GameAction
{
    public AddNewPlayerAction(Game game, String nickname) throws CannotAddPlayerException
    {
        super(game);
        game.addNewPlayer(nickname);
    }
}
