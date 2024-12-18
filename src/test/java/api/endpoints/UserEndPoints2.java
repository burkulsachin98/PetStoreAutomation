package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// It Contain for to perform the CRUD operation  to the user API 



public class UserEndPoints2 {

	public static Response  CreateUser( User payload) {
		Response res  =given ()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(Routes.post_url);
		return res ;
	}

	public static Response  ReadUser(String userName) {
		Response res  =given ()
				.pathParam("username", userName)
					
				.when()
				.get(Routes.get_url);
		return res ;
	}
	
	public static Response  UpdateUser( String userName ,User payload) {
		Response res  =given ()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)

				.when()
				.put(Routes.update_url);
		return res ;
	}
	public static Response  DeleteUser( String userName) {
		Response res=given ()
				
				.pathParam("username", userName)

				.when()
				.delete(Routes.delete_url);
		return res ;
	}
	


}
