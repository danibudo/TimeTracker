package core.ds.ds_project;

import java.io.PrintWriter;

public abstract class Format {
    /**
     * The <code>PrintWriter</code> used to create
     * files and write data to them.
     */
    private PrintWriter writer;

    abstract void visit(Title title);
    abstract void visit(Separator separator);
    abstract void visit(Table table);

    /**
     * Closes the report file.
     */
    void finishPrinting() {
        getWriter().close();
    }

    /**
     * Gets the <code>PrintWriter</code>.
     * @return the <code>PrintWriter</code>
     */
    PrintWriter getWriter() {
        return writer;
    }

    /**
     * Sets the <code>PrintWriter</code>.
     * @param printWriter the <code>PrintWriter</code> value to set
     */
    void setWriter(final PrintWriter printWriter) {
        writer = printWriter;
    }
}
