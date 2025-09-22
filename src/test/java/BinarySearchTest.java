

import org.example.BinarySearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {
private final BinarySearch search = new BinarySearch();

@Test
@DisplayName("Deve encontrar o elemento no meio do array")
void testFindElementInMiddle() {
    Integer[] array = {1, 3, 5, 7, 9};
    int index = search.find(array, 5);
    assertEquals(2, index);
}

@Test
@DisplayName("Deve encontrar o elemento no lado esquerdo do array")
void testFindElementOnLeftSide() {
    Integer[] array = {1, 3, 5, 7, 9};
    int index = search.find(array, 1);
    assertEquals(0, index);
}

@Test
@DisplayName("Deve encontrar o elemento no lado direito do array")
void testFindElementOnRightSide() {
    Integer[] array = {1, 3, 5, 7, 9};
    int index = search.find(array, 9);
    assertEquals(4, index);
}

@Test
@DisplayName("Deve retornar -1 quando o elemento não está presente")
void testElementNotFound() {
    Integer[] array = {1, 3, 5, 7, 9};
    int index = search.find(array, 6);
    assertEquals(-1, index);
}

@Test
@DisplayName("Deve retornar -1 ao buscar em um array vazio")
void testEmptyArray() {
    Integer[] array = {};
    int index = search.find(array, 10);
    assertEquals(-1, index);
}

@Test
@DisplayName("Deve encontrar o único elemento em um array unitário")
void testSingleElementFound() {
    Integer[] array = {42};
    int index = search.find(array, 42);
    assertEquals(0, index);
}

@Test
@DisplayName("Deve retornar -1 se o único elemento do array não corresponde")
void testSingleElementNotFound() {
    Integer[] array = {42};
    int index = search.find(array, 7);
    assertEquals(-1, index);
}
}