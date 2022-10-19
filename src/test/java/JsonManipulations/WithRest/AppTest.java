package JsonManipulations.WithRest;


import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	@Test
	public void GetAllData() {
		
		Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().extract().response();
				
		Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$");
		System.out.println(jpath.toString());
	}
	@Test
	public void GetDataOfArray() {
		
		Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().log().all().extract().response();
				
		Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.data");

		System.out.println(jpath.toString());
	}
	@Test
	public void GetDataOfSingleElementInArray() {
		
		Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().log().all().extract().response();
				
		Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.data[1]");
		System.out.println(jpath.toString());
	}
	@Test
	public void GetDataOfSingleElementDtaInArray() {
		
		Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().log().all().extract().response();
				
		Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.data[1].first_name");
		System.out.println(jpath.toString());
	}
	@Test
	public void GetDataOfMoreThanOneElementInArray() {
		
		Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().extract().response();
				
		Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.data[1,3,4]");
		System.out.println(jpath.toString());
		
	}
	@Test
		public void GetDataOfArrayUsingSlicing() {
			
			Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().extract().response();
					
			Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.data[0:4]"); //it will take the data of every second element in the array
			System.out.println(jpath.toString());
			
		}

	@Test
	public void GetDataOfOneElement() {
		
		Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().extract().response();
				
		Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.total");
		System.out.println(jpath.toString());
		Assert.assertEquals(jpath,12);
	}
	@Test
	public void GetDataOfObject() {
		
		Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().extract().response();
				
		Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.support");
		System.out.println(jpath.toString());
		
	}
	@Test
	public void GetDataOfElementInObject() {
		
		Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().extract().response();
				
		Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.support.text");
		System.out.println(jpath.toString());
		
	}
	@Test
	public void GetDatawithCondition() {
		
		Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().extract().response();
				
		Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.data[?(@.id==2)]"); //it will get the data whose id is equal to 2
		System.out.println(jpath.toString());
		
	}
	@Test
		public void GetDataOfSingleElementwithCondition() {
			
			Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().extract().response();
					
			Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.data[?(@.id==3)].first_name"); //it will get the first name whose id is equal to 3
			System.out.println(jpath.toString());
	}
	@Test
		public void GetMoreThanOneDatawithCondition() {
			
			Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().extract().response();
					
			Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.data[?(@.id<=3)]"); //it will get the data whose id is less than or equal to 3
			System.out.println(jpath.toString());
			
		}
	@Test
	public void GetDataOfMoreThanSingleElementwithCondition() {
		
		Response body = RestAssured.given().baseUri("https://reqres.in/api/users").contentType("application/json").when().get().then().extract().response();
				
		Object jpath =com.jayway.jsonpath.JsonPath.read(body.asString(),"$.data[?(@.id<=3)].email"); //it will get the emails whose id is less than or equal to 3
		System.out.println(jpath.toString());
		
	}

}
