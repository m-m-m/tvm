/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package org.teavm.classlib.java.time;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link TInstant}.
 */
public class TInstantTest extends Assertions {

  @Test
  public void testParse() {

    TInstant instant = TInstant.parse("1999-12-31T23:59:58.123456789Z");
    assertThat(instant.getEpochSecond()).isEqualTo(946684798L);
    assertThat(instant.getNano()).isEqualTo(123456789);

    instant = TInstant.parse("1999-12-31T23:59:58Z");
    assertThat(instant.getEpochSecond()).isEqualTo(946684798L);
    assertThat(instant.getNano()).isEqualTo(0);
  }

  @Test
  public void testToString() {

    TInstant instant = TInstant.ofEpochSecond(946684798L, 123456789);
    assertThat(instant.getEpochSecond()).isEqualTo(946684798L);
    assertThat(instant.getNano()).isEqualTo(123456789);
    assertThat(instant.toString()).isEqualTo("1999-12-31T23:59:58.123456789Z");
  }

}
