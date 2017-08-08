package br.com.opencare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.opencare.model.User;
import br.com.opencare.service.UserService;

@RestController
public class RESTController {

	@Autowired
	UserService userService;

	private class UserRest {
		private long id;
		private String email;
		private String name;

		public String getName() {
			return name;
		}

		public UserRest() {
		}

		public UserRest(User user) {
			super();
			this.id = user.getId();
			this.name = user.getName();
			this.email = user.getEmail();
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	}

	@RequestMapping("/rest/user/{id}")
	public ResponseEntity<UserRest> rest(@PathVariable("id") long id) {
		User u = userService.find(id);
		if (u == null)
			return new ResponseEntity<UserRest>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<UserRest>(new UserRest(u), HttpStatus.OK);
	}

}
