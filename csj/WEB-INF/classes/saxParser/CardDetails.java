package saxParser;


import java.io.Serializable;
import java.util.*;
import java.math.BigInteger;


public class CardDetails implements Serializable{
    public BigInteger cardNumber;
    public int cvv;
    public String cardType;
    public int expYear;
    public int expMonth;
    public String nameOnCard;
    public String ba;
    public String extraInfo;
    
    
public void setCardNumber(BigInteger cardNumber) { this.cardNumber = cardNumber; } 
public BigInteger getCardNumber() { return this.cardNumber; }
public void setCvv(int cvv) { this.cvv = cvv; } 
public int getCvv() { return this.cvv; }
public void setCardType(String cardType) { this.cardType = cardType; } 
public String getCardType() { return this.cardType; }
public void setExpYear(int expYear) { this.expYear = expYear; } 
public int getExpYear() { return this.expYear; }
public void setExpMonth(int expMonth) { this.expMonth = expMonth; } 
public int getExpMonth() { return this.expMonth; }
public void setNameOnCard(String nameOnCard) { this.nameOnCard = nameOnCard; } 
public String getNameOnCard() { return this.nameOnCard; }
public void setBa(String ba) { this.ba = ba; } 
public String getBa() { return this.ba; }
public void setExtraInfo(String extraInfo) { this.extraInfo = extraInfo; } 
public String getExtraInfo() { return this.extraInfo; }
    
}
