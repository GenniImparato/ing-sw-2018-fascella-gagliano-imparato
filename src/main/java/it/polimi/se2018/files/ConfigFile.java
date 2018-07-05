package it.polimi.se2018.files;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import it.polimi.se2018.files.exceptions.CannotReadFileException;
import it.polimi.se2018.files.exceptions.InvalidFileException;
import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.Cell;
import it.polimi.se2018.utils.Color;

public class ConfigFile extends File
{
    private int         serverTimer;
    private int         turnTimer;

    private final static String     SERVER_TIMER = "SERVER_TIMER";
    private final static String     TURN_TIMER = "TURN_TIMER";

    public ConfigFile(String filename) throws InvalidFileException, CannotReadFileException
    {
        super(filename);
        check();
        read();
    }

    //returns true if the file is valid, false if it's not
    private void check() throws CannotReadFileException, InvalidFileException
    {
        try(Scanner scanner = new Scanner(this))
        {
            while(scanner.hasNext())
            {
                if(scanner.next().equals(SERVER_TIMER) || scanner.next().equals(TURN_TIMER))
                {
                    if(!scanner.hasNext())
                        throw new InvalidFileException("File not valid!");
                    else
                    {
                        try
                        {
                            Integer.parseInt(scanner.next());
                        }
                        catch(NumberFormatException e)
                        {
                            throw new InvalidFileException("File not valid: parameter must be a number!");
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            throw new CannotReadFileException();
        }
    }

    private void read() throws CannotReadFileException
    {
        try (Scanner scanner = new Scanner(this ))
        {
            while(scanner.hasNext())
            {
                if(scanner.next().equals(SERVER_TIMER))
                    serverTimer = scanner.nextInt();

                if(scanner.next().equals(TURN_TIMER))
                    turnTimer = scanner.nextInt();
            }
        }
        catch (IOException e)
        {
            throw new CannotReadFileException();
        }
    }

    public int getServerTimer()
    {
        return serverTimer;
    }

    public int getTurnTimer()
    {
        return turnTimer;
    }
}