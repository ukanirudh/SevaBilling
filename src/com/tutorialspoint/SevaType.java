package com.tutorialspoint;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "sevatype")
public class SevaType  implements Serializable {
	private static final long serialVersionUID = 1L;
	   private Integer id;
	   private String sevaName;
	   private String sevaLabel;
	public Integer getId() {
		return id;
	}
	@XmlElement
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSevaName() {
		return sevaName;
	}
	public void setSevaName(String sevaName) {
		this.sevaName = sevaName;
	}
	public String getSevaLabel() {
		return sevaLabel;
	}
	public void setSevaLabel(String sevaLabel) {
		this.sevaLabel = sevaLabel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	   
	 public SevaType(){}

	   public SevaType(int id, String sevaName,String sevaLabel){
	      this.id = id;
	      this.sevaName = sevaName;
	      this.sevaLabel = sevaLabel;
	      
	   }   
}
