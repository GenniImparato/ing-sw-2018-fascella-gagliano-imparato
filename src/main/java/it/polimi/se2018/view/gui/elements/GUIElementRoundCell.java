package it.polimi.se2018.view.gui.elements;


import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.RoundTrack;
import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIElementRoundCell extends JLabel
{
    private List<GUIElementDie>     guiDice;
    private GUIRoundTrackPopup      popup;
    private GUIElementRoundTrack    guiRoundTrack;
    private GUI                     gui;
    private int                     roundNumber;
    private boolean                 selectable;

    public GUIElementRoundCell(int roundNumber, GUIElementRoundTrack guiRoundTrack, GUI gui)
    {
        this.gui = gui;
        this.roundNumber = roundNumber;
        this.guiRoundTrack = guiRoundTrack;

        guiDice = new ArrayList<>();

        popup = new GUIRoundTrackPopup(this, gui);

        setIcon(new ImageIcon("resources/images/elements/roundtrack/" + roundNumber + ".png"));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    public void addDie(GUIElementDie guiDie)
    {
        guiDice.add(guiDie);

        List<Die> totalDice =  guiRoundTrack.getRoundTrack().getDiceAtRound(roundNumber-1);

        if(guiDice.size() == totalDice.size())
        {
            List<GUIElementDie> newGuiDice = new ArrayList<>();

            for(Die die:totalDice)
            {
                for(GUIElementDie gDie:guiDice)
                {
                    if(gDie.getDie().isSameDie(die))
                        newGuiDice.add(gDie);
                }
            }

            guiDice = newGuiDice;

            add(guiDice.get(0));

            guiDice.get(0).setActions(new GUIDieActions()
            {
                @Override
                public void clicked(GUIElementDie die)
                {
                    if(guiRoundTrack.action != null && selectable)
                        guiRoundTrack.action.dieClicked(guiRoundTrack, roundNumber-1);
                }

                @Override
                public void mouseEntered()
                {
                    popup.show();
                }

                @Override
                public void mouseExited()
                {
                    popup.hide();
                }
            });
        }
    }

    public List<GUIElementDie> getDice()
    {
        return guiDice;
    }

    public void setSelectableDice(boolean selectable)
    {
        this.selectable = selectable;

        if(!guiDice.isEmpty())
            guiDice.get(0).setSelectable(selectable);
    }

    public void refresh()
    {
        List<Die> diceAtRound = guiRoundTrack.getRoundTrack().getDiceAtRound(roundNumber-1);
        if(!diceAtRound.isEmpty() && !guiDice.isEmpty())
        {
            //check if the first die has changed
            if(!diceAtRound.get(0).isSameDie(guiDice.get(0).getDie()))
            {
                popup.hide();

                remove(guiDice.get(0));
                guiDice.remove(0);
                guiDice.add(0, new GUIElementDie(diceAtRound.get(0), false, gui));
                add(guiDice.get(0));

                guiDice.get(0).setActions(new GUIDieActions()
                {
                    @Override
                    public void clicked(GUIElementDie die)
                    {
                        if(guiRoundTrack.action != null && selectable)
                            guiRoundTrack.action.dieClicked(guiRoundTrack, roundNumber-1);
                    }

                    @Override
                    public void mouseEntered()
                    {
                        popup.show();
                    }

                    @Override
                    public void mouseExited()
                    {
                        popup.hide();
                    }
                });
            }
        }

    }
}
