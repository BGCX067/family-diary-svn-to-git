package com.lebsh.diary.shared;

/**
 * Validate a a String with Regular expression
 * @author einavl
 *
 */
public abstract class EnvRegExValidator implements EnvStringValidator{

	/**
	 * 
	 * @return the regular expression pattern
	 */
	protected abstract String getPattern();
	@Override
	public  boolean isValid(String ex){
		return ex.matches(getPattern());
	}
}
