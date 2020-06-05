package ru.wolfa.mvc.err.handler.sfw5.web;

import javax.validation.constraints.NotNull;

public class SimpleDataClass {

	@NotNull
	private Long test;

	public Long getTest() {
		return test;
	}

	public void setTest(Long test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "DataClass [test=" + test + "]";
	}

}
