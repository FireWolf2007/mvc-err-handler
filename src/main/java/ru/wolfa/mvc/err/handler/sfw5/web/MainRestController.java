package ru.wolfa.mvc.err.handler.sfw5.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

	@PostMapping("/request-body")
	public String requestBody(@RequestBody @Valid SimpleDataClass data) {
		return "value=" + data.getTest().toString();
	}

	@PostMapping("/no-request-body")
	public String noRequestBody(@ModelAttribute @Valid DataClass data) {
		return "value=" + data.getTest().toString();
	}

}
