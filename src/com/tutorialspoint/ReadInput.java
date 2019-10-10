package com.tutorialspoint;

import java.sql.Timestamp;

import javax.json.bind.annotation.JsonbProperty;

public class ReadInput {
	
	 
	    private String devoteeName; 
	    private int cost;
	    private String contactNum;
	    private String gotra;
	    private String sevaName;
	    private String nakshatra;
	    private String sevaDate;
	    private String paymentDate;
	    
	    
	    
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
		public String getDevoteeName() {
			return devoteeName;
		}
		public void setDevoteeName(String devoteeName) {
			this.devoteeName = devoteeName;
		}
		public int getCost() {
			return cost;
		}
		public void setCost(int cost) {
			this.cost = cost;
		}
		public String getGotra() {
			return gotra;
		}
		public void setGotra(String gotra) {
			this.gotra = gotra;
		}
		public String getSevaName() {
			return sevaName;
		}
		public void setSevaName(String sevaName) {
			this.sevaName = sevaName;
		}
		public String getNakshatra() {
			return nakshatra;
		}
		public void setNakshatra(String nakshatra) {
			this.nakshatra = nakshatra;
		}
	     
	   
}
