package net.nonswag.tnl.api.event;

import net.nonswag.tnl.api.event.events.GenericEvent;
import net.nonswag.tnl.api.event.events.RegisterListenerEvent;
import net.nonswag.tnl.api.event.exceptions.EventException;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class EventManager {

    @Nonnull private static final List<Listener> registeredEvents = new ArrayList<>();
    private static boolean callGenericEvents = false;

    public static void registerEvent(Listener listener) {
        if (!getRegisteredEvents().contains(listener)) {
            getRegisteredEvents().add(listener);
            callEvents(new RegisterListenerEvent(listener));
        }
    }

    public static void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            registerEvent(listener);
        }
    }

    public static void unregisterEvent(Listener event) {
        getRegisteredEvents().remove(event);
    }

    public static void unregisterEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            unregisterEvent(listener);
        }
    }

    public static void callEvent(Event event) {
        try {
            if (!event.getClass().equals(GenericEvent.class) && isCallGenericEvents()) {
                callEvent(new GenericEvent(event));
            }
            for (Listener listener : getRegisteredEvents()) {
                for (Method method : listener.getClass().getDeclaredMethods()) {
                    EventHandler annotation = method.getAnnotation(EventHandler.class);
                    if (annotation != null) {
                        if (!event.isCancelled() || annotation.ignoreCancelled()) {
                            if (method.getParameterCount() == 1) {
                                if (method.getParameters()[0].getType().equals(event.getClass())) {
                                    method.invoke(listener, event);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable t) {
            new EventException(t.getMessage()).printStackTrace();
        }
    }

    public static void callEvents(Event... events) {
        for (Event event : events) {
            callEvent(event);
        }
    }

    @Nonnull
    public static List<Listener> getRegisteredEvents() {
        return registeredEvents;
    }

    public static void setCallGenericEvents(boolean callGenericEvents) {
        EventManager.callGenericEvents = callGenericEvents;
    }

    public static boolean isCallGenericEvents() {
        return callGenericEvents;
    }
}
