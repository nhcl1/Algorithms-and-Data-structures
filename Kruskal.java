/**
 *Please include the csv file in the project directory since the file has been hard coded in this file
 */

/**
TA's review
-5, use of indexOf is not the best choice here, as every iteration of the loop you do two linear scans of the vertices;  better to use a hash table for lookups﻿﻿
*/
package project5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Kruskal {

	/**
	 * Creating a class for the edges in a set
	 *
	 */
	class Edge implements Comparable<Edge> {
	
		String src, dest;
		int weight;
		
		Edge(String v1, String v2, int d)
		{
			this.src = v1;
			this.dest = v2;
			this.weight = d;
		}
		
		public int compareTo(Edge compareEdge)
		{
			if (this.weight < compareEdge.weight)
				return -1;
			else if (this.weight > compareEdge.weight)
				return 1;
			else 
				return 0;
		}
		
	}
	
	public void applyKruskal()
	{
		ArrayList<Edge> edges = new ArrayList<>();			//ArrayList to store edges
		ArrayList<String> vertices = new ArrayList<>();	 	//ArrayList to store vertices
		BufferedReader fileReader = null;
		String parseCSV = "assn9_data.csv";	//file parsing
		int vertexCounter = 0;		//initialize counter to count vertices
		
		try
		{
			String line = "";
			fileReader = new BufferedReader(new FileReader(parseCSV));	//reading the input from the csv file
			//line  = fileReader.readLine();
			
			//read the file until there is no data to parse
			while ((line = fileReader.readLine()) != null)
			{
				String[] splitData = line.split(",");	//data from the file separated by delimiter is stored as string
				//System.out.println(tokens[0]);
				vertices.add(splitData[0]);
				//System.out.println(tokens[0]);
				edges.add(new Edge(splitData[0], splitData[1], Integer.parseInt(splitData[2])));
				for (int index = 3; index<splitData.length; index++)
				{
					//vertices.add(splitData[3]);
					edges.add(new Edge(splitData[0], splitData[index], Integer.parseInt(splitData[index+1])));
					index++;
				}
				vertexCounter++;
			}
		}
		catch (Exception e) {
            e.printStackTrace();
        }
		
		/**
		 * Implementation of the pseudocode of Kruskal's algorithm mentioned in the book begins here.
		 */
		DisjSets ds = new DisjSets(vertices.size());	//using book author's disjoint set class
		PriorityQueue<Edge> pq = new PriorityQueue<>(); //using Java priority queue
		
		int totalDistance = 0;
		int edgesAccepted = 0;
		
		for (Edge e : edges)
		{
			pq.add(e);
		}
		
		while(edgesAccepted < vertices.size() - 1)
		{
			Edge e = pq.poll();
			
			
			 String set1 = e.src; String set2 = e.dest;
			 
			 int addr1 = vertices.indexOf(set1);	//address of the element in set 1
			 int addr2 = vertices.indexOf(set2);	//address of the element in set 2
			
			 int vset = ds.find(addr1); 	//performing find operation on set 1 
			 int uset = ds.find(addr2);		//performing find operation on set 2
			
			if (vset != uset)		//if sets are not connected(or same)
			{
				edgesAccepted++;
				ds.union(vset, uset);		//performing the union of the sets
				totalDistance += e.weight;
				System.out.println("From: " + e.src + " To: " + e.dest);
				System.out.println("Distance = " + e.weight);
				System.out.println("Total distance: " + totalDistance);
				System.out.println("|``````````````````````````````````````````````````````|");
			}
		}
	}
	
	public static void main(String[] args)
	{
		Kruskal k = new Kruskal();
		k.applyKruskal();
	}
}



