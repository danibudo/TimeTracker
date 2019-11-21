package core.ds.ds_project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Text implements Format {
    private PrintWriter writer;
    Text() {
        try {
            writer = new PrintWriter("tmp/textReport.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Text visitor for title.
     * @param title The title to visit
     */
    public void visit(final Title title) {
        writer.println(title.getTitle());
    }

    /**
     * Text visitor for separator.
     * @param separator The separator to visit
     */
    public void visit(final Separator separator) {
        writer.println(separator.getSeparator());
    }

    /**
     * Text visitor for table.
     * @param table The table to visit
     */
    public void visit(final Table table) {
        writer.println(table.getContent());
    }

    /**
     * Closes the report file.
      */
    @Override
    public void finishPrinting() {
        writer.close();
    }
}
