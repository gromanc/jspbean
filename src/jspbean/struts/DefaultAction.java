package jspbean.struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.config.providers.XmlConfigurationProvider;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.ConversionRule;
import com.opensymphony.xwork2.conversion.annotations.ConversionType;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.inject.Scope;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.ognl.OgnlUtil;
import com.opensymphony.xwork2.util.*;
import com.opensymphony.xwork2.validator.annotations.*;
import jspbean.beans.SomeBean;
import jspbean.hibernate.persistence.User;
import ognl.Ognl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.mapper.ActionMapper;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.TokenHelper;
import org.apache.struts2.views.util.UrlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.Callable;

@Namespace("/")
@Results({
  @Result(name="back", type="redirect", location = "/"),
  @Result(name="login", location = "/pages/ajaxlogin.jsp")
})
//@Actions({
//  @Action(value="", results={
//    @Result(name="input", location = "/default.jsp"),
//    //  @Result(type="redirect", location = "/default.jsp", params = {"inputFr", "${content}"})
//    @Result(location = "/default.jsp", params = {"customParam","value1"})
//  }
//  )
//})

@Conversion()
//@Conversion(
//conversions = {
     // key must be the name of a property for which converter should be used
//     @TypeConversion(key = "map", converter = "com.opensymphony.xwork2.conversion.impl.CollectionConverter")
//     @TypeConversion(key = "mySet", converter = "com.opensymphony.xwork2.conversion.impl.XWorkBasicConverter")
//}
//)
//@InterceptorRefs({@InterceptorRef(value="defaultStack", params={"validation.excludeMethods", "execute"})}) - this overrides method annotations
public class DefaultAction extends ActionSupport /*implements SessionAware*/ implements ParameterAware {
  private final static Log log =   LogFactory.getLog(DefaultAction.class);

  private String content = "médiévaux, principalement pour l’anglais et le français €";
  private String content2= "{ value: médiévaux, principalement pour l’anglais et le français €;}";
  private long mobileno;
  private String myScheme;
  private String phone;
  private Long id;
  private Date myDate = new Date();
  private String token;
  private String tokenName;

  private Float myFloat = new Float(3.14);
  private String email;
  private String name;
  private String par1;

  public String getPar1() {
    return par1;
  }

  public void setPar1(String par1) {
    this.par1 = par1;
  }

  private String frob12 = "Other";

  public String echo(String str){
    return str;
  }

  public String getFrob12() {
    return frob12;
  }

