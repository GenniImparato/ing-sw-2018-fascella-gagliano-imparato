package it.polimi.se2018.controller;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.publicobjectivecards.*;

public class PublicObjectiveCardScorer implements PublicObjectiveCardVisitor
{
    private Board board;

    public PublicObjectiveCardScorer(Board board)
    {
        this.board = board;
    }

    public int visit(ColorDiagonalsCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countColorDiagonals();
    }

    public int visit(ColorVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(BoardAnalyzer.COLOR) * card.getPoints();
    }

    public int visit(ColumnColorVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countColumns(BoardAnalyzer.COLOR) * card.getPoints();
    }

    public int visit(ColumnShadeVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countColumns(BoardAnalyzer.VALUE) * card.getPoints();
    }

    public int visit(DeepShadesCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(5,6) * card.getPoints();
    }

    public int visit(LightShadesCard card)
    {
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);
        return boardAnalyzer.countSets(1, 2) * card.getPoints();
    }

    public int visit(MediumShadesCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(3,4) * card.getPoints();
    }

    public int visit(RowColorVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countRows(BoardAnalyzer.COLOR) * card.getPoints();
    }

    public int visit(RowShadeVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countRows(BoardAnalyzer.VALUE) * card.getPoints();
    }

    public int visit(ShadeVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(BoardAnalyzer.VALUE) * card.getPoints();
    }
}
