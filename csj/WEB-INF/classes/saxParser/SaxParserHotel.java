package saxParser;

import java.io.IOException;
import java.text.ParseException;
import java.io.*;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************
    
    SAX parser use callback function  to notify client object of the XML document structure.
        You should extend DefaultHandler and override the method when parsin the XML document
            
            ***************/

////////////////////////////////////////////////////////////

public class SaxParserHotel extends DefaultHandler implements Serializable{
    static public Room room;
    static public List<Room> rooms;
    static public String roomXmlFileName;
    static public String elementValueRead;
    static public Date dt = new Date();
    static public Calendar c = Calendar.getInstance();
    
    
    public SaxParserHotel(String roomXmlFileName) {
        this.roomXmlFileName = roomXmlFileName;
        rooms = new ArrayList<Room>();
        parseDocument();
        prettyPrint();
    }
    
    
    private void parseDocument() {
        System.out.println("ParserDoc>>");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(roomXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
}
}
    
    
    private void prettyPrint() {
        
    //     for (Room room: rooms) {
    //         // System.out.println("Room #"+ room.id +":");
    // }
    }
    
    public List<Room> getRooms() {
        return rooms;
}
    
    public static HashMap<String,List<Room>> getElectronicRoomsByParsing() {
        HashMap<String,List<Room>> hm = new HashMap<String,List<Room>>();
        System.out.println("SaxParser>>sinside");
        try {
            hm.put("Rooms", new SaxParserHotel("/Applications/tomcat/webapps/csj/WEB-INF/classes/saxParser/HotelCatalog.xml").getRooms());
            System.out.println("SaxParser>>hm:"+hm.size());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //System.out.println("SaxParser>>sax:",sax);
        
        return hm;

}
    
    
    ////////////////////////////////////////////////////////////
    
    /*************
        
        There are a number of methods to override in SAX handler  when parsing your XML document :
            
            Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document.
                Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.
                    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.
                        
                        
                        There are few other methods that you could use for notification for different purposes, check the API at the following URL:
                            
                            https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html
                                
                                ***************/
                                    
                                    ////////////////////////////////////////////////////////////
                                        
                                        @Override
                                            public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
                                                
                                                if (elementName.equalsIgnoreCase("room")) {
                                                    room = new Room();
                                                        room.setRoomID(Integer.parseInt(attributes.getValue("id")));
                                                            }
                                                                
                                                                }
                                                                    
                                                                    @Override
                                                                        public void endElement(String str1, String str2, String element) throws SAXException {
                                                                            
                                                                            if (element.equals("room")) {
                                                                                rooms.add(room);
                                                                                    return;
                                                                                    }
                                                                            if (element.equalsIgnoreCase("image")) {
                                                                                room.setImageURL(elementValueRead);
                                                                                    return;
                                                                                    }
                                                                            if(element.equalsIgnoreCase("price")){
                                                                                room.setPrice(Integer.parseInt(elementValueRead));
                                                                                    return;
                                                                                    }
                                                                            if(element.equalsIgnoreCase("quantity")){
                                                                                room.setRoomQuantity(Integer.parseInt(elementValueRead));
                                                                                    return;
                                                                                    }
                                                                            if(element.equalsIgnoreCase("roomtype")){
                                                                                room.setRoomType(elementValueRead);
                                                                                    return;
                                                                                    }
                                                                            if(element.equalsIgnoreCase("roomdescription")){
                                                                                room.setRoomDescription(elementValueRead);
                                                                                    return;
                                                                                    }
                                                                            
                                                                            
                                                                                    }
                                                                                        
                                                                                        @Override
                                                                                            public void characters(char[] content, int begin, int end) throws SAXException {
                                                                                                elementValueRead = new String(content, begin, end);
                                                                                                }
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    
                                                                                                    /////////////////////////////////////////
                                                                                                        //       Kick-Start SAX in main       //
                                                                                                            ////////////////////////////////////////
                        
                            public static void main(String[] args) {
                                new SaxParserHotel("HotelCatalog.xml");
                                    
                                }

}
