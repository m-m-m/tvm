package org.teavm.classlib.java.lang.reflect;

import java.lang.reflect.Type;

public interface TParameterizedType {

  Type getOwnerType();

  Type getRawType();

  Type[] getActualTypeArguments();
}
