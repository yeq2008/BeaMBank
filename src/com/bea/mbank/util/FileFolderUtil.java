/**
 * 
 */
package com.bea.mbank.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import xc.android.file.XCFile;



/**
 * @author cuiyuguo
 *
 */
public final class FileFolderUtil {
	//删除beforeDate之前的所有文件
	public static void clearFolder(final String folder, final Date beforeDate) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				clear(folder, beforeDate);
			}
		}).start();
	}
	
	private static void clear(String folder, Date beforeDate) {
		File f = XCFile.create(folder);
		File[] files = f.listFiles();
		if (files == null) {
			return;
		}

		// 获取文件列表,这里是关键呢
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.setTime(beforeDate);
		for (File file : files) {
			if(file.isDirectory()){
				if (file.getPath().equals(FileUtils.photoTempDir)) {
					FileUtils.removePhotoPath(FileUtils.photoTempDir);
				} else {
					clear(file.getPath(), beforeDate);
				}
			} else {  
		        long time = file.lastModified();  
		        Calendar createCal = Calendar.getInstance();
		        createCal.setTimeInMillis(time); 
		        if (createCal.before(beforeCal)) {
		        	file.delete();
				}
			}
		}
	}
}
