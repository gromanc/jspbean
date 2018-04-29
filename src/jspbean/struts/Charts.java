package jspbean.struts;

import com.opensymphony.xwork2.ActionSupport;
import jspbean.model.ListValue;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Charts extends ActionSupport {

  private static final long serialVersionUID = 4851863957798371834L;

  private List<Point> points;
  private List<Point> pointsWithNull;
  private List<ListValue> objList;
  private Map<Integer, Integer> pointsFromMap;
  private Map<Date, Integer> dateFromMap;
  private String minTime;
  private String maxTime;


  @Actions({@Action(value = "charts", results = {@Result(location = "/pages/chart.jsp", name = "success")}),
      @Action(value = "jsonchartdata", results = {@Result(location = "/pages/chart.jsp", name = "success")})})
  public String execute() throws Exception {
    points = new LinkedList<>();

    points.add(new Point(0, 3));
    points.add(new Point(4, 8));
    points.add(new Point(8, 5));
    points.add(new Point(9, 13));

    pointsWithNull = new LinkedList<Point>();

    pointsWithNull.add(new Point(0, 12));
    pointsWithNull.add(new Point(7, 12));
    pointsWithNull.add(null);
    pointsWithNull.add(new Point(7, 2));
    pointsWithNull.add(new Point(12, 2));

    pointsFromMap = new HashMap<Integer, Integer>();
    pointsFromMap.put(2, 5);
    pointsFromMap.put(3, 6);
    pointsFromMap.put(4, 7);
    pointsFromMap.put(5, 8);
    pointsFromMap.put(6, 7);
    pointsFromMap.put(7, 6);

    dateFromMap = new TreeMap<Date, Integer>();
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.YEAR, -2);

    minTime = "" + calendar.getTime().getTime();
    System.out.println("minTime : " + minTime);

    Random generator = new Random();
    for (int i = 1; i <= 24; i++) {
      dateFromMap.put(calendar.getTime(), generator.nextInt(100));
      calendar.add(Calendar.MONTH, +1);
    }
    maxTime = "" + calendar.getTime().getTime();
    System.out.println("maxTime : " + maxTime);

    objList = new ArrayList<ListValue>();
    for (int i = 1; i <= 24; i++) {
      objList.add(new ListValue("" + i, "" + generator.nextInt(30)));
    }

    return SUCCESS;

  }

  public List getPoints() {
    return points;
  }

  public List getPointsWithNull() {
    return pointsWithNull;
  }

  public Map getPointsFromMap() {
    return pointsFromMap;
  }

  public Map getDateFromMap() {
    return dateFromMap;
  }

  public String getMinTime() {
    return minTime;
  }

  public String getMaxTime() {
    return maxTime;
  }

  public List getObjList() {
    return objList;
  }
}