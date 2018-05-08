package it.polimi.se2018.observer;

import java.io.Serializable;

public interface Observer <T>
{
    void update (T event);
}
