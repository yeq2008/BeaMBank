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
 * @author �����
 */
public class PictureUtils {
	private final static int compressVal = 75;

	/**
	 * ���浽����
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
	 * ����·��ɾ��ͼƬ
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
	 * ��ȡ�ļ���չ��
	 * 
	 * @param filename
	 * @return �����ļ���չ��
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
