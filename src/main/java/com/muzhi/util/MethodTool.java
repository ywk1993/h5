package com.muzhi.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 給所有接口方法添加public方法
 * 
 * @author yany
 *
 */
public class MethodTool {
	public static void main1(String[] args) {
		String str = "int getId(); public int getId(int i);";
		String regex = "public\\s*[A-y]*\\s*[A-y]*\\(";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			String group = m.group();
			System.out.println(group);
			String[] split = str.split(group);
			String replace = str.replace(group, "public " + group);
			System.out.println(replace);
		}
	}

	public static void main(String[] args) {
		String path = "E:/companyWork/SmallTownGame/src/main/java/com/muzhi/service";
		try {
			File f = new File(path);
			File[] listFiles = f.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					if (!name.contains(".java")) {
						return false;
					} else {
						return true;
					}
				}
			});

			for (File file : listFiles) {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file.getAbsolutePath()));
				BufferedReader br = new BufferedReader(isr);
				String readLine = br.readLine();
				StringBuffer sb = new StringBuffer();
				while (null != readLine) {
					sb.append(readLine + "\n");
					readLine = br.readLine();
				}

				// 定义 Java方法规则, public void main ();
				// \s*public\s*-key\s*str
				String java = sb.toString();
				String replace = sb.toString();
				if (java.contains("interface")) {
					String customString = "[A-y|<|>|,]*";
					String empty = "\\s*";
					// String trueRegex = empty + customString + empty +
					// customString + empty + customString + empty + "\\(";
					String regex = "(?!public)" + empty + customString + empty + customString + empty + "\\(";
					String noReplaceRegex = "public" + empty + customString + empty + customString + empty + "\\(";

					Pattern p1 = Pattern.compile(noReplaceRegex);
					Matcher m1 = p1.matcher(java);
					List<String> nGroups = new ArrayList<String>();
					while (m1.find()) {
						String group = m1.group();
						nGroups.add(group);
					}
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(java);
					List<String> groups = new ArrayList<String>();
					while (m.find()) {
						String group = m.group();
						groups.add(group);
					}

					for (int i = 0; i < groups.size(); i++) {
						String group = groups.get(i);
						boolean flag = true;
						for (String string : nGroups) {
							if (string.contains(group)) {
								flag = false;
								break;
							}

						}
						if (flag) {
							if (group.contains("<") || !group.contains(">")) {
								int index = 0;
								for (int j = 0; j < group.length(); j ++) {
									if (group.charAt(j) != ' ' && group.charAt(j) != '\n' && group.charAt(j) != '\t') {
										index = j;break;
									}
								}
								try{
									String pgroup = group.substring(0,index) + "public " + group.substring(index, group.length());
									replace = replace.replace(group, pgroup);
								} catch(Exception e) {
									System.out.println(e);
								}
								// System.out.println("替换" + replace);
							}
						}
					}

					if (!"".equals(replace)) {
						@SuppressWarnings("resource")
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
						System.out.println(file.getName() + "================:\n" + replace);
						bw.write(replace);
						bw.flush();
						bw.close();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void key() {
		String keyString = "abstract assert boolean break byte case catch "
				+ "char class const continue default do double else enum"
				+ " extends false final finally float for goto if implements "
				+ "import instanceof int interface long native new null "
				+ "package private protected public return short static "
				+ "strictfp super switch synchronized this throw throws true " + "transient try void volatile while";
		String[] keys = keyString.split(" ");
		String keyStr = StringUtils.join(keys, "|");
		System.out.println(keyStr);
		String regex = "\\b(" + keyStr + ")\\b";
		String target = "static public staticpublic void main()";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(target);

		while (m.find()) {
			System.out.println("|" + m.group() + "|");
			System.out.println(m.start());
			System.out.println(m.end());
		}
	}
}
