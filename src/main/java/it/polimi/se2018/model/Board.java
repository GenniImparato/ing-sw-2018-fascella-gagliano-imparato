package it.polimi.se2018.model;

import it.polimi.se2018.model.exceptions.ChangeModelStateException;
import it.polimi.se2018.utils.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class used to represent a Board
 * @author Matteo Gagliano
 * @author Carmelo Fascella
 * @author Generoso Imparato
 */
public class Board
{
    public static final int ROWS = 4;
    public static final int COLUMNS = 5;

    private Cell[][] cellMatrix;
    private Die[][] dieMatrix;

    private String     schemeCardName;

    /**
     * Constructor that creates a Board and initializes it with random restrictions
     */
    public Board()
    {
        cellMatrix = new Cell[ROWS][COLUMNS];
        dieMatrix = new Die[ROWS][COLUMNS];
        this.schemeCardName = "";

        initCellMatrixRandom();
    }

    /**
     * Constructor that creates a Board and initializes it with values of a matrix (passed by parameter)
     * @param cellMatrix matrix associated with the Board
     */
    public Board(Cell[][] cellMatrix, String schemeCardName)
    {
        this.cellMatrix = new Cell[ROWS][COLUMNS];
        this.dieMatrix = new Die[ROWS][COLUMNS];
        this.schemeCardName = schemeCardName;

        for (int row = 0; row < Board.ROWS; row++)
        {
            for (int col = 0; col < Board.COLUMNS; col++)
            {
                this.cellMatrix[row][col] = new Cell(cellMatrix[row][col]);
            }
        }
    }

