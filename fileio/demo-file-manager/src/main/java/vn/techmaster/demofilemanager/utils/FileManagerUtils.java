package vn.techmaster.demofilemanager.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.demofilemanager.model.FileInfo;

public class FileManagerUtils {
	/**
	 * Convert to FileInfo
	 */
	public static FileInfo getFileInfo(String rootDirName, String dirPath, File file) {
		if (file == null)
			return null;

		return FileInfo.builder().name(file.getName()).size(file.length() / 1024).dirPath(dirPath)
				.rootDirName(rootDirName).encName(encodeURI(file.getName())).isDir(file.isDirectory()).build();
	}

	/**
	 * JS encodeURI
	 */
	public static String encodeURI(String src) {
		String dest = null;
		try {
			dest = URLEncoder.encode(src, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!")
					.replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")")
					.replaceAll("\\%7E", "~");
		} catch (UnsupportedEncodingException e) {
			dest = src;
		}
		return dest;
	}

	/**
	 * Save received file
	 * 
	 * @param mpFile      uploaded file
	 * @param realDirPath file path to save
	 * @return saved file
	 */
	public static File save(MultipartFile mpFile, Path realDirPath) throws IOException {
		File file = realDirPath.resolve(Paths.get(mpFile.getOriginalFilename())).normalize().toAbsolutePath()
				.toFile();
		mpFile.transferTo(file);
		return file;
	}

	/**
	 * Check unsafe file name
	 * 
	 * @param file name to check
	 * @return if unsafe true else false
	 */
	public static boolean isUnSafe(String filename) {
		// /.. or ../ or \.. or ..\
		Pattern unsafeFilenamePtn = Pattern.compile("/(\\/\\.\\.)|(\\.\\.\\/)|(\\\\\\.\\.)|(\\.\\.\\\\)/");
		return unsafeFilenamePtn.matcher(filename).matches();
	}

	public static Path getRealAbsoluteFilePath(Path rootPath, String rootDirName, String... dirPath) {
		return rootPath.resolve(Paths.get(rootDirName, dirPath)).normalize().toAbsolutePath();
	}
}
