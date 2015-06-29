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


public class StringExercise {
	
//1.1 Implement an algorithm to determine if a string has all unique characters. What if
//you cannot use additional data structures?

//NOTE: I didn't do the length check to save effort
    public static boolean isUniqueChars(String s) {
		if (s.length() > 128)
			return false;
		boolean[] chars = new boolean[128];
		for (int i = 0; i < s.length(); i++) {
			if (chars[s.charAt(i)])
				return false;
			chars[s.charAt(i)] = true;
		}
		return true;
	}
	
//1.2 Implement a function void reversefchar* str) in Cor C++ which reverses a null-terminated
//string.
	public static String reverse(String s) {
		char[] charset = s.toCharArray();
		int low = 0;
		int high = charset.length-1;
		while (low < high) {
			char tmp = charset[low];
			charset[low++] = charset[high];
			charset[high--]= tmp;
		}
		return new String(charset);
	}
	
//1.3 Given two strings, write a method to decide if one is a permutation of the other.	
    public static boolean isPermutation(String src, String dest) {
        if (src.length() != dest.length())
            return false;

        char[] charsetSrc = src.toCharArray();
        char[] charsetDest = dest.toCharArray();
        java.util.Arrays.sort(charsetSrc);
        java.util.Arrays.sort(charsetDest);
        for (int i = 0; i < charsetSrc.length; i++) {
            if (charsetSrc[i] != charsetDest[i])
                return false;
        }
        return true;
    }
	
	
	//1.4 Write a method to replace all spaces in a string with '%20'. You may assume that the
    //string has sufficient space at the end of the string to hold the additional characters,
    //and that you are given the "true" length of the string. (Note: if implementing in Java,
   //please use a character array so that you can perform this operation in place.)

	public static String replaceSpace(char[] s, int len) {
		int spaceCount = 0;
		for (int i = 0; i < len; i++) {
			if (s[i] ==' ')
			  spaceCount++;
		}
		
		int newLen = len + spaceCount*2;
		for (int i = len - 1; i >= 0; i--) {
          if (s[i] == ' ') {
			  s[newLen-1] = '0';
			  s[newLen-2] = '2';
			  s[newLen-3] = '%';
			  newLen -= 3;
		  } else {
			  s[newLen-1] = s[i];
			  newLen--;
		  }
		}
		return new String(s, 0, len+spaceCount*2);
	}
	
	//1.5 Implement a method to perform basic string compression using the counts of
	//repeated characters. For example, the string aabcccccaaa would become
	//a2blc5a3. If the "compressed" string would not become smaller than the original
	//string, your method should return the original string.
	public static String compress(String s) {
		byte[] letters = s.getBytes();
		byte[] output = new byte[s.length()];
		byte last = letters[letters.length-1];
		byte c;
		int count = 1;
		int idx = letters.length - 1;
		for (int i = letters.length - 2; i >= -1; i--) {
			if (i >= 0)
				c = letters[i];
			else 
				c = 0;
			if (c == last) {
				count++;
			} else {
				while (count > 0 && idx > 0) {
					output[idx--] = (byte)(count%10+0x30);
					count /= 10;
				}
				if (idx == 0) {
					System.out.println(new String(output));
					return s;
				}
				output[idx--] = last;
				count = 1;
				last = c;
			}
		}
		return new String(output, idx+1, letters.length - idx-1);
	}
	
	//1.6 Given an image represented by an NxN matrix, where each pixel in the image is 4
	//bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
	//TODO: M
	public static void rotate(int[][] m, int n) {
	}
	
	//1.7 Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
	//column are set to 0.
	//TODO: Easy
	public static void setZero(int[][] m) {
	}

}

