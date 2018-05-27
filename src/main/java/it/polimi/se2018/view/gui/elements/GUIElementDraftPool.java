package it.polimi.se2018.view.gui.elements;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.model.RoundTrack;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class GUIElementDraftPool extends JPanel
{
    private DraftPool           draftPool;
    private List<GUIElementDie> dice;

    private GUIDraftPoolActions actions;

    private GUIElementDraftPool thisElement;

    private JLabel              backgroundLabel;


    public GUIElementDraftPool(DraftPool draftPool)
    {
        this.draftPool=draftPool;

        thisElement = this;

        dice = new CopyOnWriteArrayList<>();

        setLayout(new FlowLayout());

        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("resources/images/elements/draftpool/back.png"));
        backgroundLabel.setLayout(null);

        add(backgroundLabel);
    }

    public void setActions(GUIDraftPoolActions actions)
    {
        this.actions = actions;
    }

    public void setSelectableDice(boolean diceSelectable)
    {
        for(GUIElementDie die : dice)
            die.setSelectable(diceSelectable);
    }

    private boolean canPlaceDie (int x, int y)
    {
        for(GUIElementDie die : dice)
        {
            if( Math.sqrt( Math.pow(die.getX()-x,2) + Math.pow(die.getY()-y,2) ) < 80 )
                return false;
        }

        return true;
    }

    private boolean contains(Die die)
    {
        for(GUIElementDie guiDie : dice)
            if(guiDie.getDie().isSameDie(die))
                return true;

        return false;
    }

    private void placeDice()
    {
        //places all the new dies
        for(Die die : draftPool.getAllDice())
        {
            if(!contains(die))
            {
                int x;
                int y;

                do {
                    x = new Random().nextInt(400 - 26 * 2 - 70) + 26;        //size of the sqaure - size of the bord - size of a die
                    y = new Random().nextInt(400 - 26 * 2 - 70) + 26;
                }
                while (!canPlaceDie(x, y));


                GUIElementDie guiDie = new GUIElementDie(die);
                guiDie.setActions(new GUIDieActions() {
                    @Override
                    public void clicked(GUIElementDie die)
                    {
                        int dieNum;

                        for(dieNum = 0; dieNum < draftPool.getAllDice().size(); dieNum++)
                            if(draftPool.getAllDice().get(dieNum).isSameDie(die.getDie()))
                                break;

                        if (actions != null)
                            actions.dieClicked(thisElement, die, dieNum);
                    }
                });

                guiDie.setBounds(x, y, 70, 70);

                dice.add(guiDie);

                backgroundLabel.add(guiDie);
            }
        }

        //remove from the screen the removed dice
        for(GUIElementDie guiDie : dice)
        {
            if(!draftPool.contains(guiDie.getDie()))
            {
                backgroundLabel.remove(guiDie);
                dice.remove(guiDie);
            }
        }
    }

    public void refresh(DraftPool draftPool)
    {
        this.draftPool = draftPool;
        placeDice();
    }

}
