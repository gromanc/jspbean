package jspbean.struts.modelDriven;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A model class to be used by the simple Model-Driven example.
 */
@Component
public class Gangster2 implements Serializable {

	private static final long serialVersionUID = 3688389475320294992L;

	private String name;
	private int age;
	private String description;
	private boolean bustedBefore;

  private Set<Gangster> gangsterSet = new HashSet<>();

  public Set<Gangster> getGangsterSet() {
    return gangsterSet;
  }

  public void setGangsterSet(Set<Gangster> gangsterSet) {
    this.gangsterSet = gangsterSet;
  }

  public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isBustedBefore() {
		return bustedBefore;
	}

	public void setBustedBefore(boolean bustedBefore) {
		this.bustedBefore = bustedBefore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
