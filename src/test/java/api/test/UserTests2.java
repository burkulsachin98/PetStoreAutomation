package api.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com 	.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	
	public Logger logger;
	

	@BeforeClass
	public void setupData() {

		faker = new Faker();
		userPayload = new User();
		
		
		

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		logger=LogManager.getLogger(this.getClass());
		

	}
	@Test(priority=1)
	public void  testPostUser() {

		Response res =UserEndPoints2.CreateUser(userPayload);
		res.then().log().all();
		AssertJUnit.assertEquals(res.getStatusCode(),200);

	}
	@Test(priority=2)
	public void getUserByName() {

		Response res =UserEndPoints2.ReadUser(this.userPayload.getUsername());

		res.then().log().all();
		AssertJUnit.assertEquals(res.getStatusCode(), 200);	
	}	
	
	@Test
	public void updateUser() {

		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		Response response=UserEndPoints2.UpdateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);

		//Check response after update 
		Response responseAfterUpdate=UserEndPoints2.ReadUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}

	@Test
	public void testDeleteUserByName() {
		Response  response =UserEndPoints2.DeleteUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(response.statusCode(), 404);


	}
}
