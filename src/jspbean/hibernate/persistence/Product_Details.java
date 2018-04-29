package jspbean.hibernate.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
   public class Product_Details implements Serializable {

/**
 *
 */
private static final long serialVersionUID = 6477618197240654478L;

@Id
@GeneratedValue
private Integer Id;
private Integer Company_Id;
private String Product_Name;
private String Description;
private String Main_Photo_URL;
private String Other_Photo_URLS;
private Integer Total_Bookings;
private double Average_Rating;
private Integer Number_Of_Slots;
private Integer Time_Per_Slot;
private double Price_Per_Slot;
private String Monday_Open;
private String Tuesday_Open;
private String Wednesday_Open;
private String Thursday_Open;
private String Friday_Open;
private String Saturday_Open;
private String Sunday_Open;
private String Dates_Closed;

public Integer getId() {
    return Id;
}
public void setId(Integer id) {
    Id = id;
}
public Integer getCompany_Id() {
    return Company_Id;
}
public void setCompany_Id(Integer company_Id) {
    Company_Id = company_Id;
}
public String getProduct_Name() {
    return Product_Name;
}
public void setProduct_Name(String product_Name) {
    Product_Name = product_Name;
}
public String getDescription() {
    return Description;
}
public void setDescription(String description) {
    Description = description;
}
public String getMain_Photo_URL() {
    return Main_Photo_URL;
}
public void setMain_Photo_URL(String main_Photo_URL) {
    Main_Photo_URL = main_Photo_URL;
}
public String getOther_Photos_URLS() {
    return Other_Photo_URLS;
}
public void setOther_Photos_URLS(String other_Photo_URLS) {
    Other_Photo_URLS = other_Photo_URLS;
}
public Integer getTotal_Bookings() {
    return Total_Bookings;
}
public void setTotal_Bookings(Integer total_Bookings) {
    Total_Bookings = total_Bookings;
}
public double getAverage_Rating() {
    return Average_Rating;
}
public void setAverage_Rating(double average_Rating) {
    Average_Rating = average_Rating;
}
public Integer getNumber_Of_Slots() {
    return Number_Of_Slots;
}
public void setNumber_Of_Slots(Integer number_Of_Slots) {
    Number_Of_Slots = number_Of_Slots;
}
public Integer getTime_Per_Slot() {
    return Time_Per_Slot;
}
public void setTime_Per_Slot(Integer time_Per_Slot) {
    Time_Per_Slot = time_Per_Slot;
}
public double getPrice_Per_Slot() {
    return Price_Per_Slot;
}
public void setPrice_Per_Slot(double price_Per_Slot) {
    Price_Per_Slot = price_Per_Slot;
}
public String getMonday_Open() {
    return Monday_Open;
}
public void setMonday_Open(String monday_Open) {
    Monday_Open = monday_Open;
}
public String getTuesday_Open() {
    return Tuesday_Open;
}
public void setTuesday_Open(String tuesday_Open) {
    Tuesday_Open = tuesday_Open;
}
public String getWednesday_Open() {
    return Wednesday_Open;
}
public void setWednesday_Open(String wednesday_Open) {
    Wednesday_Open = wednesday_Open;
}
public String getThursday_Open() {
    return Thursday_Open;
}
public void setThursday_Open(String thursday_Open) {
    Thursday_Open = thursday_Open;
}
public String getFriday_Open() {
    return Friday_Open;
}
public void setFriday_Open(String friday_Open) {
    Friday_Open = friday_Open;
}
public String getSaturday_Open() {
    return Saturday_Open;
}
public void setSaturday_Open(String saturday_Open) {
    Saturday_Open = saturday_Open;
}
public String getSunday_Open() {
    return Sunday_Open;
}
public void setSunday_Open(String sunday_Open) {
    Sunday_Open = sunday_Open;
}
public String getDates_Closed() {
    return Dates_Closed;
}
public void setDates_Closed(String dates_Closed) {
    Dates_Closed = dates_Closed;
}
}