/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package org.teavm.classlib.java.time;

import java.time.chrono.Chronology;
import java.time.chrono.IsoEra;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 *
 */
public class TestMe {

  public static void main(String[] args) {

    Chronology c = ThaiBuddhistChronology.INSTANCE;
    System.out.println(c.getDisplayName(TextStyle.SHORT, Locale.GERMANY));
  }

  private void printEra() {

    List<Locale> locales = List.of(Locale.ROOT, Locale.UK, Locale.US, Locale.GERMANY, Locale.FRANCE,
        new Locale("es", "ES"));
    for (Locale locale : locales) {
      for (IsoEra era : IsoEra.values()) {
        for (TextStyle style : TextStyle.values()) {
          System.out.println(locale.toString() + "@" + style.name() + ":" + era.getDisplayName(style, locale));
        }
      }
    }

  }

}
