/**
 *
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

	}


	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

	}


	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		//assertEquals("Remove: check that list1.get(0).prev is equal to list1.head ",list1.getNode(0).prev , list1.head);
		// TODO: Add more tests here
	}

	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		list1 = new MyLinkedList<Integer>();
		int[] nums = {25,23,2,64,20,26,28};
		for (int k=0; k<nums.length; k++){
			int num = nums[k];
			list1.add(nums[k]);
			assertEquals("Add End: check end element is correct",(Integer)num,list1.get(list1.size()-1));
		}

	}


	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		list1 = new MyLinkedList<Integer>();
		int[] nums = {25,23,2,64,20,26,28};
		for (int k=0; k<nums.length; k++){
			list1.add(nums[k]);
		}
		assertEquals("Add End: check end element is correct",nums.length,list1.size());
	}



	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		list1 = new MyLinkedList<Integer>();
		int[] nums = {25,23,2,64,20,26,28};
		for (int k=0; k<nums.length; k++){
			list1.add(nums[k]);
		}
		list1.add(0, 0);
		assertEquals("Add At Index: check element is correct",(Integer)0,list1.get(0));
        list1.add(2, 15);
        assertEquals("Add At Index: check element is correct",(Integer)15,list1.get(2));
        assertEquals("Add At Index: check element is correct",(Integer)23,list1.get(3));
        assertEquals("Add At Index: check element is correct",(Integer)2,list1.get(4));
        list1.add(list1.size(), 45);
        assertEquals("Add At Index: check element is correct",(Integer)45,list1.get(list1.size()-1));
        try {
        	list1.add(11, 45);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}

	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		int oldVal = list1.set(0, 25);
		assertEquals("Set At Index: check element is correct",(Integer)25,list1.get(0));
		assertEquals("Set At Index: check element is correct",65,oldVal);
		list1.set(2,20);
		assertEquals("Set At Index: check element is correct",(Integer)20,list1.get(2));
        System.out.println(list1);
	}


	// TODO: Optionally add more test methods.

}
