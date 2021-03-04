package project1;

import java.util.*;

/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    private void clear( )
    {
        doClear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
    
    /**Method swap
     * At first the method changes the value of x.next and y.next.
     * Secondly, it changes the value of y to the value at index 1
     * At last, it changes the value of x to the value at index 2
     * @param idx1, and idx2 are the index position to swap
    */
    public void swap(int idx1, int idx2) {
    	Node <AnyType> x,y,xnext,yprev;
    	x=getNode(idx1);
    	y=getNode(idx2);
    	xnext=x.next;
    	yprev=y.prev;
    	x.next=y.next;
    	y.next=xnext;
    	//System.out.println(x.next.data)
    	//System.out.println(y.next.data)
    	
    	xnext.prev=y;
    	x.prev.next=y;
    	x.prev = yprev;
    	//System.out.println(y.data);
    	
    	yprev.next=x;
    	y.next.prev = x;
    	y.prev=x.prev;
    	//System.out.println(x.data);
    	modCount++;
    }
    
    /**
     * Method: shift 
     * It shifts the list forwards or backwards based on 'nshift' being positive or negative.
     * For (nshift > 0) this method first takes a node 'x' which is assigned as the first node of the list. 
     * Then as beginMarker takes x's position, x is shifted to the end between the tail node(endMarker) and the last node. 
     * For (nshift < 0) this method first takes a node 'y' which is assigned as the last node of the list.
     * Then as endMarker takes y's position, y is shifted to the start of the list between the head node(beginMarker) and the first node.
     * @param nshift is the number of shifts
     */
   public void shift(int nshift) {
	   if (nshift>0) {
		   for (int i = 0; i < nshift; i++) {
			   Node <AnyType> x = beginMarker.next;
			   beginMarker.next = x.next;
			   beginMarker.next.prev = beginMarker;	//beginMarker has now replaced the first node
			   x.prev = endMarker.prev;
			   x.next = endMarker;
			   endMarker.prev = x;
			   x.prev.next = x;						//'x' has squeezed in between the tail node and the previous last node. Now node 'x' is the last node
			   modCount++;
		   }
	   }
	   else if (nshift<0) {
		   for (int i = 0; i > nshift; i--) {
			   Node <AnyType> y = endMarker.prev;
			   endMarker.prev = y.prev;			//changes the prev link of endMarker to the prev of y
			   endMarker.prev.next = endMarker;	//endMarker has now replaced the last node
			   y.prev = beginMarker; 
			   y.next = beginMarker.next; 
			   y.next.prev = y;					//y has squeezed in between the head node and the previous first node. Now node y is the first node
			   beginMarker.next = y;
			   modCount++; 
		   }
	   }  
   }
    
    /**
     * Method: erase 
     * It removes elements beginning at the provided index position
     * @param idx is the index position 
     * @param n is the number of elements to be removed starting from idx position
     */
    public void erase(int idx, int n) {
    	if(idx + n > theSize)
    	{
    		System.out.println("Request is denied; requested number of removal of nodes exceeds the number of nodes in the list ");
    	}
    	else if(idx + n <= theSize)
    	{
    		for (int i = 0; i < n; i++)
    			remove(idx);		//removes the node at idx position
    	}
    }
    /**
     * Method: insertList
     * It takes in a new linked list and inserts right after 'x' node and sits in between the 'x' and 'y' node. Hence the x.next and y.prev has to be adjusted in order for the new linked list to be placed inside.
     * @param newLL is the new received linked list whose elements will be copied to the existing linked list
     * @param idx is the index position which will be the starting point of the new inserted linked list
     */
    public void insertList(MyLinkedList<AnyType> newLL,int idx)
    {
    	Node<AnyType> x,y;
    	x = getNode(idx);
    	y = getNode(idx +1);
    	
    	if (idx > theSize) {
    		System.out.println("Index exceeds the size of the existing Linked List");
    	}
    	else if (idx <= theSize) {
    		y = x.next;
        	y.prev = x;
        	//System.out.println(x.next.data);
        	//System.out.println(y.prev.data);
        	
        	x.next = newLL.beginMarker.next;
        	newLL.beginMarker.next.prev = x;	//the first node of the new list links with the x node
        	//System.out.println(x.next.data);
        	y.prev = newLL.endMarker.prev;
        	newLL.endMarker.prev.next = y;		//the last node of the new list links with the y node
        	//System.out.println(y.prev.data);
        	theSize = theSize + newLL.size(); 	//size increments with the new list inside
        	modCount++;
    	}
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main(String[] args)
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );
        MyLinkedList<Integer> newLL = new MyLinkedList<>( );

       /* for( int i = 0; i < 10; i++ )
                lst.add( i );
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );

        java.util.Iterator<Integer> itr = lst.iterator( );
        while( itr.hasNext( ) )
        {
                itr.next( );
                itr.remove( );
                System.out.println( lst );
        }*/
        
        /* Swap Method Test */
        for( int i = 0; i < 10; i++ )
            lst.add( i );
	     System.out.println("Original List: " + lst );
	    
	     lst.swap(2,5);
	     System.out.println("List after Swap method " + lst );
	     lst.doClear();
	    
	     /* Shift Method Test */
	     for( int i = 0; i < 10; i++ )
	    	 lst.add(i);
	     System.out.println("\nOriginal List: " + lst );
	     lst.shift(5);
	     System.out.println("List after Shift(POSITIVE) method " + lst );
	     lst.doClear();
	     
	     for( int i = 0; i < 10; i++ )
	    	 lst.add(i);
	     System.out.println("Original List: " + lst );
	     lst.shift(-3);
	     System.out.println("List after Shift(NEGATIVE) method " + lst );
	     lst.doClear();
	     
	     /* Erase Method Test */
	     for( int i = 0; i < 10; i++)
	    	 lst.add(i);
	     System.out.println("\nOriginal List: " + lst);
	     lst.erase(5,4);
	     System.out.println("List after Erase method " + lst);
	     lst.doClear();
	     
	     for( int i = 0; i < 10; i++)
	    	 lst.add(i);
	     System.out.println("Original List: " + lst);
	     lst.erase(6,6);
	     System.out.println("List after Erase method " + lst);
	     lst.doClear();
	     
	     /* InsertList Method Test */
	     for( int i = 0; i < 10; i++)
	    	 lst.add(i);
	     System.out.println("\nOriginal List: " + lst);
	     for( int j = 10; j < 20; j++ )
	         newLL.add(j);
	     System.out.println("Second Linked List: " + newLL);
	     lst.insertList(newLL, 5);
	     System.out.println("List after InsertList method: " + lst);
    }
}
