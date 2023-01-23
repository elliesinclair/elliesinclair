import java.util.NoSuchElementException;
import java.util.LinkedList;

public class SinglyLinkedList<T>{

	private Node head;
	private int size;

	public SinglyLinkedList() {
		head = null;
		size = 0;
	}

	public void addFirst(T v) {
		if(head == null) {  
			head = new Node(v);
		}
		else {
			Node newNode = new Node(v);
			newNode.next = head;
			head = newNode;
		}
		size++;
	}

	public void addLast(T v) {

		if(head == null ) {
			Node newNode = new Node(v);
			head = newNode;
			newNode.next = null;
		}
		else {
			Node newNode = new Node(v);
			Node temp = head;

			while(temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
			newNode.next = null;
		}
		size++;
	}

	public String toString() {
		String r = "";

		Node current = head;

		while(current != null) {
			r += current.toString() + " ";
			current = current.next;
		}

		return r;
	}

	//public T findMinimum() {
	//	T min = head.value;
	//	Node temp = head;
	//
	//	while(temp != null) {
	//		if(temp.value.compareTo(min) < 0) {
	//			min = temp.value;
	//			temp = temp.next;
	//		}
	//		else {
	//			temp = temp.next;
	//		}
	//	}
	//	return min;
	//}

	public T removeFirst() {
		if (head == null)
		{
			throw new NoSuchElementException("List is empty. Can't remove an element from empty list");
		}

		T returnValue = null;
		if(head.next == null) {
			returnValue = head.value;
			head = null;
		}
		else {
			returnValue = head.value;
			head = head.next;
		}
		size--;
		return returnValue;

	}

	public T removeLast() 
	{
		//If the list is empty, calling the method should throw an exception
		if (head == null)
		{
			throw new NoSuchElementException("List is empty. Can't remove an element from empty list");
		}

		T returnValue = null;

		//If the list has only one element, return the value of that element and set the head to null
		if(head.next == null)
		{
			returnValue = head.value;
			head = null;
		} else {
			Node temp = head;
			while(temp.next.next != null)

			{
				temp = temp.next;
			}
			returnValue = temp.next.value;
			temp.next = null;
		}
		size--;
		return returnValue;
	}

	public T removeAtIndex(int index) {
		T value = null;
		Node temp = head;
		Node preTemp = head;

		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if(index == 0) {
			head = head.next;
			size--;
			return temp.value;
		}

		else if(index == size-1) {
			while(temp.next != null) {
				preTemp = temp;
				temp = temp.next;
			}
			preTemp.next = null;
			size--;
			return temp.value;
		}

		else {
			for(int i = 0; i < index; i++) {
				preTemp = temp;
				temp = temp.next;
			}
			preTemp.next = temp.next;
			size--;
			return temp.value;
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		if(head == null) {
			return true;
		}
		return false;
	}
	
	public boolean remove(T value) {
		if(head == null) {
			throw new NoSuchElementException();
		}
		
		if(head.value.equals(value)) {
			head = head.next;
			size--;
			return true;
		}
		
		Node temp = head;
		
		while(temp.next != null && !temp.next.value.equals(value)) {
			temp = temp.next;
		}
		if(temp.next == null) {
			return false;
		}

		temp.next = temp.next.next;
		size--;
		return true;
	}
	
	public T get(int i) {
		if(head == null) {
			throw new NoSuchElementException();
		}
		if(i > size-1 || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		Node temp = head;
		T value = null;
		int index = 0;
		
		while(index < i) {
			temp = temp.next;
			index++;
		}
		
		if(index == i) {
			value = temp.value;
		}
		
		return value;
		
	}



	//Inner class for Node
	private class Node {
		private T value; //data
		private Node next; // reference to next

		public Node(T v) {
			this.value = v;
			this.next = null;
		}

		public String toString() {
			return value.toString();
		}

	}
}
