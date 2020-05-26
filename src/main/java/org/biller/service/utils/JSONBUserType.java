package org.biller.service.utils;

import org.json.simple.JSONObject;

public class JSONBUserType extends CustomUserType{

  @Override
  public Class<?> returnedClass() {
    return JSONObject.class;
  }

}
