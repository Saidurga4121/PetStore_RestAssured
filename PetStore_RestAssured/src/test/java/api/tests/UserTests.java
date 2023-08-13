package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payLoad.PayLoad;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	static PayLoad userPayload;
	public Logger logger; // for logs
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new PayLoad();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//
		logger= LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("********** Creating user  ***************");
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();			
	}
	
	@Test(priority=2)
	public void testGetUser()
	{
		Response response=UserEndPoints.getUser(userPayload.getUsername());
		response.then().log().all();
	}
	
	
}
