/**
 * 
 */
package com.bea.image;

import java.io.File;
import java.io.IOException;
import xc.android.file.XCFile;
import xc.android.file.XCFileInputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * @author cuiyuguo
 * 
 */
public class ImageCache {
	LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>(10);
	
	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {

		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}

			// This offers some additional logic in case the image has a strange
			// aspect ratio. For example, a panorama may have a much larger
			// width than height. In these cases the total pixels might still
			// end up being too large to fit comfortably in memory, so we should
			// be more aggressive with sample down the image (=larger
			// inSampleSize).

			final float totalPixels = width * height;

			// Anything more than 2x the requested pixels we'll sample down
			// further.
			final float totalReqPixelsCap = reqWidth * reqHeight * 2;

			while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
				inSampleSize++;
			}
		}
		return inSampleSize;
	}

	private File getFileForKey(String key) {
        return XCFile.create(key);
    }
	public synchronized Bitmap get(String key, int width, int height) {
		Bitmap value = null;
		if (null != (value=mLruCache.get(key))) {
			return value;
		}

		// memory miss, try reading from disk
		File file = getFileForKey(key);
		
		if (file.exists()) {
			// Log.d("--Bitmap--", "DISK cache hit for " + key.toString());
			try {
				value = readValueFromDisk(file, width, height);
			} catch (Exception e) {
				// treat decoding errors as a cache miss
				e.printStackTrace();
				return null;
			}
			if (value == null) {
				return null;
			}
			mLruCache.put(key, value);
		}

		// cache miss
		return value;
	}

	static private Bitmap readValueFromDisk(File file, int width, int height)
			throws Exception {
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			throw new IOException("Cannot read files larger than "
					+ Integer.MAX_VALUE + " bytes");
		}

		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(XCFileInputStream.create(file), null, opts);

		int inSampleSize = 1;
		if (width == Integer.MAX_VALUE && height == Integer.MAX_VALUE) {
			inSampleSize = 1;
		} else if (width == Integer.MAX_VALUE) {
			inSampleSize = computeInitialSampleSize(opts, -1, height);
		} else if (height == Integer.MAX_VALUE) {
			inSampleSize = computeInitialSampleSize(opts, -1, width);
		} else {
			inSampleSize = calculateInSampleSize(opts, width, height);
		}

		opts.inJustDecodeBounds = false;
		opts.inSampleSize = inSampleSize;
		opts.inPreferredConfig = Bitmap.Config.RGB_565;
		opts.inPurgeable = true;
		opts.inInputShareable = true;

		return BitmapFactory.decodeStream(XCFileInputStream.create(file), null, opts);
	}
	
	
}
