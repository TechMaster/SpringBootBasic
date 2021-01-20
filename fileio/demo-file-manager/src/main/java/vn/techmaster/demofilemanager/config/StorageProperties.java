package vn.techmaster.demofilemanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

@ConfigurationProperties("storage")
public class StorageProperties {
	/**
	 * Folder location for storing files
	 */
	@Getter
	private String location = "E:/techmaster/fileuploadtest";

}
