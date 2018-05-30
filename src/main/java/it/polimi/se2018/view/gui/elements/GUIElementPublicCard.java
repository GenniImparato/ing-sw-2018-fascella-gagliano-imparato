package it.polimi.se2018.view.gui.elements;


import it.polimi.se2018.model.publicobjectivecards.PublicObjectiveCard;
import it.polimi.se2018.view.gui.GUI;


public class GUIElementPublicCard extends GUIElementCard
{
    public GUIElementPublicCard(PublicObjectiveCard card, GUI gui)
    {
        super(card, "public_card", gui);
    }

}
