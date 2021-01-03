package net.nonswag.tnl.api.event.events;

import net.nonswag.tnl.api.event.Event;
import net.nonswag.tnl.api.event.Listener;

import javax.annotation.Nonnull;
import java.util.Objects;

public class RegisterListenerEvent extends Event {

    @Nonnull private final Listener listener;

    public RegisterListenerEvent(@Nonnull Listener listener) {
        this.listener = listener;
    }

    @Nonnull
    public Listener getListener() {
        return listener;
    }

    @Override
    public String toString() {
        return "RegisterListenerEvent{" +
                "listener=" + listener +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterListenerEvent that = (RegisterListenerEvent) o;
        return listener.equals(that.listener);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listener);
    }
}
