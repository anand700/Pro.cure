package saxParser;

import java.net.UnknownHostException;
import java.util.*;
// import com.mongodb.BasicDBObject;
// import com.mongodb.BasicDBObjectBuilder;
// import com.mongodb.DB;
// import com.mongodb.DBCollection;
// import com.mongodb.DBCursor;
// import com.mongodb.DBObject;
// import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import com.mongodb.*;


public class MongoDBDataStoreUtilities {
    
    public static void writeReview(Review r) {
        
        try {
            
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("reviewDatabase");
            
            DBCollection collection = db.getCollection("productReview");
            
            
            
            String json = "{'ProductId' : '"+r.getProductId()+"' , 'ProductModelName' : '"+r.getProductModelName()+"' , 'ProductCategory' : '"+r.getProductCategory()+"'," +
            "'RetailerName' : '"+r.getRetailerName()+"', 'ProductPrice' : '"+r.getProductPrice()+"', 'RetailerZip' : '"+
            r.getRetailerZip()+"', 'ProductOnSale' : '"+r.getProductOnSale()+"', 'UserAge' : '"+
            r.getUserAge()+"', 'RetailerCity' : '"+r.getRetailerCity()+"', 'RetailerState' : '"+
            r.getRetailerState()+"', 'ManufacturerName' : '"+r.getManufacturerName()+"', 'ManufacturerRebate' : '"+
            r.getManufacturerRebate()+"', 'UserID' : '"+r.getUserID()+"', 'UserGender' : '"+
            r.getUserGender()+"', 'UserOccupation' : '"+r.getUserOccupation()+"', 'ReviewRating' : '"+
            r.getReviewRating()+"', 'ReviewDate' : '"+r.getReviewDate()+"', 'ReviewText' : '"+r.getReviewText()+"'}";
            
            System.out.println(json);
            DBObject dbObject = (DBObject)JSON.parse(json);
            
            collection.insert(dbObject);
            
            DBCursor cursorDocJSON = collection.find();
            while (cursorDocJSON.hasNext()) {
                System.out.println(cursorDocJSON.next());
            }
            
            //collection.remove(new BasicDBObject());
            
        } catch (MongoException e) {
            e.printStackTrace();
        }
        
    }
    
    
    public static List<Review> readReview(Review r) {
        List<Review> reviews = new ArrayList<Review>();
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("reviewDatabase");
            
            DBCollection collection = db.getCollection("productReview");
            BasicDBObject query = new BasicDBObject("ProductId", r.getProductId());
            DBCursor cursor = collection.find(query);
            while(cursor.hasNext()) {
                DBObject theObj = cursor.next();
                System.out.println("theObj:"+theObj);
                
                Review review = new Review();
                review.setRetailerName((String)theObj.get("RetailerName"));
                review.setManufacturerName((String)theObj.get("ManufacturerName"));
                review.setProductCategory((String)theObj.get("ProductCategory"));
                review.setUserID((String)theObj.get("UserID"));
                review.setReviewDate((String)theObj.get("ReviewDate"));
                review.setProductModelName((String)theObj.get("ProductModelName"));
                review.setReviewText((String)theObj.get("ReviewText"));
                review.setProductPrice((String)theObj.get("ProductPrice"));
                review.setReviewRating((String)theObj.get("ReviewRating"));
                reviews.add(review);
            }
            
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return reviews;
        
    }
    
