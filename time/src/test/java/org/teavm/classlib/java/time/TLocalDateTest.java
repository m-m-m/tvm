/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package org.teavm.classlib.java.time;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link TLocalDate}.
 */
public class TLocalDateTest extends Assertions {

  @Test
  public void testParse() {

    TLocalDate date = TLocalDate.parse("1999-12-31");
    assertThat(date.getYear()).isEqualTo(1999);
    assertThat(date.getMonth()).isEqualTo(TMonth.DECEMBER);
    assertThat(date.getDayOfMonth()).isEqualTo(31);
    assertThat(date.getDayOfWeek()).isEqualTo(TDayOfWeek.FRIDAY);
    assertThat(date.getDayOfYear()).isEqualTo(365);
  }

  @Test
  public void testToString() {

    TLocalDate date = TLocalDate.of(1999, 12, 31);
    assertThat(date.toString()).isEqualTo("1999-12-31");
  }

}
