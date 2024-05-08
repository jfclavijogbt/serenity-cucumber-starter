package com.poc.cl.utils;

public class MessageSentTracker {
    private static boolean messageSent = false;
    private static boolean anyScenarioFailed = false;

    public static synchronized boolean isMessageSent() {
        return messageSent;
    }

    public static synchronized void setMessageSent(boolean sent) {
        messageSent = sent;
    }

    public static synchronized boolean isAnyScenarioFailed() {
        return anyScenarioFailed;
    }

    public static synchronized void setAnyScenarioFailed(boolean status) {
        anyScenarioFailed = status;
    }
}

