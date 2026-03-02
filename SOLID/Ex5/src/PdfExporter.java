import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    protected ExportResult doExport(String title, String body) {
        if (body.length() > 20) {
            throw new IllegalArgumentException("PDF cannot handle content > 20 chars");
        }

        String fakePdf = "PDF(" + title + "):" + body;
        return new ExportResult(
                "application/pdf",
                fakePdf.getBytes(StandardCharsets.UTF_8)
        );
    }

}
