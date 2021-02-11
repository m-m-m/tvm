package org.teavm.classlib.java.lang.reflect;

import java.lang.reflect.Type;

public interface TWildcardType {

  Type[] getUpperBounds();

  Type[] getLowerBounds();
}
