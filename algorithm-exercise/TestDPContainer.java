/*
 * TestDPCOntainer.java
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
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@TestExercise(enabled=true)
public class TestDPContainer extends TestContainer{
	
	@TestExercise(enabled=true,
	questionNo="",
	questionDescription="Given N jobs where every job is represented by following three elements of it.	%n1) Start Time 	%n2) Finish Time 	%n3) Profit or Value Associated. 	%n Find the maximum profit subset of jobs such that no two jobs in the subset overlap. %n")
	public static void testScheduleProblem () {
		 int[][] data = {{3, 10, 20}, 
						 {1, 2, 50}, 
						 {6, 19, 100}, 
						 {2, 100, 200}
						};
		System.out.printf("The optimal profit is %d %n", DynamicProgram.getMaxProfit(data));
		
	}


	@TestExercise(enabled=true,
		questionNo="9.1",
		questionDescription="A child is running up a staircase with n steps, and can hop either 1 step, 2 steps, or 3 steps at a time. %nImplement a method to count how many possible ways the child can run up the stairs. %npay attentiion to two problem: 1. n=0, result[0] = 1 2. for n staircases, should init result[n+1] array")
	public static void testStepProblem() {
		System.out.printf("there are %d ways to climb %d staircases %n", DynamicProgram.countWay(3), 3);
		System.out.printf("there are %d ways to climb %d staircases %n", DynamicProgram.countWay(6), 6);
	}

	@TestExercise(enabled=true, 
		questionNo="9.3",
		questionDescription="A magic index in an array A[0.. .n-1] is defined to be an index such that A[i]= i. %nGiven a sorted array of distinct integers, write a method to find a magic index, if one exists, in array A.")
	public static void testMagicIndex() {
		int[] a = {-3, -1, 1, 3, 4, 7, 9};
		System.out.printf("the magic index is %d %n", DynamicProgram.findMagicIndex(a));
	}
	
	@TestExercise(enabled=true,	questionNo="9.4")
	public static void testSubset() {
		//PAY ATTENTION TO THIS. Have to use Integer so that Arrays.asList(a) works. Don't use int[], autoWrapper doesn't work here
		//~ Integer[] a = {-3, -1, 1, 3, 4, 7, 9};
		Integer[] a = {-3, -1, 1};
		//PAY ATTENTION TO THIS: Arrays.asList returns the fixed size list, you can't add/remove it. To use it, need to create a new List out of it
		List<Integer> s = new ArrayList<Integer>(Arrays.asList(a));
		List<List<Integer>> dest = DynamicProgram.getSubset(s);
		for (List<Integer> list : dest) {
			for (Integer item : list) {
				System.out.print(item+"  ");
			}
			System.out.println();
		}
	}
}

