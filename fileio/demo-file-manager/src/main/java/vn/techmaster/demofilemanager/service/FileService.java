package vn.techmaster.demofilemanager.service;

import java.io.FileFilter;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.demofilemanager.model.FileInfo;

public interface FileService {
	/**
	 * Default rootDirName
	 */
	public static final String DIR_NAME = "files";
	/**
	 * Safe rootDirName using for administrator
	 */
	public static final String SAFE_DIR_NAME = "safe";
	List<FileInfo> findAll(String rootDirName, String dirPath, FileFilter fileFilter);
	List<FileInfo> findAllRecursive(String rootDirName, String dirPath, FileFilter fileFilter);
	FileInfo findOne(String rootDirName, String dirPath, String fileName);
	Resource loadAsResource(String rootDirName, String dirPath, String fileName);

	List<FileInfo> saveAll(String rootDirName, String dirPath, MultipartFile[] mpFiles);
	FileInfo generateDir(String rootDirName, String dirPath, String dirName);
	boolean delete(String rootDirName, String dirPath, String fileName);

}
