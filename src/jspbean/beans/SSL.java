package jspbean.beans;

import java.util.Properties;

public class SSL {
	String prop = "ssl prop";

	public String getProp() {
		return prop;
	}

  public void setProp(String prop) {
 		this.prop = prop;
 //		main(null);
 	}
	public static void main(String[] args) {
		Properties props = new Properties();
		System.out.println("Called!!");
	}


}
