package it.polimi.se2018.controller;

import it.polimi.se2018.model.toolcards.*;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.views.CLIView;
import it.polimi.se2018.view.cli.views.toolcardviews.*;

public class CLIToolCardVisitor implements ToolCardVisitor
{
    private CLIView cliView;
    private CLI     cli;

    public CLIToolCardVisitor(CLI cli)
    {
        this.cli = cli;
    }

    public void visit(CopperFoilBurnisher card)
    {
        cliView = new CLICopperFoilBurnisherView(cli);
    }

    public void visit(CorkBackedStraightedge card)
    {
        cliView = new CLICorkBackedStraightedgeView(cli);
    }

    public void visit(EglomiseBrush card)
    {
        cliView = new CLIEglomiseBrushView(cli);
    }

    public void visit(FluxRemover card)
    {
        cliView = new CLIFluxBrushView(cli);
    }

    public void visit(FluxBrush card)
    {
        cliView = new CLIFluxBrushView(cli);
    }

    public void visit(GlazingHammer card)
    {
        cliView = new CLIGlazingHammerView(cli);
    }

    public void visit(GrindingStone card)
    {
        cliView = new CLIGrindingStoneView(cli);
    }

    public void visit(GrozingPliers card)
    {
        cliView = new CLIGrozingPliersView(cli);
    }

    public void visit(Lathekin card)
    {
        cliView = new CLILathekinView(cli);
    }

    public void visit(LensCutter card)
    {
        cliView = new CLILensCutterView(cli);
    }

    public void visit(RunningPliers card)
    {
        cliView = new CLIRunningPLiersView(cli);
    }

    public void visit(TapWheel card)
    {
        cliView = new CLITapWheelView(cli);
    }

    public CLIView getCliView()
    {
        return cliView;
    }
}
