package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.toolcards.ToolCardVisitor;

public class StartUsingToolCardAction extends GameAction
{
    int             cardNum;
    ToolCardVisitor visitor;

    public StartUsingToolCardAction(int cardNum, ToolCardVisitor visitor)
    {
        this.cardNum = cardNum;
        this.visitor = visitor;
    }

    @Override
    public void execute(Game game)
    {
        game.startUsingToolCard(cardNum, visitor);
        executed = true;
    }
}
