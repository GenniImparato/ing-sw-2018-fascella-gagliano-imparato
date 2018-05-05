package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.*;

import java.util.ArrayList;

public class CLIRenderer
{
    private char[][]            charMatrix;
    private Color[][]           colorMatrix;

    private Game                game;
    private CLIRenderState      state;

    private static final int    WIDTH = 220;
    private static final int    HEIGHT = 35;

    private ArrayList<CLIElement> elements;

    public CLIRenderer(Game game)
    {
        elements = new ArrayList<>();
        charMatrix  = new char[HEIGHT][WIDTH];
        colorMatrix = new Color[HEIGHT][WIDTH];

        this.game = game;
    }

    public void render(CLIRenderState state)
    {
        this.state = state;
        refresh();

        for(int row=0; row < HEIGHT; row++)
        {
            for(int col=0; col < WIDTH; col++)
            {
                if(colorMatrix[row][col] != null)
                    System.out.print(colorMatrix[row][col].getConsoleString());
                else
                    System.out.print(Color.getResetConsoleString());
                System.out.print(charMatrix[row][col]);
            }
            System.out.println();
        }
    }

    public void setChar(char c, int x, int y)
    {
        charMatrix[y][x] = c;

    }

    public void setColor(Color color, int x, int y)
    {
        colorMatrix[y][x] = color;
    }

    //helper
    private void initMatrix()
    {
        for( int row=0; row < HEIGHT; row++)
        {
            for(int col=0; col < WIDTH; col++)
            {
                charMatrix[row][col] = ' ';
            }
        }
    }

    //helper
    private void addElement(CLIElement element)
    {
        elements.add(element);
    }

    //helper
    private void removeAllElements()
    {
        elements.clear();
    }

    //helper
    private void refresh()
    {
        initMatrix();

        removeAllElements();

        boolean draftPoolSelected = false;
        if(state == CLIRenderState.DRAFTPOOL_SELECTED)
            draftPoolSelected = true;

        CLIElementDraftPool cliDraftPool = new CLIElementDraftPool(this, game.getDraftPool(), 0, 0, draftPoolSelected);
        addElement(cliDraftPool);

        ArrayList<Player>   players = game.getAllPlayers();

        for(int i=0; i<players.size(); i++)
        {
            boolean boardSelected = false;

            if(players.get(i) == game.getCurrentPlayer()  &&  state == CLIRenderState.BOARD_SELECTED)
                boardSelected = true;

            addElement(new CLIElementBoard(this, players.get(i).getBoard(), i*53, cliDraftPool.getHeight()+3, boardSelected));
        }
    }

}