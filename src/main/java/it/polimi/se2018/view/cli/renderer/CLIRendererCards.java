package it.polimi.se2018.view.cli.renderer;

import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.model.toolcards.ToolCard;

import java.util.List;

public class CLIRendererCards extends CLIRenderer
{
    private CLIRendererCardsState state;

    public CLIRendererCards(Game game)
    {
        super(game, 220, 41);
    }


    public void render (CLIRendererCardsState state)
    {
        this.state = state;
        render();
    }

    protected void refresh ()
    {
        initMatrix();


        List<PublicObjectiveCard> publicCards = game.getAllPublicObjectiveCards();

        for(int i=0; i<publicCards.size(); i++)
            new CLIElementCard(this, publicCards.get(i), 35*i, 0 , i, false);

        List<ToolCard> toolCards = game.getAllToolCards();


        boolean selectedTools = false;
        if(state == CLIRendererCardsState.TOOL_CARDS_SELECTED)
            selectedTools = true;

        for(int i=0; i<toolCards.size(); i++)
            new CLIElementCard(this, toolCards.get(i), 35*i, 20 , i, selectedTools);

    }

}
