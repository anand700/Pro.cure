import java.util.*;
import java.io.*;
public class GameSpeedSerializedDataStore{


// The known platforms

	public static HashMap<String, Console> microsoft = new HashMap<String, Console>();
	public static HashMap<String, Console> sony = new HashMap<String, Console>();
	public static HashMap<String, Console> nintendo = new HashMap<String, Console>();
	
	public static String string_microsoft = "Microsoft";
	public static String string_sony = "Sony";
	public static String string_nintendo = "Nintendo";




// polulate the data for the different platforms into HashMaps

    public static void populateSerializedDataStore(){
 
	populateMicrosoftPlatform();
	populateSonyPlatfom();
	populateNintendoPlatfom();

        testDrive();

    }



// Polulate the data for Microsoft into HashMap

static void populateMicrosoftPlatform(){

			HashMap<String, Accessory> accessories;

			Accessory xboxone_wc = new Accessory("Controller", 40.00, "XBOX controller.jpg", "Microsoft","New",10);
			Accessory xboxone_hs = new Accessory("Turtle Beach Headset", 51.00, "Turtle Beach Headset.jpg", "Microsoft","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("xboxone_wc", xboxone_wc);
			accessories.put("xboxone_hs", xboxone_hs);			
			Console xboxone = new Console("XBox One",399.00,"xbox1.jpg","Microsoft","New",10,accessories);
			microsoft.put("xboxone", xboxone);
			
			Accessory xbox360_sw = new Accessory("Speeding Wheel", 42.00, "XBOX360-SpeedWheel.jpg", "Microsoft","New",10);
			Accessory xbox360_wa = new Accessory("Wireless Adapter", 53.00, "xbox360_wa.png", "Microsoft","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("xbox360_sw", xbox360_sw);
			accessories.put("xbox360_wa", xbox360_wa);
			Console xbox360 = new Console("XBox 360",299.00,"xbox360.jpg","Microsoft","New",10,accessories);			
			microsoft.put("xbox360", xbox360);
}


// Polulate the data for Sony into HashMap

static void populateSonyPlatfom(){}


// Polulate the data for Nintendo into HashMap

static void  populateNintendoPlatfom(){}



// Write the HashMaps into the File GameSpeedDataStore

static void  writeGameSpeedDataStore(){

    try{
    File gameSpeedDataStore=new File("GameSpeedDataStore");
    FileOutputStream fos=new FileOutputStream(gameSpeedDataStore);
        ObjectOutputStream oos=new ObjectOutputStream(fos);

	
        oos.writeObject(microsoft);
        oos.flush();
        oos.close();
        fos.close();
	
    }catch(Exception e){
		System.out.println("Could NOT Write microsoft to GameSpeedDataStore ...");
	}

}


// Read the HashMaps from the File GameSpeedDataStore

static void readGameSpeedDataStore() {

   
    try{
        File gameSpeedDataStore=new File("GameSpeedDataStore");
        FileInputStream fis=new FileInputStream(gameSpeedDataStore);
        ObjectInputStream ois=new ObjectInputStream(fis);

        HashMap<String,Console> mapInFile=(HashMap<String,Console>)ois.readObject();

        ois.close();
        fis.close();
        
        for(Map.Entry<String,Console> m :mapInFile.entrySet()){
            	System.out.println(m.getKey());
		Console c = m.getValue();
		System.out.println("\t Name : "+c.getName());
		System.out.println("\t Price : "+c.getPrice());
		System.out.println("\t Accessories : ");
		HashMap<String,Accessory> accessories = c.getAccessories();
		for(Map.Entry<String,Accessory> ma :accessories.entrySet()){
			System.out.println("\t\t\t" + ma.getKey());
			Accessory a = ma.getValue();
			System.out.println("\t\t\t\t Name : "+a.getName());
			System.out.println("\t\t\t\t Price : "+a.getPrice());
		}

        }
    }catch(Exception e){}

}



// the testDrive method for SANITY tesing

public static void testDrive(){


	writeGameSpeedDataStore();
	readGameSpeedDataStore();

}



}