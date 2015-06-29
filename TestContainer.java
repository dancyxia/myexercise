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

public class TestContainer {
	
	public static void main (String args[]) {
		try {
			Class testEntity = Class.forName("test.TestExercise");
			Class[] containers = new Class[] {Class.forName("TestStringContainer"), Class.forName("TestTreeContainer"), Class.forName("TestDPContainer")};

			for (Class<? extends TestContainer> container : containers) {
				if (container.isAnnotationPresent(testEntity)) {
					TestExercise testAnnotation = (TestExercise)container.getAnnotation(testEntity);
					if (testAnnotation.enabled()) {
						System.out.println("======================================================");
						System.out.printf("             Test class:  %s        %n", container.getName());
						System.out.println("======================================================");
						
						for (Method m : container.getDeclaredMethods()) {
							if (m.isAnnotationPresent(testEntity)) {
								TestExercise test = (TestExercise)m.getAnnotation(testEntity);
								if (test.enabled()) {
									System.out.println("******************************************");
									System.out.printf("Test method: %s %n", m.getName());
									System.out.println("******************************************");
									System.out.println();
									m.invoke(container.newInstance());
									System.out.println();
								}
							}
						}
					}
				}
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

