/* Copyright (c) 2009 Matthias Kaeppler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bea.image;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class ImageLoaderHandler extends Handler {
	public final static int ImageViewTag_LoadUse = 0x11111111;
	public final static int ImageViewTag_LoadUnuse = 0x11111112;
    private ImageView imageView;
    private String imageUrl;
    private Drawable errorDrawable;
    private int width, height;

    public ImageLoaderHandler(ImageView imageView, String imageUrl, int width, int height) {
        this.imageView = imageView;
        this.imageUrl = imageUrl;
        this.setWidth(width);
        this.setHeight(height);
    }
    
    public ImageLoaderHandler(ImageView imageView, String imageUrl,
 Drawable errorDrawable, int width, int height) {
        this(imageView, imageUrl, width, height);
        this.errorDrawable = errorDrawable;
    }

    @Override
    public final void handleMessage(Message msg) {
        if (msg.what == ImageLoader.HANDLER_MESSAGE_ID) {
            handleImageLoadedMessage(msg);
        }
    }

    protected final void handleImageLoadedMessage(Message msg) {
        Bundle data = msg.getData();
        Bitmap bitmap = data.getParcelable(ImageLoader.BITMAP_EXTRA);
        handleImageLoaded(bitmap, msg);
    }

    /**
     * Override this method if you need custom handler logic. Note that this method can actually be
     * called directly for performance reasons, in which case the message will be null
     * 
     * @param bitmap
     *            the bitmap returned from the image loader
     * @param msg
     *            the handler message; can be null
     * @return true if the view was updated with the new image, false if it was discarded
     */
    protected boolean handleImageLoaded(Bitmap bitmap, Message msg) {
    	if (null == imageView) {
			return false;
		}
    	//由于是通过线程异步加载，有一定延迟性，当imageview被用在GridView或ListView时，会有
    	//重用机制存在，导致显示问题，需要设置此标志tag
    	Object tagObject = null;
    	if (null != (tagObject=imageView.getTag(ImageViewTag_LoadUnuse)) && 
    		((Boolean)tagObject)) {
			return false;
		}
    	// If this handler is used for loading images in a ListAdapter,
        // the thread will set the image only if it's the right position,
        // otherwise it won't do anything.
        String forUrl = (String) imageView.getTag(ImageViewTag_LoadUse);
        if (imageUrl.equals(forUrl)) {
            Bitmap image = bitmap != null || errorDrawable == null ? bitmap
                    : ((BitmapDrawable) errorDrawable).getBitmap();
            if (image != null) {
            	if (imageView instanceof ImageView) {
            		imageView.setImageBitmap(image);
				} else {
					imageView.setBackgroundDrawable(new BitmapDrawable(image));
				}
            }

            return true;
        }

        return false;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
