/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package org.teavm.classlib.java.time;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link TLocalTime}.
 */
public class TLocalTimeTest extends Assertions {

  @Test
  public void testParse() {

    TLocalTime time = TLocalTime.parse("23:59:58.123456789");
    assertThat(time.getHour()).isEqualTo(23);
    assertThat(time.getMinute()).isEqualTo(59);
    assertThat(time.getSecond()).isEqualTo(58);
    assertThat(time.getNano()).isEqualTo(123456789);
    time = TLocalTime.parse("23:59:58");
    assertThat(time.getHour()).isEqualTo(23);
    assertThat(time.getMinute()).isEqualTo(59);
    assertThat(time.getSecond()).isEqualTo(58);
    assertThat(time.getNano()).isEqualTo(0);
  }

  @Test
  public void testToString() {

    TLocalTime time = TLocalTime.of(23, 59, 58, 123456789);
    assertThat(time.toString()).isEqualTo("23:59:58.123456789");
  }

}
