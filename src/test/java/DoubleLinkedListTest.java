import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

public class DoubleLinkedListTest {
    private DoubleLinkedList doublelist;

    @BeforeEach
    public void Setup() {
        doublelist = new DoubleLinkedList<>();
    }

    @Test
    public void constructorShouldsetNull() {
        // first should return null if empty trees
        assertNull(doublelist.first());
        // last should return null if empty trees
        assertNull(doublelist.last());
        // size should return 0 if empty trees
        assertEquals(doublelist.size(), 0);
    }

    @Test
    public void prepend_ShouldAddFirst_toDoubleLinkedList() {
        doublelist.prepend("hamburguesa");
        assertEquals(doublelist.first(), "hamburguesa");
        doublelist.prepend("pizza");// Ahora pizza pasaría a ser first
        assertEquals(doublelist.first(), "pizza");

    }

    @Test
    public void append_ShouldAddLast_toDoubleLinkedList() {
        doublelist.append("hamburguesa");
        assertEquals(doublelist.last(), "hamburguesa");
        doublelist.append("pizza");// Ahora pizza pasaría a ser last
        assertEquals(doublelist.last(), "pizza");

        // ademas hamburguesa debera ser first
        assertEquals(doublelist.first(), "hamburguesa");
    }

    @Test
    public void deleteFirst_ShouldDeleteFirstNode_fromDoubleLinkedList() {
        // Adds nodes to delete
        addNodes();
        doublelist.deleteFirst();// Should delete sandwich el primero.
        // al obtener el primero debe devolver perrito.
        assertEquals(doublelist.first(), "perrito");
    }

    @Test
    public void deleteLast_ShouldDeleteLastNode_fromDoubleLinkedList() {
        addNodes();
        doublelist.deleteLast();// Should delete hamburguesa el ultimo.
        // al obtener el primero debe devolver pizza.
        assertEquals(doublelist.last(), "pizza");
    }

    @Test
    public void deleteLast_ShouldThrowDoubleLinkedQueueException_ifEmpty() {
        // Should throw exception because no nodes to delete in the tree
        assertThrows(DoubleLinkedQueueException.class, () -> doublelist.deleteLast());
    }

    @Test
    public void deleteFirst_ShouldThrowDoubleLinkedQueueException_ifEmpty() {
        // Should throw exception because no nodes to delete in the tree
        assertThrows(DoubleLinkedQueueException.class, () -> doublelist.deleteFirst());
    }

    public void addNodes() {
        doublelist.prepend("hamburguesa");
        doublelist.prepend("pizza");
        doublelist.prepend("perrito");
        doublelist.prepend("sandwich");
    }

    // Pruebas de la sesión 2:
    @Nested
    @DisplayName("Operación Contains")
    class ContainsTests {

        @Test
        @DisplayName("contains_withPresentValue_shouldReturnTrue")
        void contains_withPresentValue_shouldReturnTrue() {
            // Verifica que el método contains devuelva verdadero si el valor está en la
            // lista
            doublelist.append(1);
            assertTrue(doublelist.contains(1));
        }

        @Test
        @DisplayName("contains_withAbsentValue_shouldReturnFalse")
        void contains_withAbsentValue_shouldReturnFalse() {
            // Verifica que el método contains devuelva falso si el valor no está en la
            // lista
            doublelist.append(1);
            assertFalse(doublelist.contains(3));
        }
    }

    @Nested
    @DisplayName("Operación Remove")
    class RemoveTests {

        @Test
        @DisplayName("remove_withValueInFirstPosition_shouldRemoveElement")
        void remove_withValueInFirstPosition_shouldRemoveElement() {
            // Verifica que el método remove elimine correctamente un elemento que está en
            // la lista
            doublelist.append(1);
            doublelist.append(2);
            doublelist.remove(1);
            assertEquals(1, doublelist.size());
            assertFalse(doublelist.contains(1));
        }

        @Test
        @DisplayName("remove_withValueNotInFirstPosition_shouldRemoveElement")
        void remove_withValueNotInFirstPosition_shouldRemoveElement() {
            // Verifica que el método remove elimina correctamente un elemento que no se
            // encuentra en la primera posición de la lista
            doublelist.append(1);
            doublelist.append(2);
            doublelist.append(3);

            doublelist.remove(2);

            assertFalse(doublelist.contains(2));
        }

