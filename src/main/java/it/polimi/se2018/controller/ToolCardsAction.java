package it.polimi.se2018.controller;

import it.polimi.se2018.model.toolcards.*;

import java.util.ArrayList;
import java.util.List;

public class ToolCardsAction implements ToolCardVisitor
{
    private Controller          controller;
    private int                 step;

    private int                 param1;
    private int                 param2;

    public ToolCardsAction(Controller controller)
    {
        this.controller = controller;
        step = 0;
    }

    public void setParameter1(int value)
    {
        this.param1 = value;
    }

    public void setParameter2(int value)
    {
        this.param2 = value;
    }

    public void visit(CopperFoilBurnisher card)
    {

    }

    public void  visit(CorkBackedStraightedge card)
    {

    }

    public void visit(EglomiseBrush card)
    {

    }

    public void visit(FluxRemover card)
    {

    }

    public void visit(FluxBrush card)
    {

    }

    public void visit(GlazingHammer card)
    {

    }

    public void visit(GrindingStone card)
    {

    }

    public void visit(GrozingPliers card)
    {
        switch(step)
        {
            case 0:
                controller.getView().showPlayerToolCardDraftDie();
                step++;
            break;
            case 1:

        }
    }

    public void visit(Lathekin card)
    {

    }

    public void visit(LensCutter card)
    {

    }

    public void visit(RunningPliers card)
    {

    }

    public void visit(TapWheel card)
    {

    }
}
