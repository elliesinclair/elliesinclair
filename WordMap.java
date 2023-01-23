import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class WordMap {

	public static Graph graph = new Graph();

	public static void main(String[] args) {
		Scanner fileReader = null;
		File dictionary = null;

		String name = null;

		try {
			dictionary = new File("Dict.txt");

			fileReader = new Scanner(dictionary);

			while(fileReader.hasNextLine()) {
				name = fileReader.nextLine();
				graph.addVertex(name);
			}

			for(String word : graph.getGraph().keySet()) {
				addEdges(graph.getVertex(word));
			}
			
			fileReader.close();
		}

		catch(FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		}

		Scanner input = new Scanner(System.in);
		
		boolean keepPlaying = false;
		String userKeepPlaying;

		do { 
			String startWord = "";
			String endWord = "";
			int wordSize = 0;
			
			do {
				System.out.println("Pick a starting word: ");
				startWord = input.nextLine();
				wordSize = startWord.length();
				if(!graph.contains(startWord)) {
					System.out.println("Your word does not exist. Please choose another.");
				}
			} while(!graph.contains(startWord));

			do {
				System.out.println("Pick an ending word that is the same length as the starting word: ");
				endWord = input.nextLine();

				if(endWord.length() != wordSize) {
					System.out.println("Your words must be the same length.");
				}
				else if(!graph.contains(endWord)) {
					System.out.println("Your word does not exist. Please choose another.");
				}
			} while(endWord.length() != wordSize || !graph.contains(endWord));
			
			int shortestPath = 0;
			shortestPath(startWord,endWord);
			Vertex v = graph.getVertex(endWord);
	
			while(v != null) {
				System.out.println(v);
				v = v.predecessor;
				shortestPath++;
			}
			
			if(shortestPath == 1) {
				System.out.println("There is no path from " + startWord + " to " + endWord);
			}
			else {
				System.out.println("The shortest path is length: " + shortestPath);
			}
			
			
			System.out.println("Do you want to play again? Y or N: ");
			userKeepPlaying = input.nextLine();
			userKeepPlaying = userKeepPlaying.toUpperCase();
			
			if(userKeepPlaying.equals("Y")) {
				keepPlaying = true;
			}
			else {
				keepPlaying = false;
			}
			
		} while(keepPlaying);
	}

	public static void addEdges(Vertex w1) {
		String w1String = w1.toString();
		
		for(int i = 0; i < w1String.length(); i++) {
			char[] w1Array = w1String.toCharArray();

			for(char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
				w1Array[i] = alphabet;
				String newWord = new String(w1Array);

				graph.addEdge(w1String, newWord);
			}
		}
	}

	public static void shortestPath(String startWord, String endWord) {
		graph.initializeGraph();

		Vertex start = graph.getVertex(startWord);
		start.distance = 0;
		Vertex goal = graph.getVertex(endWord);

		Vertex currentVertex = start;

		LinkedList<Vertex> vertexQueue = new LinkedList<>();
		vertexQueue.add(currentVertex);

		while(!vertexQueue.isEmpty()) {

			currentVertex = vertexQueue.poll();

			for(Vertex neighbor : currentVertex.getNeighbors()) {
				if(neighbor.distance == 70000) {
					neighbor.distance = currentVertex.distance + 1;
					neighbor.predecessor = currentVertex;
					vertexQueue.add(neighbor);

				}
				if(currentVertex == goal) {
					return;
				}
			}
		}
	}
}
