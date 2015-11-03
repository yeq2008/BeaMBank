/**
 * 
 */
package com.bea.remote.models.grwdy;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;

/**
 * @author cuiyuguo
 *
 */
@JsonModel
public class PhotosItem implements Serializable {

	// 照片类型定义  
	public static final int CustomPhoto = 1;  
	public static final int FormPhoto = 2;   
	public static final int OtherPhoto = 3;  
	public static final int SignedPhoto = -100;//借款人签名
	public static final int SignedPhotoMate = -101;//借款人配偶签名
	public static final int SignedPhotoJoin = -102;//共同借款人签名
	 
	public String photo;
	public int type;
	public String getPhoto() {
		return photo;
	}
	public int getType() {
		return type;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setType(int type) {
		this.type = type;
	}
}
