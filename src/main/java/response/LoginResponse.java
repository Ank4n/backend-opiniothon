package response;

public class LoginResponse {

	private String message;
	private int usertype;

	public LoginResponse(String message, int usertype) {
		super();
		this.message = message;
		this.usertype = usertype;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

}
