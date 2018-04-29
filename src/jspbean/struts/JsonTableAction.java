/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package jspbean.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.annotations.After;
import jspbean.hibernate.persistence.User;
import jspbean.hibernate.persistence.UserDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.math.BigDecimal;
import java.util.List;


@Action(value = "jsonTable", results = {
  @Result(type = "json", params = {"includeProperties", "gridModel\\[\\d+\\]\\.id, gridModel\\[\\d+\\]\\.name, total, records, rows, page, sidx, searchField, searchString",  "excludeNullProperties", "true"})},
  interceptorRefs = {
    @InterceptorRef("params"), @InterceptorRef("annotationWorkflow")
  })
public class JsonTableAction extends ActionSupport {

  private static final long serialVersionUID = 5078264277068533593L;
  private static final Log log              = LogFactory.getLog(JsonTableAction.class);

  // Your result List
  private List<User>   gridModel;

  // get how many rows we want to have into the grid - rowNum attribute in the
  // grid
  private Integer           rows             = 5;

  // Get the requested page. By default grid sets this to 1.
  private Integer           page             = 1;

  // sorting order - asc or desc
  private String            sord             = "asc";

  // get index row - i.e. user click to sort.
  private String            sidx;

  // Search Field
  private String            searchField;

  // The Search String
  private String            searchString;

  // he Search Operation
  // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
  private String            searchOper;

  // Your Total Pages
  private Integer           total            = 0;

  // All Records
  private Integer           records          = 0;

  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  private UserDAO userDAO;

  @Autowired
  public void setUserDAO(UserDAO UserDAO) {
    this.userDAO = UserDAO;
  }

  @After
  public void after() {
  }

  public String execute()
  {
    log.debug("Page: " + getPage() + ", Rows: " + getRows() + ", Sorting Order: " + getSord() + ", Index Row: " + getSidx());
    log.debug("Search: " + searchField + " " + searchOper + " " + searchString);

    // Calcalate until rows ware selected
    int to = (rows * page);

    // Calculate the first row to read
    int from = to - rows;

    // Criteria to Build SQL
    DetachedCriteria criteria = DetachedCriteria.forClass(User.class);

    // Handle Search
    if (searchField != null)
    {
      if (searchField.equals("id"))
      {
        Integer searchValue = Integer.parseInt(searchString);
        if (searchOper.equals("eq")) criteria.add(Restrictions.eq("id", searchValue));
        else if (searchOper.equals("ne")) criteria.add(Restrictions.ne("id", searchValue));
        else if (searchOper.equals("lt")) criteria.add(Restrictions.lt("id", searchValue));
        else if (searchOper.equals("gt")) criteria.add(Restrictions.gt("id", searchValue));
      }
      else if (searchField.equals("name") )
      {
        if (searchOper.equals("eq")) criteria.add(Restrictions.eq(searchField, searchString));
        else if (searchOper.equals("ne")) criteria.add(Restrictions.ne(searchField, searchString));
        else if (searchOper.equals("bw")) criteria.add(Restrictions.like(searchField, searchString + "%"));
        else if (searchOper.equals("cn")) criteria.add(Restrictions.like(searchField, "%" + searchString + "%"));
      }
    }

    // Count Customers
    records = userDAO.countByCriteria(criteria);

    // Reset count Projection
    criteria.setProjection(null);
    criteria.setResultTransformer(Criteria.ROOT_ENTITY);

    // Handle Order By
    if (sidx != null && !sidx.equals(""))
    {
        if (sord.equals("asc")) criteria.addOrder(Order.asc(sidx));
        else criteria.addOrder(Order.desc(sidx));
    }

    // Get Users by Criteria
    gridModel = userDAO.findByCriteria(criteria, from, rows);

    // Set to = max rows
    if (to > records) to = records;

    // Calculate total Pages
//    total = (int) Math.ceil((double) records / (double) rows);
    total=new BigDecimal(records).divide(new BigDecimal(rows), 0, BigDecimal.ROUND_CEILING).intValue();

    return SUCCESS;
  }

  private User entity=new User();

  public User getEntity() {
    return entity;
  }

  public void setEntity(User entity) {
    this.entity = entity;
  }

  private String oper;

  public String getOper() {
    return oper;
  }

  public void setOper(String oper) {
    this.oper = oper;
  }

  @Action(value = "editTable", results = {
    @Result(type = "json", params = {"includeProperties", "gridModel\\[\\d+\\]\\.id, gridModel\\[\\d+\\]\\.name, total, records, rows, page, sidx, searchField, searchString",  "excludeNullProperties", "true"})},
    interceptorRefs = {
      @InterceptorRef("params")
    })
  public String edit() throws Exception {
    System.out.println(entity.getId()+" : "+entity.getName()+" : "+" : "+oper);

    if(oper.equalsIgnoreCase("add")) {
      //Adding a row.
    }
    else if(oper.equalsIgnoreCase("edit")) {
      //Editing/updating a row.
    }
    else if(oper.equalsIgnoreCase("del")) {
      //Deleting a row.
    }
    return SUCCESS;
  }


  public String getJSON()
  {
    return execute();
  }

  /**
   * @return how many rows we want to have into the grid
   */
  public Integer getRows()
  {
    return rows;
  }

  /**
   * @param rows
   *          how many rows we want to have into the grid
   */
  public void setRows(Integer rows)
  {
    this.rows = rows;
  }

  /**
   * @return current page of the query
   */
  public Integer getPage()
  {
    return page;
  }

  /**
   * @param page
   *          current page of the query
   */
  public void setPage(Integer page)
  {
    this.page = page;
  }

  /**
   * @return total pages for the query
   */
  public Integer getTotal()
  {
    return total;
  }

  /**
   * @param total
   *          total pages for the query
   */
  public void setTotal(Integer total)
  {
    this.total = total;
  }

  /**
   * @return total number of records for the query. e.g. select count(*) from
   *         table
   */
  public Integer getRecords()
  {
    return records;
  }

  /**
   * @param records
   *          total number of records for the query. e.g. select count(*) from
   *          table
   */
  public void setRecords(Integer records)
  {

    this.records = records;

//    if (this.records > 0 && this.rows > 0)
//    {
//      this.total = (int) Math.ceil((double) this.records / (double) this.rows);
//    }
//    else
//    {
//      this.total = 0;
//    }
  }

  /**
   * @return an collection that contains the actual data
   */
  public List<User> getGridModel()
  {
    return gridModel;
  }

  /**
   * @return sorting order
   */
  public String getSord()
  {
    return sord;
  }

  /**
   * @param sord
   *          sorting order
   */
  public void setSord(String sord)
  {
    this.sord = sord;
  }

  /**
   * @return get index row - i.e. user click to sort.
   */
  public String getSidx()
  {
    return sidx;
  }

  /**
   * @param sidx
   *          get index row - i.e. user click to sort.
   */
  public void setSidx(String sidx)
  {
    this.sidx = sidx;
  }

  public void setSearchField(String searchField)
  {
    this.searchField = searchField;
  }

  public void setSearchString(String searchString)
  {
    this.searchString = searchString;
  }

  public void setSearchOper(String searchOper)
  {
    this.searchOper = searchOper;
  }
}
