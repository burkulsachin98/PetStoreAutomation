package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// It Contain for to perform the CRUD operation  to the user API 

public class UserEndPoints2 {

	//method created for getting URLs from properties files 
	static ResourceBundle getURL(){
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
		
	}
	
	public static Response  CreateUser( User payload) {
		
	String post_url =	getURL().getString("post_url");
		Response res  =given ()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(post_url);
		return res ;
	}

	public static Response  ReadUser(String userName) {
		
		String get_url =	getURL().getString("get_url");
		
		Response res  =given ()
				.pathParam("username", userName)
					
				.when()
				.get(get_url);
		return res ;
	}
	
	public static Response  UpdateUser( String userName ,User payload) {
		
		String update_url =	getURL().getString("update_url");
		
		Response res=given ()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when()
				.put(update_url);
		return res;
	}
	public static Response  DeleteUser( String userName) {
		
		String delete_url =	getURL().getString("delete_url");
		
		Response res=given ()
				
				.pathParam("username", userName)

				.when()
				.delete(delete_url);
		return res ;
	}
	


}
