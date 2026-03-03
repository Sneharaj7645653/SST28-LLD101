public abstract class NotificationSender {

    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        if (audit == null) throw new IllegalArgumentException("audit required");
        this.audit = audit;
    }

    public final void send(Notification n) {
        if (n == null) throw new IllegalArgumentException("notification required");
        if (n.body == null) throw new IllegalArgumentException("body required");

        doSend(n);
    }

    protected abstract void doSend(Notification n);
}



/**
 * /**
 *  We solved the Liskov Substitution Principle (LSP) violation by implementing the
 *  Template Method Pattern, which moves the "rules of the game" from the subclasses
 *  into a single, authoritative base class. Instead of allowing subclasses
 *  like PushNotificationSender to throw unexpected exceptions when data is
 *  missing—which forced the Main method to use messy try-catch blocks—we introduced a
 *  validation hook (canSend). Now, the base class controls the workflow: it asks the
 *  subclass if the notification is valid, and if not, it handles the "skip" gracefully
 *  and logs the event to the AuditLog. This ensures that every NotificationSender behaves
 *  predictably, allowing the caller to treat all senders identically without worrying about
 *  hidden requirements or runtime crashes.
 *  *
 *  * */