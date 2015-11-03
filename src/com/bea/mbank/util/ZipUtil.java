/**
 * 
 */
package com.bea.mbank.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import xc.android.file.XCFile;
import xc.android.file.XCFileInputStream;
import xc.android.file.XCFileOutputStream;

/**
 * @author cuiyuguo
 * 
 */
public final class ZipUtil {
	private ZipUtil() {}


	/**
	 * 解压一个压缩文档 到指定位置
	 * 
	 * @param zipFileString 压缩包的名字
	 * @param outPathString 指定的路径
	 * @throws Exception
	 */
	public static void unZipFolder(InputStream input, String outPathString)
			throws Exception {
		ZipInputStream inZip = new ZipInputStream(input);
		ZipEntry zipEntry = null;
		String szName = "";

		while ((zipEntry = inZip.getNextEntry()) != null) {
			szName = zipEntry.getName();

			if (zipEntry.isDirectory()) {
				// get the folder name of the widget
				szName = szName.substring(0, szName.length() - 1);
				File folder = XCFile.create(outPathString + File.separator + szName);
				folder.mkdirs();
			} else {
				File file = XCFile.create(outPathString + File.separator + szName);
				// get the output stream of the file
				FileOutputStream out = XCFileOutputStream.create(file);
				int len;
				byte[] buffer = new byte[1024];
				// read (len) bytes into buffer
				while ((len = inZip.read(buffer)) != -1) {
					// write (len) byte from buffer at the position 0
					out.write(buffer, 0, len);
					out.flush();
				}
				out.close();
			}
		}// end of while
		inZip.close();
	}

	/**
	 * 解压一个压缩文档 到指定位置
	 * 
	 * @param zipFileString 压缩包的名字
	 * @param outPathString 指定的路径
	 * @throws Exception
	 */
	public static void unZipFolder(String zipFileString, String outPathString)
			throws Exception {
		
		unZipFolder(XCFileInputStream.create(zipFileString), outPathString);
	}// end of func

	/**
	 * 压缩文件,文件夹
	 * 
	 * @param srcFilePath 要压缩的文件/文件夹名字
	 * @param zipFilePath 指定压缩的目的和名字
	 * @throws Exception
	 */
	public static void zipFolder(String srcFilePath, String zipFilePath)
			throws Exception {
		// 创建Zip包
		ZipOutputStream outZip = new ZipOutputStream(XCFileOutputStream.create(zipFilePath));

		// 打开要输出的文件
		File file = XCFile.create(srcFilePath);

		// 压缩
		zipFiles(file.getParent() + File.separator, file.getName(), outZip);

		// 完成,关闭
		outZip.finish();
		outZip.close();

	}// end of func

	/**
	 * 压缩文件,文件夹
	 * 
	 * @param srcFilePath 要压缩的文件/文件夹名字
	 * @param zipFilePath 指定压缩的目的和名字
	 * @throws Exception
	 */
	public static void zipFiles(List<String> files, String zipFilePath)
			throws Exception {
		// 创建Zip包
		ZipOutputStream outZip = new ZipOutputStream(XCFileOutputStream.create(zipFilePath));

		// 打开要输出的文件
		for (String f : files) {
			File file = XCFile.create(f);
			// 压缩
			zipFiles(f.replace(file.getName(), ""), file.getName(), outZip);
		}
		
		// 完成,关闭
		outZip.finish();
		outZip.close();

	}// end of func
	
	/**
	 * 压缩文件
	 * 
	 * @param folderPath
	 * @param filePath
	 * @param zipOut
	 * @throws Exception
	 */
	public static void zipFiles(String folderPath, String filePath, ZipOutputStream zipOut) throws Exception {
		if (zipOut == null) {
			return;
		}

		File file = XCFile.create(folderPath + filePath);

		// 判断是不是文件
		if (file.isFile()) {
			ZipEntry zipEntry = new ZipEntry(filePath);
			FileInputStream inputStream = XCFileInputStream.create(file);
			zipOut.putNextEntry(zipEntry);

			int len;
			byte[] buffer = new byte[4096];

			while ((len = inputStream.read(buffer)) != -1) {
				zipOut.write(buffer, 0, len);
			}

			inputStream.close();
			zipOut.closeEntry();
		} else if (file.isDirectory()) {
			// 文件夹的方式,获取文件夹下的子文件
			String fileList[] = file.list();

			// 如果没有子文件, 则添加进去即可
			if (fileList.length <= 0) {
				ZipEntry zipEntry = new ZipEntry(filePath + File.separator);
				zipOut.putNextEntry(zipEntry);
				zipOut.closeEntry();
			}

			// 如果有子文件, 遍历子文件
			for (int i = 0; i < fileList.length; i++) {
				zipFiles(folderPath, filePath + File.separator + fileList[i], zipOut);
			}// end of for

		}// end of if

	}// end of func
}
