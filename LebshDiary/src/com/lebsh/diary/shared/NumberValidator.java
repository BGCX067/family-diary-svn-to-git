  package com.lebsh.diary.shared;



public class NumberValidator extends EnvRegExValidator{
	 private static final String NUMBER_PATTERN = "[0-9]+";

	@Override
	protected String getPattern() {
		return NUMBER_PATTERN;
	}

	

}
