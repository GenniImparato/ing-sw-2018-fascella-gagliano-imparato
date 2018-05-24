package it.polimi.se2018.network.socket;

import it.polimi.se2018.mvc_comunication.Event;
import it.polimi.se2018.mvc_comunication.Message;

import java.io.Serializable;


public class NetworkMessage implements Serializable
{

    public enum NetworkMessageType
    {
        METHOD_INVOCATION,
        EVENT,
        MESSAGE,
        DISCONNECT_MESSAGE
    }

    private NetworkMessageType type;
    private Event event;
    private Message message;
    private String methodName;
    private String methodParameter;
    private boolean hasParameter;

    public NetworkMessage(Event event)
    {
        this.event = event;
        type = NetworkMessageType.EVENT;
    }

    public NetworkMessage(Message message)
    {
        this.message = message;
        type = NetworkMessageType.MESSAGE;
    }

    public NetworkMessage(String methodName)
    {
        this.methodName = methodName;
        type = NetworkMessageType.METHOD_INVOCATION;
        hasParameter = false;
    }

    public NetworkMessage(String methodName, String methodParameter)
    {
        this.methodName = methodName;
        this.methodParameter = methodParameter;
        type = NetworkMessageType.METHOD_INVOCATION;
        hasParameter = true;
    }

    public NetworkMessage()
    {
        this.type = NetworkMessageType.DISCONNECT_MESSAGE;
    }

    public boolean isMethodInvocation()
    {
        return type == NetworkMessageType.METHOD_INVOCATION;
    }

    public boolean isMessage()
    {
        return type == NetworkMessageType.MESSAGE;
    }

    public boolean isEvent()
    {
        return type == NetworkMessageType.EVENT;
    }

    public boolean isDisconnectMessage()
    {
        return type == NetworkMessageType.DISCONNECT_MESSAGE;
    }

    public boolean hasParameter()
    {
        return hasParameter;
    }

    //getter

    public Event getEvent() {
        return event;
    }

    public Message getMessage() {
        return message;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodParameter() {
        return methodParameter;
    }
}
