/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package org.teavm.classlib.java.time;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link TOffsetDateTime}.
 */
public class TZonedDateTimeTest extends Assertions {

  @Test
  public void testParse() {

    TZonedDateTime dateTime = TZonedDateTime.parse("1999-12-31T23:59:58.123456789+03:04:05[UTC+03:04:05]");
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
    assertThat(offset.getId()).isEqualTo("+03:04:05");
    assertThat(offset.getTotalSeconds()).isEqualTo(((3 * 60) + 4) * 60 + 5);
  }

  @Test
  public void testToString() {

    TZoneId offset = TZoneId.of("UTC+03:04:05");
    TZonedDateTime dateTime = TZonedDateTime.of(TLocalDate.of(1999, 12, 31), TLocalTime.of(23, 59, 58, 123456789),
        offset);
    assertThat(dateTime.toString()).isEqualTo("1999-12-31T23:59:58.123456789+03:04:05[UTC+03:04:05]");
  }

}
