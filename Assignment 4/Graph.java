package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


//  represents a weighted undirected graph

public class Graph {
	Vertex[ ] adjLists;   // array of all vertices in the graph

	public Graph(String file) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(file));

		// read number of vertices
		adjLists = new Vertex[sc.nextInt()];

		// read vertex names & create vertices
		for (int v=0; v < adjLists.length; v++) {
			adjLists[v] = new Vertex(sc.next(), null);
		}

		// read edges
		while (sc.hasNext()) {
			// read vertex names and translate to vertex numbers
			int v1 = indexForName(sc.next());
			int v2 = indexForName(sc.next());
			int weight = sc.nextInt( );

			// add v2 to front of v1's adjacency list and
			// add v1 to front of v2's adjacency list
			adjLists[v1].adjList = new AdjacencyNode(v2, adjLists[v1].adjList, weight);
			adjLists[v2].adjList = new AdjacencyNode(v1, adjLists[v2].adjList, weight);
		}
		sc.close( );
	}

	int indexForName(String name) {
		for (int v=0; v < adjLists.length; v++) {
			if (adjLists[v].name.equals(name)) {
				return v;
			}
		}
		return -1;
	}	
	
	// returns the number of vertices in the graph 
	public int numberOfVertices( ){
		return adjLists.length;
	}

	// print a summary of the graph
	public void summarize( ){
		for(int j = 0; j<numberOfVertices( ); j++){
			Vertex vj = adjLists[j];
			System.out.print(vj.name+": ");
			for(AdjacencyNode e = vj.adjList; e != null; e = e.next){
				System.out.print(adjLists[e.vertexNum].name+" "+e.weight+",  ");
			}
			System.out.println();
		}
	}
	
	public int shortestPath(String nameFrom, String nameTo){
		
		int nameFromIndex = indexForName(nameFrom);
		int nameToIndex = indexForName(nameTo);
		
		SPRecord[] newTree = new SPRecord[adjLists.length];
		newTree[nameFromIndex] = new SPRecord(true, nameFromIndex,0);
		updateNeighbors(nameFromIndex, newTree);
		
		if(newTree[nameToIndex] == null)
		{
			return 0;
		}
		else{
			while(! newTree[nameToIndex].inTree){
				int shortest = -1;
				for(int j = 0; j < adjLists.length; j++)
				{
					if(newTree[j] == null)
					{
						continue;
					}
					else if(newTree[j].inTree == false)
					{
						if(shortest == -1 && newTree[j].distance != 0)
						{
							shortest = j;
						}
						else if(newTree[j].distance != 0 &&  newTree[j].distance > newTree[shortest].distance)
						{
							shortest = j;
						}
					}
				}
				newTree[shortest].inTree = true;
				updateNeighbors(shortest, newTree);
			}
		}
		return newTree[nameToIndex].distance;
	}
	
	
       	private void updateNeighbors(int vertexIndex, SPRecord [] spRecords){
		
       		for(AdjacencyNode nbr = adjLists[vertexIndex].adjList; nbr != null; nbr = nbr.next){
			
       			SPRecord nSPR = spRecords[nbr.vertexNum];
       			if (nSPR == null){  // if neighbor is in neither the tree nor the fringe, put it in the fringe
       				spRecords[nbr.vertexNum]= new SPRecord(false, vertexIndex, nbr.weight+spRecords[vertexIndex].distance);
       			} 
       			
       			else if (! nSPR.inTree){ // if neighbor not in tree, must be in fringe.  update if needed
       				int altDistance = nbr.weight + spRecords[vertexIndex].distance;
				
       				if (altDistance < nSPR.distance){
					spRecords[nbr.vertexNum].distance = altDistance;
					spRecords[nbr.vertexNum].link = vertexIndex;
       				}

       			}
       		}
       	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Graph g1 =  new Graph("smallGraph.txt");
		g1.summarize( );
		System.out.println(g1.shortestPath("Dog", "Cat"));
		System.out.println(g1.shortestPath("AA", "BBB"));
		System.out.println(g1.shortestPath("AA", "Cat"));
		System.out.println(g1.shortestPath("AA", "Dog"));
		System.out.println(g1.shortestPath("Dog", "BBB"));
	}
}
