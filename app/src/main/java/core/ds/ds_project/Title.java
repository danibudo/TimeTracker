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
        /// add implementation
    }

    @Override
    public void accept(final HTML html) {
        /// add implementation
    }
}
