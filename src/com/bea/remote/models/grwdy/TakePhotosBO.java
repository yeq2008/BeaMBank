/**
 * 
 */
package com.bea.remote.models.grwdy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xc.android.remote.json.JsonModel;
import xc.android.utils.XCJsonHelper;

/**
 * @author cuiyuguo
 * 影像采集数据模型
 */
@JsonModel
public final class TakePhotosBO implements Serializable {
	private static final long serialVersionUID = -7403843378264024304L;

	//图片打成zip包上传后，服务器回传的地址
	public String zipFileUrl;

	private int busiType = 0;
	private List<PhotosItem> photos = new ArrayList<PhotosItem>();
	private List<PhotosItem> photosT = new ArrayList<PhotosItem>();
	public void removePhotos(PhotosItem item) {
		photos.remove(item);
		photosT.remove(item);
	}
	public List<PhotosItem> photos() {
		return photosT;
	}
	public List<PhotosItem> getPhotos() {
		return photos;
	}
	public void setPhotos(List<PhotosItem> photos) {
		this.photos = photos;
		photosT = new ArrayList<PhotosItem>();
		for (PhotosItem p : photos) {
			if (p.getType() != PhotosItem.SignedPhoto
					&& p.getType() != PhotosItem.SignedPhotoMate
					&& p.getType() != PhotosItem.SignedPhotoJoin) {
				photosT.add(p);
			}
		}
		oldPhotos = XCJsonHelper.toJSONString(photos);
	}
	public String getZipFileUrl() {
		return zipFileUrl;
	}
	public void setZipFileUrl(String zipFileUrl) {
		oldPhotos = XCJsonHelper.toJSONString(photos);
		this.zipFileUrl = zipFileUrl;
	}
	
	private String oldPhotos = XCJsonHelper.toJSONString(photos);
	public boolean isChanged() {
		return !XCJsonHelper.toJSONString(photos).equals(oldPhotos);
	}
	public void addPhoto(int type, String fileName) {
		if (PhotosItem.SignedPhoto == type) {
			for (int i = photos.size()-1; i >= 0; --i) {
				if (photos.get(i).getType() == PhotosItem.SignedPhoto) {
					photos.get(i).photo = fileName;
					return;
				}
			}
		}
		PhotosItem item = new PhotosItem();
		item.type = type;
		item.photo = fileName;
		photos.add(item);
		if (item.getType() != PhotosItem.SignedPhoto) {
			photosT.add(item);
		}
	}
	public String signedPhoto(int tag) {
		for (PhotosItem item : photos) {
			if (tag == item.getType()) {
				return item.photo;
			}
		}
		return null;
	}
	public int getBusiType() {
		return busiType;
	}
}
