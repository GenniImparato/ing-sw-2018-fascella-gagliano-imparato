package it.polimi.se2018.controller;

import it.polimi.se2018.model.toolcards.*;

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
        switch(step)
        {
            case 0:
                step++;
                controller.getView().showSelectDieFromBoard();
                break;
            case 1:
                step++;
                controller.setMoveDieOptions(false, true);
                controller.getView().showMoveDie();
                controller.endToolCardActions();
                break;
        }
    }

    public void  visit(CorkBackedStraightedge card)
    {

        controller.getView().showErrorMessage("Not yet Implemented!");
        controller.endToolCardActions();
    }

    public void visit(EglomiseBrush card)
    {
        switch(step)
        {
            case 0:
                step++;
                controller.getView().showSelectDieFromBoard();
                break;
            case 1:
                step++;
                controller.setMoveDieOptions(true, false);
                controller.getView().showMoveDie();
                controller.endToolCardActions();
                break;
        }
    }

    public void visit(FluxRemover card)
    {
        controller.getView().showErrorMessage("Not yet Implemented!");
        controller.endToolCardActions();
    }

    public void visit(FluxBrush card)
    {
        controller.getView().showErrorMessage("Not yet Implemented!");
        controller.endToolCardActions();
    }

    public void visit(GlazingHammer card)
    {
        controller.getView().showErrorMessage("Not yet Implemented!");
        controller.endToolCardActions();
    }

    public void visit(GrindingStone card)
    {
        controller.getView().showErrorMessage("Not yet Implemented!");
        controller.endToolCardActions();
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
                controller.getView().showIncrementDie();
                break;
            case 2:
                controller.getView().showAddDie();
                controller.endToolCardActions();
                break;
        }
    }

    public void visit(Lathekin card)
    {
        controller.getView().showErrorMessage("Not yet Implemented!");
        controller.endToolCardActions();
    }

    public void visit(LensCutter card)
    {
        controller.getView().showErrorMessage("Not yet Implemented!");
        controller.endToolCardActions();
    }

    public void visit(RunningPliers card)
    {
        controller.getView().showErrorMessage("Not yet Implemented!");
        controller.endToolCardActions();
    }

    public void visit(TapWheel card)
    {
        controller.getView().showErrorMessage("Not yet Implemented!");
        controller.endToolCardActions();
    }
}
