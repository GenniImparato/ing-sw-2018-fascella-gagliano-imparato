package it.polimi.se2018.network.socket.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientNetworkMessageAnalyzer
{
    private NetworkHandler networkHandler;
    private static final String START_MESSAGE = "StartMethodInvocation";
    private static final String END_MESSAGE = "EndMethodInvocation";

    private int state = WAITING;
    private static final int WAITING = 0;
    private static final int ACCEPTED = 1;
    private static final int METHOD_NAME_RECEIVED = 2;
    private static final int PARAMETERS_RECEIVED = 3;
    private static final int EXECUTE_METHOD = 4;

    private String methodName;
    private String methodParameter;
    private boolean hasParameter;


    public ClientNetworkMessageAnalyzer(NetworkHandler networkHandler)
    {
        this.networkHandler = networkHandler;
    }

    public void analyzeMessage(String message)
    {

        switch(state)
        {
            case WAITING:
                if(isStartMessage(message))
                    state = ACCEPTED;
                break;
            case ACCEPTED:
                if(isStartMessage(message))
                    state = ACCEPTED;
                else
                {
                    methodName = message;
                    state = METHOD_NAME_RECEIVED;
                }
                break;
            case METHOD_NAME_RECEIVED:
                if(isStartMessage(message))
                    state = ACCEPTED;
                else if (isEndMessage(message))
                    state = EXECUTE_METHOD;
                else
                {
                    methodParameter = message;
                    state = PARAMETERS_RECEIVED;
                }
                hasParameter = false;
                break;
            case PARAMETERS_RECEIVED:
                if(isStartMessage(message))
                    state = ACCEPTED;
                else if(isEndMessage(message))
                    state = EXECUTE_METHOD;
                hasParameter = true;
                break;
            case EXECUTE_METHOD:
                invokeMethod();
                if(isStartMessage(message))
                    state = ACCEPTED;
                else
                    state = WAITING;
                break;
        }
    }

    private boolean isStartMessage(String message)
    {
        return message.equals(START_MESSAGE);
    }

    private boolean isEndMessage(String message)
    {
        return message.equals(END_MESSAGE);
    }

    private void invokeMethod()
    {
        try
        {
            if(!hasParameter)
            {
                Method method = networkHandler.getClient().getClass().getMethod(methodName);
                method.invoke(networkHandler.getClient());
            }
            else
            {
                Method method = networkHandler.getClient().getClass().getMethod(methodName, methodParameter.getClass());
                method.invoke(networkHandler.getClient(), methodParameter);
            }
        }
        catch(IllegalAccessException|IllegalArgumentException|InvocationTargetException e)
        {e.printStackTrace();}
        catch(NoSuchMethodException e) {e.printStackTrace();}
    }
}
