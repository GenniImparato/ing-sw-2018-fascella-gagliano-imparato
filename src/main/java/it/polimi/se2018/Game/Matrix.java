package it.polimi.se2018.Game;
import java.util.Random;

public class Matrix
{
    public static final int ROWS  = 4;
    public static final int COLUMNS  = 5;

    private Cell cellMatrix[][];

    public Matrix()
    {
        cellMatrix = new Cell[ROWS][COLUMNS];
        InitCellMatrixRandom();
    }

    private void InitCellMatrixRandom()       //inizializza la matrice con restrizioni casuali
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
                }
            }
        }
    }

    //restituisce una copia di una cella date le sue coordinate
    //oppure null se i valori di row e column non sono validi
    public Cell getCell(int row, int column)
    {
        if(row>=0 && row < ROWS  && column>=0 && column<COLUMNS)
            return cellMatrix[row][column];
        else
            return null;
    }
}
