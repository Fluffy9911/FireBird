package main.fire.exception;

public class CacheException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "An error occured trying to create/load cache, check if file exists or file empty";
	}

}
