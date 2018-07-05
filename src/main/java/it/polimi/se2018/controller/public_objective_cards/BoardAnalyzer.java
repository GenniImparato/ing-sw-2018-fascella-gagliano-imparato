package it.polimi.se2018.controller.public_objective_cards;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.utils.Color;

import java.util.List;

/**
 * Class used to analyze a Player's Board.
 * Its methods calculate the Player's score related to his PrivateObjectiveCard
 * and to the PublicObjectiveCards
 * @author Matteo Gagliano
 */
public class BoardAnalyzer
{
    private Board board;
    public static final boolean COLOR = true;
    public static final boolean VALUE = false;

    /**
     * Constructor that creates a BoardAnalyzer related to the a Board passed by parameter
     * @param board Board to analyze
     */
    public BoardAnalyzer(Board board)
    {
        this.board = board;
    }

    /**
     * Tells how many sets of two values (val1,val2) are in the Board.
     * It counts how many val1 and val2 are on the Board and then returns the minimum of the counters.
     * @param val1 first element to count
     * @param val2 second element to count
     * @return total number of sets of (val1,val2)
     */
    public int countSets(int val1, int val2)
    {
        int counterVa11 = 0;
        int counterVal2 = 0;

        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLUMNS; j++) {
                Die die = board.getDie(i, j);
                if (die != null) {
                    if (die.getValue() == val1)
                        counterVa11++;
                    else if (die.getValue() == val2)
                        counterVal2++;
                }
            }
        }

        return Math.min(counterVa11, counterVal2);
    }

    /**
     * If type is color, it tells how many sets of different color are on the Board.
     * If type is value, it tells how many sets of different values are on the Board.
     * A set is counted only if it has all the possible values or colors.
     * @param type is used to choose between colors or values
     * @return the number of sets of different colors or values
     */
    public int countSets(boolean type)
    {
        int[] counters = new int[6];

        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLUMNS; j++) {
                Die die = board.getDie(i, j);
                if (die != null) {
                    if (type == VALUE)
                        counters[die.getValue() - 1]++;
                    else
                        counters[die.getColor().getNum()]++;
                }
            }
        }

        int ret = counters[0];
        for (int i = 1; (i < 6 && type == VALUE) || i < 5; i++) {               //the case i<5 implies that type==COLOR
            ret = Math.min(ret, counters[i]);
        }

        return ret;
    }

    /**
     * If type is color, it tells how many rows have all the possible colors.
     * If type is value, it tells how many rows have all the possible values.
     * @param type is used to choose between values or colors
     * @return the number of rows with no repeated values or colors
     */
    public int countRows(boolean type)
    {
        int counter = 0;

        for(int row=0; row < Board.ROWS; row++)
        {
            List<Die> dice = board.getDiceOnRow(row);

            boolean noRepeated = true;
                for (int i = 0; i < dice.size(); i++)
                {
                    Die die1 = dice.get(i);
                    if (die1 != null)
                    {
                        for (int j = i + 1; j < dice.size(); j++)
                        {
                            Die die2 = dice.get(j);
                            if (die2 != null)
                            {
                                if ((type == COLOR && die1.getColor() == die2.getColor()) ||
                                        (type == VALUE && die1.getValue() == die2.getValue()))
                                {
                                    noRepeated = false;
                                    break;
                                }
                            }

                        }
                    }
                }

            if(noRepeated && !dice.contains(null))
                counter++;
        }
        return counter;
    }

    /**
     * If type is color, it tells how many columns have all the possible colors.
     * If type is value, it tells how many columns have all the possible values.
     * @param type is used to choose between values or colors
     * @return the number of columns with no repeated values or colors
     */
    public int countColumns(boolean type)
    {
        int counter = 0;

        for(int col=0; col < Board.COLUMNS; col++)
        {
            List<Die> dice = board.getDiceOnColumn(col);

            boolean noRepeated = true;

            for(int i=0; i < dice.size(); i++)
            {
                Die die1 = dice.get(i);
                if(die1 != null)
                {
                    for(int j = i + 1; j < dice.size(); j++)
                    {
                        Die die2 = dice.get(j);
                        if(die2 != null)
                        {
                            if ((type == COLOR && die1.getColor() == die2.getColor()) ||
                                    (type == VALUE && die1.getValue() == die2.getValue()))
                            {
                                noRepeated = false;
                                break;
                            }
                        }
                    }
                }
            }

            if(noRepeated && !dice.contains(null))
                counter++;
        }
        return counter;
    }


    /**
     * Tells how many diagonals of a color are present in the Board.
     * A diagonal is counted if two dice are adjacent diagonally and have the same color
     * @return number of colored diagonals
     */
    public int countColorDiagonals()
    {
        int counter = 0;
        boolean[][] counted = new boolean[Board.ROWS][Board.COLUMNS];

        for(int row=0; row < Board.ROWS; row++)
        {
            for(int col=0; col < Board.COLUMNS; col++)
            {
                Die die = board.getDie(row, col);
                if (die != null)
                {
                    List<Die> diagonalDice = board.getDiagonalAdjacentDice(row,col);
                    for(int j=0; j < diagonalDice.size(); j++)
                    {
                        if(die.getColor() == diagonalDice.get(j).getColor())
                        {
                            if(!counted[row][col])
                            {
                                counter++;
                                counted[row][col] = true;
                            }
                            int x = board.getDieRow(diagonalDice.get(j));
                            int y = board.getDieColumn(diagonalDice.get(j));
                            if(!counted[x][y])
                            {
                                counter++;
                                counted[x][y] = true;
                            }
                        }
                    }
                }
            }
        }

        return counter;
    }

    /**
     * Sums all the dice of a color passed by parameter that have been placed on the Board
     * @param color is used to select the color of the dice to count
     * @return the sum of the values of the dice with the color passed by argument
     */
    public int sumValuesOfColor (Color color)
    {
        int counter = 0;

        for (int row=0; row<Board.ROWS; row++)
        {
            for (int col=0; col<Board.COLUMNS; col++)
            {
                Die die = board.getDie(row,col);
                if (die!=null && die.getColor() == color)
                    counter += die.getValue();
            }
        }

        return counter;
    }
}