package framework.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	@JsonProperty("password")
	public String password;

	@JsonProperty("label")
	public String label;

	@JsonProperty("userName")
	public String userName;
}