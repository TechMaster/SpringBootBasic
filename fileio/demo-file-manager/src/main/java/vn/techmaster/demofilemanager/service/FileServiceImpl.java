package vn.techmaster.demofilemanager.service;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import vn.techmaster.demofilemanager.config.StorageProperties;
import vn.techmaster.demofilemanager.error.FileManagerException;
import vn.techmaster.demofilemanager.error.FileManagerFileNotFoundException;
import vn.techmaster.demofilemanager.model.FileInfo;
import vn.techmaster.demofilemanager.utils.FileManagerUtils;

@Slf4j
@Service
public class FileServiceImpl implements FileService {
	public static final String[] fs = { DIR_NAME, SAFE_DIR_NAME };
	private final Path rootLocation;

	@Autowired
	public FileServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public List<FileInfo> findAll(String rootDirName, String dirPath, FileFilter fileFilter) {
		List<FileInfo> fileInfos = new ArrayList<>();

		Path directory = FileManagerUtils.getRealAbsoluteFilePath(rootLocation, rootDirName, dirPath);
		if (Files.exists(directory)) {
			File[] files = directory.toFile().listFiles(fileFilter);
			List<FileInfo> dirInfos = new ArrayList<>();
			for (File file : files) {
				FileInfo fileInfo = FileManagerUtils.getFileInfo(rootDirName, dirPath, file);
				if (fileInfo.isDir()) {
					dirInfos.add(fileInfo);
				} else {
					fileInfos.add(fileInfo);
				}
			}
			fileInfos.addAll(dirInfos);
		}
		return fileInfos;
	}

	@Override
	public List<FileInfo> findAllRecursive(String rootDirName, String dirPath, FileFilter fileFilter) {
		List<FileInfo> fileInfos = findAll(rootDirName, dirPath, fileFilter);
		ListIterator<FileInfo> fileInfoInterator = fileInfos.listIterator();
		while (fileInfoInterator.hasNext()) {
			FileInfo currentFile = fileInfoInterator.next();
			if (currentFile.isDir()) {
				String subDirPath = Paths.get(currentFile.getDirPath(), currentFile.getName()).toString();
				List<FileInfo> subFileInfos = findAllRecursive(rootDirName, subDirPath, fileFilter);
				if (!CollectionUtils.isEmpty(subFileInfos)) {
					subFileInfos.forEach(fileInfoInterator::add);
				}
			}
		}
		return fileInfos;
	}

	@Override
	public FileInfo findOne(String rootDirName, String dirPath, String fileName) {
		if (FileManagerUtils.isUnSafe(fileName)) {
			return null;
		}
		Path checkingPath = FileManagerUtils.getRealAbsoluteFilePath(rootLocation, rootDirName, dirPath, fileName);
		if (!Files.exists(checkingPath)) {
			return null;
		}
		return FileManagerUtils.getFileInfo(rootDirName, dirPath, checkingPath.toFile());
	}

	@Override
	public Resource loadAsResource(String rootDirName, String dirPath, String fileName) {
		try {
			Path file = FileManagerUtils.getRealAbsoluteFilePath(rootLocation, rootDirName, dirPath, fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new FileManagerFileNotFoundException(
						"Could not read file: " + fileName);
			}
		}
		catch (MalformedURLException e) {
			throw new FileManagerFileNotFoundException("Could not read file: " + fileName, e);
		}
	}

	@Override
	public List<FileInfo> saveAll(String rootDirName, String dirPath, MultipartFile[] mpFiles) {
		List<FileInfo> fileInfos = new ArrayList<>();

		try {
			Path realDirPath = FileManagerUtils.getRealAbsoluteFilePath(rootLocation, rootDirName, dirPath);
			// Creates a new directory and parent directories that do not exist to upload
			// file into.
			Files.createDirectories(realDirPath);

			for (MultipartFile mpFile : mpFiles) {
				if (mpFile == null || mpFile.isEmpty() || FileManagerUtils.isUnSafe(mpFile.getOriginalFilename())) {
					continue;
				}
				FileInfo fileInfo = findOne(rootDirName, dirPath, mpFile.getOriginalFilename());
				if (fileInfo != null) {
					continue;
				}
				try {
					File file = FileManagerUtils.save(mpFile, realDirPath);
					fileInfo = FileManagerUtils.getFileInfo(rootDirName, dirPath, file);
					fileInfos.add(fileInfo);
				} catch (IOException e) {
					log.error("Fail to upload file.", e);
					throw new FileManagerException("Failed to upload file " + mpFile.getOriginalFilename(), e);
				}
			}
			return fileInfos;
		} catch (IOException e) {
			log.error("Fail to upload file.", e);
			throw new FileManagerException("Failed to upload files", e);
		}
	}

	@Override
	public FileInfo generateDir(String rootDirName, String dirPath, String dirName) {
		Path realDirPath = FileManagerUtils.getRealAbsoluteFilePath(rootLocation, rootDirName, dirPath, dirName);

		FileInfo dirInfo = null;
		try {
			Files.createDirectories(realDirPath);
			dirInfo = FileManagerUtils.getFileInfo(rootDirName, dirPath, realDirPath.toFile());
		} catch (Exception e) {
			log.error("Fail to create dir " + dirName, e);
			throw new FileManagerException("Failed to create dir " +  dirName, e);
		}
		return dirInfo;
	}

	@Override
	public boolean delete(String rootDirName, String dirPath, String fileName) {
		if (FileManagerUtils.isUnSafe(fileName))
			return false;

		Path realDirPath = FileManagerUtils.getRealAbsoluteFilePath(rootLocation, rootDirName, dirPath, fileName);
		/*
		 * Files.walk(pathToBeDeleted) .sorted(Comparator.reverseOrder())
		 * .map(Path::toFile) .forEach(File::delete);
		 */
		try {
			return FileSystemUtils.deleteRecursively(realDirPath);
		} catch (IOException e) {
			log.error("Fail to delete " + fileName, e);
			return false;
		}
	}
}
