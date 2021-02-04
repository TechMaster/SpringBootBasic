package vn.techmaster.demoupload.service;

import vn.techmaster.demoupload.controller.request.BugRequest;
import vn.techmaster.demoupload.exception.BugException;

public interface IBugService {
  public void createNewBug(BugRequest bugRequest) throws BugException;
}
