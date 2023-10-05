package org.teavm.classlib.java.text;

/**
 * Implementation of {@link TCharacterIterator} to iterate the characters of a String.
 */
public final class TStringCharacterIterator implements TCharacterIterator {
  private String text;

  private int begin;

  private int end;

  private int pos;

  public TStringCharacterIterator(String text) {

    this(text, 0);
  }

  public TStringCharacterIterator(String text, int pos) {

    this(text, 0, text.length(), pos);
  }

  public TStringCharacterIterator(String text, int begin, int end, int pos) {

    if (text == null) {
      throw new NullPointerException();
    }
    this.text = text;

    if (begin < 0 || begin > end || end > text.length()) {
      throw new IllegalArgumentException("Invalid substring range");
    }

    if (pos < begin || pos > end) {
      throw new IllegalArgumentException("Invalid position");
    }
    this.begin = begin;
    this.end = end;
    this.pos = pos;
  }

  public void setText(String text) {

    if (text == null)
      throw new NullPointerException();
    this.text = text;
    this.begin = 0;
    this.end = text.length();
    this.pos = 0;
  }

  @Override
  public char first() {

    this.pos = this.begin;
    return current();
  }

  @Override
  public char last() {

    if (this.end != this.begin) {
      this.pos = this.end - 1;
    } else {
      this.pos = this.end;
    }
    return current();
  }

  @Override
  public char setIndex(int p) {

    if (p < this.begin || p > this.end)
      throw new IllegalArgumentException("Invalid index");
    this.pos = p;
    return current();
  }

  @Override
  public char current() {

    if (this.pos >= this.begin && this.pos < this.end) {
      return this.text.charAt(this.pos);
    } else {
      return DONE;
    }
  }

  @Override
  public char next() {

    if (this.pos < this.end - 1) {
      this.pos++;
      return this.text.charAt(this.pos);
    } else {
      this.pos = this.end;
      return DONE;
    }
  }

  @Override
  public char previous() {

    if (this.pos > this.begin) {
      this.pos--;
      return this.text.charAt(this.pos);
    } else {
      return DONE;
    }
  }

  @Override
  public int getBeginIndex() {

    return this.begin;
  }

  @Override
  public int getEndIndex() {

    return this.end;
  }

  @Override
  public int getIndex() {

    return this.pos;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if (!(obj instanceof TStringCharacterIterator)) {
      return false;
    }
    TStringCharacterIterator other = (TStringCharacterIterator) obj;
    if (hashCode() != other.hashCode()) {
      return false;
    } else if (!this.text.equals(other.text)) {
      return false;
    } else if (this.pos != other.pos || this.begin != other.begin || this.end != other.end) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {

    return this.text.hashCode() ^ this.pos ^ this.begin ^ this.end;
  }

  @Override
  public Object clone() {

    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e);
    }
  }
}
