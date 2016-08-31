package com.restfully.shop.ecommerce.core;

import java.util.Collections;
import java.util.Set;

/**
 * Unit Tests for e-commerce
 *
 * @author cardonrl
 */
public abstract class Application {
  private static final Set<Object> emptySet = Collections.emptySet();

  public abstract Set<Class<?>> getClasses();

  public Set<Object> getSingletons() {
    return emptySet;
  }
}
