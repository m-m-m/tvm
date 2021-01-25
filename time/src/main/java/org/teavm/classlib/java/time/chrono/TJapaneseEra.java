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
package org.teavm.classlib.java.time.chrono;

import java.util.Arrays;
import java.util.Objects;

import org.teavm.classlib.java.io.TSerializable;
import org.teavm.classlib.java.time.TDateTimeException;
import org.teavm.classlib.java.time.TLocalDate;
import org.teavm.classlib.java.time.temporal.TChronoField;
import org.teavm.classlib.java.time.temporal.TTemporalField;
import org.teavm.classlib.java.time.temporal.TValueRange;

public final class TJapaneseEra implements TEra, TSerializable {

  // The offset value to 0-based index from the era value.
  // i.e., getValue() + ERA_OFFSET == 0-based index; except that -999 is mapped to zero
  static final int ERA_OFFSET = 2;

  public static final TJapaneseEra MEIJI = new TJapaneseEra(-1, TLocalDate.of(1868, 9, 8), "Meiji");

  public static final TJapaneseEra TAISHO = new TJapaneseEra(0, TLocalDate.of(1912, 7, 30), "Taisho");

  public static final TJapaneseEra SHOWA = new TJapaneseEra(1, TLocalDate.of(1926, 12, 25), "Showa");

  public static final TJapaneseEra HEISEI = new TJapaneseEra(2, TLocalDate.of(1989, 1, 8), "Heisei");

  private static final int ADDITIONAL_VALUE = 3;

  // array for the singleton TJapaneseEra instances
  private static final TJapaneseEra[] KNOWN_ERAS;

  static {
    TJapaneseEra[] array = new TJapaneseEra[4];
    array[0] = MEIJI;
    array[1] = TAISHO;
    array[2] = SHOWA;
    array[3] = HEISEI;
    KNOWN_ERAS = array;
  }

  private final int eraValue;

  // the first day of the era
  private final transient TLocalDate since;

  // the name of the era
  private final transient String name;

  private TJapaneseEra(int eraValue, TLocalDate since, String name) {

    this.eraValue = eraValue;
    this.since = since;
    this.name = name;
  }

  public static TJapaneseEra registerEra(TLocalDate since, String name) {

    TJapaneseEra[] known = KNOWN_ERAS;
    if (known.length > 4) {
      throw new TDateTimeException("Only one additional Japanese era can be added");
    }
    Objects.requireNonNull(since, "since");
    Objects.requireNonNull(name, "name");
    if (!since.isAfter(HEISEI.since)) {
      throw new TDateTimeException("Invalid since date for additional Japanese era, must be after Heisei");
    }
    TJapaneseEra era = new TJapaneseEra(ADDITIONAL_VALUE, since, name);
    TJapaneseEra[] newArray = Arrays.copyOf(known, 5);
    newArray[4] = era;
    // if (!KNOWN_ERAS.compareAndSet(known, newArray)) {
    if (known != newArray) {
      throw new TDateTimeException("Only one additional Japanese era can be added");
    }
    return era;
  }

  public static TJapaneseEra of(int japaneseEra) {

    TJapaneseEra[] known = KNOWN_ERAS;
    if (japaneseEra < MEIJI.eraValue || japaneseEra > known[known.length - 1].eraValue) {
      throw new TDateTimeException("japaneseEra is invalid");
    }
    return known[ordinal(japaneseEra)];
  }

  public static TJapaneseEra valueOf(String japaneseEra) {

    Objects.requireNonNull(japaneseEra, "japaneseEra");
    TJapaneseEra[] known = KNOWN_ERAS;
    for (TJapaneseEra era : known) {
      if (japaneseEra.equals(era.name)) {
        return era;
      }
    }
    throw new IllegalArgumentException("TEra not found: " + japaneseEra);
  }

  public static TJapaneseEra[] values() {

    TJapaneseEra[] known = KNOWN_ERAS;
    return Arrays.copyOf(known, known.length);
  }

  static TJapaneseEra from(TLocalDate date) {

    if (date.isBefore(MEIJI.since)) {
      throw new TDateTimeException("TDate too early: " + date);
    }
    TJapaneseEra[] known = KNOWN_ERAS;
    for (int i = known.length - 1; i >= 0; i--) {
      TJapaneseEra era = known[i];
      if (date.compareTo(era.since) >= 0) {
        return era;
      }
    }
    return null;
  }

  private static int ordinal(int eraValue) {

    return eraValue + 1;
  }

  TLocalDate startDate() {

    return this.since;
  }

  TLocalDate endDate() {

    int ordinal = ordinal(this.eraValue);
    TJapaneseEra[] eras = values();
    if (ordinal >= eras.length - 1) {
      return TLocalDate.MAX;
    }
    return eras[ordinal + 1].startDate().minusDays(1);
  }

  @Override
  public int getValue() {

    return this.eraValue;
  }

  @Override
  public TValueRange range(TTemporalField field) {

    if (field == TChronoField.ERA) {
      return TJapaneseChronology.INSTANCE.range(TChronoField.ERA);
    }
    return TEra.super.range(field);
  }

  @Override
  public String toString() {

    return this.name;
  }

}
