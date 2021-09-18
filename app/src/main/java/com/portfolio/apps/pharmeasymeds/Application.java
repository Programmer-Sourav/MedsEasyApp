package com.portfolio.apps.pharmeasymeds;

import android.content.Context;

public class Application extends android.app.Application {
  private static Application instance;

  public Application() {
    instance = this;
  }

  public static Context getContext() {
    return instance;
  }
}