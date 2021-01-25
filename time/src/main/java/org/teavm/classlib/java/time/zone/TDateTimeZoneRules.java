/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package org.teavm.classlib.java.time.zone;

import java.util.Collections;
import java.util.List;

import org.teavm.classlib.impl.tz.DateTimeZone;
import org.teavm.classlib.java.time.TInstant;
import org.teavm.classlib.java.time.TLocalDateTime;
import org.teavm.classlib.java.time.TZoneOffset;

/**
 *
 */
public class TDateTimeZoneRules extends TZoneRules {

  private final DateTimeZone zone;

  public TDateTimeZoneRules(DateTimeZone zone) {

    super();
    this.zone = zone;
  }

  @Override
  public boolean isFixedOffset() {

    return this.zone.isFixed();
  }

  @Override
  public TZoneOffset getOffset(TInstant instant) {

    int offset = this.zone.getOffset(instant.getEpochSecond());
    return TZoneOffset.ofTotalSeconds(offset);
  }

  @Override
  public TZoneOffset getOffset(TLocalDateTime localDateTime) {

    int offset = this.zone.getOffsetFromLocal(localDateTime.toEpochSecond(TZoneOffset.UTC));
    return TZoneOffset.ofTotalSeconds(offset);
  }

  @Override
  public List<TZoneOffset> getValidOffsets(TLocalDateTime localDateTime) {

    return Collections.emptyList();
  }

  @Override
  public TZoneOffsetTransition getTransition(TLocalDateTime localDateTime) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TZoneOffset getStandardOffset(TInstant instant) {

    int offset = this.zone.getStandardOffset(instant.getEpochSecond());
    return TZoneOffset.ofTotalSeconds(offset);
  }

  @Override
  public TZoneOffsetTransition nextTransition(TInstant instant) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TZoneOffsetTransition previousTransition(TInstant instant) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<TZoneOffsetTransition> getTransitions() {

    // TODO Auto-generated method stub
    return Collections.emptyList();
  }

  @Override
  public List<TZoneOffsetTransitionRule> getTransitionRules() {

    // TODO Auto-generated method stub
    return Collections.emptyList();
  }

  @Override
  public boolean equals(Object other) {

    if (other == this) {
      return true;
    }
    if (other instanceof TDateTimeZoneRules) {
      TDateTimeZoneRules o = (TDateTimeZoneRules) other;
      return (this.zone.equals(o.zone));
    }
    return false;
  }

  @Override
  public int hashCode() {

    return ~this.zone.hashCode();
  }

}
