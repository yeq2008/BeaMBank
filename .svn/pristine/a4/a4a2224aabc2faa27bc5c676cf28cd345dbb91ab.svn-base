/**
 * 
 */
package com.bea.mbank.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import xc.android.utils.XCMD5;
import xc.android.utils.XCToolkit;

import com.bea.application.BeaAppSettings;
import com.bea.application.BeaApplication;
import com.bea.mbank.R;
import com.bea.remote.models.grwdy.PhotosItem;

import xc.android.file.XCFile;

import android.graphics.Bitmap;
import android.text.TextUtils;

/**
 * @author cuiyuguo
 * 
 */
public final class FileUtils {
	public static String photoDir = BeaApplication.workPath()
			+ File.separator + BeaAppSettings.photoPath()
			+ File.separator + "photozips";
	public static String photoTempDir = BeaApplication.workPath()
			+ File.separator + BeaAppSettings.photoPath()
			+ File.separator + "phototemp";
	public static String fileDownloadDir = BeaApplication.workPath()
			+ File.separator + BeaAppSettings.photoPath()
			+ File.separator + "download";
	public static String productShowDir = BeaApplication.workPath()
			+ File.separator + BeaAppSettings.photoPath()
			+ File.separator + "prodtemp";
	
	public static String tempTakePhotoFile() {
		String filePath = photoDir + File.separator + "photoTemp.jpg";
		final File cameraDir = XCFile.create(photoDir);
		if (!cameraDir.exists()) {
			cameraDir.mkdirs();
		}
		return filePath;
	}
	//本地编辑保存的图片及下载的压缩包解压的图片都保存在photoTempDir下
	public static String savePhotoFile(String fileName, Bitmap image) {
		if (null == fileName||null == image) {
			return null;
		}

		if (!TextUtils.isEmpty(photoTempDir)) {
			File dir = XCFile.create(photoTempDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		String filePath = photoTempDir + File.separator + fileName;
		PictureUtils.saveBitmap(image, filePath);

		return filePath;
	}
	private static Bitmap watermarkBitmap = null;
	private static Bitmap getWatermark() {
		if (null == watermarkBitmap) {
			watermarkBitmap = XCToolkit.drawabletoBitmap(BeaApplication.getInstance()
					                   .getResources().getDrawable(R.drawable.icon));
		}
		return watermarkBitmap;
	}
	//本地编辑保存的图片及下载的压缩包解压的图片都保存在photoTempDir下,图片加水印
	public static String savePhotoFile(String fileName, Bitmap image, boolean isWatermark) {
		if (null == fileName||null == image) {
			return null;
		}

		if (!TextUtils.isEmpty(photoTempDir)) {
			File dir = XCFile.create(photoTempDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
		if (isWatermark) {
			image = XCToolkit.watermarkBitmap(image, getWatermark(), null);
		}
		String filePath = photoTempDir + File.separator + fileName;
		PictureUtils.saveBitmap(image, filePath);

		return filePath;
	}

	public static void removePhotoPath(String path) {
		delete(XCFile.create(path));
	}

	public static void removePhotoFile(String fileName) {
		if (null == fileName) {
			return;
		}

		delete(XCFile.create(photoTempDir + File.separator + fileName));
	}
	
	public static void delete(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}

		if (file.isDirectory()) {
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				file.delete();
				return;
			}

			for (int i = 0; i < childFiles.length; i++) {
				delete(childFiles[i]);
			}
			file.delete();
		}
	}
	//从路径中提取文件名
	public static String getFileName(String pathandname){  
        int start=pathandname.lastIndexOf("/");  
        int end=pathandname.lastIndexOf(".");  
        if(start!=-1 && end!=-1){  
            return pathandname.substring(start+1);    
        } else if (start == -1 && end!=-1) {
        	return pathandname;
        } else {  
            return null;  
        }  
    } 
	
	public static boolean isPhotoZipExist(String zipUrl) {
		if (null == zipUrl) {
			return false;
		}

		return XCFile.create(photoDir, getFileName(zipUrl)).exists();
	}
	public static String photoPath(String fileName) {
		File dir = XCFile.create(photoDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (null == fileName) {
			return photoDir;
		} else {
			return photoDir + File.separator + getFileName(fileName);
		}
	}
	public static String photoTempPath(String fileName) {
		File dir = XCFile.create(photoTempDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (null == fileName) {
			return photoTempDir;
		} else {
			return photoTempDir + File.separator + getFileName(fileName);
		}
	}
	
	public static String downloadPath(String fileName) {
		File dir = XCFile.create(fileDownloadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (null == fileName) {
			return fileDownloadDir;
		} else {
			return fileDownloadDir + File.separator + getFileName(fileName);
		}
	}
	
	public static String productTempPath(String fileName) {
		File dir = new File(productShowDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (null == fileName) {
			return productShowDir;
		} else {
			return productShowDir + File.separator + getFileName(fileName);
		}
	}
	
	public static String zipPhotos(List<PhotosItem> photos) {
		if (null == photos || photos.size()<=0) {
			return null;
		}
		List<String> pts = new ArrayList<String>();
		for (PhotosItem item : photos) {
			pts.add(photoTempDir + File.separator +item.photo);
		}
		try {
			File dir = XCFile.create(photoDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			String zipFilePath = photoDir+File.separator+XCMD5.MD5(pts.toString())+".zip";
			ZipUtil.zipFiles(pts, zipFilePath);
			return zipFilePath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
