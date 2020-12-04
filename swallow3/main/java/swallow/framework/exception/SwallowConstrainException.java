package swallow.framework.exception;

/**
 * 违反约束异常
 * @author aohanhe
 *
 */
public class SwallowConstrainException extends SwallowException {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2521670174495511095L;
	
	// 约束key
	private String constrainKey;
	
	
	public SwallowConstrainException(String message,Throwable ex,String key) {
		super(message,ex);
		this.constrainKey=key;
	}

	public SwallowConstrainException(String message,Throwable ex) {
		super(message,ex);
	}
	
	public SwallowConstrainException(String message) {
		super(message);
	}

	public String getConstrainKey() {
		return constrainKey;
	}

	public void setConstrainKey(String constrainKey) {
		this.constrainKey = constrainKey;
	}

}
