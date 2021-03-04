package project1;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyStack <T>
{ 
	ArrayList<T> list = new ArrayList<>();
	static int p = -1;
	
	/**empty:
	 * It returns a boolean value when the stack is empty or when it has at least has one item inside it.
	 * @return
	 */
	public boolean empty()
	{
		if(p == -1)
			return true;
		else
			return false;
	}
	
	/**push:
	 * It pushes the item 'x' to the stack
	 * @param x
	 */
	public void push(T x)
	{
		list.add(++p, x);
	}
	
	/**pop:
	 * It pops the last item out of the stack as per LIFO system 
	 * @return
	 */
	public T pop()
	{
		return list.remove(p--);
	}
	 
	 public static void main(String args[]) throws NoSuchElementException 
	 {
		 MyStack<Character> stack = new MyStack<>();
		 //String s = "[{()},]";				//Invalid element inside
		 //String s = "[{([({})])}}]";			//Unbalanced Symbols
		 String s = "[({}(){})]";				//Balanced Symbols

		 for(int i=0; i < s.length(); i++)
		 {
			 char c = s.charAt(i);
			 if(c == '[' || c== '{' || c== '(' )								//push braces to stack if they are "opening braces"
			 {
				 stack.push(c);
				 System.out.println("Current Stack: "+ c + "      Top: "+ p);
			 }
			 else if(c == ']')
			 {
				 System.out.println(" "+ c + " Popped from stack  Top: "+ p);	//pop brace "]"  
				 if(stack.empty())												//when stack is empty
				 {
					 System.out.println("The stack is empty");
					 break;
				 }
				 if(stack.pop()!= '[')											//when a balancing '[' is missing
				 {
					 System.out.println("Unbalanced braces [");
					 break;
				 }
			 }
			 else if(c == '}')
			 {
				 System.out.println(" "+ c + " Popped from stack  Top: "+ p);	//pop brace "}"  
				 if(stack.empty())												//when stack is empty
				 {
					 System.out.println("The stack is empty");
					 break;
				 }
				 if(stack.pop()!= '{')											//when a balancing '{' is missing
				 {
					 System.out.println("Unbalanced braces {");
					 break;
				 }
			 }
			 else if(c == ')')
			 {
				 System.out.println(" "+ c + " Popped from stack  Top: "+ p);	//pop brace ")"  
				 if(stack.empty())												//when stack is empty
				 {
					 System.out.println("The stack is empty");
					 break;
				 }
				 if(stack.pop()!= '(')											//when a balancing '(' is missing
				 {
					 System.out.println("Unbalanced braces (");
					 break;
				 }
			 }
			 else
				 throw new NoSuchElementException(" " + c + " this item is invalid"); 	//Invalid element
		 }
		 if(stack.empty())
			 System.out.println("\nBraces Balanced");
	 }
}
