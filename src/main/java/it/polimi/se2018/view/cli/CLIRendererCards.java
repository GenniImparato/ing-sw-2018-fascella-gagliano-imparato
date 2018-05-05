package it.polimi.se2018.view.cli;

import it.polimi.se2018.model.Game;

public class CLIRendererCards extends CLIRenderer
{
    private CLIRendererCardsState state;

    public CLIRendererCards(Game game)
    {
        super(game);
    }


    public void render (CLIRendererCardsState state)
    {
        this.state = state;
        render();
    }

    protected void refresh ()
    {

    }


}
