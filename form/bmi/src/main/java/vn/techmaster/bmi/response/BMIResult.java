package vn.techmaster.bmi.response;

public class BMIResult {
  private float bmiIndex;
  private String category;
  private String recommentation;

  public float getBmiIndex() {
    return bmiIndex;
  }

  public void setBmiIndex(float bmiIndex) {
    this.bmiIndex = bmiIndex;
  }

  public String getRecommentation() {
    return recommentation;
  }

  public void setRecommentation(String recommentation) {
    this.recommentation = recommentation;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public BMIResult(float bmiIndex, String category, String recommentation) {
    this.bmiIndex = bmiIndex;
    this.category = category;
    this.recommentation = recommentation;
  }
  
}