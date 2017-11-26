package by.tr.web.collection.linkedList;

public class OwnLinkedListImpl<T> implements OwnLinkedList<T> {
    private Node<T> fstNode;
    private Node<T> lstNode;
    private int size = 0;

    @Override
    public void addFirst(T element) {

        Node<T> nextNode = fstNode;
        Node<T> newFirst = new Node<>(element, null, fstNode);
        fstNode = newFirst;
        if (nextNode == null) {
            lstNode = newFirst;
        } else {
            nextNode.prevNode = fstNode;
        }
        size++;
    }

    @Override
    public void addLast(T element) {

        Node<T> prevNode = lstNode;
        Node<T> newLast = new Node<>(element, lstNode, null);
        lstNode = newLast;
        if (prevNode == null) {
            fstNode = newLast;
        } else {
            prevNode.nextNode = lstNode;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {

        checkElementPosition(index);
        if (index == size) {
            addLast(element);
        } else {
            addBefore(element, getNodeByIndex(index));
        }
    }

    @Override
    public boolean remove(Object object) {

        if (object == null) {
            return removeNullObject();
        } else {
            return removeNotNullObject(object);
        }
    }

    @Override
    public boolean isEmpty() {

        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T get(int index) {

        checkElementIndex(index);
        Node<T> node = getNodeByIndex(index);
        return node.element;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int counter = 0;

            @Override
            public boolean hasNext() {

                if (counter < size) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {

                return get(counter++);
            }

            @Override
            public void remove() {

                Node<T> node = getNodeByIndex(counter);
                OwnLinkedListImpl.this.remove(node);
            }
        };
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            int counter = size - 1;

            @Override
            public boolean hasNext() {

                if (counter >= 0) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {

                return get(counter--);
            }

            @Override
            public void remove() {

                Node<T> node = getNodeByIndex(counter);
                OwnLinkedListImpl.this.remove(node);
            }
        };
    }

    private void addBefore(T element, Node<T> beforeNode) {

        Node<T> prevNode = beforeNode.prevNode;
        Node<T> newNode = new Node<>(element, beforeNode.prevNode, beforeNode);
        beforeNode.prevNode = newNode;
        if (prevNode == null) {
            fstNode = newNode;
        } else {
            prevNode.nextNode = newNode;
        }
        size++;
    }

    private boolean removeNullObject() {

        for (Node<T> node = fstNode; node != null; node = node.nextNode) {
            if (node.element == null) {
                remove(node);
                return true;
            }
        }
        return false;
    }

    private boolean removeNotNullObject(Object object) {

        for (Node<T> node = fstNode; node != null; node = node.nextNode) {
            if (node.element.equals(object)) {
                remove(node);
                return true;
            }
        }
        return false;
    }

    private T remove(Node<T> node) {

        T removedElement = node.element;
        Node<T> prev = node.prevNode;
        Node<T> next = node.nextNode;

        if (prev == null) {
            fstNode = null;
        } else {
            node.prevNode = null;
            prev.nextNode = next;
        }

        if (next == null) {
            lstNode = null;
        } else {
            node.nextNode = null;
            next.prevNode = prev;
        }
        node.element = null;
        size--;
        return removedElement;
    }

    private Node<T> getNodeByIndex(int index) {

        Node<T> node = fstNode;
        for (int nodeIndex = 0; nodeIndex < index; nodeIndex++) {
            node = node.nextNode;
        }
        return node;
    }

    private void checkElementIndex(int index) {

        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(getIndexOutOfBoundsMessage(index));
        }
    }

    private void checkElementPosition(int index) {

        if (!isElementPosition(index)) {
            throw new IndexOutOfBoundsException(getIndexOutOfBoundsMessage(index));
        }
    }

    private boolean isElementIndex(int index) {

        if (index >= 0 && index < size) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isElementPosition(int index) {

        if (index >= 0 && index <= size) {
            return true;
        } else {
            return false;
        }
    }

    private String getIndexOutOfBoundsMessage(int index) {

        return "Index: " + index + ", Size: " + size;
    }
    
    private static class Node<T> {
        private T element;
        private Node<T> prevNode;
        private Node<T> nextNode;

        public Node(T element, Node<T> prevNode, Node<T> nextNode) {
            this.element = element;
            this.prevNode = prevNode;
            this.nextNode = nextNode;
        }
    }
}
