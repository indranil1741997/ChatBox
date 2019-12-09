package com.connector;

import java.sql.Timestamp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Test {

	public static void main(String[] args)
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("msg", "hi");
		jsonObject.addProperty("timestamp", timestamp.toString());
		JsonArray array = new JsonArray();
		array.add(jsonObject);
		JsonObject jsonObject1 = new JsonObject();
		jsonObject1.addProperty("msg", "hello");
		jsonObject1.addProperty("timestamp", timestamp.toString());
		array.add(jsonObject1);
		
		JsonArray par =  array.getAsJsonArray();
		System.out.println(par);
	}
}
