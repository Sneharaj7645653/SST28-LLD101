public class PushNotificationSender extends NotificationSender {

    public PushNotificationSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void doSend(Notification n) {

        // Channel-specific requirement
        if (n.email == null || n.email.isEmpty()) {
            throw new IllegalArgumentException("device id required");
        }

        System.out.println(
                "PUSH -> to=" + n.email +
                        " body=" + n.body
        );

        audit.add("push sent");
    }
}