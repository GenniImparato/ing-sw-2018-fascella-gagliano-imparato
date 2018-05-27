package it.polimi.se2018.view.gui.elements;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.DraftPool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GUIElementDraftPool extends JPanel
{
    private String              path;
    private boolean             diceSelectable;
    private DraftPool           draftPool;
    private List<GUIElementDie> dice;

    private GUIDraftPoolActions actions;

    private GUIElementDraftPool thisElement;


    public GUIElementDraftPool(DraftPool draftPool)
    {
        this.draftPool=draftPool;

        thisElement = this;

        dice = new ArrayList<>();

        setLayout(new FlowLayout());

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon("resources/images/elements/draftpool/back.png"));
        backgroundLabel.setLayout(null);

        add(backgroundLabel);

        for(int i=0; i<draftPool.getAllDice().size(); i++)
        {
            int x;
            int y;

            do
            {
                x = new Random().nextInt(400 - 26 * 2 - 70) +26;        //size of the sqaure - size of the bord - size of a die
                y = new Random().nextInt(400 - 26 * 2 - 70) +26;
            }
            while(!canPlaceDie(x,y));


            GUIElementDie die = new GUIElementDie(draftPool.getAllDice().get(i));
            die.setActions(new GUIDieActions()
            {
                @Override
                public void clicked(GUIElementDie die)
                {
                    if(actions != null)
                        actions.dieClicked(thisElement, die, draftPool.getAllDice().indexOf(die.getDie()));
                }
            });

            die.setBounds(x,y,70,70);

            dice.add(die);

            backgroundLabel.add(die);

        }
    }

    public void setActions(GUIDraftPoolActions actions)
    {
        this.actions = actions;
    }

    public void setSelectableDice(boolean diceSelectable)
    {
        if(diceSelectable)
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


}
