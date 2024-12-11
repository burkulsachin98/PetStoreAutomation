
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
		Assert.assertEquals(res.getStatusCode(),201);
		
	}
	//@Test(priority=2)
	public void getUserByName() {
		
		Response res =UserEndPoints.ReadUser(this.userPayload.getUsername());
		
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
	}
}
