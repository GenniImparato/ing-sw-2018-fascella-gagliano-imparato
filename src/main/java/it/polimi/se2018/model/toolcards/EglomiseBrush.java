package it.polimi.se2018.model.toolcards;

import it.polimi.se2018.model.CannotExecuteToolCardActionException;
import it.polimi.se2018.model.CannotPlaceDieException;
import it.polimi.se2018.model.Model;

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
    public String action(Model model, int param1, int param2) throws CannotExecuteToolCardActionException
    {
        return "";
    }
}
