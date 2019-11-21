package core.ds.ds_project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HTML implements Format {
    private PrintWriter writer;
    HTML() {
        try {
            writer = new PrintWriter("tmp/textReport.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * HTML visitor for title
     * @param title The title to visit
     */
    public void visit(final Title title) {
        writer.println("<h1>" + title.getTitle() + "</h1>");
    }

    /**
     * HTML visitor for separator
     * @param separator The separator to visit
     */
    public void visit(final Separator separator) {
        writer.println("<hr>");
    }

    /**
     * HTML visitor for table
     * @param table The table to visit
     */
    public void visit(final Table table) {
        writer.println("<style>");
        writer.println("table, th, td {border: 1px ridge black;}");
        writer.println("</style>");

        writer.println("<h2>" + table.getName() + "</h2>");
        writer.println("<table>");

        int columns = table.getColumnCount();
        int iterator = 1;
        writer.println("<tr>");
        boolean headers = true;
        for (String info : table.getContent()) {
            if (headers) {
                writer.print("<th>" + info + "</th>");
            } else {
                writer.print("<td>" + info + "</td>");
            }
            if (iterator == columns) {
                headers = false;
                writer.println("</tr>");
                writer.println("<tr>");
                iterator = 1;
            } else {
                iterator++;
            }
        }
        writer.println("</table>");
    }

    /**
     * Closes the report file.
     */
    @Override
    public void finishPrinting() {
        writer.close();
    }
}
