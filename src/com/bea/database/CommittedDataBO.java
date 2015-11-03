/**
 * 
 */
package com.bea.database;

import com.bea.remote.models.draft.CommitListBO;

import xc.android.sqlite.annotation.Id;
import xc.android.sqlite.annotation.Table;
import xc.android.utils.XCMD5;

/**
 * @author cuiyuguo
 *	本地缓存的待发件数据
 */
@Table(name = "LocalCommited")
public class CommittedDataBO extends CommitListBO {
	@Id
	String primKey;
	public String getPrimKey() {
		return XCMD5.MD5(getCADate()+getListinfo().toString());
	}

	
}
