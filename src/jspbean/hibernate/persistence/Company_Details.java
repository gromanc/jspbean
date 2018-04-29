package jspbean.hibernate.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Company_Details implements Serializable {

/**
 *
 */
private static final long serialVersionUID = 3336251433829975771L;


@Id
@GeneratedValue
private Integer Id;
private String Company;
private String Address_Line_1;
private String Address_Line_2;
private String Postcode;
private String County;
private String Country;
private String Mobile_Number;
private String Telephone_Number;
private String URL;
private String Contact;
private String Logo_URL;
private double Amount_Overall;
private double Amount_Paid;
private Timestamp Date_Joined;
private int Account_Details;
@OneToMany(fetch = FetchType.EAGER, mappedBy = "Company_Id")
private List<Product_Details> products;

public Integer getId() {
    return Id;
}

public void setId(Integer id) {
    Id = id;
}

public String getCompany() {
    return Company;
}

public void setCompany(String company) {
    Company = company;
}

public String getAddress_Line_1() {
    return Address_Line_1;
}

public void setAddress_Line_1(String address_Line_1) {
    Address_Line_1 = address_Line_1;
}

public String getAddress_Line_2() {
    return Address_Line_2;
}

public void setAddress_Line_2(String address_Line_2) {
    Address_Line_2 = address_Line_2;
}

public String getPostcode() {
    return Postcode;
}

public void setPostcode(String postcode) {
    Postcode = postcode;
}

public String getCounty() {
    return County;
}

public void setCounty(String county) {
    County = county;
}

public String getCountry() {
    return Country;
}

public void setCountry(String country) {
    Country = country;
}

public String getMobile_Number() {
    return Mobile_Number;
}

public void setMobile_Number(String mobile_Number) {
    Mobile_Number = mobile_Number;
}

public String getTelephone_Number() {
    return Telephone_Number;
}

public void setTelephone_Number(String telephone_Number) {
    Telephone_Number = telephone_Number;
}

public String getURL() {
    return URL;
}

public void setURL(String uRL) {
    URL = uRL;
}

public String getContact() {
    return Contact;
}

public void setContact(String contact) {
    Contact = contact;
}

public String getLogo_URL() {
    return Logo_URL;
}

public void setLogo_URL(String logo_URL) {
    Logo_URL = logo_URL;
}

public double getAmount_Overall() {
    return Amount_Overall;
}

public void setAmount_Overall(double amount_Overall) {
    Amount_Overall = amount_Overall;
}

public double getAmount_Paid() {
    return Amount_Paid;
}

public void setAmount_Paid(double amount_Paid) {
    Amount_Paid = amount_Paid;
}

public Timestamp getDate_Joined() {
    return Date_Joined;
}

public void setDate_Joined(Timestamp date_Joined) {
    Date_Joined = date_Joined;
}

public int getAccount_Details() {
    return Account_Details;
}

public void setAccount_Details(int account_Details) {
    Account_Details = account_Details;
}

public List<Product_Details> getProducts() {
    return products;
}

public void setProducts(List<Product_Details> products) {
    this.products = products;
}
}