/*
Author: Jason Ash
Professor: Dr. AL-Agha
Course: CSCI 2350, Programming and Data Structures, Summer E-Learn2
Date: 2026-07-28
File: MyQueue.java
Description: Uses a custom MyLinkedList that uses Node and implements the List and LinkedList interfaces (as a demonstration of multiple implementation of interfaces), uses Node, and the MyQueue class implements the Queue interface. All of these classes use generics. My implementation of MyLinked list closely matches the one in the textbook, and some of the methods left as an exercise remain that way since I haven't had time to implement them yet. The Node class also closely matches what is in our textbook. I made the LinkedList and Queue interfaces as a contract for what behaviors these Abstract Data Types should contain. Then, I created MyQueue using a composition of MyLinkedList that uses addLast(), getFirst(), and removeFirst() for enqueue(), peek(), and dequeue(), respectively. The remaining methods isEmpty(), getSize(), and toString() use their respective counterparts in MyLinkedList (except for getSize() is called size() in MyLinkedList).
Sources: My implementation for List, Node, and MyLinkedList closely follow what is in our textbook since they use generics but not comparable or iterator. I also followed a suggestion in the third chapter on sorted and unsorted lists in Object-Oriented Data Structures by N. Dale, D.T. Joyce, and C. Weems on returning a copy of the object that was gotten or removed from a list to ensure information hidding and better encapsulation.
*/

class Node<E>
{
	private E element;
	private Node next;

	public Node(E e)
	{
		this.element = e;
	}

	public E getElement()
	{
		E item = (E)new Object();
		item = this.element;
		return item;
	}

	public void setElement(E e)
	{
		E item = (E)new Object();
		item = e;
		this.element = item;
	}

	public Node getNext()
	{
		return this.next;
	}

	public void setNext(Node node)
	{
		this.next = node;
	}

	public boolean hasNext()
	{
		return (this.next == null);
	}
}

interface List<E>
{
	// Append an element to the end of the list
	void add(E e);

	// Insert elements at a specified index
	void add(int index, E e);

	// Remove all elements from the list
	void clear();

	// Check if element exists
	boolean contains(E e);

	// Retrieve element at specified index
	E get(int index);

	// Get index of specified element
	int indexOf(E e);
	
	// Check if list is empty
	boolean isEmpty();

	// Get last occurrence of element
	int lastIndexOf(E e);

	// Remove element
	boolean remove(E e);

	// Remove element at index
	E remove(int index);

	// Replace element
	Object set(int index, E e);

	// Get number of elements
	int size();
}

interface LinkedList<E>
{
	// Return the first element in the list (head)
	public abstract E getFirst();

	// Return the last element in the list (tail)
	public abstract E getLast();

	// Add an element to the beginning of the list
	public void addFirst(E e);

	// Add an element to the end of the list
	public void addLast(E e);

	// Remove the head node and return the object that is contained within it.
	public E removeFirst();

	// Remove the last node and return that object that is contained within it.
	public E removeLast();

}

class MyLinkedList<E> implements List<E>, LinkedList<E>
{
	protected Node head, tail;

	// Number of elements in the list
	protected int size = 0;

	// The no-arg constructor creates an empty list
	public MyLinkedList()
	{
	}

	// Create a list from an array of objects
	public MyLinkedList(E[] e)
	{
		for (int i = 0; i < e.length; i++)
		{
			E item = (E)new Object();
			item = e[i];
			add(item);
		}
	}

	// Return the head element in the list
	@Override
	public E getFirst()
	{
		if(size == 0)
		{
			return null;
		}
		else
		{
			return (E)head.getElement();
		}
	}

	// Return the last element in the list
	@Override
	public E getLast()
	{
		if(size == 0)
		{
			return null;
		}
		else
		{
			return (E)tail.getElement();
		}
	}

	// Add an element to the beginning of the list
	@Override
	public void addFirst(E e)
	{
		// Create a new node
		Node newNode = new Node(e);

		// Link the new node with the head
		newNode.setNext(head);

		// Head points to the new node
		head = newNode;

		// Increase list size
		size++;

		// If the new node is the oly node in the list
		if(tail == null)
		{
			tail = head;
		}
	}

	// Add an element to the end of the list
	@Override
	public void addLast(E e)
	{
		// Crate a new node for the obj element
		Node newNode = new Node(e);

		// If the new node is the only node in the list
		if(tail == null)
		{
			// The new node is the only node in the list
			head = tail = newNode;
		}
		// Link the new node with the last node
		else
		{
			// Link the new node with the last node
			tail.setNext(newNode);
			// Tail now points to the last node
			tail = newNode;
		}
		// increase size
		size++;
	}
	
	// Add a new element at the specified index in this list. The index of the head is 0.
	@Override
	public void add(int index, E e)
	{
		if (index == 0)
		{
			addFirst(e);
		}
		else if (index >= size)
		{
			addLast(e);
		}
		else
		{
			Node current = head;
			for (int i = 1; i < index; i++)
			{
				current = current.getNext();
			}
			Node temp = current.getNext();
			current.setNext(new Node(e));
			(current.getNext()).setNext(temp);
			size++;
		}
	}

	// Remove the head node and return the object that is contained within it.
	@Override
	public E removeFirst()
	{
		if(size == 0)
		{
			return null;
		}
		else
		{
			E temp = (E)head.getElement();
			head = head.getNext();
			size --;
			if (head == null)
			{
				tail = null;
			}
		return temp;
		}
	}

