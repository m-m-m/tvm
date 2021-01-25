/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package org.teavm.classlib.java.time;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link TOffsetDateTime}.
 */
public class TOffsetDateTimeTest extends Assertions {

  @Test
  public void testParse() {

    TOffsetDateTime dateTime = TOffsetDateTime.parse("1999-12-31T23:59:58.123456789+05:30:01");
    assertThat(dateTime.getYear()).isEqualTo(1999);
    assertThat(dateTime.getMonth()).isEqualTo(TMonth.DECEMBER);
    assertThat(dateTime.getDayOfMonth()).isEqualTo(31);
    assertThat(dateTime.getDayOfWeek()).isEqualTo(TDayOfWeek.FRIDAY);
    assertThat(dateTime.getDayOfYear()).isEqualTo(365);
    assertThat(dateTime.getHour()).isEqualTo(23);
    assertThat(dateTime.getMinute()).isEqualTo(59);
    assertThat(dateTime.getSecond()).isEqualTo(58);
    assertThat(dateTime.getNano()).isEqualTo(123456789);
    TZoneOffset offset = dateTime.getOffset();
    assertThat(offset.getId()).isEqualTo("+05:30:01");
    assertThat(offset.getTotalSeconds()).isEqualTo(((5 * 60) + 30) * 60 + 1);
  }

  @Test
  public void testToString() {

    TZoneOffset offset = TZoneOffset.ofTotalSeconds(((5 * 60) + 30) * 60 + 1);
    TOffsetDateTime dateTime = TOffsetDateTime.of(TLocalDate.of(1999, 12, 31), TLocalTime.of(23, 59, 58, 123456789),
        offset);
    assertThat(dateTime.toString()).isEqualTo("1999-12-31T23:59:58.123456789+05:30:01");
  }

}
