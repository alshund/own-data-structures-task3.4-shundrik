package by.tr.web.collection.linkedList;

public interface Iterator<T> {

    boolean hasNext();
    T next();
    void remove();
}
