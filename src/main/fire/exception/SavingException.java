package main.fire.exception;

public class SavingException extends Exception {
	String exc;

	public SavingException(String exc) {
		this.exc = exc;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "{" + exc + "}";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2021367715395886295L;

}
