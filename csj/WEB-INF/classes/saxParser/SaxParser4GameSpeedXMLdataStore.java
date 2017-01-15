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

public class SaxParser4GameSpeedXMLdataStore extends DefaultHandler implements Serializable{
    static public Product product;
    static public List<Product> products;
    static public String productXmlFileName;
    static public String elementValueRead;
    static public Date dt = new Date();
    static public Calendar c = Calendar.getInstance();
    
    
    public SaxParser4GameSpeedXMLdataStore(String productXmlFileName) {
        this.productXmlFileName = productXmlFileName;
        products = new ArrayList<Product>();
        parseDocument();
        prettyPrint();
    }
    
    
    private void parseDocument() {
        System.out.println("ParserDoc>>");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(productXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
    
    
    private void prettyPrint() {
        
        for (Product product: products) {
            System.out.println("product #"+ product.id +":");
            System.out.println("\t\t retailer: " + product.retailer);
            System.out.println("\t\t name: " + product.name);
            for (String accessory: product.accessories) {
                System.out.println("\t\t accessory: " + accessory);
            }
        }
    }
    
    public List<Product> getProducts() {
        return products;
    }
    
    public static HashMap<String,List<Product>> getElectronicProductsByParsing() {
        HashMap<String,List<Product>> hm = new HashMap<String,List<Product>>();
        System.out.println("SaxParser>>sinside");
        try {
            hm.put("Electronic Products", new SaxParser4GameSpeedXMLdataStore("/Applications/tomcat/webapps/csj/WEB-INF/classes/saxParser/ProductCatalog.xml").getProducts());
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
        
        if (elementName.equalsIgnoreCase("product")) {
            product = new Product();
            product.setId(attributes.getValue("id"));
            product.setRetailer(attributes.getValue("retailer"));
        }
        
    }
    
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
        
        if (element.equals("product")) {
            products.add(product);
            return;
        }
        if (element.equalsIgnoreCase("image")) {
            product.setImage(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("name")) {
            product.setName(elementValueRead);
            return;
        }
        if(element.equalsIgnoreCase("accessory")){
            product.getAccessories().add(elementValueRead);
            return;
        }
        if(element.equalsIgnoreCase("price")){
            product.setPrice(Integer.parseInt(elementValueRead));
            return;
        }
        if(element.equalsIgnoreCase("quantity")){
            product.setQuantity(Integer.parseInt(elementValueRead));
            return;
        }
        if(element.equalsIgnoreCase("username")){
            product.setUsername(elementValueRead);
            return;
        }
        if(element.equalsIgnoreCase("orderstatus")){
            product.setOrderStatus(elementValueRead);
            return;
        }
        if(element.equalsIgnoreCase("expectedDelivery")){
            product.setExpectedDelivery(elementValueRead);
            return;
        }
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        if(element.equalsIgnoreCase("date")){
            product.setDate(dt);
            return;
        }
        if(element.equalsIgnoreCase("total")){
            product.setTotal(Integer.parseInt(elementValueRead));
            return;
        }
        if(element.equalsIgnoreCase("category")){
            product.setCategory(elementValueRead);
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
        new SaxParser4GameSpeedXMLdataStore("ProductCatalog.xml");
        
    }
    
}
