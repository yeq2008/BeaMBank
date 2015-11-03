/**
 * 
 */
package com.bea.image;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import xc.android.application.XCApplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.widget.ImageView;


/**
 * @author cuiyuguo
 *
 */
public class ImageLoader implements Runnable {
	public static final int HANDLER_MESSAGE_ID = 0;
	public static final String BITMAP_EXTRA = "extra_bitmap";
	public static final String IMAGE_URL_EXTRA = "extra_image_url";

	private static ThreadPoolExecutor executor;
	
	ImageCache mImageCache = new ImageCache();
	private ImageLoaderHandler handler = null;
	private ImageLoader(ImageLoaderHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public void run() {
		Bitmap bitmap = mImageCache.get(handler.getImageUrl(), handler.getWidth(), handler.getHeight());

		// TODO: gracefully handle this case.
		notifyImageLoaded(handler.getImageUrl(), bitmap);
	}

	public void notifyImageLoaded(String url, Bitmap bitmap) {
		Message message = new Message();
		message.what = HANDLER_MESSAGE_ID;
		Bundle data = new Bundle();
		data.putString(IMAGE_URL_EXTRA, url);
		Bitmap image = bitmap;
		data.putParcelable(BITMAP_EXTRA, image);
		message.setData(data);

		handler.sendMessage(message);
	}
	
	public static void load(String imageKey, ImageView imageView, int width,
			int height) {
		load(imageKey, imageView, new ImageLoaderHandler(imageView, imageKey,
				width, height), null, null);
	}
	
	public static void load(String imageKey, ImageView imageView, int width,
			int height, Drawable dummyDrawable) {
		load(imageKey, imageView, new ImageLoaderHandler(imageView, imageKey,
				width, height), null, dummyDrawable);
	}
	
	public static void load(String imageKey, ImageView imageView, int width,
			int height, int dummyDrawableResId) {
		load(imageKey, imageView, new ImageLoaderHandler(imageView, imageKey, width, height), null, 
		XCApplication.getInstance().getResources().getDrawable(dummyDrawableResId));
	}
	

	private static void load(String imageKey, ImageView imageView,
			ImageLoaderHandler handler, Drawable dummyDrawable,
			Drawable errorDrawable) {
		if (imageView != null) {
			if (imageKey == null) {
				// In a ListView views are reused, so we must be sure to remove
				// the tag that could
				// have been set to the ImageView to prevent that the wrong
				// image is set.
				imageView.setTag(ImageLoaderHandler.ImageViewTag_LoadUse, null);
				imageView.setImageDrawable(dummyDrawable);
				return;
			}
			String oldImageUrl = (String) imageView.getTag(ImageLoaderHandler.ImageViewTag_LoadUse);
			if (imageKey.equals(oldImageUrl)) {
				// nothing to do
				// Log.w("---imageurl----", oldImageUrl);
				return;
			} else {
				// Set the dummy image while waiting for the actual image to be
				// downloaded.
				imageView.setImageDrawable(dummyDrawable);
				imageView.setTag(ImageLoaderHandler.ImageViewTag_LoadUse, imageKey);
			}
			if (null == executor) {
				executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
			}
			executor.execute(new ImageLoader(handler));
		}
	}
}
