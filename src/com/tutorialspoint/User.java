package com.tutorialspoint;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "user")
public class User implements Serializable {

   private static final long serialVersionUID = 1L;
   private int id;
   private int cost;
   private String gotra;
   private String nakshatra;
   private String sevaDate;
   private String paymentDate;
   private String contactNum;
   private String devoteeName;
   
   
   
   public String getContactNum() {
	return contactNum;
}
public void setContactNum(String contactNum) {
	this.contactNum = contactNum;
}
public String getSevaDate() {
	return sevaDate;
}
public void setSevaDate(String sevaDate) {
	this.sevaDate = sevaDate;
}
public String getPaymentDate() {
	return paymentDate;
}
public void setPaymentDate(String paymentDate) {
	this.paymentDate = paymentDate;
}
public String getNakshatra() {
	return nakshatra;
}
public void setNakshatra(String nakshatra) {
	this.nakshatra = nakshatra;
}
public int getCost() {
	return cost;
}
   @XmlElement
public void setCost(int cost) {
	this.cost = cost;
}
   @XmlElement
public String getDevoteeName() {
	return devoteeName;
}
   @XmlElement
public void setDevoteeName(String devoteeName) {
	this.devoteeName = devoteeName;
}
   @XmlElement
public String getGotra() {
	return gotra;
}
   @XmlElement
public void setGotra(String gotra) {
	this.gotra = gotra;
}

public String getSevaName() {
	return sevaName;
}

public void setSevaName(String savaname) {
	this.sevaName = savaname;
}

private String sevaName;

   public User(){}

   public User(int id, String devoteeName,int cost,String nakshatra, String gotra, String savaname,String sevaDate, String paymentDate,String contactNum){
      this.id = id;
      this.devoteeName = devoteeName;
      this.gotra = gotra;
      this.sevaName = savaname;
      this.nakshatra = nakshatra;
      this.cost = cost;
      this.sevaDate = sevaDate;
      this.paymentDate = paymentDate;
      this.contactNum = contactNum;
   }

   public int getId() {
      return id;
   }
   @XmlElement
   public void setId(int id) {
      this.id = id;
   }

 

   @Override
   public boolean equals(Object object){
      if(object == null){
         return false;
      }else if(!(object instanceof User)){
         return false;
      }else {
         User user = (User)object;
         if(id == user.getId()
            && gotra.equals(user.getDevoteeName())
            && gotra.equals(user.getGotra())
         ){
            return true;
         }			
      }
      return false;
   }	
}