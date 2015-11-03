package com.bea.mbank.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import xc.android.file.XCFile;
import xc.android.file.XCFileOutputStream;
import xc.android.utils.XCLog;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * @author 崔玉国
 */
public class PictureUtils {
	private final static int compressVal = 75;

	/**
	 * 保存到本地
	 * 
	 * @param bitmap
	 */
	public static void saveBitmap(Bitmap bitmap, String savePath) {
		if (null == bitmap || null == savePath) {
			return;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		File file = XCFile.create(savePath);
		try {
			BufferedOutputStream os = new BufferedOutputStream(
					XCFileOutputStream.create(file));

			bitmap.compress(Bitmap.CompressFormat.JPEG, compressVal, baos);
			os.write(baos.toByteArray());

			os.flush();
			os.close();
		} catch (Exception e) {
			XCLog.d("BITMAP", e.getMessage());
		} finally {
			if (bitmap != null) {
				bitmap.recycle();
			}
		}
	}

	/***
	 * 根据路径删除图片
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static boolean deleteTempFile(String path) throws IOException {
		boolean isOk = true;
		File file = XCFile.create(path);
		if (file != null) {
			if (file.exists()) {
				if (!file.delete()) {
					isOk = false;
				}
			}
		}
		return isOk;
	}

	/***
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return 返回文件扩展名
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
}
