package core.ds.ds_project;

public class Title implements Element {
    private String title;
    Title(final String titleName) {
        title = titleName;
    }
    /**
     * Gets the <code>String</code> with the title.
     * @return the title
     */
    String getTitle() {
        return title;
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
