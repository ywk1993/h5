package com.muzhi.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtil {

	public static String getStackStr(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
}
