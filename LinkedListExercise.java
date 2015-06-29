/*
 * StrReplaceSpace.java
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


public class LinkedListExercise {
	
	//2.4 Write code to partition a linked list around a value x, such that all nodes less than x
	//come before alt nodes greater than or equal to x.

		private static class LinkedListNode {
			int data;
			LinkedListNode next;
		}
		
		public static LinkedListNode partition(LinkedListNode node, int x) {
		}
		
	//2.6 Given a circular linked list, implement an algorithm which returns the node at the
	//beginning of the loop.
	//TODO: homework	
		public static LinkedListNode findLoopHead(LinkedListNode head) {
			//1. setup a slow runner and a fast runner
			//2. walk down the list until collide
			//3. put one runner to link head, both walk at the rate of one step, the collide node is the head of the loop
		}
		
	//2.7 Implement a function to check if a linked list is a palindrome
		public static boolean LinkedListNode isPalindrome(LinkedListNode head) {
			//1. setup a fast runner and a slow runner
			//2. push the data visited by slow runner to stack until fast runner arrive at the end
			//3. pop data from the stack and compare it with the data slow runner continue to visit
		}

	//3.2 How would you design a stack which, in addition to push and pop, also has a
	//function min which returns the minimum element? Push, pop and min should all
	//operate in 0(1) time.
	//super smart
	//add additional stack in the first stack
	//TODO: homework

	//3.4 Hanoi tower
	public class Tower {
		Stack<Integer> plates;
		
			public moveTo(int n, Tower dest, Tower buffer) {
			}
	}

	//3.5 Implement a MyQueue class which implements a queue using two stacks.
	public T class MyQueue<T> {
		Stack<T> oldStack = new Stack<T>();
		Stack<T> newStack = new Stack<T>();
		public void enqueue(T data) {
		}
		
		public T dequeue() {
			return T;
		}
		
	}

	//3.6 Write a program to sort a stack in ascending order (with biggest items on top).
	//You may use at most one additional stack to hold items, but you may not copy the
	//elements into any other data structure (such as an array). The stack supports the
	//following operations: push, pop, peek, and isEmpty.
	public Stack<Integer> sort(Stack<Integer> s) {
	}

}

