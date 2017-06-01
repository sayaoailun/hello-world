package com.dc.mongo;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class TestMongo {

	public static void main(String[] args) {
		String user = "admin"; // the user name
		String db = "admin"; // the name of the database in which the user is
							// defined
		char[] password = "admin".toCharArray(); // the password as a character array
		// ...

		MongoCredential credential = MongoCredential.createCredential(user, db, password);

		MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();

//		MongoClient mongoClient = new MongoClient(new ServerAddress("host1", 27017), Arrays.asList(credential),
//				options);
		MongoClient mongoClient = new MongoClient(new ServerAddress("10.1.108.243", 27017), Arrays.asList(credential));
		MongoDatabase database = mongoClient.getDatabase("admin");
		for (String name : database.listCollectionNames()) {
		    System.out.println(name);
		}
//		MongoCollection<Document> coll = database.getCollection("myTestCollection");
		
	}

}
