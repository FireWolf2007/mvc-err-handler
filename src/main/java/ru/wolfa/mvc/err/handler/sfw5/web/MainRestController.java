package ru.wolfa.mvc.err.handler.sfw5.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MainRestController {

	@PostMapping("/request-body")
	public String requestBody(@RequestBody @Valid DataClass2 data) {
		return "value=" + data.getTest().toString();
	}

	@PostMapping("/no-request-body")
	public String noRequestBody(@ModelAttribute @Valid DataClass data) {
		return "value=" + data.getTest().toString();
	}

	@PostMapping("/no-request-body-2")
	public String noRequestBody2(@ModelAttribute @Valid DataClass2 data,
			@RequestPart(value = "file", required = true) final MultipartFile f) {
		data.setFile(f);
		return "value=" + data.getTest().toString();
	}
}
