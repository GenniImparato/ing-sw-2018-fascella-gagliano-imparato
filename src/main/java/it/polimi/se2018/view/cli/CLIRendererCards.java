package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Card;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.PublicObjectiveCard;
import it.polimi.se2018.model.ToolCard;

import java.util.ArrayList;

public class CLIRendererCards extends CLIRenderer
{
    private CLIRendererCardsState state;

    public CLIRendererCards(Game game)
    {
        super(game, 220, 40);
    }


    public void render (CLIRendererCardsState state)
    {
        this.state = state;
        render();
    }

    protected void refresh ()
    {
        initMatrix();


        ArrayList<PublicObjectiveCard> publicCards = game.getAllPublicObjectiveCards();

        for(int i=0; i<publicCards.size(); i++)
            new CLIElementCard(this, publicCards.get(i), 35*i, 0 );

        ArrayList<ToolCard> toolCards = game.getAllToolCards();

        for(int i=0; i<toolCards.size(); i++)
            new CLIElementCard(this, toolCards.get(i), 35*i, 20 );

    }




}
