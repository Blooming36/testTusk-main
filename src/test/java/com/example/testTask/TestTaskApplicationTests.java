package com.example.testTask;

import com.example.testTask.models.authModels.LoginRequest;
import com.example.testTask.models.authModels.SignupRequest;
import com.example.testTask.models.userModels.User;
import com.example.testTask.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestTaskApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Test
	public void testRegisterUser() throws Exception {
		SignupRequest signupRequest = new SignupRequest("email2222@example.com", Collections.singleton("user"), "2002");
		mockMvc.perform(post("/api/auth/signup")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(signupRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message", isA(String.class)));

		User savedUser = userService.findByEmail(signupRequest.getEmail())
				.orElseThrow(() -> new AssertionError("User not found in the database"));
		Assert.assertThat(savedUser.getEmail(), is(signupRequest.getEmail()));

	}
	@Test
	public void testSignInUser() throws Exception {
		LoginRequest loginRequest = new LoginRequest("email2222@example.com","2002");
		User user = userService.findByEmail(loginRequest.getEmail()).get();
		mockMvc.perform(post("/api/auth/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(loginRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email", is(user.getEmail())))
				.andExpect(jsonPath("$.id", is(user.getId().intValue())));
	}

	private String asJsonString(Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(obj);
		} catch (com.fasterxml.jackson.core.JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}




}
