import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTest {
    private DoubleLinkedList doublelist;

    @BeforeEach
    public void Setup() {
        doublelist = new DoubleLinkedList<>();
    }

    @Test
    public void constructorShouldsetNull(){
        //first should return null if empty trees
        assertNull(doublelist.first());
        //last should return null if empty trees
        assertNull(doublelist.last());
        //size should return 0 if empty trees
        assertEquals(doublelist.size(),0);
    }

    @Test
    public void prepend_ShouldAddFirst_toDoubleLinkedList(){
        doublelist.prepend("hamburguesa");
        assertEquals(doublelist.first(),"hamburguesa");
        doublelist.prepend("pizza");//Ahora pizza pasaría a ser first
        assertEquals(doublelist.first(),"pizza");


    }

    @Test
    public void append_ShouldAddLast_toDoubleLinkedList(){
        doublelist.append("hamburguesa");
        assertEquals(doublelist.last(),"hamburguesa");
        doublelist.append("pizza");//Ahora pizza pasaría a ser last
        assertEquals(doublelist.last(),"pizza");

        //ademas hamburguesa debera ser first
        assertEquals(doublelist.first(),"hamburguesa");
    }

    @Test
    public void deleteFirst_ShouldDeleteFirstNode_fromDoubleLinkedList(){
        //Adds nodes to delete
        addNodes();
        doublelist.deleteFirst();//Should delete sandwich el primero.
        //al obtener el primero debe  devolver perrito.
        assertEquals(doublelist.first(),"perrito");
    }

    @Test
    public void deleteLast_ShouldDeleteLastNode_fromDoubleLinkedList(){
        addNodes();
        doublelist.deleteLast();//Should delete hamburguesa el ultimo.
        //al obtener el primero debe  devolver pizza.
        assertEquals(doublelist.last(),"pizza");
    }

    @Test
    public void deleteLast_ShouldThrowDoubleLinkedQueueException_ifEmpty(){
        //Should throw exception because no nodes to delete in the tree
        assertThrows(DoubleLinkedQueueException.class, ()-> doublelist.deleteLast());
    }
    @Test
    public void deleteFirst_ShouldThrowDoubleLinkedQueueException_ifEmpty(){
        //Should throw exception because no nodes to delete in the tree
        assertThrows(DoubleLinkedQueueException.class, ()-> doublelist.deleteFirst());
    }

    public void addNodes(){
        doublelist.prepend("hamburguesa");
        doublelist.prepend("pizza");
        doublelist.prepend("perrito");
        doublelist.prepend("sandwich");
    }



}
