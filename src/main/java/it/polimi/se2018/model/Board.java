package it.polimi.se2018.model;

import java.util.ArrayList;
import java.util.Random;

public class Board
{
    public static final int ROWS = 4;
    public static final int COLUMNS = 5;

    private Cell[][] cellMatrix;
    private Die[][] dieMatrix;

    public Board()
    {
        cellMatrix = new Cell[ROWS][COLUMNS];
        dieMatrix = new Die[ROWS][COLUMNS];

        initCellMatrixRandom();
    }

    public Board(Cell[][] cellMatrix)
    {
        this.cellMatrix = new Cell[ROWS][COLUMNS];
        this.dieMatrix = new Die[ROWS][COLUMNS];

        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLUMNS; col++) {
                this.cellMatrix[row][col] = new Cell(cellMatrix[row][col]);
            }
        }
    }

    //if the die is on the board returns the row, else return -1
    public int getDieRow(Die die)
    {
        for(int row=0; row < Board.ROWS; row++)
        {
            for(int col=0; col < Board.COLUMNS; col++)
            {
                if(die == getDie(row,col))
                    return row;
            }
        }
        return -1;
    }

    //if the die is on the board returns the column, else return -1
    public int getDieColumn(Die die)
    {
        for(int row=0; row < Board.ROWS; row++)
        {
            for(int col=0; col < Board.COLUMNS; col++)
            {
                if(die == getDie(row,col))
                    return col;
            }
        }
        return -1;
    }


    //return a cell given its coordinates
    // null in case of not valid coordinates values
    public Cell getCell(int row, int column)
    {
        if (checkCoordinates(row, column))
            return cellMatrix[row][column];
        else
            return null;
    }

    //return a die given its coordinates
    // null in case of not valid coordinates values or die not present
    public Die getDie(int row, int column)
    {
        if (checkCoordinates(row, column))
            return dieMatrix[row][column];
        else
            return null;
    }

    //return an ArrayList of Dice in a row
    //if index is out of range, return an empty ArrayList
    public ArrayList<Die> getDiceOnRow(int row)
    {
        if(row < 0 || row >= Board.ROWS)
            return new ArrayList<Die>();

        ArrayList<Die> ret = new ArrayList<>();
        for(int j=0; j<Board.COLUMNS; j++)
            ret.add(getDie(row,j));

        return ret;
    }

    //return an ArrayList of Dice in a column
    //if index is out of range, return an empty ArrayList
    public ArrayList<Die> getDiceOnColumn(int col)
    {
        if(col < 0 || col >= Board.COLUMNS)
            return new ArrayList<Die>();

        ArrayList<Die> ret = new ArrayList<>();
        for(int j=0; j<Board.ROWS; j++)
            ret.add(getDie(j,col));

        return ret;
    }

    //return the number of dice in the matrix
    public int getNumberOfDice()
    {
        int num = 0;

        for (int row = 0; row < Board.ROWS; row++)
        {
            for (int col = 0; col < Board.COLUMNS; col++)
            {
                if (getDie(row, col) != null)
                    num++;
            }
        }

        return num;
    }

    //add a die to the board if it's possible
    //return true if the die is added or false if it cannot be added
    public boolean addDie(Die die, int row, int column)
    {
        if (!checkCoordinates(row, column))              //check if the coordinates are valid
            return false;

        if (getCell(row, column).getRestriction().isColor() &&           //check if the color of the die is not equal to the restriction of the cell
            getCell(row, column).getRestriction().getColor() != die.getColor())
                return false;

        if (getCell(row, column).getRestriction().isValue() &&         //check if the value of the die is not equal to the restriction of the cell
            getCell(row, column).getRestriction().getValue() != die.getValue())
                return false;

        if (getDie(row, column) != null)     //check if the cell is free
            return false;

        if (getAdjacentDice(row, column).size() == 0)     //check if there is at least one adjacent die
            return false;

        if ((getNumberOfDice() == 0) &&            //if the die is the first it can only be positioned on the border
            !isOnBorder(row, column))
                return false;

        ArrayList<Die> orthogonalDice = getOrthogonalAdjacentDice(row, column);
        for (int i = 0; i < orthogonalDice.size(); i++)          //for every orthogonal adjacent dice, check if values or colors are the same
        {
            if (orthogonalDice.get(i).getColor() == die.getColor() ||
               orthogonalDice.get(i).getValue() == die.getValue())
                return false;
        }

        dieMatrix[row][column] = die;   //the die is added in the selected position
        return true;
    }


    //helpers methods

    //initialize the matrix with random restrictions
    private void initCellMatrixRandom()
    {
        Random rand = new Random();

        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS; row++) {
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

    //check if the coordinates are in a valid range
    private boolean checkCoordinates(int row, int column)
    {
        if (row < 0 || row >= Board.ROWS || column < 0 || column >= Board.COLUMNS)
            return false;
        else
            return true;
    }

    //return true if the given coordinates are on the border
    private boolean isOnBorder(int row, int column)
    {
        if (row == 0 || row == Board.ROWS - 1)
            return true;
        if (column == 0 || column == Board.COLUMNS - 1)
            return true;

        return false;
    }

    private ArrayList<Die> getOrthogonalAdjacentDice(int row, int column)
    {
        ArrayList<Die> ret = new ArrayList<>();

        if (!checkCoordinates(row, column))
            return ret;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++)
        {
            for (int colOffset = -1; colOffset <= 1; colOffset++)
            {
                if ((Math.abs(colOffset) != Math.abs(rowOffset)) &&                            //select only the orthogonal adjacent cells
                getDie(row + rowOffset, column + colOffset) != null)           //if a die is present in the cell
                        ret.add(getDie(row + rowOffset, column + colOffset));        //add it to the return list
            }
        }
        return ret;
    }

    public ArrayList<Die> getDiagonalAdjacentDice(int row, int column)
    {
        ArrayList<Die> ret = new ArrayList<>();

        if (!checkCoordinates(row, column))
            return ret;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++)
        {
            for (int colOffset = -1; colOffset <= 1; colOffset++)
            {
                if ((Math.abs(colOffset) == 1 && Math.abs(rowOffset) == 1) &&                            //select only the orthogonal adjacent cells
                        getDie(row + rowOffset, column + colOffset) != null)           //if a die is present in the cell
                    ret.add(getDie(row + rowOffset, column + colOffset));        //add it to the return list
            }
        }
        return ret;
    }

    private ArrayList<Die> getAdjacentDice(int row, int column)
    {
        ArrayList<Die> ret = new ArrayList<>();

        if (!checkCoordinates(row, column))
            return ret;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (!(rowOffset == 0 && colOffset == 0) &&                                  //select all the adjacent cell (orthogonal and diagonal)
                   getDie(row + rowOffset, column + colOffset) != null)        //if a die is present in the cell
                        ret.add(getDie(row + rowOffset, column + colOffset));  //add it to the return list
            }
        }

        return ret;
    }
}
