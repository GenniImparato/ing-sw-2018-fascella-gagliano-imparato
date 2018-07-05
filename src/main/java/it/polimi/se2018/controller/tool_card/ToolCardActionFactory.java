package it.polimi.se2018.controller.tool_card;


import it.polimi.se2018.controller.tool_card.actions.*;

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
        ASK_TO_STOP("AskToStop", 0),
        CHOOSE_DIE_FROM_ROUNDTRACK("ChooseDieFromRoundTrack", 0),
        SELECT_SAME_COLOR_DIE("SelectSameColorDie", 1),
        SWAP_DRAFTED_CHOSEN_DIE("SwapDraftedChosenDice", 0);

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
            return new SelectDieFromBoardAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.ADD_DRAFTED_DIE_TO_BOARD.getName()))
            return new AddDieToBoardAction(new ToolCardParameterBuilder().setIgnore(parameter).build());
        else if(action.equals(Action.MOVE_SELECTED_DIE.getName()))
            return new MoveSelectedDieAction(new ToolCardParameterBuilder().setIgnore(parameter).build());
        else if(action.equals(Action.ROLL_DIE.getName()))
            return new RollDieAction(new ToolCardParameterBuilder().setDie(parameter).build());
        else if(action.equals(Action.RE_ROLL_DRAFTPOOL.getName()))
            return new ReRollDraftPoolAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.FLIP_DIE.getName()))
            return new FlipDieAction(new ToolCardParameterBuilder().setDie(parameter).build());
        else if(action.equals(Action.INCREMENT_DECREMENT_DIE.getName()))
            return new IncrementDecrementDieAction(new ToolCardParameterBuilder().setDie(parameter).build());
        else if(action.equals(Action.SKIP_NEXT_TURN.getName()))
            return new SkipTurnAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.CHOOSE_DIE_FROM_ROUNDTRACK.getName()))
            return new ChooseDieFromRoundTrackAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.SELECT_SAME_COLOR_DIE.getName()))
            return new SelectSameColorDieAction(new ToolCardParameterBuilder().setDie(parameter).build());
        else if(action.equals(Action.SWAP_DRAFTED_CHOSEN_DIE.getName()))
            return new SwapDraftedChosenDieAction(new ToolCardParameterBuilder().build());
        else if(action.equals(Action.RE_DRAW_DIE.getName()))
            return new ReDrawDieAction(new ToolCardParameterBuilder().setDie(parameter).build());

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
