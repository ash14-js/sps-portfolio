
package com.google.sps.data;

/** An object for a comment. */
public final class Task {

  private final long id;
  private final String text;

  public Task(long id, String text) {
    this.id = id;
    this.text = text;
  }
}