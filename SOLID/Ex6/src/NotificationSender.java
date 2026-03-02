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