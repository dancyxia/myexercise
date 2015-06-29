/*
 * TestTreeContainer.java
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
import test.TestExercise;
import java.util.List;

@TestExercise(enabled=true)
public class TestTreeContainer {
	
	@TestExercise(enabled=true)
	public void testIsBalancedTree () {
		TreeExercise.BinaryTreeNode<String> root = new TreeExercise.BinaryTreeNode<String>("a");
		root.left = new TreeExercise.BinaryTreeNode<String>("b");
		
		root.left.left = new TreeExercise.BinaryTreeNode<String>("d");
		System.out.printf("the tree is %s %n", TreeExercise.isBalancedTree(root)? "balanced": "not balnaced");

		root.right = new TreeExercise.BinaryTreeNode<String>("c");
		System.out.printf("the tree is %s %n", TreeExercise.isBalancedTree(root)? "balanced": "not balnaced");
	}

//~ Given an array such that it is increasing until a point and then decreasing. Return the index of the number n in the array in sub-linear time. 
//~ Ex. 
//~ [1,2,5,8,13,9,3,-1] n = 5 
//~ Output = 2 
//~ 
//~ Next: Can you do it without finding the maximum element?
	@TestExercise(enabled=true)
	public void testFindNumber () {
		int[] a = {1,2,5,8,13,9,3,7, 6, -1};
		int n = 13;
		int idx = TreeExercise.findNumber(a, n);
		System.out.printf("look for %d in array ", n); 
		for (int input : a)
			System.out.print(input+" ");
		
		if (idx == -1)
			System.out.printf("%n didn't find %d %n", n);
		else 
			System.out.printf("%n find it at %d %n", idx); 
			
	}
	
	@TestExercise(enabled=true)
	public void testGetMaxNumber () {
		int[] a = {23,2,4, 7, 10, 20, 89, 30,27, 78, 11, 45, 34, 22, 18};
		Integer[] ret = TreeExercise.getMaxNumber(a, 3);
		for (Integer out : ret)
			System.out.print(out+" ");
		System.out.println();
	}
	


	@TestExercise(enabled=true)
	public void testGetMedian () {
		int[] a = {23,2,4, 7, 10, 20, 89, 30,27, 78, 11, 45, 34, 22, 18};
		int ret = TreeExercise.findMedian(a);
		System.out.printf("the median of array "); 
		for (int input : a)
			System.out.print(input+" ");
		
		System.out.println("is "+ret);
	}
	@TestExercise(enabled=true)
	public void testGetSum () {
//~ 2
//~ 3   5 
//~ 4 8 6 -2		
		TreeExercise.BinaryTreeNode<Integer> root = new TreeExercise.BinaryTreeNode<Integer>(2);
		root.left = new TreeExercise.BinaryTreeNode<Integer>(3);
		root.right = new TreeExercise.BinaryTreeNode<Integer>(5);
		root.left.left = new TreeExercise.BinaryTreeNode<Integer>(4);
		root.left.right = new TreeExercise.BinaryTreeNode<Integer>(8);
		root.right.left = new TreeExercise.BinaryTreeNode<Integer>(6);
		root.right.right = new TreeExercise.BinaryTreeNode<Integer>(-2);
		root.right.right.left = new TreeExercise.BinaryTreeNode<Integer>(2);
		
		for (List<Integer> path : TreeExercise.findSumPaths(root, 7)) {
			for (int item : path) 
				System.out.print(item+" ");
			System.out.println();
		}
	}

}

