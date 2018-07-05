package it.polimi.se2018.files;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import it.polimi.se2018.files.exceptions.CannotReadFileException;
import it.polimi.se2018.files.exceptions.InvalidFileException;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Cell;
import it.polimi.se2018.utils.Color;

/**
 * This class is used to manage the sagrada scheme cards files
 */
public class SagradaSchemeCardFile extends File
{
    private int         difficulty;
    private String      schemeName;
    private Cell[][]    cellMatrix;


    /**
     * Constructor
     * @param filename name of the file
     * @throws InvalidFileException if the file is not valid
     * @throws CannotReadFileException if the file cannot be read
     */
    public SagradaSchemeCardFile(String filename) throws InvalidFileException, CannotReadFileException
    {
        super(filename);

        cellMatrix = new Cell[Board.ROWS][Board.COLUMNS];

        check();
        read();

        this. schemeName = getSchemeName(filename);
    }

    /**
     * Checks if the file is valid
     * @throws CannotReadFileException if the file cannot be read
     * @throws InvalidFileException if the file is not vaild
     */
    private void check() throws CannotReadFileException, InvalidFileException
    {
        try(Scanner scanner = new Scanner(this))
        {
            int elements = 0;               //used to count the total number of elements in the file
                                            //to pass the check the file should have 4*5 + 1 = 21 elements

            while(scanner.hasNext())
            {
                elements++;
                if(elements>21)             //too many elements, file is not valid
                    throw new InvalidFileException("File not valid: found more than 21 many elements");


                String buff = scanner.next();
                Integer intBuff;

                try                                         //checks if current element is a number
                {
                    intBuff = Integer.parseInt(buff);

                    if (!(intBuff >= 0 && intBuff <= 6))      //if the number is not in range the file is not valid
                        throw new InvalidFileException("File not valid: found value restrictions not in range [0, 6]");
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
                        throw new InvalidFileException("File not valid: found string not representing a color");
                }
            }

            if(elements != 21)              //if the file doesn't contain exactly  21 elements it's not valid
                throw new InvalidFileException("File not valid: found less than 21 elements");

            //the file has passed all the checks, it is valid
        }
        catch (IOException e)
        {
            throw new CannotReadFileException();
        }
    }

    /**
     * Reads the file
     * @throws CannotReadFileException if file cannot be read
     */
    private void read() throws CannotReadFileException
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
                        else
                            cellMatrix[row][col] = new Cell(intBuff);
                    }
                    catch(NumberFormatException e)
                    {
                        cellMatrix[row][col] = new Cell(Color.getColorFromString(buff));
                    }
                }
            }

            difficulty = Integer.parseInt(scanner.next());
        }
        catch (IOException e)
        {
            throw new CannotReadFileException();
        }
    }

    /**
     * Returns the name of the scheme card
     * @param  filename name of the file
     * @return name of the scheme card
     */
    private String getSchemeName(String filename)
    {
        return filename.replaceAll(".*[\\\\/]|\\.[^\\.]*$","");
    }

    /**
     * Returns the board generated (associated to a scheme card)
     * @return board genereted (associated to a scheme card)
     */
    public Board generateBoard()
    {
        return new Board(cellMatrix, schemeName, difficulty);
    }

}
