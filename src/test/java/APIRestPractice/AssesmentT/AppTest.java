package APIRestPractice.AssesmentT;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;



/**
 * Unit test for simple App.
 */
public class AppTest {
	//https://reqres.in/api/users
	@Test
	public void RestAssesmentPost() throws IOException {
		File data = new File("C:\\Users\\7000032449\\eclipse-workspace\\AssesmentT\\src\\main\\resources\\post.json");
		Response response= RestAssured
		.given()
		.baseUri("https://reqres.in/api")
		.body(data)
		.contentType(ContentType.JSON)
		.when()
		.post("/users")
		.then()
		.log().all().extract().response();
		
		Response response2 =  RestAssured
				.given()
				.baseUri("https://reqres.in/api")
				.body(data)
				.contentType(ContentType.JSON)
				.when()
				.get("/users")
				.then()
				.log().all().extract().response();
		
		Object json = com.jayway.jsonpath.JsonPath.read(response2.asString(),"$.data[2]");
		ObjectMapper mapper = new ObjectMapper();
		String str =mapper.writeValueAsString(json);
		
		TypeReference <HashMap<String, Object>> map = new TypeReference <HashMap<String, Object>>()
				{};
		HashMap<String, Object> hash =mapper.readValue(str, map);
		for(Entry<String, Object> get:hash.entrySet()) {
			System.out.println(""+get.getKey()+":"+get.getValue());
			Assert.assertEquals(hash.get("id"),3);
			Assert.assertEquals(hash.get("first_name"),"Emma");
		}
//		Object mail = com.jayway.jsonpath.JsonPath.read(data,"$.data[2].email");
//		Assert.assertEquals("emma.wong@reqres.in",mail);
//		
//		Object fname = com.jayway.jsonpath.JsonPath.read(data,"$.data[2].first_name");
//		Assert.assertEquals("Emma",fname);
//		Object lname = com.jayway.jsonpath.JsonPath.read(data,"$.data[2].last_name");
//		Assert.assertEquals("Wong",lname);
//		
//		//"https://reqres.in/img/faces/3-image.jpg"
//		Object avatar = com.jayway.jsonpath.JsonPath.read(data,"$.data[2].avatar");
//		Assert.assertEquals("https://reqres.in/img/faces/3-image.jpg",avatar);

		
				
   
}
}
