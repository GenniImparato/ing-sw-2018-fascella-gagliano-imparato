package it.polimi.se2018;
import it.polimi.se2018.game.Board;
import it.polimi.se2018.output.ConsoleOutput;
import it.polimi.se2018.output.Output;


public class App 
{
    public static void main( String[] args )
    {
        Board board = new Board();
        Output output = new ConsoleOutput(board);
        output.refresh();
    }
}
