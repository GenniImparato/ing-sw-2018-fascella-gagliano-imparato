package it.polimi.se2018.model.publicobjectivecards;

public interface PublicObjectiveCardVisitor
{
    int visit(ColorDiagonalsCard card);
    int visit(ColorVarietyCard card);
    int visit(ColumnColorVarietyCard card);
    int visit(ColumnShadeVarietyCard card);
    int visit(DeepShadesCard card);
    int visit(LightShadesCard card);
    int visit(MediumShadesCard card);
    int visit(RowColorVarietyCard card);
    int visit(RowShadeVarietyCard card);
    int visit(ShadeVarietyCard card);
}
