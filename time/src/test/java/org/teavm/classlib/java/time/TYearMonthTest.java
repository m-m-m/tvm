/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package org.teavm.classlib.java.time;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link TYearMonth}.
 */
public class TYearMonthTest extends Assertions {

  @Test
  public void testParse() {

    TYearMonth yearMonth = TYearMonth.parse("1999-12");
    assertThat(yearMonth.getYear()).isEqualTo(1999);
    assertThat(yearMonth.getMonth()).isEqualTo(TMonth.DECEMBER);
  }

  @Test
  public void testToString() {

    TYearMonth yearMonth = TYearMonth.of(1999, 12);
    assertThat(yearMonth.getYear()).isEqualTo(1999);
    assertThat(yearMonth.getMonthValue()).isEqualTo(12);
    assertThat(yearMonth.toString()).isEqualTo("1999-12");
  }

}
