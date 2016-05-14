package response;

public class LoginResponse {

	private String message;
	private String usertype;
	
	public LoginResponse(String message, String usertype) {
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
	
	public String getUsertype() {
		return usertype;
	}
	
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	
	
}
