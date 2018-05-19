package it.polimi.se2018.network.socket.client;

import it.polimi.se2018.network.socket.NetworkMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientNetworkMessageAnalyzer
{
    private NetworkHandler networkHandler;

    public ClientNetworkMessageAnalyzer(NetworkHandler networkHandler)
    {
        this.networkHandler = networkHandler;
    }

    public void analyzeMessage(NetworkMessage message)
    {
        if(message.isMethodInvocation())
        {
            if(message.hasParameter())
            {
                invokeMethod(message.getMethodName(), message.getMethodParameter());
            }
            else
            {
                invokeMethod(message.getMethodName());
            }
        }
        else if(message.isMessage())
        {
            networkHandler.notify(message.getMessage());
        }
    }


    private void invokeMethod(String methodName)
    {
        try {
            Method method = networkHandler.getClient().getClass().getMethod(methodName);
            method.invoke(networkHandler.getClient());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    private void invokeMethod(String methodName, String parameter)
    {
        try
        {
            Method method = networkHandler.getClient().getClass().getMethod(methodName, parameter.getClass());
            method.invoke(networkHandler.getClient(), parameter);
        }
        catch(IllegalAccessException|InvocationTargetException|NoSuchMethodException e)
        {e.printStackTrace();
        }
    }


}
