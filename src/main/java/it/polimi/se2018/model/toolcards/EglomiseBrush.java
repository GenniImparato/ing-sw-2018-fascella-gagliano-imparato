package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.CannotExecuteToolCardActionException;
import it.polimi.se2018.model.CannotPlaceDieException;
import it.polimi.se2018.model.Game;

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
    public String action(Game game, int param1, int param2) throws CannotExecuteToolCardActionException
    {
        try
        {
            game.getCurrentPlayer().getBoard().moveDie(game.getSelectedDie(), param1, param2, false, true);
            return "moved a die ( " + game.getSelectedDie().getValue() + ", " + game.getSelectedDie().getColor() + " )"
                    + " to (row: " + param1 + ", col: " + param2 + " )";
        }
        catch (CannotPlaceDieException e)
        {
            throw new CannotExecuteToolCardActionException(e.getMessage());
        }
    }
}
