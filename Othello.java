/*
 * Othello.java
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


public class Piece {
	public enum State {
		BLACK, WHITE
	}
	
	State state;
	int player;
}

public class Board {
	Piece[][] board;
	
	public void flip(int i, int j) {
	}
	
	public void add(int i, int j, Piece piece) {
	}
}

public class Game {
	Board board;
	private int init() {
		board = new Board();
	}
	
	public int finished() {
		
	}
	
	public boolean move(int i, int j) {
		return false;
	}
}
	
	public static void main (String args[]) {
		
	}
}

