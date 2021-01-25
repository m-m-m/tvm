/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package org.teavm.classlib.java.time;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link TLocalTime}.
 */
public class TLocalDateTimeTest extends Assertions {

  @Test
  public void testParse() {

    TLocalDateTime dateTime = TLocalDateTime.parse("1999-12-31T23:59:58.123456789");
    assertThat(dateTime.getYear()).isEqualTo(1999);
    assertThat(dateTime.getMonth()).isEqualTo(TMonth.DECEMBER);
    assertThat(dateTime.getDayOfMonth()).isEqualTo(31);
    assertThat(dateTime.getDayOfWeek()).isEqualTo(TDayOfWeek.FRIDAY);
    assertThat(dateTime.getDayOfYear()).isEqualTo(365);
    assertThat(dateTime.getHour()).isEqualTo(23);
    assertThat(dateTime.getMinute()).isEqualTo(59);
    assertThat(dateTime.getSecond()).isEqualTo(58);
    assertThat(dateTime.getNano()).isEqualTo(123456789);
  }

  @Test
  public void testToString() {

    TLocalDateTime dateTime = TLocalDateTime.of(1999, 12, 31, 23, 59, 58, 123456789);
    assertThat(dateTime.toString()).isEqualTo("1999-12-31T23:59:58.123456789");
  }

}
