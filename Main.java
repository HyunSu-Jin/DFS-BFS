import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

class Edge implements Comparable<Edge>{
	static int idCount = 1;
	int id;
	int from;
	int to;
	Edge(int from,int to){
		this.from = from;
		this.to= to;
		this.id = idCount;
		idCount++;
	}
	@Override
	public int compareTo(Edge o) {
		if(this.id < o.id){
			return -1;
		} else if(this.id > o.id){
			return 1;
		} else{
			return 0;
		}
	}
	@Override
	public String toString() {
		return ""+from+":"+to;
	}
}


public class Main {
	public static ArrayList<LinkedList<Integer>> graph;
	public static int numOfVertice;
	public static int numOfEdges;
	public static int root;
	
	public static void DFS_visit(int root,boolean visited[]){
		Stack<Integer> stack = new Stack();
		visited[root] = true;
		System.out.print(root+" ");
		stack.push(root);
		while(!stack.isEmpty()){
			int curNode = stack.pop();
			for(int node : graph.get(curNode)){
				if(visited[node] == false){
					stack.push(node);
					DFS_visit(node,visited);
				}
			}
		}
	}

	public static void DFS(int root){
		boolean visited[] = new boolean[numOfVertice+1];
		for(int i = root ;i <= numOfVertice;i++){
			if(visited[i] == false){
				DFS_visit(i,visited);
			}
		}
	}
	
	public static void BFS(int root){
		boolean visited[] = new boolean[numOfVertice+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(root);
		while(!queue.isEmpty()){
			int curNode = queue.poll();
			visited[curNode] = true;
			System.out.print(curNode+" ");
			for(int node : graph.get(curNode)){
				if(visited[node] == false){
					visited[node] = true;
					queue.offer(node);
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		numOfVertice = scanner.nextInt();
		numOfEdges = scanner.nextInt();
		root = scanner.nextInt();
		graph = new ArrayList<>();
		Set<Edge> set = new TreeSet();
		for(int i = 0 ; i <=numOfVertice ; i++){
			graph.add(i,new LinkedList<>());
		}
		for(int i = 0 ; i < numOfEdges ; i++){
			int from = scanner.nextInt();
			int to = scanner.nextInt();
			//System.out.println(""+from+":"+to);
			set.add(new Edge(from,to));
		}
		for(Edge edge : set){
			//System.out.println(edge);
			graph.get(edge.from).add(edge.to);
			graph.get(edge.to).add(edge.from);
		}
		for(LinkedList<Integer> bucket : graph){
			bucket.sort(new Comparator<Integer>(){
				@Override
				public int compare(Integer o1, Integer o2) {
					if(o1 < o2){
						return -1;
					} else if(o1 > o2){
						return 1;
					} else{
						return 0;
					}
				}
			});
		}
		System.out.println(graph.toString());
		DFS(root);
		System.out.println();
		BFS(root);
	}
}
