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