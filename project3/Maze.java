package projec4;

import java.util.*;

public class Maze
{
    public class CellBlock
    {
        public boolean north;
        public boolean south;
        public boolean west;
        public boolean east;

        public CellBlock()
        {
            south = true;
            east = true;
        }

        public void print()
        {
            if(south)
                System.out.print("_");
            else
                System.out.print(" ");
            if(east)
                System.out.print("|");
            else
                System.out.print(" ");
        }
    }

    private CellBlock[] cell;
    private int row;
    private int column;
    private int totalCells;

    /**
     * Building the maze step by step by evaluating each scenario of a cell and its neighboring cell
     * @param row
     * @param column
     */
    public Maze(int height, int width)
    {
        this.row = height;
        this.column = width;
        this.totalCells = row * column;
        this.cell = new CellBlock[this.totalCells];

        for(int i = 0; i < this.totalCells; i++)
        {
            cell[i] = new CellBlock();
        }
        
        DisjSets dset = new DisjSets(totalCells);
        int numUnion = 1;
        while(numUnion <= totalCells - 1)
        {
        		//int x = (int) (Math.random() * row);
        		//int y = (int) (Math.random() * column)
        		int num = (int) (Math.random() * totalCells);
        		int index;
        		int neighborCell,root1,root2;
        		
        		//System.out.println(num);
        		
        		if(num == 0) 								//the first cell
    			{
    				index = (int)(Math.random() * 2);
    				//System.out.println(index);
    				if(index != 0)
    					neighborCell = num + column;
    				else
    					neighborCell = num + 1;
    			}
        		
        		else if(num == column - 1)					//last cell on the first row
        		{
    				index = (int)(Math.random() * 2);
    				if(index != 0)
    					neighborCell = num + column;
    				else
    					neighborCell = num - 1;
    			}
        		
        		else if(num == totalCells - column)			//last cell on the second last row
        		{
    				index = (int)(Math.random() * 2);
    				if(index != 0)
    					neighborCell = num + 1 ;
    				else
    					neighborCell = num - column;
    			}	
        		
        		else if(num == totalCells - 1)				//second last cell
        		{
    				index = (int)(Math.random() * 2);
    				if(index != 0)
    					neighborCell = num - column;
    				else
    					neighborCell = num - 1;
    			}
     
        		else if(num > 0 && num < column - 1)		//all the cell in the first row other than first and last cell
        		{
        			index = (int)(Math.random() * 3);
        			//System.out.println(index);
    				if(index == 0)
    					neighborCell = num - 1;
    				else if(index == 1)
    					neighborCell = num + 1; 	
    				else
    					neighborCell = num + column;
        		}
        		
        		else if(num % column == 0)					//all the first cells in the rows
        		{
        			index = (int)(Math.random() * 3);
    				if(index == 0)
    					neighborCell = num - column;
    				else if(index == 1)
    					neighborCell = num + 1; 	
    				else
    					neighborCell = num + column;
        		}	
        		
        		else if(num > totalCells - column && num < totalCells - 1)	//all the cell in the last row other than first and last cell 
        		{
        			index = (int)(Math.random() * 3);
    				if(index == 0)
    					neighborCell = num - column;
    				else if(index == 1)
    					neighborCell = num - 1; 	
    				else
    					neighborCell = num + 1;
        		}
        		
        		else if(num % column == column - 1)			//all the last cells in grid
        		{
        			index = (int)(Math.random() * 3);
    				if(index == 0)
    					neighborCell = num - column;
    				else if(index == 1)
    					neighborCell = num - 1; 	
    				else
    					neighborCell = num + column;
        		}
        		
        		else										//other remaining cells
        		{
        			index = (int)(Math.random() * 4);
        			//System.out.println(index);
    				if(index == 0)
    					neighborCell = num - column;
    				else if(index == 1)
    					neighborCell = num - 1; 	
    				else if(index == 2)
    					neighborCell = num + 1;
    				else
    					neighborCell = num + column;
        		}
        		
        		root1 = dset.find(num);						//setting the current cell as root1 in the DisjSets 
        		root2 = dset.find(neighborCell);			//setting the neighbor cell as root2 in the DisjSets
        		
        		if(root1 != root2)
        		{
        			dset.union(root1, root2);
        			
        			if(neighborCell == num + 1)
        				this.cell[num].east = false;
        			else if(neighborCell == num + column)
        				this.cell[num].south = false;
        			else if(neighborCell == num - 1)
        				this.cell[neighborCell].east = false;
        			else if(neighborCell == num - column)
        				this.cell[neighborCell].south = false;
        			else ;
            		numUnion++;
        		}       			       			
        	      	
        }
    }

    /**
     * printMaze prints the method based on the user input.
     */
    public void printMaze()
    {
        System.out.print("  ");
        for(int i = 1; i < this.column; i++)
        {
            System.out.print(" _");
        }
        System.out.println();
        for(int i = 0; i < this.totalCells - 1; i++)
        {
            if(i % this.column == 0)
            {
            	if(i == 0)
            		System.out.print(" ");
            	else
            		System.out.print("|");
            }            
            this.cell[i].print();
            if(i % this.column == this.column - 1)
            {
                System.out.println();
            }
        }
    }
    
  /**
   * Testing the Maze with two inputs
   * @param args
   */
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Please enter an integer for the rows of the maze: ");
        int row = sc.nextInt();
        System.out.println("Please enter an integer for the columns of the maze: ");
        int column = sc.nextInt();
        
        Maze m = new Maze(row, column);
        m.printMaze();
    }
}


