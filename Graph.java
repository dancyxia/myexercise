/*
* Graph.java
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
import java.util.LinkedList;
import java.lang.Iterable;

public class Graph {
	int V;
	boolean isDirected = false;
	LinkedList<Integer>[] edgesList;
	
	public Graph(int v, boolean directed) {
		this(v);
		isDirected = directed;
	}
	
	public Graph(int v) {
		this.V = v;
		edgesList = (LinkedList<Integer>[])new LinkedList[v];
		for (LinkedList edges : edgesList) {
			edges = new LinkedList<Integer>();
		}
	}

	
	public int V() { return V;}
	
	public void addEdge(int v, int w) {
		edgesList[v].add(w);
		if (!isDirected)
			edgesList[w].add(v);
	}
	
	public Iterable<Integer> getEdges(int v) {
		return edgesList[v];
	}
}
