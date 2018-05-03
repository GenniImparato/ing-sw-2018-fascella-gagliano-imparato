package it.polimi.se2018.observer;

import java.util.ArrayList;

public abstract class Observable <T>
{
    private ArrayList <Observer <T>> observers;

    public Observable ()
    {
        observers = new ArrayList<>();
    }

    public void attach (Observer <T> observer)
    {
        observers.add(observer);
    }

    public void detach (Observer <T> observer)
    {
        observers.remove(observer);
    }

    protected void notify (T event)
    {
        for (Observer<T> observer : observers)
            observer.update(event);
    }
}
