package jspbean.struts;

import jersey.repackaged.com.google.common.base.Function;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Roma on 17.02.2015.
 */
class SumTask implements Callable<Integer> {
  private int num = 0;
  public SumTask(int num){
    this.num = num;
  }
  @Override
  public Integer call() throws Exception {
    int result = 0;
    for(int i=1;i<=num;i++){
      result+=i;
    }
    return result;
  }
}

public class CallableDemo {

  Integer result;
  Integer num;

  public Integer getNumValue() {
    return 123;
  }
  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public Integer getResult() {
    return result;
  }

  public void setResult(Integer result) {
    this.result = result;
  }

  ExecutorService service =  Executors.newSingleThreadExecutor();

  public String  execute() {
    try{

      Future<Integer> future = service.submit(new SumTask(num));
      result = future.get();
      //System.out.println(result);
      service.shutdown();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    return "showlinks";
  }
}
