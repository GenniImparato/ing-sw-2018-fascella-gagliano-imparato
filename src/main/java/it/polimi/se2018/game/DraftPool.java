package it.polimi.se2018.game;

import java.util.ArrayList;

public class DraftPool
{
    private ArrayList <Die> dice;
    private DiceBag diceBag;

    public DraftPool(DiceBag diceBag)
    {
        this.diceBag = diceBag;
    }

    public void draw()
    {
        dice = diceBag.pullDice(5);
    }

}