    /**
     * Copy constructor
     * @param board source instance to be cloned
     */
    public Board(Board board)
    {
        this.cellMatrix = new Cell[ROWS][COLUMNS];
        this.dieMatrix = new Die[ROWS][COLUMNS];
        this.schemeCardName = "";

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


    public String getSchemeCardName()
    {
        return this.schemeCardName;
    }

    /**
     * If the Die is on the Board returns the row of the matrix associated with the Board, else returns -1
     * @param die Die to search on the Board
     * @return row of the matrix where the Die is placed; -1 if the Die is not placed
     */
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

    /**
     * If the Die is on the Board returns the column of the matrix associated with the Board, else return -1
     * @param die Die to search on the Board
     * @return column of the matrix where the Die is placed; -1 if the Die is not placed
     */
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

    public boolean contains(Die die)
    {
        for(int row=0; row < Board.ROWS; row++)
        {
            for(int col=0; col < Board.COLUMNS; col++)
            {
                if(die == getDie(row,col))
                    return true;
            }
        }
        return false;
    }


    /**
     * Returns a Cell given its coordinates; null if the coordinates are not valid
     * @param row row of the matrix of dice associated with the Board
     * @param column column of the matrix of dice associated with the Board
     * @return Cell placed in (row,column) coordinates; null if the coordinates are not valid
     */
    public Cell getCell(int row, int column)
    {
        if (checkCoordinates(row, column))
            return cellMatrix[row][column];
        else
            return null;
    }

    /**
     * Returns a Die given its coordinates;
     * null if the coordinates are not valid or the Die is not present at (row,column) position
     * @param row row of the matrix of dice associated with the Board
     * @param column column of the matrix of dice associated with the Board
     * @return a Die placed in (row,column) coordinates;
     * null if the coordinates are not accepted or the Die is not placed
     */
    public Die getDie(int row, int column)
    {
        if (checkCoordinates(row, column))
            return dieMatrix[row][column];
        else
            return null;
    }

    /**
     * Returns an ArrayList of dice placed in a row passed by parameter;
     * if the index is out of range.sagradaschemecard, returns an empty ArrayList
     * @param row row of the matrix of dice associated with the Board
     * @return an ArrayList of dice placed in the same row of the matrix of the Board;
     * an empty ArrayList if the index is not accepted
     */
    public List<Die> getDiceOnRow(int row)
    {
        if(row < 0 || row >= Board.ROWS)
            return new ArrayList<>();

        List<Die> ret = new ArrayList<>();
        for(int j=0; j<Board.COLUMNS; j++)
            ret.add(getDie(row,j));

        return ret;
    }

    /**
     * Returns an ArrayList of Dice placed in a column passed by parameter;
     * if the index is out of range.sagradaschemecard, returns an empty ArrayList
     * @param col column of the matrix of dice associated with the Board
     * @return an ArrayList of dice placed in the same column of the matrix of the Board;
     * an empty ArrayList if the index is not accepted
     */
    public List<Die> getDiceOnColumn(int col)
    {
        if(col < 0 || col >= Board.COLUMNS)
            return new ArrayList<>();

        ArrayList<Die> ret = new ArrayList<>();
        for(int j=0; j<Board.ROWS; j++)
            ret.add(getDie(j,col));

        return ret;
    }

    /**
     * Returns the total number of dice that have been placed in the Board
     * @return number of dice added in the Board
     */
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

    private void canDieBePlaced(Die die, int row, int column, boolean ignoreValueRestriction, boolean ignoreColorRestriction) throws ChangeModelStateException
    {
        if (!checkCoordinates(row, column))              //check if the coordinates are valid
            throw new ChangeModelStateException("Cannot place die: invalid coordinates!");

        if (getCell(row, column).getRestriction().isColor() &&           //check if the color of the die is not equal to the restriction of the cell
                getCell(row, column).getRestriction().getColor() != die.getColor()
                && !ignoreColorRestriction)
            throw new ChangeModelStateException("Cannot place die: the selected cell requires a " + getCell(row, column).getRestriction().getColor() + " die!");

        if (getCell(row, column).getRestriction().isValue() &&         //check if the value of the die is not equal to the restriction of the cell
                getCell(row, column).getRestriction().getValue() != die.getValue()
                && !ignoreValueRestriction)
            throw new ChangeModelStateException("Cannot place die: the selected cell requires a die with a value of " + getCell(row, column).getRestriction().getValue() + "!");

        if (getDie(row, column) != null)     //check if the cell is free
            throw new ChangeModelStateException("Cannot place die: the selected cell is not free!");

        if (getAdjacentDice(row, column).size() == 0  && getNumberOfDice()>0)     //check if there is at least one adjacent die and the die is not the first
            throw new ChangeModelStateException("Cannot place die: no adjacent dice!");

        if ((getNumberOfDice() == 0) &&            //if the die is the first it can only be positioned on the border
                !isOnBorder(row, column))
            throw new ChangeModelStateException("Cannot place die: the first die must be on the border!");

        List<Die> orthogonalDice = getOrthogonalAdjacentDice(row, column);
        for (int i = 0; i < orthogonalDice.size(); i++)          //for every orthogonal adjacent dice, check if values or colors are the same
        {
            if (orthogonalDice.get(i).getColor() == die.getColor() ||
                    orthogonalDice.get(i).getValue() == die.getValue())
                throw new ChangeModelStateException("Cannot place die: another die with same value or color is adjacent!");
        }
    }

    /**
     * Tries to add a Die in a position decided by the given coordinates (row,column) if it is possible
     * @param die Die that can be added to the Board
     * @param row row of the matrix associated with the Board
     * @param column column of the matrix associated with the Board
     * @throws ChangeModelStateException if the die cannot be added
     */
    public void addDie(Die die, int row, int column) throws ChangeModelStateException
    {
        canDieBePlaced(die, row, column, false, false);
        dieMatrix[row][column] = die;   //the die is added in the selected position if it can be placed
    }

    public void moveDie(Die die, int row, int column, boolean ignoreValueRestriction, boolean ignoreColorRestriction) throws ChangeModelStateException
    {

        if(!contains(die))
        {
            throw new ChangeModelStateException("The die is not present on the board");
        }

        int oldRow = getDieRow(die);
        int oldCol = getDieColumn(die);

        try
        {
            dieMatrix[oldRow][oldCol] = null; //remove the die from it's current position
            canDieBePlaced(die, row, column, ignoreValueRestriction, ignoreColorRestriction);  //check if the die can be placed in the new position
            dieMatrix[row][column] = die;   //add the die in the selected position if it can be placed
        }
        catch (ChangeModelStateException e)
        {
            dieMatrix[oldRow][oldCol] = die; //if the die cannot be moved it's added again to it's old position
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

    /**
     * Checks if the coordinates are in a valid range
     * @param row row of the matrix of dice
     * @param column column of the matrix of dice
     */
    private boolean checkCoordinates(int row, int column)
    {
        if (row < 0 || row >= Board.ROWS || column < 0 || column >= Board.COLUMNS)
            return false;
        else
            return true;
    }

    /**
     * Tells if the coordinates represent a Cell placed on the border
     * @return true if the given coordinates are on the border
     */
    private boolean isOnBorder(int row, int column)
    {
        if (row == 0 || row == Board.ROWS - 1)
            return true;
        if (column == 0 || column == Board.COLUMNS - 1)
            return true;

        return false;
    }

    /**
     * Generates an ArrayList of dice placed orthogonally and adjacently to the coordinates (row,column)
     * @param row row of the matrix of dice associated with the Board
     * @param column column of the matrix of dice associated with the Board
     * @return ArrayList of orthogonal and adjacent dice present in the matrix
     */
    private List<Die> getOrthogonalAdjacentDice(int row, int column)
    {
        List<Die> ret = new ArrayList<>();

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

    /**
     * Generates an ArrayList of dice placed diagonally and adjacently to the coordinates (row,column)
     * @param row row of the matrix of dice associated with the Board
     * @param column column of the matrix of dice associated with the Board
     * @return ArrayList of diagonal and adjacent dice present in the matrix
     */
    public List<Die> getDiagonalAdjacentDice(int row, int column)
    {
        List<Die> ret = new ArrayList<>();

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

    /**
     * Generates an ArrayList of dice placed adjacently to the coordinates (row,column)
     * @param row row of the matrix of dice associated with the Board
     * @param column column of the matrix of dice associated with the Board
     * @return ArrayList of adjacent dice present in the matrix
     */
    private List<Die> getAdjacentDice(int row, int column)
    {
        List<Die> ret = new ArrayList<>();

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
