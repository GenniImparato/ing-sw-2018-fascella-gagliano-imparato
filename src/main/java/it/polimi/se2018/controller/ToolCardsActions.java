package it.polimi.se2018.controller;

import it.polimi.se2018.model.toolcards.*;

import java.util.ArrayList;
import java.util.List;

public class ToolCardsActions implements ToolCardVisitor
{
    private Controller          controller;
    private int                 step;

    public ToolCardsActions(Controller controller)
    {
        this.controller = controller;
        step = 0;
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
                step++;
                controller.getView().showDraft();
                break;
            case 1:
                step++;
                controller.getView().showToolCardIncrementDie();
                break;
            case 2:
                controller.getView().showAddDie();
                controller.endToolCardActions();
                break;
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