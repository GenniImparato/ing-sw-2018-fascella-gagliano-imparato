package it.polimi.se2018.game;

import java.util.ArrayList;

public class RoundTrack {
    private static RoundTrack instance = null;
    private ArrayList<Die> []dice;

    public static RoundTrack getInstance() //Singleton
    {
        if(instance==null)
            instance=new RoundTrack();
        return instance;
    }

    private RoundTrack()
    {
        dice=new ArrayList[10];
    }
}
