package com;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class test {

	public static void main(String args[]) {
		String parentPath = "/eerte/dfds";
		String filePath = parentPath.substring(0, parentPath.lastIndexOf("/"))
				.trim();
		System.out.println("the filepath is:" + filePath + "/");
		System.out.println(new Date());
		Map resultMap = new HashMap();
		int successCount = 4;// 成功上传条数
		int failCount = 4;// 上传失败条数
		resultMap.put("successCount", successCount);
		resultMap.put("failCount", failCount);
		int x = Integer.parseInt(resultMap.get("successCount").toString());
		System.out.println(x);
	}

}
