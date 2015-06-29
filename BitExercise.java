/*
 * BitExercise.java
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


public class BitExercise {

	//5.1 You are given two 32-bit numbers, N and M, and two bit positions, land j. Write
	//a method to insert M into N such that M starts at bit j and ends at bit i. You can
	//assume that the bits j through i have enough space to fit all of M. That is, if
	//M = 10011, you can assume that there are at least 5 bits between j and i. You
	//would not, for example, have j = 3 and i = 2, because M could not fully fit
	//between bit 3 and bit 2.
	//EXAMPLE
	//Input: N = 10000000000, M = 10011, i = 2, j = 6
	//Output: N = 10001001100
	public static int updateBits (int n, int m, int i, int j) {
		//1. create a mask which clear bits between i and j
		int allOnes = ~0;
		int left = allOnes << (j+1);
		int right = (1 << i) - 1;
		int mask = left | right;
		
		//2. clear bits between i and j
		n &= mask;
		
		//3. shift m
		m = m << i;
		
		return n | m;
	}
	
//    5.2 Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double,
//    print the binary representation. If the number cannot be represented accurately
//    in binary with at most 32 characters, print "ERROR."


//    5.3 Given a positive integer, print the next smallest and the next largest number
//    that have the same number of 1 bits in their binary representation.

//    5.4 Explain what the following code does: ((n & (n-1)) == 0).

//    5.5 Write a function to determine the number of bits required to convert integer A
//    to integer B.
//            EXAMPLE
//    Input: 31,14
//    Output: 2


//    5.6 Write a program to swap odd and even bits in an integer with as few instructions
//    as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and
//            soon).

//    5.7 An array A contains all the integers from 0 to n, except for one number which is
//    missing. In this problem, we cannot access an entire integer in A with a single
//    operation. The elements of A are represented in binary, and the only operation
//    we can use to access them is "fetch the jth bit of A[i]," which takes constant
//    time. Write code to find the missing integer. Can you do it in 0(n) time?

//    5.8 A monochrome screen is stored as a single array of bytes, allowing eight consecutive
//    pixels to be stored in one byte.The screen has width w, where w is divisible
//    by 8 (that is, no byte will be split across rows).The height of the screen, of course,
//    can be derived from the length of the array and the width. Implement a function
//    drawHorizontall_ine(byte[] screen, int width, int xl, int x2, int y) which draws a horizontal line from (xl, y)to(x2, y).
//
//
	
	
}

