package core.ds.ds_project;

public class Separator implements Elements {
    private static final int LENGTH = 99;
    private String separator;

    Separator() {
        separator = new String(new char[LENGTH]).replace("\0", "-");
    }

    public String getSeparator() {
        return separator;
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
