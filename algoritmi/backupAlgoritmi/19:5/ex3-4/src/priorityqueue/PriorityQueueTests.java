import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTests {
/**
 * It specifies a test suite for the Ordered Array library
 * To improve readability, Java methods' naming conventions are not fully
 * respected...
 * @author magro
 */  
  class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
      return i1.compareTo(i2);
    }
  }
  
  
  private Integer i1,i2,i3;
  private PriorityQueue<Integer> queue;
  
  @Before
  public void createOrderedArray() throws PriorityQueueException{
    i1 = -12;
    i2 = 0;
    i3 = 4;
    queue = new PriorityQueue<>(new IntegerComparator());
  }
  
  @Test
  public void testIsEmptyZeroEl(){
    assertTrue(queue.empty());
  }
   
  @Test
  //It directly accesses the queue instance variable queue.array
  public void testAddOneEl() throws Exception{
    queue.push(i1);
    assertEquals(i1, queue.minHeap.get(0));
  }
  
  @Test
  //It directly accesses the queue instance variable queue.array
  public void testAddThreeElement() throws Exception{
    Integer[] expectedArray = {i1,i2,i3};
    queue.push(i1);
    queue.push(i2);
    queue.push(i3);
    assertArrayEquals(expectedArray, queue.minHeap.toArray());
  }

  @Test
  //It directly accesses the queue instance variable queue.array
  public void testContainsOneElement() throws Exception{
    queue.push(i1);
    assertTrue(queue.contains(i1));
  }

  @Test
  public void testContainsTwoElement() throws Exception{
    queue.push(i1);
    queue.push(i2);
    assertTrue(queue.contains(i2));
  }

  @Test
  public void testNotContainsElement() throws Exception{
    queue.push(i1);
    queue.push(i2);
    assertFalse(queue.contains(i3));
  }

  @Test
  public void testTopElementWithOneElement() throws Exception{
    queue.push(i1);
    assertEquals(queue.top(),i1);
  }

  @Test
  public void testTopElementWithThreeElement() throws Exception{
    queue.push(i1);
    queue.push(i2);
    queue.push(i3);
    assertEquals(queue.top(),i1);
  }

  @Test
  public void testTopElementWithThreeElementNotInOrder() throws Exception{
    queue.push(i3);
    queue.push(i1);
    queue.push(i2);
    assertEquals(queue.top(),i1);
  }

  @Test
  public void testPopElement() throws Exception{
    queue.push(i3);
    queue.pop();
    assertTrue(queue.empty());
  }
/*
  @Test
  //It directly accesses the queue instance variable queue.array
  public void testAddThreeElpushedInReverseOrder() throws Exception{
    Integer[] expectedArray = {i1,i2,i3};
    queue.push(i3);
    queue.push(i2);
    queue.push(i1);
    assertArrayEquals(expectedArray, queue.minHeap.toArray());
  }
  @Test
  //It directly accesses the queue instance variable queue.array
  public void testAddThreeElpushedInNoOrder() throws Exception{
    Integer[] expectedArray = {i1,i2,i3};
    queue.push(i2);
    queue.push(i3);
    queue.push(i1);
    assertArrayEquals(expectedArray, queue.minHeap.toArray());
  }

  @Test
  //It directly accesses the queue instance variable queue.array
  public void testAddThreeElTwoEqual() throws Exception{
    Integer[] expectedArray = {i2,i2,i3};
    queue.push(i2);
    queue.push(i3);
    queue.push(i2);
    assertArrayEquals(expectedArray, queue.minHeap.toArray());
  }
*/
  @Test
  //It directly accesses the queue instance variable queue.array
  public void testAddThreeElAllEqual() throws Exception{
    Integer[] expectedArray = {i2,i2,i2};
    queue.push(i2);
    queue.push(i2);
    queue.push(i2);
    assertArrayEquals(expectedArray, queue.minHeap.toArray());
  }

  @Test
  public void testAddGetOneEl() throws Exception{
    queue.push(i1);
    assertEquals(i1,queue.get(0));
  }
  
  @Test
  public void testAddGetThreeElFirst() throws Exception{
    queue.push(i2);
    queue.push(i1);
    queue.push(i3);
    assertEquals(i1,queue.get(0));
  }

  @Test
  public void testAddGetThreeElSecond() throws Exception{
    queue.push(i2);
    queue.push(i1);
    queue.push(i3);
    assertEquals(i2,queue.get(1));
  }

  @Test
  public void testAddGetThreeElThird() throws Exception{
    queue.push(i2);
    queue.push(i1);
    queue.push(i3);
    assertEquals(i3,queue.get(2));
  }
 
}
