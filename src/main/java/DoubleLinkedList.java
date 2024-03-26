import java.util.LinkedList;

public class DoubleLinkedList<T> implements DoubleLinkedQueue<T> {

    private LinkedNode<T> first;
    private LinkedNode<T> last;

    private int size;

    public DoubleLinkedList() {
        this.first = null;
        this.last = null;
        this.size =0;
    }

    @Override
    public void prepend(T value) {
        LinkedNode<T> node = new LinkedNode<>(value,null,first);
        if (size == 0){
            last = node;
        } else {
            first.setPrevious(node);
        }
        first = node;
        size++;
    }

    @Override
    public void append(T value) {
        LinkedNode<T> node = new LinkedNode<>(value,last,null);
        if(size == 0){
            first=node;
        }else{
            last.setNext(node);
        }
        last=node;
        size++;

    }

    @Override
    public void deleteFirst(){
        if(size>0) {
            first.setPrevious(null);
            first = first.getNext();
            size--;
        }else {
            throw new DoubleLinkedQueueException("No hay nodos en el arbol");
        }
    }

    @Override
    public void deleteLast(){
        if(size>0) {
            last.setNext(null);
            last = last.getPrevious();
            size--;
        }else {
            throw new DoubleLinkedQueueException("No hay nodos en el arbol");
        }
    }

    @Override
    public T first() {
        if(size>0){
            return first.getItem();
        }
        return null;
    }

    @Override
    public T last() {
        if(size>0){
            return last.getItem();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