	// Remove the last node and return that object that is contained within it.
	@Override
	public E removeLast()
	{
		if (size == 0)
		{
			return null;
		}
		else if (size == 1)
		{
			E temp = (E)head.getElement();
			head = tail = null;
			size = 0;
			return temp;
		}
		else
		{
			Node current = head;
			
			for (int i = 0; i < size -2; i++)
			{
				current = current.getNext();
			}

			E temp = (E)tail.getElement();
			tail = current;
			tail.setNext(null);
			size--;
			return temp;
		}
	}

	// Remove and return the element at the specified position in this list.
	@Override
	public E remove(int index)
	{
		if (index < 0 || index >= size)
		{
			return null;
		}
		else if (index == 0)
		{
			return removeFirst();
		}
		else if (index == size - 1)
		{
			return removeLast();
		}
		else
		{
			Node previous = head;

			for (int i = 1; i < index; i++)
			{
				previous = previous.getNext();
			}
			Node current = previous.getNext();
			previous.setNext(current.getNext());
			size--;
			return (E)current.getElement();
		}
	}

	// Override toString() to return elements in the list
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder("[");
		Node current = head;
		for (int i = 0; i < size; i++)
		{
			result.append(String.valueOf(current.getElement()));
			current = current.getNext();
			if (current != null)
			{
				// Separate two elements with a comma
				result.append(", ");
			}
			else
			{
				// End the string with the closing ]
				result.append("]");
			}
		}
		return result.toString();
	}

	// Dangerous. Use only if intended. Clear the list.
	@Override
	public void clear()
	{
		size = 0;
		head = tail = null;
	}

	// Left as an exercise
	// Return true if this list contains the object obj
	@Override
	public boolean contains(E e)
	{
		Node current = head;
			
		for (int i = 0; i < size; i++)
		{
			if((current.getElement()).equals(e))
			{
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	// Left as an exercise
	// Return the element at the specified index
	@Override
	public E get(int index)
	{
		if (index == 0)
		{
			return (E)head.getElement();
		}
		else if (index >= size)
		{
			return (E)tail.getElement();
		}
		else
		{
			Node current = head;
			for (int i = 1; i < index; i++)
			{
				current = current.getNext();
			}
			return (E)current.getElement();
		}
	}

	// Return the index of the first mathcing element in this list. Return -1 if no match.
	@Override
	public int indexOf(E e)
	{
		// Left as an exercise
		return 0;
	}

	// Return the index of the last matching element in this list. Return -1 if no match.
	@Override
	public int lastIndexOf(E e)
	{
		// Left as an exercise
		return 0;
	}

	// Replace the element at the specified position in this list with the specified element.
	@Override
	public E set(int index, E e)
	{
		// Left as an exercise
		return null;
	}
	
	// Return the number of elements in this list
	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean remove(E e)
	{
		// Left as an exercise
		return true;
	}
	
	@Override
	public void add(E e)
	{
		addLast(e);
	}

	@Override
	public boolean isEmpty()
	{
		return (size == 0);
	}
}

interface Queue<E>
{
	// Add an item to the back of the list.
	public void enqueue(E item);
	
	// Remove the first item from the list.
	public E dequeue();

	// Return but do not remove the last item in the list
	public E peek();

	// Get the size of the queue
	public int getSize();

	// Return true if the queue is empty or false if otherwise
	public boolean isEmpty();
}

public class MyQueue<E> implements Queue<E>{
	private MyLinkedList<E> list = new MyLinkedList<>();

	@Override
	public void enqueue(E item)
	{
        	list.addLast(item);
    	}

	@Override
	public E dequeue()
	{
        	return list.removeFirst();
	}

	@Override
	public E peek()
	{
		return list.getFirst();
	}

	@Override
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	@Override
	public int getSize()
	{
        	return list.size();
    	}

	@Override
	public String toString()
	{
		return list.toString();
	}

	public static void main(String[] args)
	{
		MyQueue<String> list = new MyQueue<>();
		System.out.println("The initial size of the queue is: " + list.getSize());
		System.out.println("Is the queue empty to start with? " + list.isEmpty());
		
		list.enqueue("Canada");
		System.out.println(list);

		list.enqueue("USA");
		System.out.println(list);

		list.enqueue("Russia");
		System.out.println(list);

		list.enqueue("United Kindom");
		System.out.println(list);

		list.enqueue("Germany");
		System.out.println(list);

		list.enqueue("Norway");
		System.out.println(list);

		list.enqueue("Sweeden");
		System.out.println(list);

		list.enqueue("Poland");
		System.out.println(list);

		System.out.println("The queue now has " + list.getSize() + " elements.");
		System.out.println("Queue to string is " + list.toString());
		
		System.out.println("Removing the first element from the queue");
		System.out.println("dequeuing: " + list.dequeue());
		System.out.println(list);

		System.out.println("Removing the first element from the queue");
		System.out.println("dequeuing: " + list.dequeue());
		System.out.println(list);

		System.out.println("Removing the first element from the queue");
		System.out.println("dequeuing: " + list.dequeue());
		System.out.println(list);

		System.out.println("The queue now has " + list.getSize() + " elements.");

		System.out.println("The queue\'s size is now " + list.getSize() + " elements.");
		
		while(list.getSize() > 0)
		{
			System.out.println("dequeuing: " + list.dequeue());
		}

		System.out.println("Is the queue now empty? " + list.isEmpty());
		System.out.println("The queue now has " + list.getSize() + " elements.");
        }
/*
Works Cited:
Dale, Nell, Joyce, Daniel T., and Weems, Chip. Object-Oriented Data Structures Using Java. Jones and Bartlett Learning, 2002. 
Liang, Y. Daniel. Introduction to Java Programming and Data Structures. 13th ed., Pearson Education Limited, 2024.
*/
}
