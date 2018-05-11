package it.polimi.se2018.model.gameactions;

import it.polimi.se2018.model.CannotAddPlayerException;
import it.polimi.se2018.model.Game;
import it.polimi.se2018.model.toolcards.ToolCard;
import it.polimi.se2018.model.toolcards.ToolCardVisitor;

public class UseToolCardAction extends GameAction
{
    int             cardNum;
    ToolCardVisitor visitor;

    public UseToolCardAction(int cardNum, ToolCardVisitor visitor)
    {
        this.cardNum = cardNum;
        this.visitor = visitor;
    }

    @Override
    public void execute(Game game)
    {
        game.startUsingToolCard(cardNum, visitor);
    }
}
