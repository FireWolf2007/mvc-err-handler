package ru.wolfa.mvc.err.handler.sfw5.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
 * throws different exceptions: MethodArgumentNotValidException and
 * BindException respectively.
 * 
 * @author Sasha
 *
 */
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { MainApp.class })
class MainRestControllerTest {

	private static final MockMultipartFile file = new MockMultipartFile("file", "file2.pdf", "application/pdf",
			new byte[] { 0 });

	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public static void init() {
	}

	@Test
	void requestBodyOkTest() throws Exception {
		// file actually does not received
		this.mockMvc
				.perform(post("/request-body").contentType(MediaType.APPLICATION_JSON)
						.content("{\"test\":23,\"file\":[0]}"))
				.andDo(log()).andExpect(status().isOk()).andExpect(content().string(containsString("value=23")));
	}

	@Test
	void noRequestBodyOkTest() throws Exception {
		// Spring 5.2 - new MockMvcRequestBuilders.multipart
		// Spring 4.3 - old MockMvcRequestBuilders.fileUpload (deprecated in 5.2)
		MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/no-request-body");
		builder.with(request -> {
			request.setMethod("POST");
			return request;
		});
		builder.param("test", "24");
		builder.file(file);
		this.mockMvc.perform(builder).andDo(log()).andExpect(status().isOk())
				.andExpect(content().string(containsString("value=24")));
	}

	@Test
	void noRequestBody2OkTest() throws Exception {
		// Spring 5.2 - new MockMvcRequestBuilders.multipart
		// Spring 4.3 - old MockMvcRequestBuilders.fileUpload (deprecated in 5.2)
		MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/no-request-body-2");
		builder.with(request -> {
			request.setMethod("POST");
			return request;
		});
		builder.param("test", "25");
		builder.file(file);
		this.mockMvc.perform(builder).andDo(log()).andExpect(status().isOk())
				.andExpect(content().string(containsString("value=25")));
	}

	// ERRORS / EXCEPTIONS

	@Test
	void requestBodyErrTest() throws Exception {
		this.mockMvc.perform(post("/request-body").contentType(MediaType.APPLICATION_JSON).content("{}")).andDo(log())
				.andExpect(status().isBadRequest());
		this.mockMvc.perform(post("/request-body").contentType(MediaType.APPLICATION_JSON).content("{\"test1\":23}"))
				.andDo(log()).andExpect(status().isBadRequest());
	}

	@Test
	void noRequestBodyErrTest() throws Exception {
		// Spring 5.2 - new MockMvcRequestBuilders.multipart
		// Spring 4.3 - old MockMvcRequestBuilders.fileUpload (deprecated in 5.2)
		MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/no-request-body");
		builder.with(request -> {
			request.setMethod("POST");
			return request;
		});
		builder.param("test", "24");
		// builder.file(file);
		this.mockMvc.perform(builder).andDo(log()).andExpect(status().isBadRequest());
	}

	@Test
	void noRequestBody2ErrTest() throws Exception {
		// Spring 5.2 - new MockMvcRequestBuilders.multipart
		// Spring 4.3 - old MockMvcRequestBuilders.fileUpload (deprecated in 5.2)
		MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.fileUpload("/no-request-body-2");
		builder.with(request -> {
			request.setMethod("POST");
			return request;
		});
		builder.param("test", "25");
		// builder.file(file);
		this.mockMvc.perform(builder).andDo(log()).andExpect(status().isBadRequest());
	}

}
