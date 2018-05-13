package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.CannotExecuteToolCardActionException;
import it.polimi.se2018.model.CannotPlaceDieException;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.Player;

public class CopperFoilBurnisher extends ToolCard
{
    public CopperFoilBurnisher()
    {
        super ("Copper Foil Burnisher", "Move any one die in your window ignoring value restrictions. You must obey all other placement restrictions", 3);
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
            game.getCurrentPlayer().getBoard().moveDie(game.getSelectedDie(), param1, param2, true, false);
            return "moved a die ( " + game.getSelectedDie().getValue() + ", " + game.getSelectedDie().getColor() + " )"
                    + " to (row: " + param1 + ", col: " + param2 + " )";
        }
        catch (CannotPlaceDieException e)
        {
            throw new CannotExecuteToolCardActionException(e.getMessage());
        }
    }
}