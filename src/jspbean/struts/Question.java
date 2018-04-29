package jspbean.struts;

public class Question {
  private Long id;
  private String name;
  private String ans1;
  private String ans2;
  private String ans3;

  public Question(){
    System.out.println("Question <>");
  }

  public Question(Long id, String name, String ans1, String ans2, String ans3) {
    System.out.println("Question <"+id+", "+name+", "+ans1+", "+ans2+", "+ans3+">");
    this.id = id;
    this.name = name;
    this.ans1 = ans1;
    this.ans2 = ans2;
    this.ans3 = ans3;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAns1() {
    return ans1;
  }

  public void setAns1(String ans1) {
    this.ans1 = ans1;
  }

  public String getAns2() {
    return ans2;
  }

  public void setAns2(String ans2) {
    this.ans2 = ans2;
  }

  public String getAns3() {
    return ans3;
  }

  public void setAns3(String ans3) {
    this.ans3 = ans3;
  }
}
