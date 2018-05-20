package it.polimi.se2018.view.cli.renderer.elements;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.view.cli.renderer.CLIRenderer;

public class CLIElementSchemeCard extends CLIElement
{
    private Board board;

    public CLIElementSchemeCard(CLIRenderer renderer, Board board, int x, int y)
    {
        super(renderer, x, y, 51, 27);
        this.board = board;
        refresh();
    }

    @Override
    //fills all the matrix and renders on the CLIRenderer
    protected void refresh()
    {
        initMatrix();
        drawName();
        drawDifficulty();
        drawOnRenderer();
        drawBoard();
    }

    private void drawBoard()
    {
        new CLIElementBoard(renderer, board, x, y, false);
    }

    private void drawName()
    {
        for(int i = 0; i < board.getSchemeCardName().length(); i++)
            charMatrix[23][i+1] = board.getSchemeCardName().charAt(i);
    }

    private void drawDifficulty()
    {
        String text = "D I F F I C U L T Y:";
        for(int i = 0; i < text.length(); i++)
            charMatrix[25][i+1] = text.charAt(i);

        for (int i=0; i<board.getDifficulty(); i++)
            charMatrix[26][i*3 +1] = 'Ø';
    }
}
