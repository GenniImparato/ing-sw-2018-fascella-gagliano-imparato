package it.polimi.se2018;
import it.polimi.se2018.controller.*;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.*;
import it.polimi.se2018.view.cli.*;


public class App
{
    public static void main( String[] args )
    {
       Game game = new Game ();
       View view = new CLI(game);
       Controller controller = new Controller (game, view);

       game.attach(view);
       view.attach(controller);


    }
}