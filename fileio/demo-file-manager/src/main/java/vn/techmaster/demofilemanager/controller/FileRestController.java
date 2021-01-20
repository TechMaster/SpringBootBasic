package vn.techmaster.demofilemanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import vn.techmaster.demofilemanager.error.FileManagerException;
import vn.techmaster.demofilemanager.error.FileManagerFileNotFoundException;
import vn.techmaster.demofilemanager.model.FileInfo;
import vn.techmaster.demofilemanager.service.FileService;
import vn.techmaster.demofilemanager.utils.HttpSessionWrapper;

@Slf4j
@RestController
public class FileRestController {
	@Autowired
	private FileService fileService;

	/**
	 * Change working directory
	 * 
	 * @param depth   Working directory level to be changed
	 * @param request HttpRequest
	 * @return
	 */
	@GetMapping("/api/dir/{depth}")
	public ResponseEntity<Map<String, Object>> getDir(@PathVariable int depth, HttpServletRequest request) {
		return getDir(depth, "", request);
	}

	/**
	 * Change working directory
	 * 
	 * @param depth   Working directory level to be changed
	 * @param dirName Target working directory name
	 * @param request HttpRequest
	 * @return
	 */
	@GetMapping("/api/dir/{depth}/{dirName}")
	public ResponseEntity<Map<String, Object>> getDir(@PathVariable int depth, @PathVariable String dirName,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		int currentDepth = HttpSessionWrapper.getDepth(session);
		String currentDirPath = HttpSessionWrapper.getDirPath(session);

		String dirPath = "";
		if (currentDepth == depth - 1) {
			// Move to subfolder
			dirPath = currentDirPath + "/" + dirName;
		} else if (currentDepth == depth + 1) {
			// Move to upper folder
			String[] toks = currentDirPath.split("/");
			for (int i = 0; i < toks.length - 1; i++) {
				if (toks[i].length() > 0) {
					dirPath += ("/" + toks[i]);
				}
			}
		} else if (currentDepth != depth) {
			// Mismatch between dirPath stored in session and client dirPath
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			dirPath = currentDirPath;
		}

		HttpSessionWrapper.setDepth(session, depth);
		HttpSessionWrapper.setDirPath(session, dirPath);

		// File information list
		List<FileInfo> fileInfos = fileService.findAll(FileService.DIR_NAME, dirPath, null);
		if (request.isUserInRole("ADMIN"))
			fileInfos.addAll(fileService.findAll(FileService.SAFE_DIR_NAME, dirPath, null));

		Map<String, Object> data = new HashMap<>();
		data.put("depth", depth);
		data.put("dirPath", dirPath);
		data.put("fileInfos", fileInfos);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	/**
	 * Add directory
	 * 
	 * @param dirName Name of directory to be added
	 * @param request HttpRequest
	 * @return
	 */
	@PostMapping("/api/dir/" + FileService.DIR_NAME)
	public ResponseEntity<FileInfo> postDir(@RequestParam String dirName, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);

		FileInfo dirInfo = fileService.generateDir(FileService.DIR_NAME, dirPath, dirName);
		if (dirInfo != null) {
			return new ResponseEntity<>(dirInfo, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Safe Add directory
	 * 
	 * @param dirName Name of directory to be added
	 * @param request HttpRequest
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/dir/" + FileService.SAFE_DIR_NAME)
	public ResponseEntity<FileInfo> postSafeDir(@RequestParam String dirName, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);

		FileInfo dirInfo = fileService.generateDir(FileService.SAFE_DIR_NAME, dirPath, dirName);
		if (dirInfo != null) {
			return new ResponseEntity<>(dirInfo, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 
	 * @param request HttpRequest
	 * @return
	 */
	@GetMapping("/api/files")
	public ResponseEntity<List<FileInfo>> getFiles(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);

		List<FileInfo> fileInfos = fileService.findAll(FileService.DIR_NAME, dirPath, null);
		if (request.isUserInRole("ADMIN")) {
			fileInfos.addAll(fileService.findAll(FileService.SAFE_DIR_NAME, dirPath, null));
		}

		return new ResponseEntity<>(fileInfos, HttpStatus.OK);
	}

	/**
	 * File information
	 * 
	 * @param fileName
	 * @param request  HttpRequest
	 * @return
	 */
	@GetMapping("/api/files/" + FileService.DIR_NAME + "/{fileName}")
	public ResponseEntity<FileInfo> getFile(@PathVariable String fileName, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);

		FileInfo fileInfo = fileService.findOne(FileService.DIR_NAME, dirPath, fileName);
		if (fileInfo == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(fileInfo, HttpStatus.OK);
	}

	/**
	 * File information to download
	 * 
	 * @param fileName
	 * @param request  HttpRequest
	 * @return
	 */
	@GetMapping("/api/files/" + FileService.DIR_NAME + "/{fileName}/fileContentDownload")
	public ResponseEntity<Resource> getFileContentToDownload(@PathVariable String fileName,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);
		Resource file = fileService.loadAsResource(FileService.DIR_NAME, dirPath, fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	/**
	 * Safe File information
	 * 
	 * @param fileName
	 * @param request  HttpRequest
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/files/" + FileService.SAFE_DIR_NAME + "/{fileName}")
	public ResponseEntity<FileInfo> getSafeFile(@PathVariable String fileName, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);
		FileInfo fileInfo = fileService.findOne(FileService.SAFE_DIR_NAME, dirPath, fileName);
		if (fileInfo == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(fileInfo, HttpStatus.OK);
	}

	/**
	 * Safe File information to download
	 * 
	 * @param fileName
	 * @param request  HttpRequest
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/files/" + FileService.SAFE_DIR_NAME + "/{fileName}/fileContentDownload")
	public ResponseEntity<Resource> getSafeFileContentToDownload(@PathVariable String fileName,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);

		Resource file = fileService.loadAsResource(FileService.DIR_NAME, dirPath, fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	/**
	 * File upload
	 * 
	 * @param files   File upload
	 * @param request HttpRequest
	 * @return
	 */
	@PostMapping("/api/files/" + FileService.DIR_NAME)
	public ResponseEntity<List<FileInfo>> postFile(@RequestParam MultipartFile[] files, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);
		try {
			List<FileInfo> fileInfos = fileService.saveAll(FileService.DIR_NAME, dirPath, files);
			log.debug("{} files saved", fileInfos.size());
			return new ResponseEntity<>(fileInfos, HttpStatus.CREATED);
		} catch (IllegalStateException e) {
			log.debug(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Safe File upload
	 * 
	 * @param files
	 * @param request HttpRequest
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/files/" + FileService.SAFE_DIR_NAME)
	public ResponseEntity<List<FileInfo>> postSafeFile(@RequestParam MultipartFile[] files,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);
		/*
		 * if (!request.isUserInRole("ADMIN")) { return new
		 * ResponseEntity<>(HttpStatus.NO_CONTENT); }
		 */
		try {
			List<FileInfo> fileInfos = fileService.saveAll(FileService.SAFE_DIR_NAME, dirPath, files);
			log.debug("{} files saved", fileInfos.size());
			return new ResponseEntity<>(fileInfos, HttpStatus.CREATED);
		} catch (IllegalStateException e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/api/files/" + FileService.DIR_NAME + "/{fileName}")
	public ResponseEntity<Void> deleteFile(@PathVariable String fileName, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);

		if (fileService.delete(FileService.DIR_NAME, dirPath, fileName))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * @param fileName
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/files/" + FileService.SAFE_DIR_NAME + "/{fileName}")
	public ResponseEntity<Void> deleteSafeFile(@PathVariable String fileName, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String dirPath = HttpSessionWrapper.getDirPath(session);

		if (fileService.delete(FileService.SAFE_DIR_NAME, dirPath, fileName)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(FileManagerFileNotFoundException.class)
	public ResponseEntity<?> handleFileManagerFileNotFoundException(FileManagerFileNotFoundException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FileManagerException.class)
	public ResponseEntity<?> handleFileManagerException(FileManagerException exc) {
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
