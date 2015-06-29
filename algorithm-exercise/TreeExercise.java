/*
 * TreeExercise.java
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
import java.lang.Integer; 
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class TreeExercise {
	public static class BinaryTreeNode<T> {
		T data;
		BinaryTreeNode left;
		BinaryTreeNode right;
		
		public BinaryTreeNode(T v) {
			data = v;
		}
	}
	
	public static class TreeNode<T> {
		T data;
		List<TreeNode<T>> children;
	}
	
//4.1 Implement a function to check if a binary tree is balanced. For the purposes of
//this question, a balanced tree is defined to be a tree such that the heights of the
//two subtrees of any node never differ by more than one.
	
	public static <T> boolean isBalancedTree (BinaryTreeNode<T> root) {
		return checkHeight(root) == -1 ? false : true;
	}
	
	private static <T> int checkHeight(BinaryTreeNode<T> node) {
		if (node == null)
			return 0;
			
		int leftHeight = checkHeight(node.left);
		if (leftHeight == -1) //it's not balanced
			return -1;
		
		int rightHeight = checkHeight(node.right);
		if (rightHeight == -1)
			return -1;
			
		if (Math.abs(leftHeight - rightHeight) > 1)
			return -1;
		else 
			return Math.max(leftHeight, rightHeight) + 1;
	}
	
	
//~ /*
	//~ //4.2 Given a directed graph, design an algorithm to find out whether there is a route
	//~ //between two nodes.	
	//~ //TODO: MUST
	//~ }
//~ */
	public static boolean hasPath(Graph g, int v, int w) {
		
		DirectDFS h = new DirectDFS(g,v);
		
		if (h.getPath(v, w) != null)
			return true;
		return false;
	}
	
	//4.3 Given a sorted (increasing order) array with unique integer elements, write an
	//algorithm to create a binary search tree with minimal height.
	public static BinaryTreeNode<Integer> createMinBST(int[] array) {
		return createMinBST(array, 0, array.length-1);
	}
	
	private static BinaryTreeNode<Integer> createMinBST(int[] array, int low, int high) {
		if ( low > high)
			return null;
			
		int mid = (low + high) >> 1;
		BinaryTreeNode<Integer> root = new BinaryTreeNode(array[mid]);
		root.left = createMinBST(array, 0, mid-1);
		root.right = createMinBST(array, mid+1, array.length-1);
		return root;
	}
	
	//4.4 Given a binary tree, design an algorithm which creates a linked list of all the
	//nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked
	//lists).
	public static <T> List<LinkedList<BinaryTreeNode<T>>> createLevelLinkedList(BinaryTreeNode<T> root) {
		return null;
	}
	 
   //4.5 Implement a function to check if a binary tree is a binary search tree.
   //solution 1: in-order traversal
	public static <T> boolean checkBST1(BinaryTreeNode<T> root) {
		return false;
	}

	//solution 2: min/max solution
	//all nodes in left tree is smaller than the current node, all nodes in the right tree is bigger
	public static <T> boolean checkBST2(BinaryTreeNode<T> root) {
		return false;
	}

//4.6 Write an algorithm to find the'next'node (i.e., in-order successor) of a given node
//in a binary search tree. You may assume that each node has a link to its parent.

//4.7 Design an algorithm and write code to find the first common ancestor of two
//nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE:
//This is not necessarily a binary search tree.
//issue:  check1 and check2 must exist in the tree
//solution for this issue: first check if p and q are both in the tree
public static <T> BinaryTreeNode<T> findLCA(BinaryTreeNode<T> node, T check1, T check2) {
   // If either n1 or n2 matches with root's key, report
    // the presence by returning root (Note that if a key is
    // ancestor of other, then the ancestor key becomes LCA
   if (node.data == check1 || node.data == check2)
	 return node;
	 
   BinaryTreeNode<T> inLeft = findLCA(node.left, check1, check2);
   BinaryTreeNode<T> inRight = findLCA(node.right, check1, check2);
   
    // If both of the above calls return Non-NULL, then one key
    // is present in once subtree and other is present in other,
    // So this node is the LCA
   if (inLeft != null && inRight != null)
     return node;
   
   // Otherwise check if left subtree or right subtree is LCA
   return inLeft != null? inLeft : inRight;
}
   
