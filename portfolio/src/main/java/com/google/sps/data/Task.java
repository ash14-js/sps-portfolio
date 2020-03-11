
package com.google.sps.data;

/** An object for a comment. */
public final class Task {

  private final long id;
  private final String text;
  private final String email;

  public Task(long id, String text, String email) {
    this.id = id;
    this.text = text;
    this.email = email;
  }
}