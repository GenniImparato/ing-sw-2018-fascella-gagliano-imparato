package it.polimi.se2018.controller;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.publicobjectivecards.*;

/**
 * Class used to calculate the score of each public objective card.
 * It has multiple visit() method due to the Visitor Pattern.
 * It uses the class BoardAnalyzer to analyze the board and to concretely calculate the score
 * of the public objective cards passed by parameter in each visit() method.
 * @author Carmelo Fascella
 * @author Matteo Gagliano
 * @author Generoso Imparato
 */
public class PublicObjectiveCardScorer implements PublicObjectiveCardVisitor
{
    private Board board;

    /**
     * Constructor that sets a Board where to calculate the score.
     * @param board This is the Board to analyze and to score
     */
    public PublicObjectiveCardScorer(Board board)
    {
        this.board = board;
    }

    /**
     * This method is used to calculate the score of the Board only caring about the color diagonals public objective card
     * @param card ColorDiagonalsCard to visit in order to calculate the public score relate to this card.
     * @return An integer representing the score of the Board relate to the color diagonals public objective card.
     */
    public int visit(ColorDiagonalsCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countColorDiagonals();
    }

    /**
     * This method is used to calculate the score of the Board only caring about the color variety public objective card
     * @param card ColorVarietyCard to visit in order to calculate the public score relate to this card.
     * @return An integer representing the score of the Board relate to the color variety public objective card.
     */
    public int visit(ColorVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(BoardAnalyzer.COLOR) * card.getPoints();
    }

    /**
     * This method is used to calculate the score of the Board only caring about the column color variety public objective card
     * @param card ColumnColorVarietyCard to visit in order to calculate the public score relate to this card.
     * @return An integer representing the score of the Board relate to the column color variety public objective card.
     */
    public int visit(ColumnColorVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countColumns(BoardAnalyzer.COLOR) * card.getPoints();
    }

    /**
     * This method is used to calculate the score of the Board only caring about the column shade variety public objective card
     * @param card ColumnShadeVarietyCard to visit in order to calculate the public score relate to this card.
     * @return An integer representing the score of the Board relate to the column shade variety public objective card.
     */
    public int visit(ColumnShadeVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countColumns(BoardAnalyzer.VALUE) * card.getPoints();
    }

    /**
     * This method is used to calculate the score of the Board only caring about the deep shades public objective card
     * @param card DeepShadesCard to visit in order to calculate the public score relate to this card.
     * @return An integer representing the score of the Board relate to the deep shades public objective card.
     */
    public int visit(DeepShadesCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(5,6) * card.getPoints();
    }

    /**
     * This method is used to calculate the score of the Board only caring about the light shades public objective card
     * @param card LightShadesCard to visit in order to calculate the public score relate to this card.
     * @return An integer representing the score of the Board relate to the light shades public objective card.
     */
    public int visit(LightShadesCard card)
    {
        BoardAnalyzer boardAnalyzer = new BoardAnalyzer(board);
        return boardAnalyzer.countSets(1, 2) * card.getPoints();
    }

    /**
     * This method is used to calculate the score of the Board only caring about the medium shades public objective card
     * @param card MediumShadesCard to visit in order to calculate the public score relate to this card.
     * @return An integer representing the score of the Board relate to the medium shades public objective card.
     */
    public int visit(MediumShadesCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(3,4) * card.getPoints();
    }

    /**
     * This method is used to calculate the score of the Board only caring about the row color variety public objective card
     * @param card RowColorVarietyCard to visit in order to calculate the public score relate to this card.
     * @return An integer representing the score of the Board relate to the row color variety public objective card.
     */
    public int visit(RowColorVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countRows(BoardAnalyzer.COLOR) * card.getPoints();
    }

    /**
     * This method is used to calculate the score of the Board only caring about the row shade variety public objective card
     * @param card RowShadeVarietyCard to visit in order to calculate the public score relate to this card.
     * @return An integer representing the score of the Board relate to the row shade variety public objective card.
     */
    public int visit(RowShadeVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countRows(BoardAnalyzer.VALUE) * card.getPoints();
    }

    /**
     * This method is used to calculate the score of the Board only caring about the shade variety card public objective card
     * @param card ShadeVarietyCard to visit in order to calculate the public score relate to this card.
     * @return An integer representing the score of the Board relate to the shade variety public objective card.
     */
    public int visit(ShadeVarietyCard card)
    {
        BoardAnalyzer boardAnalyzer =  new BoardAnalyzer(board);
        return boardAnalyzer.countSets(BoardAnalyzer.VALUE) * card.getPoints();
    }
}