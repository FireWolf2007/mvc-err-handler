package ru.wolfa.mvc.err.handler.sfw5.web;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class DataClass {

	@NotNull
	private Long test;
	@NotNull
	private MultipartFile file;

	public Long getTest() {
		return test;
	}

	public void setTest(Long test) {
		this.test = test;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "DataClass [test=" + test + ", file=" + file + "]";
	}

}
