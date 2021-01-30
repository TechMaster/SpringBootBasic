package vn.techmaster.blog.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paging {
  public String title;
  public int index;
  public boolean isCurrent;
  public Paging(String title, int index, boolean isCurrent) {
    this.title = title;
    this.index = index;
    this.isCurrent = isCurrent;
  }

  public static List<Paging> generatePages(int selectedPage, int totalPages) {
    //https://codereview.stackexchange.com/questions/240235/java-pagination-algorithm
    int offset = 5;
    // set start index relative to selected  
    int start = selectedPage - (offset / 2);
    // adjust for first pages   
    start = Math.max(start, 0);
    // set end index relative to start    
    int end = start + offset;
    // adjust start and end for last pages     
    if (end > totalPages - 1) {
        end = totalPages - 1;
        start = end - offset + 1;
    }
    ArrayList<Paging> pagings = new ArrayList<>();
    for (int i = start; i < end; i++){
      Paging paging = new Paging()
    }


  }
}