public static BinaryTreeNode<Integer> findLCAForBST(BinaryTreeNode<Integer> root, int check1, int check2) {
	if (check1 == root.data || check2 == root.data)
		return root;
		
	if (check1 < root.data && check2 > root.data)
		return root;
	
	if (check1 < root.data)
     	return findLCAForBST(root.left, check1, check2);
    else 
		return findLCAForBST(root.right, check1, check2);
}

//4.8 You have two very large binary trees: Tl, with millions of nodes, and T2, with
//hundreds of nodes. Create an algorithm to decide if T2 is a subtree of Tl.
//A tree T2 is a subtree of Tl if there exists a node n in Tl such that the subtree of
//n is identical to T2. That is, if you cut off the tree at node n, the two trees would
//be identical.



//4.9 You are given a binary tree in which each node contains a value. Design an algorithm
//to print all paths which sum to a given value. The path does not need to
//start or end at the root or a leaf.
	
//~ Find all paths in binary tree that add up to a given sum. 
//~ Given a tree like this:
//~ 
//~ 
//~ 2
 //~ 3   5 
//~ 4 8 6 -2
//~         2
//~ return {3,4}, {2,5}, {2, 5, -2, 2}
//Tree traversal, when reaches a node, calculate the sum starting from it and end with its upper ancestor
public static List<List<Integer>> findSumPaths(BinaryTreeNode<Integer> root, int sum) {
	List <List<Integer>> result = new LinkedList<List<Integer>>();
	ArrayList<Integer> path = new ArrayList<Integer>();
	findSumPaths(root, sum, 0, path, result);
	return result;
}

private static void findSumPaths(BinaryTreeNode<Integer> node, int sum, int level, ArrayList<Integer> path, List<List<Integer>> result) {
	if (node == null)
		return;
		
	path.add(level, node.data);
	
	int t = 0;
	for (int i = level; i >= 0; i--) {
		t += path.get(i);
		if (t == sum) {
			result.add(getPath(path, i, level));
		}
	}
	
	findSumPaths(node.left, sum, level+1, path, result);
	findSumPaths(node.right, sum, level+1, path, result);
	path.remove(level);
}

private static List<Integer> getPath(ArrayList<Integer> path, int end, int start) {
	List sumPath = new LinkedList<Integer>();
	for (int i = end; i <= start; i++)
		sumPath.add(path.get(i));
		
	return sumPath;
}

//~ Given an array such that it is increasing until a point and then decreasing. Return the index of the number n in the array in sub-linear time. 
//~ Ex. 
//~ [1,2,5,8,13,9,3,-1] n = 5 
//~ Output = 2 
//~ 
//~ Next: Can you do it without finding the maximum element?
//~ 1. get mid item
//~ 2.1. if n < a[mid], need to search both side
//~ 2.2. n > a[mid], need to decide which side for checking according to its on the increasing slope or decreasing slope
	private enum Slope {
		INCREASING, DECREASING, PEAK, INVALID
	}
	
	public static int findNumber(int[] a, int n) {
		return findNumber(a, 0, a.length-1, n);
	}

	private static int findNumber(int[] a, int low, int high, int n) {
		if (low > high)
			return -1;
		
		int mid = (low + high) >> 1;
		//hit it
		if (a[mid] == n)
			return mid;
			
		//there's only one item, missed
		if (low == high)
			return -1;
			
		int result = -1;
		//need to check both side if n < a[mid]
		if (n < a[mid]) { 
			result = findNumber(a, mid+1, high, n);
			if (result != -1)
				return result;
			result = findNumber(a, low, mid-1, n);
			if (result != -1)
				return result;
		} else { //n > a[mid], need to decide which side for checking according to its on the increasing half or decreasing half
			switch(checkSlope(a, mid)) {
				case INCREASING:
					result = findNumber(a, mid+1, high, n);
					break;
				case DECREASING:
					result = findNumber(a, low, mid-1, n);
				case PEAK:
					result = -1;
			}
		}
		return result;
	}
	
	private static Slope checkSlope(int[] a, int mid) {
		if (mid == 0)
			return a[mid+1] > a[mid] ? Slope.INCREASING : Slope.DECREASING;
		
		if (mid == a.length -1)
			return a[mid] > a[mid-1] ? Slope.INCREASING : Slope.DECREASING;
			
		if (a[mid-1] < a[mid] && a[mid] < a[mid+1])
			return Slope.INCREASING;
		
		if (a[mid-1] > a[mid] && a[mid] > a[mid+1])
			return Slope.DECREASING;
		
		if (a[mid-1] < a[mid] && a[mid] > a[mid+1])
			return Slope.PEAK;

		return Slope.INVALID;
	}
	
