package com.restfully.shop.ecommerce.services;

import java.util.HashSet;
import java.util.Set;

import com.restfully.shop.ecommerce.core.Application;

/**
 * Example of implementing Application to deploy our service
 *
 * @author Russell Cardon
 */
public class ShoppingApplication extends Application {
  private Set<Object> singletons = new HashSet<Object>();
  private Set<Class<?>> empty = new HashSet<Class<?>>();

  public ShoppingApplication() {
    singletons.add(new CustomerResource());
  }

  @Override
  public Set<Object> getSingletons() {
    return singletons;
  }


  @Override
  public Set<Class<?>> getClasses() {
    return empty;
  }
}
