package it.polimi.se2018.game;

import java.util.ArrayList;

public class DraftPool {
    private static DraftPool instance = null;
    ArrayList <Die> dice;

    public static DraftPool getInstance() //Singleton
    {
        if(instance==null)
            instance=new DraftPool();
        return instance;
    }

    private DraftPool()
    {
    }

    private void draw()
    {
    }

}
