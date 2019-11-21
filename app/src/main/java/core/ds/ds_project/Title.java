package core.ds.ds_project;

public class Title implements Element {
    private String title;
    Title(final String titleName) {
        title = titleName;
    }
    public String getTitle() {
        return title;
    }
    @Override
    public void accept(final Format format) {
        format.visit(this);
    }
}
