package core.ds.ds_project;

public interface Element {
    /**
     * When a <code>Format</code> tries to access the object,
     * this method accepts the visitor and helps it create
     * an element the right way.
     * @param format The visitor that accesses the object.
     */
    void accept(Format format);
}
