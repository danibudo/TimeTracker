package core.ds.ds_project;

public class Separator implements Element {
    private static final int LENGTH = 99;
    private String separator;

    Separator() {
        separator = new String(new char[LENGTH]).replace("\0", "-");
    }

    /**
     * Gets the <code>String</code> with the separator.
     * @return the separator
     */
    String getSeparator() {
        return separator;
    }

    /**
     * Accepts a visitor to allow it to print the <code>Separator</code>.
     * @param format the visitor that accesses the object
     */
    @Override
    public void accept(final Format format) {
        format.visit(this);
    }
}
