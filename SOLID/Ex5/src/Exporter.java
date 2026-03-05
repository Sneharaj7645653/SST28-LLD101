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
 * Problems:
 * 1. No base contract defined
 * 2. strengthening pre-conditions in pdf exporter
 * 3. csv alterns content as in replaces \n to " "
 * 4. inconsistent null handling: json lets it succeed if req is null
 *
 * Solution:
 * 1. final export method
 * 2. validation: if req is null or title is null -> returns export result with error
 * 3. normalisastion req.body == null - > ""
 * 4. store the export result which we get from abstract method doexport
 * 5. try - catch block: we do export
 * 6. if result it null or contenttype is null bytes is null we return result with error
 * 7. helps to gracefully handle <20 length precondition
 * * */


