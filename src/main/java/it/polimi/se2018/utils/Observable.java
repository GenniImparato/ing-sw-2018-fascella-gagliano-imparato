package it.polimi.se2018.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Observable <T>
{
    private List<Observer <T>> observers;

    public Observable ()
    {
        observers = new CopyOnWriteArrayList();
    }

    public void attach (Observer <T> observer)
    {
        observers.add(observer);
    }

    public void detach (Observer <T> observer)
    {
        observers.remove(observer);
    }

    public void notify (T event)
    {
        for (Observer<T> observer : observers)
            observer.update(event);
    }
}
