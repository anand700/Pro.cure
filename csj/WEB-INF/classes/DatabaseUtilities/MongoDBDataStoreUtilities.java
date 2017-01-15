package DatabaseUtilities;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class MongoDBDataStoreUtilities
{
	
	// getConnection method for mongodb
	public static MongoClient getConnection() throws Exception {
      	// Connect to Mongo DB
		MongoClient mongo = new MongoClient("localhost", 27017);
		return mongo;
	}
	
	
	//Insert Reviews
	public static boolean InsertReviews(String model, String category, String price, String retailer, String zip, String city, String state, String sale, String manufacture, String rebate, String userid, String age, String gender, String occupation, String rating, String date1, String review)
	{
		try{
			
		MongoClient mongo = null;
		mongo = getConnection();
		
		DB db = mongo.getDB("Reviews");	
		DBCollection myReviews = db.getCollection("myReviews");
		BasicDBObject doc = new BasicDBObject("title", "myReviews").
		append("model",model).
		append("category",category).
		append("price",price).
		append("retailer",retailer).
		append("zip",zip).
		append("city",city).
		append("state",state).
		append("sale",sale).
		append("manufacture",manufacture).
		append("rebate",rebate).
		append("userid",userid).
		append("age",age).
		append("gender",gender).
		append("occupation",occupation).
		append("rating",rating).
		append("date",date1).
		append("review",review);
		myReviews.insert(doc);
        return true;
        }
        catch(Exception exp)
        {
            exp.printStackTrace();
			return false;	
        }
		
	}
	
	
	// getReviews Method for MongoDBDataStoreUtilities
	
	// public static ArrayList<String> getReviews(String model) {
    // ArrayList reviewdata = new ArrayList<String>();
	
      
    // return reviewdata;
// }
	
	
	
		
}
