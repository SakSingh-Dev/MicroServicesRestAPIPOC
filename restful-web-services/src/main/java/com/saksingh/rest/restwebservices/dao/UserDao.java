package com.saksingh.rest.restwebservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
//Since thgis will talk to DB so mention the annotation as COmponent or Repository

import com.saksingh.rest.restwebservices.bean.User;
import com.saksingh.rest.restwebservices.exception.UserNotFoundException;

/**
 * @author saksingh
 *
 */
@Component
public class UserDao {
	private static List<User> userList = new ArrayList<>();
	static {
		userList.add(new User(1, "Saket", new Date()));
		userList.add(new User(2, "Shyam", new Date()));
		userList.add(new User(3, "Manohan", new Date()));
	}

	public List<User> getAllUsers() {
		return userList;
	}

	public User save(User user) {
		if (0 == user.getId()) {
			user.setId(userList.size() + 1);
			System.out.println("Heyy Insideeeeeeeeee");
		}
		userList.add(user);
		return user;
	}

	public User findUser(Integer userid) {
		// userList.forEach(user-> user.getId().equals(userid))
		return userList.stream().filter(user -> userid == user.getId()).findAny().orElse(null);
	}

	public User deleteById(Integer id) {
		Iterator<User> iterator = userList.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
