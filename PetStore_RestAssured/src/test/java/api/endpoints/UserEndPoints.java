package api.endpoints;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payLoad.PayLoad;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints 
{
	static ResourceBundle geturl()
	{
		 ResourceBundle routes=ResourceBundle.getBundle("routes");
		 return routes;
	}

	public static Response createUser(PayLoad obj)
	{
	  String postUrl=geturl().getString("postUrl");
	  Response createResponse=given()
			                     .contentType(ContentType.JSON)
			                     .accept(ContentType.JSON)
			                     .body(obj)
						      .when().post(postUrl);
	  return createResponse;
	}
	public static Response getUser(String userName)
	{
		Response getResponse=given()
								.pathParam("username", userName)
		                     .when().get(Routes.getUrl);
        return getResponse;
	}
	public static Response putUser(String userName,String payLoad)
	{
		Response response=given()
				               .headers("accept",ContentType.JSON)
			                   .headers("Content-Type", ContentType.JSON)
							   .pathParam("username", userName)
							   .body(payLoad)
		                     .when().put(Routes.putUrl);
        return response;
	}
	

}
