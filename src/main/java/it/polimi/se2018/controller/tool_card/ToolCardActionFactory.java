package it.polimi.se2018.controller.tool_card;


import it.polimi.se2018.controller.tool_card.actions.*;

import java.util.List;

public class ToolCardActionFactory
{
    private enum Action
    {
        DRAFT_DIE("DraftDie", 0),
        SELECT_DIE_FROM_BOARD("SelectDieFromBoard", 0),
        ADD_DRAFTED_DIE_TO_BOARD("AddDraftedDie", 1),
        MOVE_SELECTED_DIE("MoveSelectedDie", 1),
        ROLL_DIE("RollDie", 1),
        RE_DRAW_DIE("ReDrawDie", 1),
        RE_ROLL_DRAFTPOOL("ReRollDraftPool", 0),
        FLIP_DIE("FlipDie", 1),
        INCREMENT_DECREMENT_DIE("IncrementDecrementDie", 1),
        SWAP_DICE("SwapDice", 0),
        SKIP_NEXT_TURN("SkipNextTurn", 0),
        ASK_TO_STOP("AskToStop", 0);

        private String name;
        private int paramNumber;

        Action(String name, int parametersNumber)
        {
            this.name = name;
            this.paramNumber = parametersNumber;
        }

        public int getParametersNumber()
        {
            return paramNumber;
        }

        public String getName()
        {
            return name;
        }
    }

    private ToolCardActionFactory()
    {}

    public static ToolCardAction createAction(String action, String parameter) throws InvalidToolCardActionException
    {
        if(action.equals(Action.DRAFT_DIE.getName()))
            return new DraftDieAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.SELECT_DIE_FROM_BOARD.getName()))
            return new SelectDieAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.ADD_DRAFTED_DIE_TO_BOARD.getName()))
            return new AddDieToBoardAction(new ToolCardParameterBuilder().setIgnore(parameter).build());
        else if(action.equals(Action.MOVE_SELECTED_DIE.getName()))
            return new MoveDieAction(new ToolCardParameterBuilder().setIgnore(parameter).build());


        throw new InvalidToolCardActionException("Invalid action: " + action + "!");
    }

    public static int getParameterNumber(String action) throws InvalidToolCardActionException
    {
        for(Action possibleAction : Action.values())
            if(action.equals(possibleAction.getName()))
                return possibleAction.getParametersNumber();

        throw new InvalidToolCardActionException("Invalid action: " + action + "!");
    }
}