        @Test
        @DisplayName("remove_withValueInLastPosition_shouldRemoveElement")
        void remove_withValueInLastPosition_shouldRemoveElement() {
            // Verifica que el método remove elimina correctamente un elemento que se
            // encuentra en la ultima posición de la lista
            doublelist.append(1);
            doublelist.append(2);
            doublelist.append(3);

            doublelist.remove(3);

            assertFalse(doublelist.contains(3));
        }

        @Test
        @DisplayName("remove_withValueNotExisting_shouldNotDoAnything")
        void remove_withValueNotExisting_shouldNotDoAnything() {
            // Verifica que el metodo remove no hace nada si el valor a eliminar no se
            // encuentra en la lista
            doublelist.append(1);
            doublelist.append(2);
            doublelist.append(3);

            doublelist.remove(4);

            assertTrue(doublelist.contains(1));
            assertTrue(doublelist.contains(2));
            assertTrue(doublelist.contains(3));
        }

        @Test
        @DisplayName("remove_withOnlyOneValueInList_shouldRemoveElement")
        void remove_withOnlyOneValueInList_shouldRemoveElement() {
            doublelist.append(1);

            doublelist.remove(1);

            assertFalse(doublelist.contains(1));
        }

        @Test
        @DisplayName("remove_withEmptyList_shouldNotDoAnything")
        void remove_withEmptyList_shouldNotDoAnything() {
            doublelist.remove(1);
            assertTrue(doublelist.size() == 0);
        }
    }

    @Nested
    @DisplayName("Operación Get")
    class GetTests {
        @Test
        @DisplayName("get_notEmptyList_shouldReturnValue")
        void get_notEmptyList_shouldReturnValue() {
            // Verifica que el método get devuelve el valor correcto de un índice
            // en una lista no vacía
            doublelist.append(1);
            doublelist.append(2);
            doublelist.append(3);

            assertEquals(1, doublelist.get(0));
            assertEquals(2, doublelist.get(1));
            assertEquals(3, doublelist.get(2));
        }

        @Test
        @DisplayName("get_withIndexOutOfBounds_shouldThrowException")
        void get_withIndexOutOfBounds_shouldThrowException() {
            // Verifica que el método get lance una excepción cuando el índice
            // está fuera de los límites
            doublelist.append(1);
            doublelist.append(2);

            assertThrows(DoubleLinkedQueueException.class, () -> doublelist.get(-1));
            assertThrows(DoubleLinkedQueueException.class, () -> doublelist.get(2));
        }

        @Test
        @DisplayName("get_emptyList_shouldThrowException")
        void get_emptyList_shouldThrowException() {
            // Verifica que el método get lance una excepción al intentar acceder
            // a un índice de una lista vacía
            DoubleLinkedList<Integer> doublelist = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, () -> doublelist.get(0));
        }
    }

    @Nested
    @DisplayName("Operación Sort")
    class SortTests {

        @Test
        @DisplayName("sort_withUnsortedList_shouldSortList")
        void sort_withUnsortedList_shouldSortList() {
            // Verifica que el método sort ordena correctamente una lista desordenada
            Comparator<Integer> comparator = Integer::compareTo;
            doublelist.append(2);
            doublelist.append(1);
            doublelist.sort(comparator);

            assertEquals(1, doublelist.get(0));
            assertEquals(2, doublelist.get(1));
        }

        @Test
        @DisplayName("sort_withAlreadySortedList_shouldKeepOrder")
        void sort_withAlreadySortedList_shouldKeepOrder() {
            // Verifica que el método sort mantiene el orden de una lista ya ordenada
            Comparator<Integer> comparator = Integer::compareTo;
            doublelist.append(1);
            doublelist.append(2);
            doublelist.sort(comparator);

            assertEquals(1, doublelist.get(0));
            assertEquals(2, doublelist.get(1));
        }

        @Test
        @DisplayName("sort_withListHavingDuplicates_shouldSortList")
        void sort_withListHavingDuplicates_shouldSortList() {
            // Verifica que el método sort ordena correctamente una lista con valores
            // duplicados
            Comparator<Integer> comparator = Integer::compareTo;
            doublelist.append(2);
            doublelist.append(1);
            doublelist.append(2);
            doublelist.sort(comparator);

            assertEquals(1, doublelist.get(0));
            assertEquals(2, doublelist.get(1));
            assertEquals(2, doublelist.get(2));
        }

        @Test
        @DisplayName("sort_withEmptyList_shouldDoNothing")
        void sort_withEmptyList_shouldDoNothing() {
            // Verifica que el método sort no hace nada en una lista vacía
            Comparator<Integer> comparator = Integer::compareTo;
            doublelist.sort(comparator);

            assertEquals(0, doublelist.size());
        }
    }

}
