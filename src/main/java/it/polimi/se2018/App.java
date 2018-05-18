package it.polimi.se2018;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.files.SchemeCardLoader;
import it.polimi.se2018.files.exceptions.LoadingFilesException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.*;


public class App
{
    public static void main( String[] args )
    {
        Model model = new Model();
        View cli = new CLI(false);
        Controller controller = new Controller(model);
        controller.setView(cli);

        model.attach(cli);
        cli.attach(controller);

        controller.start();
    }
}