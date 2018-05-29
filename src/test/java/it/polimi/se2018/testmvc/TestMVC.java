package it.polimi.se2018.testmvc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAddPlayerEvent.class,
        TestPlayerReadyEvent.class,
        TestSelectSchemeCardEvent.class
})

public class TestMVC
{
}
