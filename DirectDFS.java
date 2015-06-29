/*
 * DirectDFS.java
 * 
 * Copyright 2015 dancy <dancy@DANCY-HOME>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

import java.util.List;
import java.util.Stack;
import java.util.LinkedList;
import java.lang.Iterable;

enum State {
		UNVISITED, VISITED, PROCESSED
	}
	

public class DirectDFS {
	int[] to;
	State[] state;
	boolean hasLoop = false;
	boolean isTree = true;
	
	public DirectDFS(Graph g, int v) {
		to = new int[g.V];
		state = new State[g.V];
		for (int i = 0; i < g.V(); i++) {
			state[i] = State.UNVISITED;
			to[i] = -1;
		}
		dfs(g, v);
		if (hasLoop)
			System.out.println("loop is included");
		if (isTree)
			System.out.println("it's a tree");
	}
	
	private void processEdge(int v, int w) {
		if (state[w] == State.VISITED) //loop
			hasLoop = true;
		else if (state[w] == State.PROCESSED) {
			isTree = false;
		}
	}
	
	public boolean dfs(Graph g, int v){
		if (state[v] == State.UNVISITED)
			state[v] = State.VISITED;
		
		for (int child : g.getEdges(v)) {
			if (state[child] == State.UNVISITED) { //first time visit the child
				to[child] = v;
				dfs(g, child);
			} else {
				processEdge(v, child);
			}
		}
		
		state[v] = State.PROCESSED;
		return false;
	}
	
	public Iterable<Integer> getPath(int v, int w) {
		if (to[w] != -1) {
			Stack<Integer> lifo = new Stack<Integer>();
			int node = w;
			while(node != -1) {
				lifo.push(node);
				node = to[node];
			}
			return lifo;
		}
		return null;
	}
}


