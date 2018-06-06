package it.polimi.se2018.view.gui.elements;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;
import it.polimi.se2018.view.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


public class GUIElementDraftPool extends JPanel
{
    private DraftPool                   draftPool;
    private List<GUIElementDie>    guiDice;

    private GUIDraftPoolActions actions;

    private GUIElementDraftPool thisElement;

    private JLabel              backgroundLabel;

    private GUI                 gui;


    public GUIElementDraftPool(DraftPool draftPool, GUI gui)
    {
        this.draftPool=draftPool;
        this.gui = gui;

        thisElement = this;

        guiDice = new CopyOnWriteArrayList<>();

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
        for(GUIElementDie die : guiDice)
            die.setSelectable(diceSelectable);
    }

    private boolean canPlaceDie (int x, int y)
    {
        for(GUIElementDie die : guiDice)
        {
            if( Math.sqrt( Math.pow(die.getX()-x,2) + Math.pow(die.getY()-y,2) ) < 80 )
                return false;
        }

        return true;
    }

    private boolean contains(Die die)
    {
        for(GUIElementDie guiDie : guiDice)
            if(guiDie.getDie().isSameDie(die))
                return true;

        return false;
    }

    private void placeDice()
    {
        //remove from the screen the removed dice
        for(GUIElementDie guiDie : guiDice)
        {
            if(!draftPool.contains(guiDie.getDie()))
            {
                backgroundLabel.remove(guiDie);
                guiDice.remove(guiDie);
            }
        }
        this.validate();

        int startAnimationDelay = 0;
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


                GUIElementDie guiDie = new GUIElementDie(die, true, gui);
                guiDie.setActions(new GUIDieActions()
                {
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

                    @Override
                    public void mouseEntered() {}

                    @Override
                    public void mouseExited() {}
                });

                guiDie.setBounds(x, y, 70, 70);

                guiDice.add(guiDie);

                backgroundLabel.add(guiDie);

                guiDie.playGenerateAnimation(400+startAnimationDelay*100);
                startAnimationDelay++;
            }
        }
    }

    public void refresh(DraftPool draftPool)
    {
        this.draftPool = draftPool;
        placeDice();
        refreshDice();
    }

    public List<GUIElementDie> getGUIDice()
    {
        return guiDice;
    }

    public GUIElementDie getGUIDie(Die die)
    {
        for(GUIElementDie guiDie: guiDice)
            if(guiDie.getDie().isSameDie(die))
                return guiDie;

        return null;
    }

    private void refreshDice()
    {
        for(Die draftPoolDie : draftPool.getAllDice())
        {
            getGUIDie(draftPoolDie).refresh(draftPoolDie);
        }
    }

}
