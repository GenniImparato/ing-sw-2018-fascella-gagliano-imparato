package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.CannotAddPlayerException;
import it.polimi.se2018.model.Game;

public class AddNewPlayerAction extends GameAction
{
    private String nickname;

    public AddNewPlayerAction(String nickname)
    {
        this.nickname = nickname;
    }

    @Override
    public void execute(Game game)
    {
        try
        {
            game.addNewPlayer(nickname);
            executed = true;
        }
        catch(CannotAddPlayerException e)
        {
            errorMessage = e.getMessage();
        }
    }
}
