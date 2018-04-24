package it.polimi.se2018.files;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import it.polimi.se2018.game.Board;
import it.polimi.se2018.game.Cell;
import it.polimi.se2018.game.Color;

public class SagradaSchemeCardFile extends File
{
    private int         difficulty;
    private Cell[][]    cellMatrix;

    public SagradaSchemeCardFile(String filename) throws Exception
    {
        super(filename);

        cellMatrix = new Cell[Board.ROWS][Board.COLUMNS];

        if(!check())
        {
            System.err.println(filename + " is not a valid file!");
            throw new Exception();
        }
        if(!read())
            throw new Exception();
    }

    //returns true if the file is valid, false if it's not
    private boolean check()
    {
        try (Scanner scanner = new Scanner(this)) 
        {
            int elements = 0;               //used to count the total number of elements in the file
                                            //to pass the check the file should have 4*5 + 1 = 21 elements

            while(scanner.hasNext())
            {
                elements++;
                if(elements>21)             //too many elements, file is not valid
                    return false;


                String buff = scanner.next();
                Integer intBuff;

                try                                         //checks if current element is a number
                {
                    intBuff = Integer.parseInt(buff);

                    if (!(intBuff >= 0 && intBuff <= 6))      //if the number is not in range the file is not valid
                        return false;
                }
                catch(NumberFormatException e)              //else if the element is not a number it has to be a valid color
                {
                    if(
                            !(buff.equals("red") ||
                            buff.equals("green") ||
                            buff.equals("purple") ||
                            buff.equals("yellow") ||
                            buff.equals("blue"))
                       )
                        return false;
                }
            }

            if(elements != 21)              //if the file doesn't contain exactly  21 elements it's not valid
                return false;
            else                            //the file has passed all the checks, it is valid
                return true;
        }
        catch (IOException e)
        {
            System.err.println("Message: " + e.getMessage());
            return false;
        }
    }

    private boolean read()
    {
        try (Scanner scanner = new Scanner(this ))
        {
            for (int row = 0; row < Board.ROWS; row++)
            {
                for (int col = 0; col < Board.COLUMNS; col++)
                {
                    String buff = scanner.next();
                    Integer intBuff;

                    try
                    {
                        intBuff = Integer.parseInt(buff);

                        if(intBuff == 0)
                            cellMatrix[row][col] = new Cell();
                        else if(intBuff > 0)
                            cellMatrix[row][col] = new Cell(intBuff);
                    }
                    catch(NumberFormatException e)
                    {
                        cellMatrix[row][col] = new Cell(Color.getColorFromString(buff));
                    }
                }
            }
            return true;
        }
        catch (IOException e)
        {
            System.err.println("Message: " + e.getMessage());
            return false;
        }
    }

    public Board generateBoard()
    {
        return new Board(cellMatrix);
    }
}
