public abstract class Exporter {

    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (req.title == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }

        String body = req.body == null ? "" : req.body;

        ExportResult result = doExport(req.title, body);

        if (result == null || result.contentType == null || result.bytes == null) {
            throw new IllegalStateException("Invalid export result");
        }

        return result;
    }

    protected abstract ExportResult doExport(String title, String body);
}



/**
 We solved the Liskov Substitution Principle (LSP) violation by implementing the
 Template Method Pattern, which moves the "rules of the game" from the subclasses
 into a single, authoritative base class. Instead of allowing subclasses
 like PushNotificationSender to throw unexpected exceptions when data is
 missing—which forced the Main method to use messy try-catch blocks—we introduced a
 validation hook (canSend). Now, the base class controls the workflow: it asks the
 subclass if the notification is valid, and if not, it handles the "skip" gracefully
 and logs the event to the AuditLog. This ensures that every NotificationSender behaves
 predictably, allowing the caller to treat all senders identically without worrying about
 hidden requirements or runtime crashes.
 *
 * */

