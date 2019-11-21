package core.ds.ds_project;

public class Title implements Elements {
    private String title;
    Title(final String titleName) {
        title = titleName;
    }
    public String getTitle() {
        return title;
    }
    @Override
    public void accept(final Text text) {
        text.visit(this);
    }

    @Override
    public void accept(final HTML html) {
        html.visit(this);
    }
}
