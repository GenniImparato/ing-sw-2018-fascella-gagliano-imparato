package it.polimi.se2018.model.toolcards;

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

}
