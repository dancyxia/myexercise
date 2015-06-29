/*
 * SolutionNetwork.java
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

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Comparator;

public class SolutionNetwork {
	public final static int MAXLEVEL = 2;

    private static Map<String, List<String>> friendsMap = new HashMap<String, List<String>>();
    private static Map<String, List<String>> courseMap = new HashMap<String, List<String>>();

    //this class works as the node for network traversal
    private class User {
            String id;
            int level;
            List<String> friends;
            boolean visited;
            User parent;

            public User(String id, List<String> friends) {
                this.id = id;
                this.friends = friends;
                level = 0;
            }

            public void setLevel(int level) { this.level = level;}
            public int getLevel() { return level; }

            public boolean isVisited() { return visited;}
            public void setVisited(boolean visited) {this.visited = visited; }

            public List<String> getDirectFriends() {return friends;}
    }

    private class Network {
        User thisUser;
        List<User> network;
        final Map<String, User> userMap = new HashMap<String, User>();
        LinkedList<User> q;

		//this is the factory of User instance
        public User getUser(String userID) {
            if (!userMap.containsKey(userID)) {
                User newUser = new User(userID, getDirectFriends(userID));
                userMap.put(userID, newUser);
                return newUser;
            }
            return userMap.get(userID);
        }

        public List<User> getNetwork()   {
            return network;
        }

        public Network(String id) {
            thisUser = getUser(id);
            q = new LinkedList<User>();
            network = new LinkedList<User>();
            bfs(thisUser, MAXLEVEL);
        }

        private void bfs(User user, int maxLevel) {
            q.add(user);
            user.setVisited(true);

            while(!q.isEmpty()) {
				User u = q.removeFirst();
				u.setVisited(true);
				if (u.getLevel() >= maxLevel)
					return;
	
				List<String> friends = u.getDirectFriends();
				if (friends != null) {
					for (String t : u.getDirectFriends()) {
						User friend = getUser(t);
						if (!friend.isVisited()) {
							friend.setVisited(true);
							friend.setLevel(u.getLevel()+1);
							network.add(friend);
							q.add(friend);
						}
					}
				}
			}
        }
    }

    public List<String> getRecommendedCourses(String user) {
        List<User> friends = new Network(user).getNetwork();
        final Map<String, Integer> courseCountMap = new HashMap<String, Integer>();
        for (User friend : friends) {
			List<String> courses = getCourses(friend.id);
			if (courses != null) {
				for (String course : getCourses(friend.id)) {
					int count = courseCountMap.containsKey(course) ? courseCountMap.get(course) : 0;
					courseCountMap.put(course, count + 1);
				}
			}
        }
        String[] keys = courseCountMap.keySet().toArray(new String[courseCountMap.size()]);
        Arrays.sort(keys, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
               return courseCountMap.get(rhs) - courseCountMap.get(lhs);
            }
        });
        
        return Arrays.asList(keys);
    }

    public List<String> getDirectFriends(String user) {
        return friendsMap.get(user);
    }

    public List<String> getCourses(String user) {
        return courseMap.get(user);
    }

    private static void setupTest() {
        List<String> friendList = new LinkedList<String> ();
        friendList.add("John");
        friendList.add("Peter");
        friendsMap.put("Amy", friendList);

        friendList = new LinkedList<String>();
        friendList.add("Amy");
        friendList.add("Joe");
        friendList.add("Ben");
        friendsMap.put("John", friendList);

        friendList = new LinkedList<String>();
        friendList.add("Amy");
        friendList.add("Joe");
        friendsMap.put("Peter", friendList);

        friendList = new LinkedList<String>();
        friendList.add("Smith");
        friendList.add("Peter");
        friendList.add("John");
        friendsMap.put("Joe", friendList);

        friendList = new LinkedList<String>();
        friendList.add("John");
        friendsMap.put("Ben", friendList);

        friendList = new LinkedList<String>();
        friendList.add("Joe");
        friendsMap.put("Smith", friendList);

        List<String> courseList = new LinkedList<String> ();
        courseList.add("a");
        courseList.add("d");
        courseList.add("b");
        courseMap.put("John", courseList);

        courseList = new LinkedList<String> ();
        courseList.add("a");
        courseList.add("b");
        courseList.add("c");
        courseMap.put("Amy", courseList);

        courseList = new LinkedList<String> ();
        courseList.add("b");
        courseList.add("d");
        courseMap.put("Peter", courseList);

        courseList = new LinkedList<String> ();
        courseList.add("b");
        courseList.add("c");
        courseList.add("d");
        courseMap.put("Joe", courseList);

        courseList = new LinkedList<String> ();
        courseList.add("a");
        courseList.add("b");
        courseMap.put("Ben", courseList);

        courseList = new LinkedList<String> ();
        courseList.add("a");
        courseMap.put("Smith", courseList);

    }

    public static void main(String[] args) {
        setupTest();
        SolutionNetwork solution = new SolutionNetwork();
        
        System.out.println("courses for Amy: ");
        for (String course: solution.getRecommendedCourses("Amy")) {
            System.out.println(course);
        }

        System.out.println("courses for John: ");
        for (String course: solution.getRecommendedCourses("John")) {
            System.out.println(course);
        }
        
        System.out.println("courses for Smith: ");
        for (String course: solution.getRecommendedCourses("Smith")) {
            System.out.println(course);
        }
        
    }
}

