package net.nonswag.tnl.api.event.exceptions;

import javax.annotation.Nonnull;

public class EventException extends Throwable {

    public EventException(@Nonnull String string) {
        super(string);
    }

    public EventException() {
        this("An error has occurred while calling an event");
    }
}
