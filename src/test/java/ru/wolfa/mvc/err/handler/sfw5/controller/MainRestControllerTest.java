package ru.wolfa.mvc.err.handler.sfw5.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ru.wolfa.mvc.err.handler.sfw5.MainApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

/**
 * Diffrent binding error behavior.
 * 
 * Binding request parameters for @RequestBody and @ModelAttribute with @Valid
 * throws different exceptions: MethodArgumentNotValidException and BindException respectively.
 * 
 * @author Sasha
 *
 */
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { MainApp.class })
class MainRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void requestBodyOkTest() throws Exception {
		this.mockMvc.perform(post("/request-body").contentType(MediaType.APPLICATION_JSON).content("{\"test\":23}"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("value=23")));
	}

	@Test
	void requestBodyErrTest() throws Exception {
		MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/no-request-body");
		builder.with(request -> {
			request.setMethod("POST");
			return request;
		});
		builder.param("test1", "23");
		this.mockMvc.perform(builder).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void noRequestBodyOkTest() throws Exception {
		MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/no-request-body");
		builder.with(request -> {
			request.setMethod("POST");
			return request;
		});
		builder.param("test", "23");
		this.mockMvc.perform(builder)
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("value=23")));
	}

	@Test
	void noRequestBodyErrTest() throws Exception {
		this.mockMvc.perform(post("/no-request-body").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andDo(print()).andExpect(status().isBadRequest());
	}

}
