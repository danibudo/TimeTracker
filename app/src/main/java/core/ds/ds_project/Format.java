package core.ds.ds_project;

public interface Format {
    public void visit(Title title);
    public void visit(Separator separator);
    public void visit(Table table);
}
