package it.polimi.se2018;
import it.polimi.se2018.controller.*;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.*;
import it.polimi.se2018.view.cli.*;


public class App
{
    public static void main( String[] args )
    {
        new CLIController (new CLI());
    }
}