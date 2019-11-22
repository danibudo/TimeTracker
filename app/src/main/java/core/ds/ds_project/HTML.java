package core.ds.ds_project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HTML extends Format {
    HTML() {
        try {
            setWriter(new PrintWriter("tmp/Report.html"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visits a <code>Title</code> in order to write it to an HTML file.
     * @param title the title to visit
     */
    public void visit(final Title title) {
        getWriter().println("<h1>" + title.getTitle() + "</h1>");
    }

    /**
     * Visits a <code>Separator</code> in order to write it to an HTML file.
     * @param separator the separator to visit
     */
    public void visit(final Separator separator) {
        getWriter().println("<hr>");
    }

    /**
     * Visits a <code>Table</code> in order to write it to an HTML file.
     * @param table the table to visit
     */
    public void visit(final Table table) {
        PrintWriter writer = getWriter();
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
}
