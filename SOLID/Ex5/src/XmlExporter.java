import java.nio.charset.StandardCharsets;

public class XmlExporter extends Exporter {

    @Override
    protected ExportResult doExport(String title, String body) {

        String xml =
                "<report>" +
                        "<title>" + escape(title) + "</title>" +
                        "<body>" + escape(body) + "</body>" +
                        "</report>";

        return new ExportResult(
                "application/xml",
                xml.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String escape(String s) {
        return s
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}