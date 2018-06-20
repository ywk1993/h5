package com.muzhi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.alibaba.fastjson.JSON;
import com.muzhi.model.configbean.Config;
import com.muzhi.util.ImportExeclUtil;

import org.apache.poi.ss.usermodel.Workbook;

public class ImportExcelToJson {
	
	public static void main(String[] args) throws IOException, Exception {
		ImportExcelToJson importExcelToJson = new ImportExcelToJson();
		importExcelToJson.toJson();
	}

	public void toJson() throws IOException, Exception {

		String fileName = "config.xlsx";
		File file =new File("D:\\Users\\admin\\Desktop\\config.xlsx");
		if (!file.exists()) {
			String url =System.getProperty("user.dir")+"\\src\\main\\resources\\excelfile\\config.xlsx";
			file =new File(url);
		}
		InputStream in = new FileInputStream(file);
		Workbook wb = ImportExeclUtil.chooseWorkbook(fileName, in);
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		//map.put(0, "com.muzhi.model.configbean.ConfigTask");
		map.put(1, "com.muzhi.model.configbean.ConfigStrength");
		//map.put(2, "com.muzhi.model.configbean.Configfame");
		//map.put(3, "com.muzhi.model.configbean.Configadvertise");
		map.put(4, "com.muzhi.model.configbean.ConfigManor");
		map.put(5, "com.muzhi.model.configbean.ConfigSkill");
		//map.put(6, "com.muzhi.model.configbean.ConfigLove");
		map.put(7, "com.muzhi.model.configbean.ConfigAdvancedProp");
		map.put(8, "com.muzhi.model.configbean.ConfigCookcenter");
		map.put(9, "com.muzhi.model.configbean.ConfigFood");
		map.put(10, "com.muzhi.model.configbean.ConfigResearch");
		//map.put(11, "com.muzhi.model.configbean.ConfigArticle");
		map.put(12, "com.muzhi.model.configbean.ConfigRole");
		//map.put(13, "com.muzhi.model.configbean.ConfigOrder");
		//map.put(14, "com.muzhi.model.configbean.ConfigRate");
		map.put(15, "com.muzhi.model.configbean.ConfigResources");
		map.put(16, "com.muzhi.model.configbean.ConfigDialog");
		map.put(17, "com.muzhi.model.configbean.ConfigFacilities");
		map.put(18, "com.muzhi.model.configbean.ConfigRestaurant");
		
		Config config = new Config();
		Field[] objfield = config.getClass().getDeclaredFields();
		for (Entry<Integer, String> entry : map.entrySet()) {

			Object object = Class.forName(entry.getValue()).newInstance();

			List<Object> readDateListT = ImportExeclUtil.readDateListT(entry.getKey(), wb, object, 2, 0);
			System.out.println(readDateListT.size());

			for (Field field : objfield) {
				field.setAccessible(true);
				if (object.getClass().getSimpleName().toLowerCase().substring(6).equals(field.getName())) {
					field.set(config, readDateListT);
					System.out.println(field.getName());
				}
			}
		}
		Object str2 = JSON.toJSON(config);
		//System.out.println(str2);
		file = new File("D:\\Users\\admin\\Desktop\\config.json");
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			String message = str2.toString();
			byte[] data = message.getBytes();
			out.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