  public void setFrob12(String frob12) {
    this.frob12 = frob12;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRegistrationType(){
    return "The registration type value";
  }
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Float getMyFloat() {
    return myFloat;
  }

//  @TypeConversion(converter = "jspbean.struts.conversion.FloatConverter")
  public void setMyFloat(Float myFloat) {
    this.myFloat = myFloat;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getTokenName() {
    return tokenName;
  }

  public void setTokenName(String tokenName) {
    this.tokenName = tokenName;
  }

  public Date getMyDate() {
    return myDate;
  }

  public void setMyDate(Date myDate) {
    this.myDate = myDate;
  }

  public static final int NINE = 9;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public void setParameters(final Map<String, String[]> parameters) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  class MyType {
    MyType(String value) {
      this.value = value;
    }

    String value;
  }
  private MyType myVariable = new MyType("test4");

  public MyType getMyVariable() {
    return myVariable;
  }

  public void setMyVariable(MyType myVariable) {
    this.myVariable = myVariable;
  }

  public String myMethod(String value){
    return value;
  }

  //  @Autowired
  @Inject
  private Session session;

  public void setSession(Session session) {
    this.session = session;
  }

  public Session getSession() {
    return session;
  }

  private ActionMapper actionMapper;

  @Inject
  public void setActionMapper(ActionMapper actionMapper) {
    this.actionMapper = actionMapper;
  }

  private UrlHelper urlHelper;

  @Inject
  public void setUrlHelper(UrlHelper urlHelper) {
    this.urlHelper = urlHelper;
  }

  //  private Map<String, Object> session;

//  @Override
//  public void setSession(Map<String, Object> session) {
//   this.session = session;
//  }

  public String getPhone() {
    return phone;
  }

  @RequiredStringValidator(type= ValidatorType.FIELD, message="Phone required.")
  @RegexFieldValidator(type= ValidatorType.FIELD, message="Invalid Phone",
    regexExpression="\\([0-9][0-9][0-9]\\)\\s[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]")
  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Element(value= Question.class)
  @Key(value = Long.class)
  @KeyProperty(value = "id")
  @CreateIfNull(value = true)
  private List<Question> myQuestions = new ArrayList<>();

  public void setMyQuestions(List<Question> myQuestions) {
    this.myQuestions = myQuestions;
  }

  public List<Question> getMyQuestions() {
    return myQuestions;
  }

  private Map<String, String> myMap = new HashMap<String, String>();

  public Map<String, String> getMyMap() {
    return myMap;
  }

  public void setMyMap(Map<String, String> myMap) {
    this.myMap = myMap;
  }

  private String myQuestionq1;
  private String myQuestionq2;

  public String getMyQuestionq1() {
    return myQuestionq1;
  }

  public void setMyQuestionq1(String myQuestionq1) {
    this.myQuestionq1 = myQuestionq1;
  }

  public String getMyQuestionq2() {
    return myQuestionq2;
  }

  public void setMyQuestionq2(String myQuestionq2) {
    this.myQuestionq2 = myQuestionq2;
  }

//  @Element(value = ArrayList.class)
//  @Key(value = ArrayList.class)
  List<List<String>> list = new ArrayList<>();

  @TypeConversion(converter = "jspbean.struts.conversion.CollectionConverter")
  public List<List<String>> getList() {
    return list;
  }

  @TypeConversion(converter = "jspbean.struts.conversion.CollectionConverter")
  public void setList(List<List<String>> list) {
    this.list = list;
  }

//  @Element(value = HashMap.class)
  private Map<String, Map<String, String>> map = new HashMap<>();

//  @TypeConversion(converter = "com.opensymphony.xwork2.conversion.impl.CollectionConverter")
  public void setMap(Map<String, Map<String, String>> map) {
    this.map = map;
  }

  public Map<String, Map<String, String>> getMap() {
    return this.map;
  }

  private Map<String, Question> map2 = new HashMap<>();

  public Map<String, Question> getMap2() {
    return map2;
  }

  public void setMap2(Map<String, Question> map2) {
    this.map2 = map2;
  }

//  @Element(value = HashMap.class)
  private Map<String, HashMap<String, Question>> map3 = new HashMap<>();

  public Map<String, HashMap<String, Question>> getMap3() {
    return map3;
  }

  public void setMap3(Map<String, HashMap<String, Question>> map3) {
    this.map3 = map3;
  }

  public String getMyScheme() {
    return myScheme;
  }

  public void setMyScheme(String myScheme) {
    this.myScheme = myScheme;
  }

  public long getMobileno() {
    return mobileno;
  }

  public void setMobileno(long mobileno) {
    this.mobileno = mobileno;
  }

  public String getContent() {
    try {
      return URLEncoder.encode(content, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setContent(String content) {
    this.content = content;
  }
//  private UserDAO userDAO;

//  public void setUserDAO(UserDAO userDAO) {
//    this.userDAO = userDAO;
//  }


  public String getContent2() {
    return content2;
  }

  public void setContent2(String content2) {
    this.content2 = content2;
  }


  public boolean isItTrue() {
    return true;
  }

  private Set<Integer> mySet = new LinkedHashSet<>();

  public Set<Integer> getMySet() {
    return mySet;
  }

  @TypeConversion(converter="com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter")
  public void setMySet(Set<Integer> mySet) {
    this.mySet = mySet;
  }

  private Double doubleValue;

  public Double getDoubleValue() {
    return doubleValue;
  }

  @TypeConversion(converter = "jspbean.struts.conversion.DoubleConverter")
  public void setDoubleValue(Double doubleValue) {
    this.doubleValue = doubleValue;
  }

  private SomeBean someBean;

  public SomeBean getSomeBean() {
    return someBean;
  }

  public void setSomeBean(SomeBean someBean) {
    this.someBean = someBean;
  }

  private  HashMap<Long, Long> categoryLogVal;

  public HashMap<Long, Long> getCategoryLogVal() {
    return categoryLogVal;
  }

  @Element(Long.class)
  @Key(Long.class)
  @TypeConversion(rule = ConversionRule.MAP, type= ConversionType.CLASS, converter = "jspbean.struts.conversion.HashMapTypeConverter")
  public void setCategoryLogVal(HashMap<Long, Long> categoryLogVal) {
    this.categoryLogVal = categoryLogVal;
  }

  public DefaultAction() {
////    session = container.getInstance(Session.class,"session");
  }

  public String input() throws Exception {
    myQuestions.add(new Question(1l, "Question1 €", "ans1", "ans2","ans3"));
    myQuestions.add(new Question(2l, "Question2", "ans1", "ans2","ans3"));
    session.getMap().put("myMap", myMap);

    HashMap<String, Question> qmap = new HashMap<>();
    qmap.put("q1", new Question(1l, "Question1 €", "ans1", "ans2","ans3"));
    qmap.put("q2", new Question(2l, "Question2", "ans1", "ans2","ans3"));
    map2 = qmap;

    myMap.put("q1", "Question1 €");
    myMap.put("q2", "Question2");

    map.put("key1", myMap);
    map3.put("key1", qmap);


    list.add(new ArrayList(Arrays.asList(new String[]{"abc", "bca"})));

    return INPUT;
  }

  @Override
  public void validate(){
    System.out.println("validate");
  }

  @Override
  @SkipValidation
  public String execute() throws Exception {
    mySet.add(0);
    someBean = new SomeBean();
    someBean.setPunchOutRes("it's a test");
    String[] centres = BeanUtils.getArrayProperty(someBean, "centres");
    String[] centres2 = (String[])new OgnlUtil().getValue("Centres", Ognl.createDefaultContext(someBean), someBean );
    ServletActionContext.getRequest().setAttribute("someBean", someBean);
    ServletActionContext.getRequest().setAttribute("someBeanList", new ArrayList(Arrays.asList(someBean)));
    URL url1 = ClassLoaderUtil.getResource("../etc/struts-model-driven.xml", this.getClass());
    File ff = new File(url1.toURI());
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
//    Document doc = (builder.parse(ff));

    Map<String, Object> context = ActionContext.getContext().getValueStack().getContext();
//    tokenName = TokenHelper.generateGUID();
    tokenName=UUID.randomUUID().toString();
//    Object myToken = context.get(tokenName);
//    if (myToken == null) {
//      myToken = TokenHelper.setToken(tokenName);
//      context.put(tokenName, myToken);
//    }
//    token = myToken.toString();

    Map<String, Object> parameters = new HashMap<>();
    ActionMapping mapping = new ActionMapping("action", "namespace", "", parameters);
    String uri = actionMapper.getUriFromActionMapping(mapping);
    String url  = urlHelper.buildUrl(uri, ServletActionContext.getRequest(), ServletActionContext.getResponse(), parameters, "http", true, false, true, false);


    id = 1l;
    myScheme="http";
//   List userList = userDAO.findAll();
//    PrintWriter out = response.getWriter();
//    System.setProperty("file.encoding", "UTF-8");
    System.out.println("file.encoding=" + System.getProperty("file.encoding"));
    System.out.println("sun.jnu.encoding=" + System.getProperty("sun.jnu.encoding"));
    System.out.println("sun.io.unicode.encoding=" + System.getProperty("sun.io.unicode.encoding"));
    System.out.println("file.encoding.pkg=" + System.getProperty("file.encoding.pkg"));
    PrintWriter out;
    try {
      out = new PrintWriter(new OutputStreamWriter(System.out, "UTF8"), true);
      out.print(content);
      System.out.println(new String(content.getBytes("UTF-8")));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();  //Todo change body of catch statement.
    }
    String content3 = null;
    System.out.println("médiévaux, principalement pour l’anglais et le français");
    try {
      System.out.println(new String(content.getBytes("UTF8"), "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();  //Todo change body of catch statement.
    }
    log.info("content3=" + "médiévaux, principalement pour l’anglais et le français");

//    MessageFormat mf = new MessageFormat("{0,number,#,##0.00}");
//    System.out.println(mf.format(new Object[]{myFoat}));
//    System.out.println(LocalizedTextUtil.findDefaultText("format.number", ActionContext.getContext().getLocale(), new Object[]{myFoat}));
//    System.out.println(getText("format.number",Arrays.asList(new Object[]{myFloat})));

    input();

    ServletActionContext.getRequest().getSession().setAttribute("user", new User("Test", null));

//    DataSource dataSource = (DataSource)ServletActionContext.getServletContext().getAttribute("ds");
//    Connection con = dataSource.getConnection();

    System.out.println(message);

    return SUCCESS;
  }

  @Validations(
//    requiredFields = {
//      @RequiredFieldValidator(type = ValidatorType.FIELD, fieldName = "myQuestionq1", message = "You must enter a value for field myQuestionq1."),
//      @RequiredFieldValidator(type = ValidatorType.FIELD, fieldName = "myQuestionq2", message = "You must enter a value for field myQuestionq2.")
//    },
    requiredStrings={
      @RequiredStringValidator(fieldName="myQuestionq1", type= ValidatorType.FIELD, message = "myQuestionq1 is mandatory."),
      @RequiredStringValidator(fieldName="myQuestionq2", type= ValidatorType.FIELD, message = "myQuestionq2 is mandatory.")
    },
    stringLengthFields={
      @StringLengthFieldValidator(fieldName="myQuestionq1", type= ValidatorType.FIELD, minLength="2", maxLength="45", message="The length must be between 10 and 1000."),
      @StringLengthFieldValidator(fieldName="myQuestionq2", type= ValidatorType.FIELD, minLength="2", maxLength="45", message="The length must be between 10 and 1000.")
    },
    regexFields = @RegexFieldValidator(type = ValidatorType.FIELD, fieldName = "email", message = "You must enter a valid email",
      regexExpression = "^['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|arpa|asia|biz|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx|cat)$",
      caseSensitiveExpression = "${true}", trimExpression = "${true}"
    ),
    customValidators = @CustomValidator(type="retypeValidator", message="the value and retype value should be the same",
      parameters = { @ValidationParameter( name = "value", value = "${myQuestionq1}" ), @ValidationParameter( name = "retypeValue", value = "${myQuestionq2}" )})
  )

  @Action(value="save", results = {
    @Result(name="input", location = "/default.jsp"),
    @Result(name="back", type="redirect", location = "/")
  },interceptorRefs = @InterceptorRef(value="defaultStack", params = {"validation.validateAnnotatedMethodOnly", "true"}))

  @InputConfig(methodName="input")
  public String save() throws SQLException {
//    System.out.println("mobileno="+mobileno);
//    System.out.println("content2="+content2);
//    System.out.println("phone="+phone);
//    System.out.println("id="+id);
//    try {
//      System.out.println("content2="+new String(content2.getBytes("UTF8"), "UTF-8"));
//    } catch (UnsupportedEncodingException e) {
//      e.printStackTrace();  //Todo change body of catch statement.
//    }
//    XWorkList xwl = (Object)map.get("key1").get("q1");
//    System.out.println("map[key1][q1]="+xwl.get(0));
//    System.out.println("map3[key1][q1].name="+map3.get("key1").get("q1").getName());

    return "back";

  }

  @SkipValidation
  @Action(value="save1", params = {"par1", "${par1}"}, results = {
      @Result(name="input", location = "/default.jsp"),
      @Result(name="back", type="redirect", location = "/")
  },interceptorRefs = @InterceptorRef(value="defaultStack"))
  public String save1() throws SQLException {
    System.out.println("Before"+ ServletActionContext.getRequest().getParameter("par1"));
    return "back";
  }

    @Validations(
    requiredFields = {
      @RequiredFieldValidator(type = ValidatorType.FIELD, fieldName = "myFloat", message = "You must enter a value for field myFloat."),
    },
    conversionErrorFields = @ConversionErrorFieldValidator(fieldName = "myFloat", message = "", shortCircuit = true)
  )

  @Action(value="test", results = {
    @Result(name="input", location = "/default.jsp"),
    @Result(name="back", type="redirect", location = "/")
  },interceptorRefs = @InterceptorRef(value="defaultStack", params = {"validation.validateAnnotatedMethodOnly", "true"}))

  public String test() throws Exception {
    return "back";
  }


  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    System.out.println(message);
    this.message = message;
  }

  private String foo;

  public String getFoo() {
    return foo;
  }

  public void setFoo(String foo) {
    this.foo = foo;
  }

  @SkipValidation
  @Action(value="getBookingorderDeatils", results = @Result(location = "/admin.jsp"))
  public String getBookingorderDeatils(){
    System.out.println("foo="+foo);
    return SUCCESS;
  }
}