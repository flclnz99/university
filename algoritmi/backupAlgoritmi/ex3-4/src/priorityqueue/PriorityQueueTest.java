package priorityqueue;

import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * It specifies a test suite for the Ordered Array library
 * To improve readability, Java methods' naming conventions are not fully
 * respected...
 * @author magro
 */
public class PriorityQueueTest {
  
  class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {
      return i1.compareTo(i2);
    }
  }
  
  
  private Integer i1,i2,i3;
  private PriorityQueue<Integer> priorityqueue;
  
  @Before
  public void createPriorityQueue() throws PriorityQueueException{
    i1 = 0;
    i2 = 1;
    i3 = 4;
    priorityqueue = new PriorityQueue<>(new IntegerComparator());
  }
  
  @Test
  public void testIsEmptyZeroEl(){
    assertTrue(priorityqueue.empty());
  }
  
  @Test
  public void testIsEmptyOneEl() throws Exception{
    priorityqueue.push(i1);
    assertFalse(priorityqueue.empty());
  }
  
  
  @Test
  public void testSizeZeroEl() throws Exception{
    assertEquals(0,priorityqueue.size());
  }
  
  @Test
  public void testSizeOneEl() throws Exception{
    priorityqueue.push(i1);
    assertEquals(1,priorityqueue.size());
  }
  
  @Test
  public void testSizeTwoEl() throws Exception{
    priorityqueue.push(i1);
    priorityqueue.push(i2);
    assertEquals(2,priorityqueue.size());
  }
  
  @Test
  //It directly accesses the OrderedArray instance variable orderedArray.array
  public void testAddOneEl() throws Exception{
    priorityqueue.push(i1);
    assertEquals(i1, priorityqueue.minHeap.get(0));
  }
  
  @Test
  //It directly accesses the OrderedArray instance variable orderedArray.array
  public void testAddThreeElAddedInOrder() throws Exception{
    Integer[] expectedArray = {i1,i2,i3};
    priorityqueue.push(i1);
    priorityqueue.push(i2);
    priorityqueue.push(i3);
    assertArrayEquals(expectedArray, priorityqueue.minHeap.toArray());
  }

  @Test
  //It directly accesses the OrderedArray instance variable orderedArray.array
  public void testAddThreeElAddedInReverseOrder() throws Exception{
    Integer[] expectedArray = {i1,i3,i2};
    priorityqueue.push(i3);
    priorityqueue.push(i2);
    priorityqueue.push(i1);
    assertArrayEquals(expectedArray, priorityqueue.minHeap.toArray());
  }

  @Test
  //It directly accesses the OrderedArray instance variable orderedArray.array
  public void testAddThreeElAddedInNoOrder() throws Exception{
    Integer[] expectedArray = {i1,i3,i2};
    priorityqueue.push(i2);
    priorityqueue.push(i3);
    priorityqueue.push(i1);
    assertArrayEquals(expectedArray, priorityqueue.minHeap.toArray());
  }

  @Test
  //It directly accesses the OrderedArray instance variable orderedArray.array
  public void testAddThreeElTwoEqual() throws Exception{
    Integer[] expectedArray = {i2,i3};
    priorityqueue.push(i2);
    priorityqueue.push(i3);
    priorityqueue.push(i2);
    assertArrayEquals(expectedArray, priorityqueue.minHeap.toArray());
  }

  @Test
  //It directly accesses the OrderedArray instance variable orderedArray.array
  public void testAddThreeElAllEqual() throws Exception{
    Integer[] expectedArray = {i2};
    priorityqueue.push(i2);
    priorityqueue.push(i2);
    priorityqueue.push(i2);
    assertArrayEquals(expectedArray, priorityqueue.minHeap.toArray());
  }

  @Test
  public void testAddGetOneEl() throws Exception{
    priorityqueue.push(i1);
    assertEquals(i1,priorityqueue.get(0));
  }
  
  @Test
  public void testAddGetThreeElFirst() throws Exception{
    priorityqueue.push(i2);
    priorityqueue.push(i1);
    priorityqueue.push(i3);
    assertEquals(i1,priorityqueue.get(0));
  }

  @Test
  public void testAddGetThreeElSecond() throws Exception{
    priorityqueue.push(i2);
    priorityqueue.push(i1);
    priorityqueue.push(i3);
    assertEquals(i2,priorityqueue.get(1));
  }

  @Test
  public void testAddGetThreeElThird() throws Exception{
    priorityqueue.push(i2);
    priorityqueue.push(i1);
    priorityqueue.push(i3);
    assertEquals(i3,priorityqueue.get(2));
  }
 
}

