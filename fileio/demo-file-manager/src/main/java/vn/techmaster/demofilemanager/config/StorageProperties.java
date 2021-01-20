package vn.techmaster.demofilemanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("storage")
public class StorageProperties {
	/**
	 * Folder location for storing files
	 */
	@Getter
	@Setter
	private String location = "E:/techmaster/fileuploadtest";// Default location

}
