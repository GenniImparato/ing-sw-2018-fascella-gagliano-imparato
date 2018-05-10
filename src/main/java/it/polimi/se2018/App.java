package it.polimi.se2018;
import it.polimi.se2018.controller.*;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.*;
import it.polimi.se2018.view.cli.*;


public class App
{
    public static void main( String[] args )
    {
        Game game = new Game();
        CLI cli = new CLI(new Game(game));
        CLIController controller = new CLIController(cli, game);

        game.attach(cli);
        cli.attach(controller);

        cli.start();
    }
}