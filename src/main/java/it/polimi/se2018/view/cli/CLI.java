package it.polimi.se2018.view.cli;
import it.polimi.se2018.events.Message;
import it.polimi.se2018.model.*;
import it.polimi.se2018.view.*;

import java.util.ArrayList;


public class CLI extends View
{


    public CLI (Game game)
    {
        super (game);
        System.out.println("CLI creata");
        render ();

    }

    @Override
    public void update(Message event)
    {

    }

    private void render ()
    {
        for (Player player : game.getAllPlayers())
            draw(player);
    }

    private void draw (Player player)
    {

        System.out.println("Player " +player.getNickname() + " : ");
        for (int i=0; i<Board.ROWS; i++)
        {
            for (int j=0; j<Board.COLUMNS; j++)
            {
                System.out.print ("[ ");

                CellRestriction restriction = player.getBoard().getCell(i,j).getRestriction();
                if (restriction.isColor())
                {
                    System.out.print (restriction.getColor().getConsoleString());
                    System.out.print (restriction.getColor().getFirstChar()+ " ");
                    System.out.print ("\033[0m");
                    System.out.print ("| ");
                }

                else if (restriction.isValue())
                    System.out.print(restriction.getValue() + " | ");

                else
                {
                    System.out.print("- | ");
                }

                Die die = player.getBoard().getDie(i,j);

                if (die == null)
                    System.out.print("- ] ");

                else
                {
                    System.out.print(die.getColor().getConsoleString());
                    System.out.print(die.getValue() + "." + die.getColor().getFirstChar() + " ] ");
                }

            }

            System.out.println();

        }


    }










}
