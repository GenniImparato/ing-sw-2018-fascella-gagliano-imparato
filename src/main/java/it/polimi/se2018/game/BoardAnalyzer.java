package it.polimi.se2018.game;

import java.util.ArrayList;

public class BoardAnalyzer {
    private Board board;
    public static final boolean COLOR = true;
    public static final boolean VALUE = false;

    public BoardAnalyzer(Board board) {
        this.board = board;
    }

    //return the number of sets of (val1,val2)
    public int countSets(int val1, int val2) {
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

    //return the number of sets of different colors/values
    //type is used to choose between colors/values
    public int countSets(boolean type) {
        int[] counters = new int[6];

        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLUMNS; j++) {
                Die die = board.getDie(i, j);
                if (die != null) {
                    if (type == VALUE)
                        counters[die.getValue() - 1]++;
                    else if (type == COLOR)
                        counters[die.getColor().getNum()]++;
                }
            }
        }

        int ret = counters[0];
        for (int i = 1; (i < 6 && type == VALUE) || (i < 5 && type == COLOR); i++) {
            ret = Math.min(ret, counters[i]);
        }

        return ret;
    }

    //return the number of rows with no repeated values/colors
    //type is used to choose between values or colors
    public int countRows(boolean type)
    {
        int counter = 0;

        for(int row=0; row < Board.ROWS; row++)
        {
            ArrayList<Die> dice = board.getDiceOnRow(row);

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

    //return the number of columns with no repeated values/colors
    //type is used to choose between values or colors
    public int countColumns(boolean type)
    {
        int counter = 0;

        for(int col=0; col < Board.COLUMNS; col++)
        {
            ArrayList<Die> dice = board.getDiceOnColumn(col);

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
                    ArrayList<Die> diagonalDice = board.getDiagonalAdjacentDice(row,col);
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

    //returns the sum of the values of the dice with the color passed by argument
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