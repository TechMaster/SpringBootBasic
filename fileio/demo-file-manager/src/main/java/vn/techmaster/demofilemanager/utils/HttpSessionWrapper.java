package vn.techmaster.demofilemanager.utils;

import javax.servlet.http.HttpSession;

public class HttpSessionWrapper {
	private static final String SESSION_KEY_DEPTH = "depth";
	private static final String SESSION_KEY_DIR_PATH = "dirPath";

	public static Integer getDepth(HttpSession session) {
		Integer depth = (Integer) session.getAttribute(SESSION_KEY_DEPTH);
		if (depth == null)
			depth = 0;
		return depth;
	}

	public static void setDepth(HttpSession session, Integer depth) {
		session.setAttribute(SESSION_KEY_DEPTH, depth);
	}

	public static String getDirPath(HttpSession session) {
		String dirPath = (String) session.getAttribute(SESSION_KEY_DIR_PATH);
		if (dirPath == null)
			dirPath = "";
		return dirPath;
	}

	public static void setDirPath(HttpSession session, String dirPath) {
		session.setAttribute(SESSION_KEY_DIR_PATH, dirPath);
	}

}
