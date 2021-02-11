/*
 *  Copyright 2013 Alexey Andreev.
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
package org.teavm.classlib.java.time;

import org.teavm.interop.NoSideEffects;

@NoSideEffects
public final class TMath {
  public static final double E = 2.71828182845904523536;

  public static final double PI = 3.14159265358979323846;

  private TMath() {

  }

  public static int round(float a) {

    return (int) (a + signum(a) * 0.5f);
  }

  public static long round(double a) {

    return (long) (a + signum(a) * 0.5);
  }

  public static int floorDiv(int a, int b) {

    int div = a / b;
    return (a ^ b) < 0 && div * b != a ? div - 1 : div;
  }

  public static long floorDiv(long a, int b) {

    return floorDiv(a, (long) b);
  }

  public static long floorDiv(long a, long b) {

    long div = a / b;
    return (a ^ b) < 0 && div * b != a ? div - 1 : div;
  }

  public static int floorMod(int a, int b) {

    return a - floorDiv(a, b) * b;
  }

  public static int floorMod(long a, int b) {

    return (int) (a - floorDiv(a, b) * b);
  }

  public static long floorMod(long a, long b) {

    return a - floorDiv(a, b) * b;
  }

  public static int incrementExact(int a) {

    if (a == Integer.MAX_VALUE) {
      throw new ArithmeticException("integer overflow");
    }
    return a + 1;
  }

  public static long incrementExact(long a) {

    if (a == Long.MAX_VALUE) {
      throw new ArithmeticException("long overflow");
    }
    return a + 1L;
  }

  public static int decrementExact(int a) {

    if (a == Integer.MIN_VALUE) {
      throw new ArithmeticException("integer overflow");
    }
    return a - 1;
  }

  public static long decrementExact(long a) {

    if (a == Long.MIN_VALUE) {
      throw new ArithmeticException("long overflow");
    }
    return a - 1L;
  }

  public static int negateExact(int a) {

    if (a == Integer.MIN_VALUE) {
      throw new ArithmeticException("integer overflow");
    }
    return -a;
  }

  public static long negateExact(long a) {

    if (a == Long.MIN_VALUE) {
      throw new ArithmeticException("long overflow");
    }
    return -a;
  }

  public static int toIntExact(long value) {

    if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
      throw new ArithmeticException("Calculation overflows an int: " + value);
    }
    return (int) value;
  }

  public static int addExact(int a, int b) {

    int sum = a + b;
    if ((a ^ sum) < 0 && (a ^ b) >= 0) {
      throw new ArithmeticException("Addition overflows an int: " + a + " + " + b);
    }
    return sum;
  }

  public static long addExact(long a, long b) {

    long sum = a + b;
    if ((a ^ sum) < 0 && (a ^ b) >= 0) {
      throw new ArithmeticException("Addition overflows a long: " + a + " + " + b);
    }
    return sum;
  }

  public static int subtractExact(int a, int b) {

    int result = a - b;
    if ((a ^ result) < 0 && (a ^ b) < 0) {
      throw new ArithmeticException("Subtraction overflows an int: " + a + " - " + b);
    }
    return result;
  }

  public static long subtractExact(long a, long b) {

    long result = a - b;
    if ((a ^ result) < 0 && (a ^ b) < 0) {
      throw new ArithmeticException("Subtraction overflows a long: " + a + " - " + b);
    }
    return result;
  }

  public static int multiplyExact(int a, int b) {

    long total = (long) a * (long) b;
    if (total < Integer.MIN_VALUE || total > Integer.MAX_VALUE) {
      throw new ArithmeticException("Multiplication overflows an int: " + a + " * " + b);
    }
    return (int) total;
  }

  public static long multiplyExact(long a, int b) {

    return multiplyExact(a, (long) b);
  }

  public static long multiplyExact(long a, long b) {

    if (b == 1) {
      return a;
    } else if (a == 1) {
      return b;
    } else if (a == 0 || b == 0) {
      return 0;
    }
    long total = a * b;
    if (total / b != a || (a == Long.MIN_VALUE && b == -1) || (b == Long.MIN_VALUE && a == -1)) {
      throw new ArithmeticException("Multiplication overflows a long: " + a + " * " + b);
    }
    return total;
  }

  public static int min(int a, int b) {

    return a < b ? a : b;
  }

  public static int max(int a, int b) {

    return a > b ? a : b;
  }

  public static long min(long a, long b) {

    return a < b ? a : b;
  }

  public static long max(long a, long b) {

    return a > b ? a : b;
  }

  public static double min(double a, double b) {

    return a < b ? a : b;
  }

  public static double max(double a, double b) {

    return a > b ? a : b;
  }

  public static float min(float a, float b) {

    return a < b ? a : b;
  }

  public static float max(float a, float b) {

    return a > b ? a : b;
  }

  public static int abs(int n) {

    return n > 0 ? n : -n;
  }

  public static long abs(long n) {

    return n > 0 ? n : -n;
  }

  public static float abs(float n) {

    return n > 0 ? n : -n;
  }

  public static double abs(double n) {

    return n > 0 ? n : -n;
  }

  public static double ulp(double d) {

    if (Double.isNaN(d)) {
      return d;
    } else if (Double.isInfinite(d)) {
      return Double.POSITIVE_INFINITY;
    }

    if (Double.isNaN(d)) {
      return d;
    } else if (Double.isInfinite(d)) {
      return Double.POSITIVE_INFINITY;
    }

    long bits = Double.doubleToLongBits(d);
    bits &= 0xEFF0000000000000L;
    if (bits >= 53L << 52L) {
      bits -= 52L << 52L;
    } else {
      int exponent = (int) (bits >> 52);
      bits = 1 << Math.max(0, exponent - 1);
    }
    return Double.longBitsToDouble(bits);
  }

  public static float ulp(float d) {

    if (Float.isNaN(d)) {
      return d;
    } else if (Float.isInfinite(d)) {
      return Float.POSITIVE_INFINITY;
    }

    int bits = Float.floatToIntBits(d);
    bits &= 0x7F800000;
    if (bits >= 24L << 23L) {
      bits -= 23L << 23L;
    } else {
      int exponent = bits >> 23;
      bits = 1 << Math.max(0, exponent - 1);
    }
    return Float.intBitsToFloat(bits);
  }

  public static double signum(double d) {

    return d > 0 ? 1 : d < -0 ? -1 : d;
  }

  public static float signum(float d) {

    return d > 0 ? 1 : d < -0 ? -1 : d;
  }

  public static float copySign(float magnitude, float sign) {

    if (sign == 0 || sign == -0) {
      return sign;
    }
    return (sign > 0) == (magnitude > 0) ? magnitude : -magnitude;
  }

  public static double copySign(double magnitude, double sign) {

    if (sign == 0 || sign == -0) {
      return sign;
    }
    return (sign > 0) == (magnitude > 0) ? magnitude : -magnitude;
  }

  public static int getExponent(double d) {

    long bits = Double.doubleToLongBits(d);
    int exponent = (int) ((bits >> 52) & 0x7FF);
    return exponent - 1023;
  }

  public static int getExponent(float f) {

    int bits = Float.floatToIntBits(f);
    int exponent = (bits >> 23) & 0xF;
    return exponent + 128;
  }

  public static double nextAfter(double start, double direction) {

    if (start == direction) {
      return direction;
    }
    return direction > start ? nextUp(start) : nextDown(start);
  }

  public static float nextAfter(float start, double direction) {

    if (start == direction) {
      return start;
    }
    return direction > start ? nextUp(start) : nextDown(start);
  }

  public static double nextUp(double d) {

    if (Double.isNaN(d)) {
      return d;
    }
    if (d == Double.POSITIVE_INFINITY) {
      return d;
    }
    long bits = Double.doubleToLongBits(d);
    boolean negative = (bits & (1L << 63)) != 0;
    if (negative) {
      bits--;
    } else {
      bits++;
    }
    return Double.longBitsToDouble(bits);
  }

  public static float nextUp(float d) {

    if (Float.isNaN(d)) {
      return d;
    }
    if (d == Float.POSITIVE_INFINITY) {
      return d;
    }
    int bits = Float.floatToIntBits(d);
    boolean negative = (bits & (1L << 31)) != 0;
    if (negative) {
      bits--;
    } else {
      bits++;
    }
    return Float.intBitsToFloat(bits);
  }

  public static double nextDown(double d) {

    if (Double.isNaN(d)) {
      return d;
    }
    if (d == Double.NEGATIVE_INFINITY) {
      return d;
    }
    long bits = Double.doubleToLongBits(d);
    boolean negative = (bits & (1L << 63)) != 0;
    if (negative) {
      bits++;
    } else {
      bits--;
    }
    return Double.longBitsToDouble(bits);
  }

  public static float nextDown(float d) {

    if (Float.isNaN(d)) {
      return d;
    }
    if (d == Float.POSITIVE_INFINITY) {
      return d;
    }
    int bits = Float.floatToIntBits(d);
    boolean negative = (bits & (1L << 31)) != 0;
    if (negative) {
      bits++;
    } else {
      bits--;
    }
    return Float.intBitsToFloat(bits);
  }
}