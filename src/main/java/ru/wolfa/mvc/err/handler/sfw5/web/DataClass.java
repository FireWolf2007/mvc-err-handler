package ru.wolfa.mvc.err.handler.sfw5.web;

import javax.validation.constraints.NotNull;

public class DataClass {

	@NotNull
	private Long test;
	private Long field2;

	public Long getTest() {
		return test;
	}

	public void setTest(Long test) {
		this.test = test;
	}

	public Long getField2() {
		return field2;
	}

	public void setField2(Long field2) {
		this.field2 = field2;
	}

	@Override
	public String toString() {
		return "DataClass [test=" + test + ", field2=" + field2 + "]";
	}

}
