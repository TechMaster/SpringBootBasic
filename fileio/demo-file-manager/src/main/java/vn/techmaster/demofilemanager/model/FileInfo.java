package vn.techmaster.demofilemanager.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FileInfo {
	private String name;
	private long size;
	private String rootDirName;
	private String dirPath;
	private String encName;
	private boolean isDir;
}
