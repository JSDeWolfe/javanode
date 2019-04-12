package com.example;

//http://square.github.io/okhttp/
//https://www.baeldung.com/guide-to-okhttp
//https://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
//https://programtalk.com/java-api-usage-examples/okhttp3.OkHttpClient/
//https://www.journaldev.com/13629/okhttp-android-example-tutorial
//https://stackoverflow.com/questions/28221555/how-does-okhttp-get-json-string
//https://stackoverflow.com/questions/38110481/how-to-get-json-data-for-any-website-to-parse-and-use-in-my-android-native-app

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;


public class WebServices {
	
	public static String getJson(String url) {
		try {
			OkHttpClient client = new OkHttpClient();
		    Request request = new Request.Builder()
		            .url("https://gp4javanode.herokuapp.com/returnchain")
		            .build();
		        Response responses = null;
		try {
			responses = client.newCall(request).execute();
			String jsonData = responses.body().string();
			String jreturn = jsonData.toString();
			//JSONObject Jobject = new JSONObject(jsonData);
			//error: A JSONObject text must begin with '{' at 1 [character 2 line 1]
			return jreturn;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
		finally {
			System.out.println("stuff not workin");
			return "pinetree";
		}
}
	
	public static String getJsonV2(String url) {
		try {
			OkHttpClient client = new OkHttpClient();
		    Request request = new Request.Builder()
		            .url("https://gp4javanode.herokuapp.com/returnchain")
		            .build();
		        Response responses = null;
			responses = client.newCall(request).execute();
			String jsonData = responses.body().string();
			String jreturn = jsonData.toString();
			//JSONObject Jobject = new JSONObject(jsonData);
			//error: A JSONObject text must begin with '{' at 1 [character 2 line 1]
			return jreturn;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	
		finally {
			System.out.println("stuff not workin");
			return "pinetree";
		}
}
}

