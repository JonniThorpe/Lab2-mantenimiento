import java.util.Comparator;

public class DoubleLinkedList<T> implements DoubleLinkedQueue<T> {

    private LinkedNode<T> first;
    private LinkedNode<T> last;

    private int size;

    public DoubleLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public void prepend(T value) {
        LinkedNode<T> node = new LinkedNode<>(value, null, first);
        if (size == 0) {
            last = node;
        } else {
            first.setPrevious(node);
        }
        first = node;
        size++;
    }

    @Override
    public void append(T value) {
        LinkedNode<T> node = new LinkedNode<>(value, last, null);
        if (size == 0) {
            first = node;
        } else {
            last.setNext(node);
        }
        last = node;
        size++;

    }

    @Override
    public void deleteFirst() {
        if (size > 0) {
            first.setPrevious(null);
            first = first.getNext();
            size--;
        } else {
            throw new DoubleLinkedQueueException("No hay nodos en el arbol");
        }
    }

    @Override
    public void deleteLast() {
        if (size > 0) {
            last.setNext(null);
            last = last.getPrevious();
            size--;
        } else {
            throw new DoubleLinkedQueueException("No hay nodos en el arbol");
        }
    }

    @Override
    public T first() {
        if (size > 0) {
            return first.getItem();
        }
        return null;
    }

    @Override
    public T last() {
        if (size > 0) {
            return last.getItem();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new DoubleLinkedQueueException("Index out of bounds");
        }
        int i = 0;
        LinkedNode<T> aux = first;
        while (i != index) {
            aux.isNotATerminalNode();
            aux = aux.getNext();
            i++;
        }
        return aux.getItem();
    }

    @Override
    public boolean contains(T value) {
        LinkedNode<T> aux = first;
        while (aux != null) {
            if (value.equals(aux.getItem())) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    @Override
    public void remove(T value) {
        if (first != null) {
            if (first.getItem().equals(value)) {
                first = first.getNext();
                if (first == null) {
                    last = null;
                } else {
                    first.isFirstNode();
                }
                size--;
            } else {
                boolean found = false;
                LinkedNode<T> aux = first;
                while (aux != null && !found) {
                    if (aux.getItem().equals(value)) {
                        if (aux == last) {
                            last = aux.getPrevious();
                            last.isLastNode();
                            last.setNext(null);
                        } else {
                            aux.getPrevious().setNext(aux.getNext());
                            if (aux.getNext() != null) {
                                aux.getNext().setPrevious(aux.getPrevious());
                            }
                        }
                        size--;
                        found = true;
                    } else {
                        aux = aux.getNext();
                    }
                }

            }
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        if (size > 1) {
            boolean wasChanged = true;
    
            while (wasChanged) {
                wasChanged = false;
                LinkedNode<T> current = first;
    
                while (current.getNext() != null) { 
                    LinkedNode<T> next = current.getNext();
                    if (comparator.compare(current.getItem(), next.getItem()) > 0) {
                        T temp = current.getItem();
                        current.setItem(next.getItem());
                        next.setItem(temp);
                        wasChanged = true;
                    }
                    current = next;
                }
            }
        }
    }

}
