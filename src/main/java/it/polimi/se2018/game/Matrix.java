package it.polimi.se2018.game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Matrix
{
    public static final int ROWS  = 4;
    public static final int COLUMNS  = 5;

    private Cell[][]    cellMatrix;
    private Die[][]     dieMatrix;

    public Matrix()
    {
        cellMatrix = new Cell[ROWS][COLUMNS];
        dieMatrix = new Die[ROWS][COLUMNS];

        initCellMatrixFromFile("resources/schemecards/1-Firmitas.sagradaschemecard");
    }

    private void initCellMatrixRandom()       //initialize the matrix with random restrictions
    {
        Random rand = new Random();

        for(int col = 0; col<COLUMNS; col++)
        {
            for(int row = 0; row<ROWS; row++) {
                int rest = rand.nextInt(3);

                switch (rest) {
                    case 0:
                        cellMatrix[row][col] = new Cell();
                        break;
                    case 1:
                        cellMatrix[row][col] = new Cell(rand.nextInt(5) + 1);
                        break;
                    case 2:
                        cellMatrix[row][col] = new Cell(Color.getRandomColor());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void initCellMatrixFromFile(String filename)  //initialize the matrix reading from .sagradaschemecard file
    {
        try  ( Scanner scanner = new Scanner(new File(filename)) )
        {
            for (int row = 0; row < Matrix.ROWS; row++) {
                for (int col = 0; col < Matrix.COLUMNS; col++) {
                    String buff = scanner.next();

                    if (buff.length() == 1) {
                        if (Integer.parseInt(buff) == 0)
                            cellMatrix[row][col] = new Cell();
                        else if (Integer.parseInt(buff) > 0)
                            cellMatrix[row][col] = new Cell(Integer.parseInt(buff));
                    } else
                        cellMatrix[row][col] = new Cell(Color.getColorFromString(buff));
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Message: " + e.getMessage());
        }
    }

    //return a cell given its coordinates
    // null in case of not valid coordinates values
    public Cell getCell(int row, int column)
    {
        if(row>=0 && row < ROWS  && column>=0 && column<COLUMNS)
            return cellMatrix[row][column];
        else
            return null;
    }

    //return a die given its coordinates
    // null in case of not valid coordinates values or die not present
    public Die getDie(int row, int column)
    {
        if(row>=0 && row < ROWS  && column>=0 && column<COLUMNS)
            return dieMatrix[row][column];
        else
            return null;
    }
}
