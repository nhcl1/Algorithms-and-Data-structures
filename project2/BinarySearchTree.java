package project2BST;

import java.util.*;
//BinarySearchTree class
//
//CONSTRUCTION: with no initializer
//
//******************PUBLIC OPERATIONS*********************
//void insert( x )       --> Insert x
//void remove( x )       --> Remove x
//boolean contains( x )  --> Return true if x is present
//Comparable findMin( )  --> Return smallest item
//Comparable findMax( )  --> Return largest item
//boolean isEmpty( )     --> Return true if empty; else false
//void makeEmpty( )      --> Remove all items
//void printTree( )      --> Print tree in sorted order
//******************ERRORS********************************
//Throws UnderflowException as appropriate

/**
* Implements an unbalanced binary search tree.
* Note that all "matching" is based on the compareTo method.
* @author Mark Allen Weiss
*/
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
 /**
  * Construct the tree.
  */
 public BinarySearchTree( )
 {
     root = null;
 }

 /**
  * Insert into the tree; duplicates are ignored.
  * @param x the item to insert.
  */
 public void insert( AnyType x )
 {
     root = insert( x, root );
 }

 /**
  * Remove from the tree. Nothing is done if x is not found.
  * @param x the item to remove.
  */
 public void remove( AnyType x )
 {
     root = remove( x, root );
 }

 /**
  * Find the smallest item in the tree.
  * @return smallest item or null if empty.
  */
 public AnyType findMin( )
 {
     if( isEmpty( ) )
         throw new UnderflowException( );
     return findMin( root ).element;
 }

 /**
  * Find the largest item in the tree.
  * @return the largest item of null if empty.
  */
 public AnyType findMax( )
 {
     if( isEmpty( ) )
         throw new UnderflowException( );
     return findMax( root ).element;
 }

 /**
  * Find an item in the tree.
  * @param x the item to search for.
  * @return true if not found.
  */
 public boolean contains( AnyType x )
 {
     return contains( x, root );
 }

 /**
  * Make the tree logically empty.
  */
 public void makeEmpty( )
 {
     root = null;
 }

 /**
  * Test if the tree is logically empty.
  * @return true if empty, false otherwise.
  */
 public boolean isEmpty( )
 {
     return root == null;
 }

 /**
  * Print the tree contents in sorted order.
  */
 public void printTree( )
 {
     if( isEmpty( ) )
         System.out.println( "Empty tree" );
     else
         printTree( root );
 }
 
 /**
  * nodeCount counts all the node in the tree
  * @return
  */
 public int nodeCount( )
 {
	 return nodeCount (root);
 }
 
 /**
  * Internal method to count all the node in the tree 
  * @param t
  * @return
  */
 private int nodeCount(BinaryNode<AnyType> t) 
 {
	 if (t == null) {
		 //System.out.println("No nodes in the tree");
		 return 0;
	 }
	 else 
		 return nodeCount(t.left) + nodeCount(t.right) + 1;
 }
 /**
  * isFull return true if the tree is full.
  * @return
  */
 public boolean isFull()
 {
	 return isFull(root);
 }
 /**
  * Internal method to check if the tree is full
  * @param t
  * @return
  */
 private boolean isFull(BinaryNode<AnyType> t)
 {
	 if (t == null)
		 return true;
	 else if ((t.left == null) && (t.right == null))
		 return true;
	 else if ((t.left !=null)&&(t.right !=null) ) 
		 return isFull(t.left) && isFull(t.right);
	 else 
		 return false;
 }

 /**
  * Method returns true if the structure between two trees are a match.
  * @param t
  * @return
  */
public boolean compareStructure(BinarySearchTree<AnyType> t)
{
	return compareStructure(this.root, t.root);
}
 /**
  * Internal method to check structure similarity
  * @param t1
  * @param t2
  * @return
  */
 private boolean compareStructure(BinaryNode<AnyType>t1 , BinaryNode<AnyType> t2) {
	 if (t1 == null && t2 == null)
		 return true;
	 else if ((t1 != null) && (t2 != null))
		 return (compareStructure(t1.left, t2.left) && compareStructure(t1.right, t2.right));
	 else
		 return false;
 }
