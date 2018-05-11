package it.polimi.se2018.model.toolcards;

public interface ToolCardVisitor
{
    void visit(CopperFoilBurnisher card);
    void visit(CorkBackedStraightedge card);
    void visit(EglomiseBrush card);
    void visit(FluxRemover card);
    void visit(FluxBrush card);
    void visit(GlazingHammer card);
    void visit(GrindingStone card);
    void visit(GrozingPliers card);
    void visit(Lathekin card);
    void visit(LensCutter card);
    void visit(RunningPliers card);
    void visit(TapWheel card);
}
