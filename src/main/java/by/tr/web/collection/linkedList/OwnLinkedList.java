package by.tr.web.collection.linkedList;

public interface OwnLinkedList<T> {

    void addFirst(T element);
    void addLast(T element);
    void add(int index, T element);
    boolean remove(Object object);
    boolean isEmpty();
    T get(int index);
    int size();
    Iterator<T> iterator();
    Iterator<T> descendingIterator();
}
