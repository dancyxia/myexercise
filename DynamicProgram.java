/*
 * DynamicProgram.java
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
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

public class DynamicProgram {

//Find kth median number
//~ find-kth(A, k)
  //~ pivot = random element of A
  //~ (L, R) = split(A, pivot)
  //~ if k = |L|+1, return A[k]
  //~ if k ¡Ü |L|  , find-kth(L, k)
  //~ if k > |L|+1, find-kth(R, k-(|L|+1))

//Given N jobs where every job is represented by following three elements of it.
//1) Start Time
//2) Finish Time.
//3) Profit or Value Associated.
//Find the maximum profit subset of jobs such that no two jobs in the subset overlap.	
	private static class Job implements Comparable<Job> {
		int start;
		int finish;
		int weight;
		
		Job(int s, int f, int w) {
			start = s;
			finish = f;
			weight = w;
		}
		
		@Override
		public int compareTo(Job that) {
			return this.finish - that.finish;
		}
	}
	
	public static int getMaxProfit (int[][] data) {
		Job[] jobs = new Job[data.length];
		for (int i = 0; i < data.length; i++) {
			jobs[i] = new Job(data[i][0], data[i][1], data[i][2]);
		}
		
		int[] result = new int[jobs.length];
		int[] pre = new int[jobs.length];
		//1. sort jobs
		Arrays.sort(jobs);
		
		//2. prepare previous non-conflict job for each job
		for (int i = jobs.length - 1; i > 0; i--) {
			for (int j = i - 1; j >= 0 ; j--) {
				if (jobs[j].finish <= jobs[i].start) {
					pre[i] = j;
					break;
				}
			}
		}
		
		return findMaxProfit(jobs, pre, jobs.length-1, result);
	}
	
	public static int findMaxProfit(Job[] jobs, int[] pre, int idx, int[] result) {
		//if f[i] == 0, result[i] = weight[i]
		if (idx == 0)
			return jobs[0].weight;
			
		//else result[i] = max(weight[i]+result[pre[i]], result[i-1])
		if (result[idx] == 0)
			result[idx] = Math.max(jobs[idx].weight + findMaxProfit(jobs, pre, pre[idx], result), findMaxProfit(jobs, pre, idx-1, result));
			
		return result[idx];
	}
	
//9.1 A child is running up a staircase with n steps, and can hop either 1 step, 2 steps,
//or 3 steps at a time. Implement a method to count how many possible ways the
//child can run up the stairs.
//pay attentiion to two problem: 1. n=0, result[0] = 1 2. for n staircases, should init result[n+1] array
	public static int countWay(int n, int[] result) {
		
		if (n < 0)
			return 0;
			
		if (n == 0)
			result[0] = 1;
		
		if (result[n] == -1) {
			result[n] = countWay(n-1, result)+countWay(n-2, result)+countWay(n-3,result);
	 	}
	    
	    return result[n];	
	}	

	public static int countWay(int n) {
		int[] result = new int[n+1];
		for (int i = 1; i < result.length; i++)
			result[i] = -1;
		return countWay(n, result);
	}
	
//9.2 Imagine a robot sitting on the upper left corner of an X by Y grid. The robot can
//only move in two directions: right and down. How many possible paths are there
//~ for the robot to go from (0,0) to (x,y)?
//~ FOLLOW UP
//~ imagine certain spots are "off limits," such that the robot cannot step on them.
//~ design an algorithm to find a path for the robot from the top left to the bottom
//~ right.
	
	
//~ 9.3 A magic index in an array A[0.. .n-1] is defined to be an index such that A[i]
//~ = i. Given a sorted array of distinct integers, write a method to find a magic
//~ index, if one exists, in array A.
//~ FOLLOW UP
//~ What if the values are not distinct?
//11:20 - 11:27
	public static int findMagicIndex(int[] array) {
			Arrays.sort(array);
			return findMagicIndex(array, 0, array.length-1);
	}
	
	private static int findMagicIndex(int[] array, int low, int high) {
		if (low > high)
			return -1;
		
		int mid = (low + high) >> 1;
		if (array[mid] == mid)
			return mid;
			
		if (array[mid] > mid)
			return findMagicIndex(array, low, mid - 1);
		else 
			return findMagicIndex(array, mid+1, high);
	}
	
//~ 9.4 Write a method to return all subsets of a set.	
//11:39-12:07
	public static <T> List<List<T>> getSubset(List<T> s) {
		List<List<T>> dest = new ArrayList<List<T>>();
		getSubset(s, dest);
		return dest;
	}

	private static <T> void getSubset(List<T> s, List<List<T>> dest) {
		    //invalid case
			if (s.isEmpty()) 
				return;

			//reach the end of iteration: only one item in the set
			//pay attention to this
			T item = s.remove(0);
			if (s.isEmpty()) {
				List<T> newList = new ArrayList<T>();
				newList.add(item);
				dest.add(newList);
				return;
			}
            
            //do the iteration
			getSubset(s, dest);
			
			//add new item to all existed lists
			List<List<T>> addedList = new ArrayList<List<T>>();
			for (List<T> list : dest) {
				List<T> newList = new ArrayList<T>();
				newList.addAll(list);
				newList.add(item);
				addedList.add(newList);
			}
			dest.addAll(addedList);
	}
	
//~ 9.5 Write a method to compute all permutations of a string.

//9.6 Implement an algorithm to print all valid (e.g., properly opened and closed)
//~ combinations of n-pairs of parentheses.
//~ EXAMPLE
//~ Input: 3
//~ Output: ((())), (()()), (())(), ()(()), ()()()





	
}

