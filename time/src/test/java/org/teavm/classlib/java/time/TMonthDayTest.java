/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package org.teavm.classlib.java.time;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link TMonthDay}.
 */
public class TMonthDayTest extends Assertions {

  @Test
  public void testParse() {

    TMonthDay monthDay = TMonthDay.parse("--12-31");
    assertThat(monthDay.getMonth()).isEqualTo(TMonth.DECEMBER);
    assertThat(monthDay.getDayOfMonth()).isEqualTo(31);
    monthDay = TMonthDay.parse("--01-01");
    assertThat(monthDay.getMonth()).isEqualTo(TMonth.JANUARY);
    assertThat(monthDay.getDayOfMonth()).isEqualTo(1);
  }

  @Test
  public void testToString() {

    TMonthDay monthDay = TMonthDay.of(12, 31);
    assertThat(monthDay.getMonthValue()).isEqualTo(12);
    assertThat(monthDay.getDayOfMonth()).isEqualTo(31);
    assertThat(monthDay.toString()).isEqualTo("--12-31");
    monthDay = TMonthDay.of(1, 1);
    assertThat(monthDay.getMonthValue()).isEqualTo(1);
    assertThat(monthDay.getDayOfMonth()).isEqualTo(1);
    assertThat(monthDay.toString()).isEqualTo("--01-01");
  }

}
