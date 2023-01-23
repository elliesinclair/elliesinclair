import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {
	
	private SinglyLinkedList<T> theStack;
	
	//Top of the stack is the head of the linked list
	public LinkedListStack() {
		theStack = new SinglyLinkedList();
	}

	@Override
	public void push(T v) {
		theStack.addFirst(v);
	}

	@Override
	public T pop() throws NoSuchElementException {
		if(theStack.isEmpty()) {
			throw new NoSuchElementException("Stack is empty.");
		}
		
		return theStack.removeFirst();
	}

	@Override
	public T top() throws NoSuchElementException {
		if(theStack.isEmpty()) {
			throw new NoSuchElementException("Stack is empty.");
		}
		
		return theStack.get(0);
	}

	@Override
	public int size() {
		return theStack.size();
	}

	@Override
	public boolean isEmpty() {
		return theStack.isEmpty();
	}
	
	public String toString() {
		return "Top of the Stack: " + theStack.toString();
	}

}
