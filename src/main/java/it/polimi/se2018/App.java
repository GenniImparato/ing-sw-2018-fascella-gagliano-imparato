package it.polimi.se2018;
import it.polimi.se2018.game.*;
import it.polimi.se2018.output.ConsoleOutput;
import it.polimi.se2018.output.Output;



public class App 
{
    public static void main( String[] args )
    {
        Output output = new ConsoleOutput(new Game());
        output.refresh();
    }
}