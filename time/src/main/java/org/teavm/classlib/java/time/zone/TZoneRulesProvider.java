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
package org.teavm.classlib.java.time.zone;

import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import org.teavm.classlib.impl.tz.DateTimeZoneProvider;
import org.teavm.classlib.java.time.TZoneId;

public abstract class TZoneRulesProvider {

  private static final String VERSION_ID = "v1";

  private static final Set<String> REMOVED_IDS = new HashSet<>();
  static {
    REMOVED_IDS.add("GMT-0");
    REMOVED_IDS.add("GMT+0");
    REMOVED_IDS.add("Canada/East-Saskatchewan");
    REMOVED_IDS.add("EST");
    REMOVED_IDS.add("HST");
    REMOVED_IDS.add("MST");
    REMOVED_IDS.add("ROC");
  }

  public static Set<String> getAvailableZoneIds() {

    String[] ids = DateTimeZoneProvider.getIds();
    Set<String> set = new HashSet<>(ids.length);
    for (String id : ids) {
      if (!REMOVED_IDS.contains(id)) {
        set.add(id);
      }
    }
    return set;
  }

  public static TZoneRules getRules(String zoneId, boolean forCaching) {

    TZoneId zone = TZoneId.of(zoneId);
    if (zone == null) {
      throw new TZoneRulesException("Unknown time-zone ID: " + zoneId);
    }
    return zone.getRules();
  }

  public static NavigableMap<String, TZoneRules> getVersions(String zoneId) {

    TZoneRules rules = getRules(zoneId, false);
    TreeMap<String, TZoneRules> map = new TreeMap<>();
    map.put(VERSION_ID, rules);
    return map;
  }

  public static void registerProvider(TZoneRulesProvider provider) {

    throw new UnsupportedOperationException();
  }

  public static boolean refresh() {

    return false;
  }

  protected TZoneRulesProvider() {

  }

  protected abstract Set<String> provideZoneIds();

  protected abstract TZoneRules provideRules(String regionId, boolean forCaching);

  protected abstract NavigableMap<String, TZoneRules> provideVersions(String zoneId);

  protected boolean provideRefresh() {

    return false;
  }

}
