package vn.techmaster.demoupload.model;

public enum Status {
  NEW("New Bug"), 
  FIXED("Bug is fixed"), 
  FORWARD("Forward bug to other");

  private final String displayValue;

  private Status(String displayValue) {
    this.displayValue = displayValue;
  }

  public String getDisplayValue() {
    return displayValue;
  }
}
