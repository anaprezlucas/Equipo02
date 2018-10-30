package com.uclm.equipo02.persistencia;

import org.bson.BsonDocument;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoBroker {
	private static MongoBroker yo;
	private MongoClientURI uri;
	private MongoClient mongoClient;
	private MongoDatabase db;

	private MongoBroker(){
		this.uri= new MongoClientURI("mongodb://equipo02:equipo02gps@ds115740.mlab.com:15740/fichajes");
		this.mongoClient= new MongoClient(uri);
		this.db=mongoClient.getDatabase(uri.getDatabase());
	}

	public static MongoBroker get(){
		if (yo==null){
			yo = new MongoBroker();
		}
		return yo;
	}

	public MongoCollection<BsonDocument> getCollection (String collection){
		MongoCollection <BsonDocument> result=db.getCollection(collection, BsonDocument.class);
	
		if(result==null){
			db.createCollection(collection);
			result=db.getCollection(collection,BsonDocument.class);
		}

		return result;
	}
}