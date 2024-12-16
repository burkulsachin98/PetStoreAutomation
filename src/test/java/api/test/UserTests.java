package api.test;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com 	.github.javafaker.Faker;

import api.endpoints.UserEndPoints;

public class UserTests {

	Faker faker;
	User userPayload;

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

	}
	@Test(priority=1)
	public void  testPostUser() {

		Response res =UserEndPoints.CreateUser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);

	}
	@Test(priority=2)
	public void getUserByName() {

		Response res =UserEndPoints.ReadUser(this.userPayload.getUsername());

		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);	
	}	
	public void updateUser() {

		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		Response response=UserEndPoints.UpdateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		//Check response after update 
		Response responseAfterUpdate=UserEndPoints.ReadUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}

	public void testDeleteUserByName() {
		Response  response =UserEndPoints.DeleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.statusCode(), 200);


	}
}
