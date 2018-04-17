package it.polimi.se2018.Output;

import it.polimi.se2018.Game.Board;
import it.polimi.se2018.Game.Matrix;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Output
{
    protected Board brd;

    public Output(Board board) {brd = board;}

    //to be override
    public void refresh()
    {
    }
}