//	Given set of strings find longest common prefix. If no common prefix present print null
//Example: {abcd, abbd, abdy, az}
//Answer: a

//find k max number in the stream
//MaxHeap
//input: {23,2,4, 7, 10, 20, 89, 30,27, 78, 11, 45, 34, 22, 18} n: 3
//output: 89, 78, 45
private final static Comparator maxComparator = new Comparator<Integer>() {
	@Override
	public int compare(Integer a, Integer b) {
		return -a.compareTo(b);
	}

};
private final static Comparator minComparator = new Comparator<Integer>(){
	
	@Override
	public int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}
};

//maintain a fix-size minPriorityQueue and remove the smallest one whenever there's overflow
	public static Integer[] getMaxNumber(int[] input, int n) {
		if (n == 0)
			return null;
			
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(n+1, minComparator );
		for (int item : input) {
			pq.offer(item);
			if (pq.size() == n+1) {
				pq.poll();
			}
		}

		return pq.toArray(new Integer[pq.size()]);
	}

	//18.9 find median value 
	public static int findMedian(int[] input) {
		int halfLen = input.length>>1;
		PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>(halfLen, minComparator);
		PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(halfLen, maxComparator);
		
		for (int x : input) {
			//if size is the same, insert to minQueue(store the big numbers)
			if (minPQ.size() == maxPQ.size())
			{
				minPQ.offer(x);
			}
			//if size is different, it means minPQ has more items. Need to insert into maxPQ
			else {
				if (x > minPQ.peek()) {
				  maxPQ.offer(minPQ.poll());
				  minPQ.offer(x);
			  }
				else {
				  maxPQ.offer(x);
				}				  
			}
		}
		
		if (minPQ.size() > maxPQ.size())
			return minPQ.peek().intValue();
		else 
			return (minPQ.peek() + maxPQ.peek())>>1;
	}

//KD tree
//~ function kdtree (list of points pointList, int depth)
//~ {
    //~ // Select axis based on depth so that axis cycles through all valid values
    //~ var int axis := depth mod k;
        //~ 
    //~ // Sort point list and choose median as pivot element
    //~ select median by axis from pointList;
        //~ 
    //~ // Create node and construct subtrees
    //~ var tree_node node;
    //~ node.location := median;
    //~ node.leftChild := kdtree(points in pointList before median, depth+1);
    //~ node.rightChild := kdtree(points in pointList after median, depth+1);
    //~ return node;
    
    private final static class KDNode {
		Point p;
		KDNode left;
		KDNode right;
	}
	
	private final static class Point {
		int x;
		int y;
	}
}
//~ bestDistance = INF
//~ 
//~ def getClosest(node, point)
    //~ if node is null
        //~ return
    //~ // I will assume that this node splits points 
    //~ // by their x coordinate for the sake of brevity.
    //~ if node is a leaf
        //~ // updateAnswer updates bestDistance value
        //~ // and keeps track of the closest point to the given one.
        //~ updateAnswer(node.point, point)
    //~ else
        //~ middleX = node.median
        //~ if point.x < middleX
            //~ getClosest(node.left, point)
            //~ if node.right.minX - point.x < bestDistance
                //~ getClosest(node.right, point)
        //~ else
            //~ getClosest(node.right, point)
            //~ if point.x - node.left.maxX < bestDistance
                //~ getClosest(node.left, point)
