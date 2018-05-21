package it.polimi.se2018.network.socket.client;

import it.polimi.se2018.network.socket.NetworkMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SocketNetworkMessageAnalyzer {
    private SocketNetworkHandler socketNetworkHandler;

    public SocketNetworkMessageAnalyzer(SocketNetworkHandler socketNetworkHandler) {
        this.socketNetworkHandler = socketNetworkHandler;
    }

    public void analyzeMessage(NetworkMessage message) {
        if (message.isMethodInvocation()) {
            if (message.hasParameter()) {
                invokeMethod(message.getMethodName(), message.getMethodParameter());
            } else {
                invokeMethod(message.getMethodName());
            }
        } else if (message.isMessage()) {
            socketNetworkHandler.notify(message.getMessage());
        }
    }


    private void invokeMethod(String methodName) {
        try {
            Method method = socketNetworkHandler.getView().getClass().getMethod(methodName);
            method.invoke(socketNetworkHandler.getView());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    private void invokeMethod(String methodName, String parameter) {
        try {
            Method method = socketNetworkHandler.getView().getClass().getMethod(methodName, parameter.getClass());
            method.invoke(socketNetworkHandler.getView(), parameter);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}