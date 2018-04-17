package it.polimi.se2018;
import it.polimi.se2018.game.Game;
import it.polimi.se2018.output.ConsoleOutput;
import it.polimi.se2018.output.Output;


public class App 
{
    public static void main( String[] args )
    {
        Game.getInstance();
        Output output = new ConsoleOutput();
        output.refresh();
    }
}
