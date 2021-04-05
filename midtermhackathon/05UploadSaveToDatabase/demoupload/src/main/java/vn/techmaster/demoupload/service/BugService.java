package vn.techmaster.demoupload.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.demoupload.controller.request.BugRequest;
import vn.techmaster.demoupload.exception.BugException;
import vn.techmaster.demoupload.exception.StorageException;
import vn.techmaster.demoupload.model.Bug;
import vn.techmaster.demoupload.model.Photo;
import vn.techmaster.demoupload.repository.BugRepository;
import vn.techmaster.demoupload.repository.PhotoRepository;
@Service
public class BugService implements IBugService {
  @Autowired StorageService storageService;

  @Autowired BugRepository bugRepository;
  @Autowired PhotoRepository photoRepository;

  @Override
  @Transactional(rollbackOn = StorageException.class)
  public void createNewBug(BugRequest bugRequest) throws BugException {
    Bug bug = new Bug();
    bug.setTitle(bugRequest.getTitle());
    bug.setStatus(bugRequest.getStatus());
    bugRepository.save(bug);
    long bugId = bug.getId();

    
    for (MultipartFile file : bugRequest.getPhotos()) {
      String newFileName = bugId + "-" + file.getOriginalFilename();
      Photo photo = new Photo(newFileName);
      storageService.uploadFileSaveNewName(file, newFileName);
      bug.addPhoto(photo);
    }
    bugRepository.flush();
  }
  
}
