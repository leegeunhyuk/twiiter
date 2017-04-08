package namoo.social.util.exception;

/**
 * 나무소셜 익셉션
 */
public class NamooSocialException extends RuntimeException {
	//
	private static final long serialVersionUID = -8718311926650745043L;

	public NamooSocialException(String message) {
		// 
		super(message, null, false, false); 
	}
	
	public NamooSocialException(Exception exception) {
		//
		super(exception.getClass().getName() + ": " + exception.getMessage(), null, false, false);
	}
}
