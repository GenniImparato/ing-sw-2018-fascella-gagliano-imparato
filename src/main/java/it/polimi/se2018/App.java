package it.polimi.se2018;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.files.SagradaSchemeCardFile;
import it.polimi.se2018.files.SchemeCardLoader;
import it.polimi.se2018.files.exceptions.LoadingFilesException;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.View;
import it.polimi.se2018.view.cli.*;
import it.polimi.se2018.view.gui.GUI;


public class App
{
    public static void main( String[] args )
    {
        View cli = new CLI(false);
        cli.start();

        //View gui = new GUI();


    }
}