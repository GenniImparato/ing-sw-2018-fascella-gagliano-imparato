package it.polimi.se2018.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

        for (int row = 0; row < Board.ROWS; row++)
        {
            for (int col = 0; col < Board.COLUMNS; col++)
            {
                this.cellMatrix[row][col] = new Cell(cellMatrix[row][col]);
            }
        }
    }

    //copy constructor
    public Board(Board board)
    {
        this.cellMatrix = new Cell[ROWS][COLUMNS];
        this.dieMatrix = new Die[ROWS][COLUMNS];

        for (int row = 0; row < Board.ROWS; row++)
        {
            for (int col = 0; col < Board.COLUMNS; col++)
            {
                this.cellMatrix[row][col] = new Cell(board.getCell(row, col));

                Die die = board.getDie(row, col);
                if(die != null)
                    this.dieMatrix[row][col] = new Die(die);
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
    public List<Die> getDiceOnRow(int row)
    {
        if(row < 0 || row >= Board.ROWS)
            return new ArrayList<>();

        List<Die> ret = new ArrayList<>();
        for(int j=0; j<Board.COLUMNS; j++)
            ret.add(getDie(row,j));

        return ret;
    }

    //return an ArrayList of Dice in a column
    //if index is out of range, return an empty ArrayList
    public List<Die> getDiceOnColumn(int col)
    {
        if(col < 0 || col >= Board.COLUMNS)
            return new ArrayList<>();

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

    private void canDieBePlaced(Die die, int row, int column, boolean ignoreValueRestriction, boolean ignoreColorRestriction) throws CannotAddDieException
    {
        if (!checkCoordinates(row, column))              //check if the coordinates are valid
            throw new CannotAddDieException("Cannot place die: invalid coordinates!");

        if (getCell(row, column).getRestriction().isColor() &&           //check if the color of the die is not equal to the restriction of the cell
                getCell(row, column).getRestriction().getColor() != die.getColor()
                && !ignoreColorRestriction)
            throw new CannotAddDieException("Cannot place die: the selected cell requires a " + getCell(row, column).getRestriction().getColor() + " die!");

        if (getCell(row, column).getRestriction().isValue() &&         //check if the value of the die is not equal to the restriction of the cell
                getCell(row, column).getRestriction().getValue() != die.getValue()
                && !ignoreValueRestriction)
            throw new CannotAddDieException("Cannot place die: the selected cell requires a die with a value of " + getCell(row, column).getRestriction().getValue() + "!");

        if (getDie(row, column) != null)     //check if the cell is free
            throw new CannotAddDieException("Cannot place die: the selected cell is not free!");

        if (getAdjacentDice(row, column).size() == 0  && getNumberOfDice()>0)     //check if there is at least one adjacent die and the die is not the first
            throw new CannotAddDieException("Cannot place die: no adjacent dice!");

        if ((getNumberOfDice() == 0) &&            //if the die is the first it can only be positioned on the border
                !isOnBorder(row, column))
            throw new CannotAddDieException("Cannot place die: the first die must be on the border!");

        ArrayList<Die> orthogonalDice = getOrthogonalAdjacentDice(row, column);
        for (int i = 0; i < orthogonalDice.size(); i++)          //for every orthogonal adjacent dice, check if values or colors are the same
        {
            if (orthogonalDice.get(i).getColor() == die.getColor() ||
                    orthogonalDice.get(i).getValue() == die.getValue())
                throw new CannotAddDieException("Cannot place die: another die with same value or color is adjacent!");
        }
    }
    //try to add die at the given position if its possible
    //if the die cannot be added throws a CannotAddDieException
    public void addDie(Die die, int row, int column) throws CannotAddDieException
    {
        try
        {
            canDieBePlaced(die, row, column, false, false);
            dieMatrix[row][column] = die;   //the die is added in the selected position if it can be placed
        }
        catch (CannotAddDieException e)
        {
            throw e;
        }
    }

    public void moveDie(Die die, int row, int column, boolean ignoreValueRestriction, boolean ignoreColorRestriction) throws CannotAddDieException
    {
        try
        {
            canDieBePlaced(die, row, column, ignoreValueRestriction, ignoreColorRestriction);
            dieMatrix[row][column] = die;   //the die is added in the selected position if it can be placed
            dieMatrix[getDieRow(die)][getDieColumn(die)] = null; //the die is removed from it's previous position
        }
        catch (CannotAddDieException e)
        {
            throw e;
        }
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
