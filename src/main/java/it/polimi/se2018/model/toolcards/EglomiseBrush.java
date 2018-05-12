package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.CannotAddDieException;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.Player;

public class EglomiseBrush extends ToolCard
{
    public EglomiseBrush ()
    {
        super ("Eglomise Brush","Move any one die in your window ignoring color restrictions", 2);
    }

    @Override
    public void acceptVisitor(ToolCardVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public String action(Game game, int param1, int param2)
    {
        String ret = "";
        try
        {
            game.getCurrentPlayer().getBoard().moveDie(game.getSelectedDie(), param1, param2, false, true);
        }
        catch (CannotAddDieException e)
        {
            ret = e.getMessage();
        }

        return ret;
    }
}
