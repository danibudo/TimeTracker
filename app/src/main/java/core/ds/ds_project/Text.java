package core.ds.ds_project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Text extends Format {
    Text() {
        try {
            setWriter(new PrintWriter("tmp/Report.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visits a <code>Title</code> in order to write it to a text file.
     * @param title the title to visit
     */
    public void visit(final Title title) {
        getWriter().println(title.getTitle());
    }

    /**
     * Visits a <code>Separator</code> in order to write it to a text file.
     * @param separator the separator to visit
     */
    public void visit(final Separator separator) {
        getWriter().println(separator.getSeparator());
    }

    /**
     * Visits a <code>Table</code> in order to write it to a text file.
     * @param table the table to visit
     */
    public void visit(final Table table) {
        getWriter().println(table.getName());
        int columns = table.getColumnCount();
        int iterator = 1;
        for (String info : table.getContent()) {
            getWriter().print(info);
            if (iterator == columns) {
               getWriter().println();
               iterator = 1;
            } else {
                iterator++;
            }
        }
    }
}
