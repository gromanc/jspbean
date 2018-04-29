package jspbean.rest;

import org.springframework.stereotype.Component;

/**
 * Created by Roma on 22.09.2014.
 */
@Component
public class MoreServiceImpl implements MoreService {
  @Override
  public String validate() {
    return "testing";
  }

}