    public static List<Review> top5LikedProd() {
        List<Review> reviews = new ArrayList<Review>();
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("reviewDatabase");
            
            DBCollection collection = db.getCollection("productReview");
            BasicDBObject query = new BasicDBObject();
            
            int returnLimit = 5;
            DBObject sort = new BasicDBObject();
            sort.put("ReviewRating",-1);
            // DBCursor cursor = collection.find().sort(new BasicDBObject ("ReviewRating",-1)).limit(5);
            
            String filter= "{ReviewRating:{$gt:\"4\"}}";
            DBObject filterJson = (DBObject)JSON.parse(filter);
            DBCursor cursor = collection.find(filterJson).limit(returnLimit).sort(sort);
            
            while(cursor.hasNext()) {
                DBObject theObj = cursor.next();
                System.out.println("theObj:"+theObj);
                
                Review review = new Review();
                review.setRetailerName((String)theObj.get("RetailerName"));
                review.setManufacturerName((String)theObj.get("ManufacturerName"));
                review.setProductCategory((String)theObj.get("ProductCategory"));
                review.setUserID((String)theObj.get("UserID"));
                review.setReviewDate((String)theObj.get("ReviewDate"));
                review.setProductModelName((String)theObj.get("ProductModelName"));
                review.setReviewText((String)theObj.get("ReviewText"));
                review.setProductPrice((String)theObj.get("ProductPrice"));
                review.setReviewRating((String)theObj.get("ReviewRating"));
                reviews.add(review);
                
            }
            
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return reviews;
        
    }
    
    
    
    public static List<String> top5Zip() {
        // List<Review> reviews = new ArrayList<Review>();
        List<String> returnVal = new ArrayList<String>();
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("reviewDatabase");
            
            DBCollection collection = db.getCollection("productReview");
            BasicDBObject query = new BasicDBObject();
            
            DBObject groupFieldsInId = new BasicDBObject();
            groupFieldsInId.put( "RetailerZip", "$RetailerZip");
            DBObject groupFields = new BasicDBObject( "_id", groupFieldsInId);
            groupFields.put("count", new BasicDBObject( "$sum", 1));
            DBObject group = new BasicDBObject("$group", groupFields );
            DBObject limit=new BasicDBObject("$limit",5);
            AggregationOutput output = collection.aggregate(group,limit);
            
            for (DBObject result : output.results()) {
                DBObject result1 = (DBObject) result.get("_id");
                // System.out.println("Json: "+result1.get("RetailerZip"));
                // System.out.println("Result: "+bobj.toString());
                // System.out.println("(String)bobj.getRetailerZip: "+bobj.get("_id"));
                // System.out.println("(String)bobj.getRetailerZip: "+bobj.get("count"));
                
                Review review = new Review();
                review.setRetailerZip((String)result1.get("RetailerZip"));
                // reviews.add(review);
                returnVal.add((String)result1.get("RetailerZip")+" : "+result.get("count").toString());
            }
            
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return returnVal;
        
    }
    
    public static List<String> top5Prod() {
        List<Review> reviews = new ArrayList<Review>();
        List<String> returnVal = new ArrayList<String>();
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("reviewDatabase");
            
            DBCollection collection = db.getCollection("productReview");
            BasicDBObject query = new BasicDBObject();
            
            DBObject groupFieldsInId = new BasicDBObject();
            groupFieldsInId.put( "ProductModelName", "$ProductModelName");
            DBObject groupFields = new BasicDBObject( "_id", groupFieldsInId);
            groupFields.put("count", new BasicDBObject( "$sum", 1));
            DBObject group = new BasicDBObject("$group", groupFields );
            DBObject limit=new BasicDBObject("$limit",5);
            AggregationOutput output = collection.aggregate(group,limit);
            
            // DBObject group =new BasicDBObject("$group",new BasicDBObject("_id","$ProductName").append("countProduct", new BasicDBObject("$sum",1)));
            
            // DBObject limit =new BasicDBObject("$limit",5);    
            // DBObject sort =new BasicDBObject("$sort",new BasicDBObject("countProduct",-1));
            
            // AggregationOutput output = collection.aggregate(group,sort,limit);
            
            for (DBObject result : output.results()) {
                DBObject result1 = (DBObject) result.get("_id");
                // System.out.println("Result: "+result.get("_id"));
                // System.out.println("Result: "+result.get("countProduct"));
                // BasicDBObject bobj = (BasicDBObject) result; 
                // Review review = new Review();
                // review.setRetailerZip((String)bobj.get("ProductName"));
                // reviews.add(review);
                returnVal.add((String)result1.get("ProductModelName")+" : "+result.get("count").toString());
            }
            
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return returnVal;
        
    }
    
    
    
    
}
