package it.polimi.se2018.view.cli.renderer;

import it.polimi.se2018.model.Model;
import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.model.toolcards.ToolCard;
import it.polimi.se2018.view.cli.CLI;

import java.util.List;

public class CLIRendererCards extends CLIRenderer
{
    private CLIRendererCardsState state;

    public CLIRendererCards(CLI cli, Model model)
    {
        super(cli, model, 220, 41);
    }


    public void render (CLIRendererCardsState state)
    {
        this.state = state;
        render();
    }

    protected void refresh ()
    {
        initMatrix();


        List<PublicObjectiveCard> publicCards = model.getPublicObjectiveCards();

        for(int i=0; i<publicCards.size(); i++)
            new CLIElementCard(this, publicCards.get(i), 35*i, 0 , i, false);

        List<ToolCard> toolCards = model.getToolCards();


        boolean selectedTools = false;
        if(state == CLIRendererCardsState.TOOL_CARDS_SELECTED)
            selectedTools = true;

        for(int i=0; i<toolCards.size(); i++)
            new CLIElementCard(this, toolCards.get(i), 35*i, 20 , i, selectedTools);

    }

}
