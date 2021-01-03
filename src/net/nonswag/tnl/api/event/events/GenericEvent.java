package net.nonswag.tnl.api.event.events;

import net.nonswag.tnl.api.event.Event;

import javax.annotation.Nonnull;
import java.util.Objects;

public class GenericEvent extends Event {

    @Nonnull public final Event event;

    public GenericEvent(@Nonnull Event event) {
        this.event = event;
    }

    @Nonnull
    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "GenericEvent{" +
                "event=" + event +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericEvent that = (GenericEvent) o;
        return event.equals(that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event);
    }
}
