package com.tutorialspoint;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Expenses")
public class Expenses implements Serializable {
	private static final long serialVersionUID = 1L;
	   private Integer id;
	   private int amount;
	   private String description;
	   private String paidTo;
	   private String paymentDate;
	public Integer getId() {
		return id;
	}
	@XmlElement
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPaidTo() {
		return paidTo;
	}
	public void setPaidTo(String paidTo) {
		this.paidTo = paidTo;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	   
	public Expenses(){}

	   public Expenses(int id,int amount,String description, String paidTo, String paymentDate){
	      this.id = id;
	      this.description = description;
	      this.paidTo = paidTo;
	      this.amount = amount;
	      this.paymentDate = paymentDate;
	   }   
	   

}
