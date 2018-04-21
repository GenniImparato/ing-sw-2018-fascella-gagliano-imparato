package it.polimi.se2018.game;

import it.polimi.se2018.game.files.SagradaSchemeCardFile;

public class Board
{
    private Matrix matrix;

    public Board()
    {
        try
        {
            matrix = new SagradaSchemeCardFile("resources/schemecards/1-Kaleidoscopic Dream.sagradaschemecard").generateMatrix();
        }
        catch(Exception e)
        {
            matrix = new Matrix();
        }
    }

    public Matrix getMatrix()
    {
        return matrix;
    }
}
