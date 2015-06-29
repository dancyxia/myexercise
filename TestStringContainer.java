/*
 * TestContainer.java
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
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import test.TestExercise;

@TestExercise(enabled=true)
public class TestStringContainer extends TestContainer {
	
	@TestExercise(enabled=true)
	public void testUnique() {
		String[] testStrSet = {"abccdfereareafasdfawre", "abcdef", "aboute"};
		for (String s : testStrSet) {
			System.out.printf("%s is %s %n", s, StringExercise.isUniqueChars(s)? "unique" : "not unique");
		}
	}
	
	@TestExercise(enabled=true)
	public void testReplace() {
		String s = "aaaaab eeererrrrrrrre ddffffdf";
		char[] ori = s.toCharArray();
		char[] input = new char[ori.length+10];
		System.arraycopy(ori, 0, input, 0, ori.length);
		System.out.printf("%s is replaced to %n %s %n", s, StringExercise.replaceSpace(input, ori.length));
	}
	
	@TestExercise(enabled = true)
	public void testCompress() {
		String s = "aaaaab eeererrrrrrrre ddffffdf";
		char[] ori = s.toCharArray();
		char[] input = new char[ori.length+10];
		System.arraycopy(ori, 0, input, 0, ori.length);
     	System.out.printf("%s is compressed to %s %n", s, StringExercise.compress(s));
	}
	
	@TestExercise(enabled = true)
	public void testReverse() {
		String s = "aaaaab eeererrrrrrrre ddffffdf";
     	System.out.printf("%s is reversed to %s %n", s, StringExercise.reverse(s));
	}
	
	@TestExercise(enabled = true)
	public void testIsPermutation() {
		String s = "about";
		String d = "baost";
     	System.out.printf("%s is %s the permutation of %s %n", s, StringExercise.isPermutation(s, d)? "":"not", d);
	}
	
}

