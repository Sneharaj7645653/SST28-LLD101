import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    protected ExportResult doExport(String title, String body) {

        String escapedTitle = escape(title);
        String escapedBody = escape(body);

        String csv = "title,body\n" +
                escapedTitle + "," +
                escapedBody + "\n";

        return new ExportResult(
                "text/csv",
                csv.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String escape(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
