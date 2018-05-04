package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Color;
import it.polimi.se2018.model.Die;

import java.util.ArrayList;

public class CLIRenderer
{

    private char[][] charMatrix;
    private Color[][] colorMatrix;
    private static final int WIDTH = 120;
    private static final int HEIGHT = 80;

    private ArrayList<CLIElement> elements;

    public CLIRenderer()
    {
        elements = new ArrayList<>();
        charMatrix  = new char[HEIGHT][WIDTH];
        colorMatrix = new Color[HEIGHT][WIDTH];
        initMatrix();

        Board b = new Board();
        b.newDie(new Die(Color.getRandomColor()), 1, 2);
        b.newDie(new Die(Color.getRandomColor()), 0, 0);
        b.newDie(new Die(Color.getRandomColor()), 3, 3);
        addElement(new CLIElementBoard(this, b, 0, 0));

        Board b2 = new Board();
        b2.newDie(new Die(Color.getRandomColor()), 3, 1);
        addElement((new CLIElementBoard(this, b2, 55, 0)));

        Board b3 = new Board();
        b3.newDie(new Die(Color.getRandomColor()), 2, 1);
        b3.newDie(new Die(Color.getRandomColor()), 2, 3);
        addElement((new CLIElementBoard(this, b3, 0, 23)));

        Board b4 = new Board();
        b4.newDie(new Die(Color.getRandomColor()), 2, 1);
        addElement((new CLIElementBoard(this, b4, 55, 23)));
    }

    public void draw()
    {
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

    private void addElement(CLIElement element)
    {
        elements.add(element);
    }

}