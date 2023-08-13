package api.tests;

import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payLoad.PayLoad;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class Providers 
{
	PayLoad userPayload;
	@Test(priority =0,dataProvider ="Data",dataProviderClass = DataProviders.class)
	public void post(String UserID,String UserName,String FirstName,String LastName,String	Email,String Password,String Phone)
	{
		userPayload=new PayLoad();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
	}
	
	
	@Test(priority=1,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void get(String username)
	{
		Response response=UserEndPoints.getUser(username);
		response.then().log().all();			
	}
	
	
	

}
