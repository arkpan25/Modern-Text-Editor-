package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 *
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element )
	{
		// TODO: Implement this method
		if (element == null){
			throw new NullPointerException("MyLinkedList object cannot store null pointers.");
		}
		LLNode<E> node = new LLNode<E>(element,tail.prev);
		tail.prev = node;
		size++;
		return false;
	}
	/**
	 * Appends an element to the front of the list
	 * @param element The element to add
	 */
	public void addFront(E element){
		// TODO: Implement this method
		if (element == null){
			throw new NullPointerException("MyLinkedList object cannot store null pointers.");
			}
		LLNode<E> prevnode = head;
		LLNode<E> nextnode = head.next;
		LLNode<E> idxnode = new LLNode<E>(element);
		prevnode.next = idxnode;
		idxnode.next = nextnode;
		idxnode.prev = prevnode;
		nextnode.prev = idxnode;
		size++;
	}

	/** Get the element at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		// TODO: Implement this method.
        if (index <0 || index >= size){
        	throw new IndexOutOfBoundsException("Provided Index out of bounds");
        }
        LLNode<E> prevnode = head;
        for (int idx = 0; idx<=size; idx++){
        	LLNode<E> idxnode = prevnode.next;
        	if (index == idx){
        		return idxnode.getData();
        	}
        	else{
        		prevnode = idxnode;
        	}
        }
		return null;
	}

	/** Get the LLNode at position index
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public LLNode<E> getNode(int index)
	{
		// TODO: Implement this method.
        if (index <0 || index >= size){
        	throw new IndexOutOfBoundsException("Provided Index out of bounds");
        }
        LLNode<E> prevnode = head;
        for (int idx = 0; idx<=size; idx++){
        	LLNode<E> idxnode = prevnode.next;
        	if (index == idx){
        		return idxnode;
        	}
        	else{
        		prevnode = idxnode;
        	}
        }
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element )
	{
		// TODO: Implement this method
		if (element == null){
			throw new NullPointerException("MyLinkedList object cannot store null pointers.");
			}
		if (index <0 || index > size){
        	throw new IndexOutOfBoundsException("Provided Index out of boudns");
        }
		if (index == 0){
			addFront(element);
		}
		else if (index == size){
			add(element);
		}
		else{
			LLNode<E> nextNode = getNode(index);
			LLNode<E> prevNode = nextNode.prev;
            LLNode<E> addNode = new LLNode<E>(element);
            addNode.next = nextNode;
            prevNode.next = addNode;
            nextNode.prev = addNode;
            addNode.prev = prevNode;
            size++;

		}

	}


	/** Return the size of the list */
	public int size()
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 *
	 */
	public E remove(int index)
	{
		// TODO: Implement this method
		if (index <0 || index >= size){
        	throw new IndexOutOfBoundsException("Provided Index out of boudns");
        }
		LLNode<E> delNode = getNode(index);
		LLNode<E> prevNode = delNode.prev;
		LLNode<E> nextNode = delNode.next;
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		size--;
		return delNode.getData();
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element)
	{
		// TODO: Implement this method
		if (element == null){
			throw new NullPointerException("MyLinkedList object cannot store null pointers.");
			}
		if (index <0 || index >= size){
        	throw new IndexOutOfBoundsException("Provided Index out of boudns");
        }
		LLNode<E> setNode = getNode(index);
		E oldData = setNode.getData();
		setNode.setData(element);
		return oldData;
	}
	public String toString(){
		String LList = "";
		for (int idx=0; idx<size; idx++){
			LList += get(idx)+"\t";
		}
		return LList;
	}

}

class LLNode<E>
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e)
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E theData,LLNode<E> prevNode)
	{
			this(theData);
			this.next = prevNode.next;
			prevNode.next = this;
			this.prev = prevNode;
    }

	public E getData(){
		return data;
	}

	public void setData(E e){
		this.data = e;
	}

}
