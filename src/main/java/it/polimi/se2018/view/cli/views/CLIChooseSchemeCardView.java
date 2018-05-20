package it.polimi.se2018.view.cli.views;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.exceptions.NoElementException;
import it.polimi.se2018.mvc_comunication.events.SelectSchemeCardEvent;
import it.polimi.se2018.view.cli.CLI;
import it.polimi.se2018.view.cli.renderer.CLIRendererSchemeCardChoice;

public class CLIChooseSchemeCardView extends CLIView
{
    private Board[] schemeCards;

    public CLIChooseSchemeCardView(CLI cli, Board[] schemeCards)
    {
        super(cli);
        this.schemeCards = schemeCards;
    }

    public void draw()
    {
        cli.clear();
        new CLIRendererSchemeCardChoice(cli, cli.getModel(), schemeCards).render();

        try
        {
            if(!cli.getModel().findPlayer(cli.getAssociatedPlayerNickname()).hasChoosenSchemeCard())
            {
                cli.showMessage("Choose a scheme card to play with:");
                for(int i=0; i<4; i++)
                    cli.showMessage(i + ") Choose " + schemeCards[i].getSchemeCardName());

                cli.readInputFromUser();
            }
            else
            {
                cli.showMessage("You have chosen your scheme card.");
                cli.showMessage("Waiting for other players...");
            }
        }
        catch(NoElementException e)
        {
            cli.showErrorMessage(e.getMessage());
        }


    }

    public void parseInput(String input)
    {
        try
        {
            Integer choice = Integer.parseInt(input);
            cli.notify(new SelectSchemeCardEvent(cli, cli.getAssociatedPlayerNickname(), choice));
        }
        catch(NumberFormatException e)
        {
            cli.showErrorMessage("Input must be a number!");
            cli.reShowCurrentView();
        }
    }
}