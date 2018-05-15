package it.polimi.se2018;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.cli.*;


public class App
{
    public static void main( String[] args )
    {
        Model model = new Model();
        CLI cli = new CLI();
        Controller controller = new Controller(cli, model);

        model.attach(cli);
        cli.attach(controller);

        cli.start();
    }
}