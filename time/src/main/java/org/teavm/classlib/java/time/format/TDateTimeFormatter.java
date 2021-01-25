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
package org.teavm.classlib.java.time.format;

import java.io.IOException;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import org.teavm.classlib.java.time.TDateTimeException;
import org.teavm.classlib.java.time.TZoneId;
import org.teavm.classlib.java.time.chrono.TChronology;
import org.teavm.classlib.java.time.temporal.TTemporalAccessor;
import org.teavm.classlib.java.time.temporal.TTemporalField;
import org.teavm.classlib.java.time.temporal.TTemporalQuery;

public final class TDateTimeFormatter {

  public Locale getLocale() {

    return null;
  }

  public TDateTimeFormatter withLocale(Locale locale) {

    return this;
  }

  public TDecimalStyle getDecimalStyle() {

    return null;
  }

  public TDateTimeFormatter withDecimalStyle(TDecimalStyle decimalStyle) {

    return this;
  }

  public TChronology getChronology() {

    return null;
  }

  public TDateTimeFormatter withChronology(TChronology chrono) {

    return this;
  }

  public TZoneId getZone() {

    return null;
  }

  public TDateTimeFormatter withZone(TZoneId zone) {

    return this;
  }

  public TResolverStyle getResolverStyle() {

    return null;
  }

  public TDateTimeFormatter withResolverStyle(TResolverStyle resolverStyle) {

    return this;
  }

  public Set<TTemporalField> getResolverFields() {

    return null;
  }

  public TDateTimeFormatter withResolverFields(TTemporalField... resolverFields) {

    return this;
  }

  public TDateTimeFormatter withResolverFields(Set<TTemporalField> resolverFields) {

    return this;
  }

  public String format(TTemporalAccessor temporal) {

    StringBuilder buf = new StringBuilder(32);
    formatTo(temporal, buf);
    return buf.toString();
  }

  public void formatTo(TTemporalAccessor temporal, Appendable appendable) {

    Objects.requireNonNull(temporal, "temporal");
    Objects.requireNonNull(appendable, "appendable");
    try {
      appendable.append("not supported");
    } catch (IOException ex) {
      throw new TDateTimeException(ex.getMessage(), ex);
    }
  }

  public TTemporalAccessor parse(CharSequence text) {

    Objects.requireNonNull(text, "text");
    try {
      return null;
    } catch (TDateTimeParseException ex) {
      throw ex;
    } catch (RuntimeException ex) {
      throw createError(text, ex);
    }
  }

  public TTemporalAccessor parse(CharSequence text, ParsePosition position) {

    Objects.requireNonNull(text, "text");
    Objects.requireNonNull(position, "position");
    try {
      return null;
    } catch (TDateTimeParseException ex) {
      throw ex;
    } catch (IndexOutOfBoundsException ex) {
      throw ex;
    } catch (RuntimeException ex) {
      throw createError(text, ex);
    }
  }

  public <T> T parse(CharSequence text, TTemporalQuery<T> type) {

    Objects.requireNonNull(text, "text");
    Objects.requireNonNull(type, "type");
    try {
      return null;
    } catch (TDateTimeParseException ex) {
      throw ex;
    } catch (RuntimeException ex) {
      throw createError(text, ex);
    }
  }

  public TTemporalAccessor parseBest(CharSequence text, TTemporalQuery<?>... types) {

    Objects.requireNonNull(text, "text");
    Objects.requireNonNull(types, "types");
    if (types.length < 2) {
      throw new IllegalArgumentException("At least two types must be specified");
    }
    try {
      return null;
    } catch (TDateTimeParseException ex) {
      throw ex;
    } catch (RuntimeException ex) {
      throw createError(text, ex);
    }
  }

  private TDateTimeParseException createError(CharSequence text, RuntimeException ex) {

    String abbr = "";
    if (text.length() > 64) {
      abbr = text.subSequence(0, 64).toString() + "...";
    } else {
      abbr = text.toString();
    }
    return new TDateTimeParseException("Text '" + abbr + "' could not be parsed: " + ex.getMessage(), text, 0, ex);
  }

  public TTemporalAccessor parseUnresolved(CharSequence text, ParsePosition position) {

    return null;
  }

  public Format toFormat() {

    return new ClassicFormat(this, null);
  }

  public Format toFormat(TTemporalQuery<?> query) {

    Objects.requireNonNull(query, "query");
    return new ClassicFormat(this, query);
  }

  @SuppressWarnings("serial")
  static class ClassicFormat extends Format {
    private final TDateTimeFormatter formatter;

    private final TTemporalQuery<?> query;

    public ClassicFormat(TDateTimeFormatter formatter, TTemporalQuery<?> query) {

      this.formatter = formatter;
      this.query = query;
    }

    @Override
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {

      Objects.requireNonNull(obj, "obj");
      Objects.requireNonNull(toAppendTo, "toAppendTo");
      Objects.requireNonNull(pos, "pos");
      if (obj instanceof TTemporalAccessor == false) {
        throw new IllegalArgumentException("Format target must implement TTemporalAccessor");
      }
      pos.setBeginIndex(0);
      pos.setEndIndex(0);
      try {
        this.formatter.formatTo((TTemporalAccessor) obj, toAppendTo);
      } catch (RuntimeException ex) {
        throw new IllegalArgumentException(ex.getMessage(), ex);
      }
      return toAppendTo;
    }

    @Override
    public Object parseObject(String text) throws ParseException {

      Objects.requireNonNull(text, "text");
      try {
        if (this.query == null) {
          return null;
        }
        return this.formatter.parse(text, this.query);
      } catch (TDateTimeParseException ex) {
        throw new ParseException(ex.getMessage(), ex.getErrorIndex());
      } catch (RuntimeException ex) {
        throw (ParseException) new ParseException(ex.getMessage(), 0).initCause(ex);
      }
    }

    @Override
    public Object parseObject(String text, ParsePosition pos) {

      Objects.requireNonNull(text, "text");
      return null;
    }
  }

}
