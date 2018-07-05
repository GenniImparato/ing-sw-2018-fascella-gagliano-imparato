package it.polimi.se2018.view.gui.elements;


import it.polimi.se2018.controller.public_objective_cards.PublicObjectiveCard;
import it.polimi.se2018.model.Card;
import it.polimi.se2018.view.gui.GUI;


public class GUIElementPublicCard extends GUIElementCard
{
    public GUIElementPublicCard(Card card, GUI gui)
    {
        super(card, "public_card", gui);
    }

}
