/**
 * 
 */
package com.bea.mbank.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import xc.android.sqlite.annotation.Table;

import dalvik.system.DexFile;

import android.content.Context;

/**
 * @author cuiyuguo
 * 
 */
public final class ClassUtil {
	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static Map<String, Class<?>> getCFromPackage(Context context, String packageName) {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();
		try {
			DexFile df = new DexFile(context.getPackageCodePath());
			Enumeration<String> entries = df.entries();
			while (entries.hasMoreElements()) {
				String className = entries.nextElement();
				if (className.contains(packageName)) {
					try {
						Class cl = Class.forName(className);
						String clName = ((Table)cl.getAnnotation(Table.class)).name();
						map.put(clName, Class.forName(className));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
