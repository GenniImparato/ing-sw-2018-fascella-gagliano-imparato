package it.polimi.se2018;
import it.polimi.se2018.Game.Board;
import it.polimi.se2018.Output.ConsoleOutput;
import it.polimi.se2018.Output.Output;


public class App 
{
    public static void main( String[] args )
    {
        Board board = new Board();
        Output output = new ConsoleOutput(board);
        output.refresh();
    }
}
