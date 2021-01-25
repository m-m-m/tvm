/*
 *  Copyright 2020 adopted to TeaVM by Joerg Hohwiller
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/*
 * Copyright (c) 2007-present, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.teavm.classlib.java.time;

import static org.teavm.classlib.java.time.TLocalTime.HOURS_PER_DAY;
import static org.teavm.classlib.java.time.TLocalTime.MICROS_PER_DAY;
import static org.teavm.classlib.java.time.TLocalTime.MILLIS_PER_DAY;
import static org.teavm.classlib.java.time.TLocalTime.MINUTES_PER_DAY;
import static org.teavm.classlib.java.time.TLocalTime.NANOS_PER_DAY;
import static org.teavm.classlib.java.time.TLocalTime.NANOS_PER_HOUR;
import static org.teavm.classlib.java.time.TLocalTime.NANOS_PER_MINUTE;
import static org.teavm.classlib.java.time.TLocalTime.NANOS_PER_SECOND;
import static org.teavm.classlib.java.time.TLocalTime.SECONDS_PER_DAY;

import java.util.Objects;

import org.teavm.classlib.java.io.TSerializable;
import org.teavm.classlib.java.time.chrono.TChronoLocalDateTime;
import org.teavm.classlib.java.time.format.TDateTimeFormatter;
import org.teavm.classlib.java.time.format.TDateTimeParseException;
import org.teavm.classlib.java.time.temporal.TChronoField;
import org.teavm.classlib.java.time.temporal.TChronoUnit;
import org.teavm.classlib.java.time.temporal.TTemporal;
import org.teavm.classlib.java.time.temporal.TTemporalAccessor;
import org.teavm.classlib.java.time.temporal.TTemporalAdjuster;
import org.teavm.classlib.java.time.temporal.TTemporalAmount;
import org.teavm.classlib.java.time.temporal.TTemporalField;
import org.teavm.classlib.java.time.temporal.TTemporalQueries;
import org.teavm.classlib.java.time.temporal.TTemporalQuery;
import org.teavm.classlib.java.time.temporal.TTemporalUnit;
import org.teavm.classlib.java.time.temporal.TUnsupportedTemporalTypeException;
import org.teavm.classlib.java.time.temporal.TValueRange;
import org.teavm.classlib.java.time.zone.TZoneRules;

public final class TLocalDateTime
    implements TChronoLocalDateTime<TLocalDate>, TTemporal, TTemporalAdjuster, TSerializable {

  public static final TLocalDateTime MIN = TLocalDateTime.of(TLocalDate.MIN, TLocalTime.MIN);

  public static final TLocalDateTime MAX = TLocalDateTime.of(TLocalDate.MAX, TLocalTime.MAX);

  public static final TTemporalQuery<TLocalDateTime> FROM = new TTemporalQuery<TLocalDateTime>() {
    @Override
    public TLocalDateTime queryFrom(TTemporalAccessor temporal) {

      return TLocalDateTime.from(temporal);
    }
  };

  private final TLocalDate date;

  private final TLocalTime time;

  public static TLocalDateTime now() {

    return now(TClock.systemDefaultZone());
  }

  public static TLocalDateTime now(TZoneId zone) {

    return now(TClock.system(zone));
  }

  public static TLocalDateTime now(TClock clock) {

    Objects.requireNonNull(clock, "clock");
    final TInstant now = clock.instant(); // called once
    TZoneOffset offset = clock.getZone().getRules().getOffset(now);
    return ofEpochSecond(now.getEpochSecond(), now.getNano(), offset);
  }

  public static TLocalDateTime of(int year, TMonth month, int dayOfMonth, int hour, int minute) {

    TLocalDate date = TLocalDate.of(year, month, dayOfMonth);
    TLocalTime time = TLocalTime.of(hour, minute);
    return new TLocalDateTime(date, time);
  }

  public static TLocalDateTime of(int year, TMonth month, int dayOfMonth, int hour, int minute, int second) {

    TLocalDate date = TLocalDate.of(year, month, dayOfMonth);
    TLocalTime time = TLocalTime.of(hour, minute, second);
    return new TLocalDateTime(date, time);
  }

  public static TLocalDateTime of(int year, TMonth month, int dayOfMonth, int hour, int minute, int second,
      int nanoOfSecond) {

    TLocalDate date = TLocalDate.of(year, month, dayOfMonth);
    TLocalTime time = TLocalTime.of(hour, minute, second, nanoOfSecond);
    return new TLocalDateTime(date, time);
  }

  public static TLocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute) {

    TLocalDate date = TLocalDate.of(year, month, dayOfMonth);
    TLocalTime time = TLocalTime.of(hour, minute);
    return new TLocalDateTime(date, time);
  }

  public static TLocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second) {

    TLocalDate date = TLocalDate.of(year, month, dayOfMonth);
    TLocalTime time = TLocalTime.of(hour, minute, second);
    return new TLocalDateTime(date, time);
  }

  public static TLocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second,
      int nanoOfSecond) {

    TLocalDate date = TLocalDate.of(year, month, dayOfMonth);
    TLocalTime time = TLocalTime.of(hour, minute, second, nanoOfSecond);
    return new TLocalDateTime(date, time);
  }

  public static TLocalDateTime of(TLocalDate date, TLocalTime time) {

    Objects.requireNonNull(date, "date");
    Objects.requireNonNull(time, "time");
    return new TLocalDateTime(date, time);
  }

  public static TLocalDateTime ofInstant(TInstant instant, TZoneId zone) {

    Objects.requireNonNull(instant, "instant");
    Objects.requireNonNull(zone, "zone");
    TZoneRules rules = zone.getRules();
    TZoneOffset offset = rules.getOffset(instant);
    return ofEpochSecond(instant.getEpochSecond(), instant.getNano(), offset);
  }

  public static TLocalDateTime ofEpochSecond(long epochSecond, int nanoOfSecond, TZoneOffset offset) {

    Objects.requireNonNull(offset, "offset");
    long localSecond = epochSecond + offset.getTotalSeconds(); // overflow caught later
    long localEpochDay = Math.floorDiv(localSecond, SECONDS_PER_DAY);
    int secsOfDay = Math.floorMod(localSecond, SECONDS_PER_DAY);
    TLocalDate date = TLocalDate.ofEpochDay(localEpochDay);
    TLocalTime time = TLocalTime.ofSecondOfDay(secsOfDay, nanoOfSecond);
    return new TLocalDateTime(date, time);
  }

  public static TLocalDateTime from(TTemporalAccessor temporal) {

    if (temporal instanceof TLocalDateTime) {
      return (TLocalDateTime) temporal;
    } else if (temporal instanceof TZonedDateTime) {
      return ((TZonedDateTime) temporal).toLocalDateTime();
    }
    try {
      TLocalDate date = TLocalDate.from(temporal);
      TLocalTime time = TLocalTime.from(temporal);
      return new TLocalDateTime(date, time);
    } catch (TDateTimeException ex) {
      throw new TDateTimeException("Unable to obtain TLocalDateTime from TTemporalAccessor: " + temporal + ", type "
          + temporal.getClass().getName());
    }
  }

  public static TLocalDateTime parse(CharSequence text) {

    int length = text.length();
    if (length <= 31) { // "1999-12-31T23:59:59.123456789".length() = 29
      TLocalDateTime dateTime = parse(text.toString());
      if (dateTime != null) {
        return dateTime;
      }
    }
    throw new TDateTimeParseException("Failed to parse LocalDateTime: " + text, text, 0);
  }

  private static TLocalDateTime parse(String text) {

    int start = 0;
    int end = text.indexOf('T');
    if (end <= 0) {
      return null;
    }
    TLocalDate date = TLocalDate.parse(text.substring(start, end));
    TLocalTime time = TLocalTime.parse(text.substring(end + 1));
    return TLocalDateTime.of(date, time);
  }

  public static TLocalDateTime parse(CharSequence text, TDateTimeFormatter formatter) {

    Objects.requireNonNull(formatter, "formatter");
    return formatter.parse(text, TLocalDateTime.FROM);
  }

  private TLocalDateTime(TLocalDate date, TLocalTime time) {

    this.date = date;
    this.time = time;
  }

  private TLocalDateTime with(TLocalDate newDate, TLocalTime newTime) {

    if (this.date == newDate && this.time == newTime) {
      return this;
    }
    return new TLocalDateTime(newDate, newTime);
  }

  @Override
  public boolean isSupported(TTemporalField field) {

    if (field instanceof TChronoField) {
      return field.isDateBased() || field.isTimeBased();
    }
    return field != null && field.isSupportedBy(this);
  }

  @Override
  public boolean isSupported(TTemporalUnit unit) {

    if (unit instanceof TChronoUnit) {
      return unit.isDateBased() || unit.isTimeBased();
    }
    return unit != null && unit.isSupportedBy(this);
  }

  @Override
  public TValueRange range(TTemporalField field) {

    if (field instanceof TChronoField) {
      return (field.isTimeBased() ? this.time.range(field) : this.date.range(field));
    }
    return field.rangeRefinedBy(this);
  }

  @Override
  public int get(TTemporalField field) {

    if (field instanceof TChronoField) {
      return (field.isTimeBased() ? this.time.get(field) : this.date.get(field));
    }
    return TChronoLocalDateTime.super.get(field);
  }

  @Override
  public long getLong(TTemporalField field) {

    if (field instanceof TChronoField) {
      return (field.isTimeBased() ? this.time.getLong(field) : this.date.getLong(field));
    }
    return field.getFrom(this);
  }

  public int getYear() {

    return this.date.getYear();
  }

  public int getMonthValue() {

    return this.date.getMonthValue();
  }

  public TMonth getMonth() {

    return this.date.getMonth();
  }

  public int getDayOfMonth() {

    return this.date.getDayOfMonth();
  }

  public int getDayOfYear() {

    return this.date.getDayOfYear();
  }

  public TDayOfWeek getDayOfWeek() {

    return this.date.getDayOfWeek();
  }

  public int getHour() {

    return this.time.getHour();
  }

  public int getMinute() {

    return this.time.getMinute();
  }

  public int getSecond() {

    return this.time.getSecond();
  }

  public int getNano() {

    return this.time.getNano();
  }

  @Override
  public TLocalDateTime with(TTemporalAdjuster adjuster) {

    // optimizations
    if (adjuster instanceof TLocalDate) {
      return with((TLocalDate) adjuster, this.time);
    } else if (adjuster instanceof TLocalTime) {
      return with(this.date, (TLocalTime) adjuster);
    } else if (adjuster instanceof TLocalDateTime) {
      return (TLocalDateTime) adjuster;
    }
    return (TLocalDateTime) adjuster.adjustInto(this);
  }

  @Override
  public TLocalDateTime with(TTemporalField field, long newValue) {

    if (field instanceof TChronoField) {
      if (field.isTimeBased()) {
        return with(this.date, this.time.with(field, newValue));
      } else {
        return with(this.date.with(field, newValue), this.time);
      }
    }
    return field.adjustInto(this, newValue);
  }

  public TLocalDateTime withYear(int year) {

    return with(this.date.withYear(year), this.time);
  }

  public TLocalDateTime withMonth(int month) {

    return with(this.date.withMonth(month), this.time);
  }

  public TLocalDateTime withDayOfMonth(int dayOfMonth) {

    return with(this.date.withDayOfMonth(dayOfMonth), this.time);
  }

  public TLocalDateTime withDayOfYear(int dayOfYear) {

    return with(this.date.withDayOfYear(dayOfYear), this.time);
  }

  public TLocalDateTime withHour(int hour) {

    TLocalTime newTime = this.time.withHour(hour);
    return with(this.date, newTime);
  }

  public TLocalDateTime withMinute(int minute) {

    TLocalTime newTime = this.time.withMinute(minute);
    return with(this.date, newTime);
  }

  public TLocalDateTime withSecond(int second) {

    TLocalTime newTime = this.time.withSecond(second);
    return with(this.date, newTime);
  }

  public TLocalDateTime withNano(int nanoOfSecond) {

    TLocalTime newTime = this.time.withNano(nanoOfSecond);
    return with(this.date, newTime);
  }

  public TLocalDateTime truncatedTo(TTemporalUnit unit) {

    return with(this.date, this.time.truncatedTo(unit));
  }

  @Override
  public TLocalDateTime plus(TTemporalAmount amount) {

    return (TLocalDateTime) amount.addTo(this);
  }

  @Override
  public TLocalDateTime plus(long amountToAdd, TTemporalUnit unit) {

    if (unit instanceof TChronoUnit) {
      TChronoUnit f = (TChronoUnit) unit;
      switch (f) {
        case NANOS:
          return plusNanos(amountToAdd);
        case MICROS:
          return plusDays(amountToAdd / MICROS_PER_DAY).plusNanos((amountToAdd % MICROS_PER_DAY) * 1000);
        case MILLIS:
          return plusDays(amountToAdd / MILLIS_PER_DAY).plusNanos((amountToAdd % MILLIS_PER_DAY) * 1000000);
        case SECONDS:
          return plusSeconds(amountToAdd);
        case MINUTES:
          return plusMinutes(amountToAdd);
        case HOURS:
          return plusHours(amountToAdd);
        case HALF_DAYS:
          return plusDays(amountToAdd / 256).plusHours((amountToAdd % 256) * 12); // no overflow (256 is
                                                                                  // multiple of 2)
      }
      return with(this.date.plus(amountToAdd, unit), this.time);
    }
    return unit.addTo(this, amountToAdd);
  }

  public TLocalDateTime plusYears(long years) {

    TLocalDate newDate = this.date.plusYears(years);
    return with(newDate, this.time);
  }

  public TLocalDateTime plusMonths(long months) {

    TLocalDate newDate = this.date.plusMonths(months);
    return with(newDate, this.time);
  }

  public TLocalDateTime plusWeeks(long weeks) {

    TLocalDate newDate = this.date.plusWeeks(weeks);
    return with(newDate, this.time);
  }

  public TLocalDateTime plusDays(long days) {

    TLocalDate newDate = this.date.plusDays(days);
    return with(newDate, this.time);
  }

  public TLocalDateTime plusHours(long hours) {

    return plusWithOverflow(this.date, hours, 0, 0, 0, 1);
  }

  public TLocalDateTime plusMinutes(long minutes) {

    return plusWithOverflow(this.date, 0, minutes, 0, 0, 1);
  }

  public TLocalDateTime plusSeconds(long seconds) {

    return plusWithOverflow(this.date, 0, 0, seconds, 0, 1);
  }

  public TLocalDateTime plusNanos(long nanos) {

    return plusWithOverflow(this.date, 0, 0, 0, nanos, 1);
  }

  @Override
  public TLocalDateTime minus(TTemporalAmount amount) {

    return (TLocalDateTime) amount.subtractFrom(this);
  }

  @Override
  public TLocalDateTime minus(long amountToSubtract, TTemporalUnit unit) {

    return (amountToSubtract == Long.MIN_VALUE ? plus(Long.MAX_VALUE, unit).plus(1, unit)
        : plus(-amountToSubtract, unit));
  }

  public TLocalDateTime minusYears(long years) {

    return (years == Long.MIN_VALUE ? plusYears(Long.MAX_VALUE).plusYears(1) : plusYears(-years));
  }

  public TLocalDateTime minusMonths(long months) {

    return (months == Long.MIN_VALUE ? plusMonths(Long.MAX_VALUE).plusMonths(1) : plusMonths(-months));
  }

  public TLocalDateTime minusWeeks(long weeks) {

    return (weeks == Long.MIN_VALUE ? plusWeeks(Long.MAX_VALUE).plusWeeks(1) : plusWeeks(-weeks));
  }

  public TLocalDateTime minusDays(long days) {

    return (days == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1) : plusDays(-days));
  }

  public TLocalDateTime minusHours(long hours) {

    return plusWithOverflow(this.date, hours, 0, 0, 0, -1);
  }

  public TLocalDateTime minusMinutes(long minutes) {

    return plusWithOverflow(this.date, 0, minutes, 0, 0, -1);
  }

  public TLocalDateTime minusSeconds(long seconds) {

    return plusWithOverflow(this.date, 0, 0, seconds, 0, -1);
  }

  public TLocalDateTime minusNanos(long nanos) {

    return plusWithOverflow(this.date, 0, 0, 0, nanos, -1);
  }

  private TLocalDateTime plusWithOverflow(TLocalDate newDate, long hours, long minutes, long seconds, long nanos,
      int sign) {

    // 9223372036854775808 long, 2147483648 int
    if ((hours | minutes | seconds | nanos) == 0) {
      return with(newDate, this.time);
    }
    long totDays = nanos / NANOS_PER_DAY + // max/24*60*60*1B
        seconds / SECONDS_PER_DAY + // max/24*60*60
        minutes / MINUTES_PER_DAY + // max/24*60
        hours / HOURS_PER_DAY; // max/24
    totDays *= sign; // total max*0.4237...
    long totNanos = nanos % NANOS_PER_DAY + // max 86400000000000
        (seconds % SECONDS_PER_DAY) * NANOS_PER_SECOND + // max 86400000000000
        (minutes % MINUTES_PER_DAY) * NANOS_PER_MINUTE + // max 86400000000000
        (hours % HOURS_PER_DAY) * NANOS_PER_HOUR; // max 86400000000000
    long curNoD = this.time.toNanoOfDay(); // max 86400000000000
    totNanos = totNanos * sign + curNoD; // total 432000000000000
    totDays += Math.floorDiv(totNanos, NANOS_PER_DAY);
    long newNoD = Math.floorMod(totNanos, NANOS_PER_DAY);
    TLocalTime newTime = (newNoD == curNoD ? this.time : TLocalTime.ofNanoOfDay(newNoD));
    return with(newDate.plusDays(totDays), newTime);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <R> R query(TTemporalQuery<R> query) {

    if (query == TTemporalQueries.localDate()) {
      return (R) toLocalDate();
    }
    return TChronoLocalDateTime.super.query(query);
  }

  @Override
  public TTemporal adjustInto(TTemporal temporal) {

    return TChronoLocalDateTime.super.adjustInto(temporal);
  }

  @Override
  public long until(TTemporal endExclusive, TTemporalUnit unit) {

    TLocalDateTime end = TLocalDateTime.from(endExclusive);
    if (unit instanceof TChronoUnit) {
      TChronoUnit f = (TChronoUnit) unit;
      if (f.isTimeBased()) {
        long daysUntil = this.date.daysUntil(end.date);
        long timeUntil = end.time.toNanoOfDay() - this.time.toNanoOfDay();
        if (daysUntil > 0 && timeUntil < 0) {
          daysUntil--;
          timeUntil += NANOS_PER_DAY;
        } else if (daysUntil < 0 && timeUntil > 0) {
          daysUntil++;
          timeUntil -= NANOS_PER_DAY;
        }
        long amount = daysUntil;
        switch (f) {
          case NANOS:
            amount = Math.multiplyExact(amount, NANOS_PER_DAY);
            return Math.addExact(amount, timeUntil);
          case MICROS:
            amount = Math.multiplyExact(amount, MICROS_PER_DAY);
            return Math.addExact(amount, timeUntil / 1000);
          case MILLIS:
            amount = Math.multiplyExact(amount, MILLIS_PER_DAY);
            return Math.addExact(amount, timeUntil / 1000000);
          case SECONDS:
            amount = Math.multiplyExact(amount, SECONDS_PER_DAY);
            return Math.addExact(amount, timeUntil / NANOS_PER_SECOND);
          case MINUTES:
            amount = Math.multiplyExact(amount, MINUTES_PER_DAY);
            return Math.addExact(amount, timeUntil / NANOS_PER_MINUTE);
          case HOURS:
            amount = Math.multiplyExact(amount, HOURS_PER_DAY);
            return Math.addExact(amount, timeUntil / NANOS_PER_HOUR);
          case HALF_DAYS:
            amount = Math.multiplyExact(amount, 2);
            return Math.addExact(amount, timeUntil / (NANOS_PER_HOUR * 12));
        }
        throw new TUnsupportedTemporalTypeException("Unsupported unit: " + unit);
      }
      TLocalDate endDate = end.date;
      if (endDate.isAfter(this.date) && end.time.isBefore(this.time)) {
        endDate = endDate.minusDays(1);
      } else if (endDate.isBefore(this.date) && end.time.isAfter(this.time)) {
        endDate = endDate.plusDays(1);
      }
      return this.date.until(endDate, unit);
    }
    return unit.between(this, end);
  }

  public TOffsetDateTime atOffset(TZoneOffset offset) {

    return TOffsetDateTime.of(this, offset);
  }

  @Override
  public TZonedDateTime atZone(TZoneId zone) {

    return TZonedDateTime.of(this, zone);
  }

  @Override
  public TLocalDate toLocalDate() {

    return this.date;
  }

  @Override
  public TLocalTime toLocalTime() {

    return this.time;
  }

  @Override
  public int compareTo(TChronoLocalDateTime<?> other) {

    if (other instanceof TLocalDateTime) {
      return compareTo0((TLocalDateTime) other);
    }
    return TChronoLocalDateTime.super.compareTo(other);
  }

  private int compareTo0(TLocalDateTime other) {

    int cmp = this.date.compareTo0(other.toLocalDate());
    if (cmp == 0) {
      cmp = this.time.compareTo(other.toLocalTime());
    }
    return cmp;
  }

  @Override
  public boolean isAfter(TChronoLocalDateTime<?> other) {

    if (other instanceof TLocalDateTime) {
      return compareTo0((TLocalDateTime) other) > 0;
    }
    return TChronoLocalDateTime.super.isAfter(other);
  }

  @Override
  public boolean isBefore(TChronoLocalDateTime<?> other) {

    if (other instanceof TLocalDateTime) {
      return compareTo0((TLocalDateTime) other) < 0;
    }
    return TChronoLocalDateTime.super.isBefore(other);
  }

  @Override
  public boolean isEqual(TChronoLocalDateTime<?> other) {

    if (other instanceof TLocalDateTime) {
      return compareTo0((TLocalDateTime) other) == 0;
    }
    return TChronoLocalDateTime.super.isEqual(other);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj instanceof TLocalDateTime) {
      TLocalDateTime other = (TLocalDateTime) obj;
      return this.date.equals(other.date) && this.time.equals(other.time);
    }
    return false;
  }

  @Override
  public int hashCode() {

    return this.date.hashCode() ^ this.time.hashCode();
  }

  @Override
  public String toString() {

    return this.date.toString() + 'T' + this.time.toString();
  }

  @Override
  public String format(TDateTimeFormatter formatter) {

    return TChronoLocalDateTime.super.format(formatter);
  }

}
