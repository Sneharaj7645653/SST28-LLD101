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
 In the old flow, the system operated on "blind trust": the Main method treated
 all senders as simple actions (void), but the WhatsAppSender hiddenly enforced
 a strict rule that would crash the entire program with an exception if a phone
 number was invalid. This forced the caller to use a messy try-catch block to
 "guard" against that specific subclass, proving that the senders weren't truly
 interchangeable.

 In the new flow, we replaced that "crash" with a "report."
 We changed the base contract to return a SendResult object, which
 acts like a standardized envelope. Now, when WhatsAppSender encounters a
 bad phone number, it doesn't explode; it simply places a "Failure" note inside
 that envelope and returns it politely to the Main method. Because every sender
 now hands back the same type of envelope, the Main method can treat them all
 exactly the same in a single loop—checking the success flag to decide whether
 to print a confirmation or an error message—making the system predictable,
 safe, and truly LSP-compliant.



 The Flow:

 Main loops through List<NotificationSender>.

 Main calls sender.send(n).

 The child class performs its specific logic (truncating, validating, or printing).

 The child class hands back a SendResult.

 Main reads the success flag. If it's false, it prints the error.
 *
 * */

