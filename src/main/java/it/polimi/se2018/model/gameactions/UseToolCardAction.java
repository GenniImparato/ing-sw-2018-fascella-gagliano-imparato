package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.CannotAddPlayerException;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.toolcards.ToolCard;
import it.polimi.se2018.model.toolcards.ToolCardVisitor;

public class UseToolCardAction extends GameAction
{
    private int param1;
    private int param2;

    public UseToolCardAction(int param1, int param2)
    {
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public void execute(Game game)
    {
        game.executeCurrentToolCardAction(param1, param2);
        executed = true;
    }
}