/**
 * Checks to see if the tree is identical to the other tree
 * @param t
 * @return
 */
 public boolean equals(BinarySearchTree<AnyType> t) {
	 return equals (this.root, t.root);
 }
 /**
  * Internal boolean method for comparing the current tree to another tree
  * @param t1
  * @param t2
  * @return
  */
 private boolean equals(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2 )
 {
	 if (t1 == null && t2 == null)
		 return true;
	 else if ( t1 != null && t2 != null)
		 return (t1.element == t2.element && equals(t1.left,t2.left) && equals(t1.right, t2.right) );
	 else 
		 return false;
 }
 /**
  * Method creates a new tree which copies the original tree
  * @return
  */
 public BinarySearchTree<AnyType> copy()
 {
	 BinarySearchTree<AnyType> newTree = new BinarySearchTree<>();
	 newTree.root = copy(this.root);
	 return newTree;
 }
 /**
  * Internal method for copying nodes
  * @param t1
  * @return
  */
 private BinaryNode<AnyType> copy (BinaryNode<AnyType> t1) 
 {
	 if (t1 == null)
		 return null;
	 else
	 {
		 BinaryNode<AnyType> t2 = new BinaryNode<>(t1.element);
		 t2.left = copy(t1.left);
		 t2.right = copy(t1.right);
		 return t2;
	 }
 }
 
 /**
  * Method creates a new tree which is a mirror of the original tree
  * @return
  */
 public BinarySearchTree<AnyType> mirror()
 {
	 BinarySearchTree<AnyType> mirrorTree = new BinarySearchTree<>();
	 mirrorTree.root = mirror(this.root);
	 return mirrorTree;
 }
 /**
  * Internal method to create a mirror of a tree
  * @param t1
  * @return
  */
 private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t1){
	 if (t1 == null)
		 return null;
	 else 
	 {
		 BinaryNode<AnyType> t2 = new BinaryNode<> (t1.element);
		 t2.left = mirror(t1.right);
		 t2.right = mirror(t1.left);
		 return t2;
	 }
 }
 
 /**
  * Method checks to see if two trees are mirror image of each other
  * @param t
  * @return
  */
 public boolean isMirror(BinarySearchTree<AnyType> t) {
	 return isMirror(this.root, t.root);
 }
 /**
  * isMirror
  * @param t1
  * @param t2
  * @return
  */
 private boolean isMirror(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2)
 {
	 if (t1 == null && t2 == null)
		 return true;
	 else if (t1 != null && t2 != null)
		 return t1.element == t2.element && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
		 return false;
 }
 
 /**
  * Method to rotate left the passed Node.
  * @param x
  */
 public void rotateLeft(AnyType x)
 {
	 rotateLeft(x);
 }
 
 public BinaryNode rotateLeft(BinaryNode<AnyType> t1)
 {
	BinaryNode<AnyType>t2 = t1.left;
	t1.left = t2.right;
	t2.right = t1;
	height(t1);
	height(t2);
	return t2;
 }
 
 /**
  * Method to rotate right the passed node. 
  * @param t1
  * @return
  */
 public BinaryNode rotateRight(BinaryNode<AnyType> t1)
 {
	 BinaryNode<AnyType> t2 = t1.right;
	 t1.right= t2.left;
	 t2.left = t1;
	 height(t1);
	 height(t2);
	 return t2;
 }
 
 /**
  * Method to print the tree in level-by-level basis
  */
 public void printLevels( )
 {
 	Queue<BinaryNode<AnyType>> queue = new LinkedList<>();
 	queue.add(root);
 	while(!queue.isEmpty())
 	{
 		BinaryNode<AnyType> node = queue.poll();
 		System.out.print(node.element+ " ");
 		if(node.left != null)
 			queue.add(node.left);
 		if(node.right != null)
 			queue.add(node.right);
 	}
 }
 
 /**
  * Method to convert the items inside the tree into a string
  */
 public String toString()
 {
     if( isEmpty( ) )
         return "Tree is empty";
     else
         return treetoString(root);

 }
 /**
  * Internal method to convert anytype nodes into string format
  * @param t
  * @return
  */
 private String treetoString( BinaryNode<AnyType> t )
 {
     if( t != null )
     {
         return treetoString( t.left ) + t.element.toString() + "\n" + treetoString( t.right );
     }
     return "";
 }
 
 /**
  * Internal method to insert into a subtree.
  * @param x the item to insert.
  * @param t the node that roots the subtree.
  * @return the new root of the subtree.
  */
 private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return new BinaryNode<>( x, null, null );
     
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         t.left = insert( x, t.left );
     else if( compareResult > 0 )
         t.right = insert( x, t.right );
     else
         ;  // Duplicate; do nothing
     return t;
 }

 /**
  * Internal method to remove from a subtree.
  * @param x the item to remove.
  * @param t the node that roots the subtree.
  * @return the new root of the subtree.
  */
 private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return t;   // Item not found; do nothing
         
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         t.left = remove( x, t.left );
     else if( compareResult > 0 )
         t.right = remove( x, t.right );
     else if( t.left != null && t.right != null ) // Two children
     {
         t.element = findMin( t.right ).element;
         t.right = remove( t.element, t.right );
     }
     else
         t = ( t.left != null ) ? t.left : t.right;
     return t;
 }

 /**
  * Internal method to find the smallest item in a subtree.
  * @param t the node that roots the subtree.
  * @return node containing the smallest item.
  */
 private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
 {
     if( t == null )
         return null;
     else if( t.left == null )
         return t;
     return findMin( t.left );
 }

 /**
  * Internal method to find the largest item in a subtree.
  * @param t the node that roots the subtree.
  * @return node containing the largest item.
  */
 private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
 {
     if( t != null )
         while( t.right != null )
             t = t.right;

     return t;
 }

 /**
  * Internal method to find an item in a subtree.
  * @param x is item to search for.
  * @param t the node that roots the subtree.
  * @return node containing the matched item.
  */
 private boolean contains( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return false;
         
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         return contains( x, t.left );
     else if( compareResult > 0 )
         return contains( x, t.right );
     else
         return true;    // Match
 }

 /**
  * Internal method to print a subtree in sorted order.
  * @param t the node that roots the subtree.
  */
 private void printTree( BinaryNode<AnyType> t )
 {
     if( t != null )
     {
         printTree( t.left );
         System.out.println( t.element );
         printTree( t.right );
     }
 }

 
 
 /**
  * Internal method to compute height of a subtree.
  * @param t the node that roots the subtree.
  */
 private int height( BinaryNode<AnyType> t )
 {
     if( t == null )
         return -1;
     else
         return 1 + Math.max( height( t.left ), height( t.right ) );    
 }
 
 // Basic node stored in unbalanced binary search trees
 private static class BinaryNode<AnyType>
 {
         // Constructors
     BinaryNode( AnyType theElement )
     {
         this( theElement, null, null );
     }

     BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
     {
         element  = theElement;
         left     = lt;
         right    = rt;
     }

     AnyType element;            // The data in the node
     BinaryNode<AnyType> left;   // Left child
     BinaryNode<AnyType> right;  // Right child
     
 }


   /** The tree root. */
 private BinaryNode<AnyType> root;


     // Test program
 public static void main( String [ ] args )
 {
     BinarySearchTree<Integer> t = new BinarySearchTree<>( );
     BinarySearchTree<Integer> t1 = new BinarySearchTree<>();
     BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
     BinarySearchTree<Integer> t3 = new BinarySearchTree<>( );
     final int NUMS = 50;
     final int GAP  = 4;
     
     
    // System.out.println( "Checking... (no more output means success)" );
     final int [] listarray = {4,10,12,18,15,22,24};
     for (int i = 0; i < 7; i++)
     {
    	 t.insert(listarray[i]);
    	 t1.insert(listarray[i]);
     }
     for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS)
         t2.insert( i );
     
        
     t.printTree();
     t.printLevels(); 
     
     int x  = t.nodeCount();
     System.out.println("\nNode Count: " + x);
     
     System.out.println("Is t full?: " + t.isFull());
     System.out.println("t compare structure with t1: " + t.compareStructure(t1));
     System.out.println("t compare structure with t2: " + t.compareStructure(t2));
     System.out.println("Does t equal to t1? : " + t.equals(t1));
     System.out.println("Does t equal to t2? : " + t.equals(t2));
     System.out.println("Printing a copied tree of 't' tree: \n" + t.copy());
     System.out.println("Printing a mirror image of 't' tree: \n" + t.mirror());
     
     t3 = t.mirror();
     System.out.println("Checking if t3 is a mirror of t: " + t.isMirror(t3));
     System.out.println("Checking if t2 is a mirror of t: " + t.isMirror(t2));
     //t.rotateLeft(15);    //I couldn't get the rotateLeft to work. It causes a stackoverflow error
     //t.rotateRight(15);   //I couldn't get the rotateRight to work. It causes a stackoverflow error
     
     
     
    /* for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
         t.insert( i );

     for( int i = 1; i < NUMS; i+= 2 )
         t.remove( i );

     if( NUMS < 40 )
         t.printTree( );
     if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
         System.out.println( "FindMin or FindMax error!" );

     for( int i = 2; i < NUMS; i+=2 )
          if( !t.contains( i ) )
              System.out.println( "Find error1!" );

     for( int i = 1; i < NUMS; i+=2 )
     {
         if( t.contains( i ) )
             System.out.println( "Find error2!" );
     }*/
 }
}
