/**
 * 
 */
package com.bea.mbank.edit.step1;

import java.io.File;
import java.util.Date;

import xc.android.file.XCFile;
import xc.android.utils.XCSdCardDirUtils;
import xc.android.utils.XCToolkit;
import com.android.uitableview.ViewHolder;
import com.android.widgets.GroupTagView;
import com.bea.image.ImageLoader;
import com.bea.image.ImageLoaderHandler;
import com.bea.image.PZSImageView;
import com.bea.mbank.R;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.mbank.util.FileUtils;
import com.bea.remote.models.grwdy.PhotosItem;
import com.bea.remote.models.grwdy.TakePhotosBO;
import com.cyg.viewinject.annotation.ViewInject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

/**
 * @author 崔玉国 拍照进件页面
 */
public class TakePhotosFragment extends ContentBaseFragment<TakePhotosBO>
		implements OnItemClickListener, OnItemLongClickListener {
	@Override
	protected Class getInfoClass() {
		return TakePhotosBO.class;
	}

	public GridViewAdapter adapter;

	class GridViewAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return 1 + infoBO.photos().size();
		}

		@Override
		public Object getItem(int position) {
			if (infoBO.photos().size() == 0) {
				return null;
			} else {
				if (position < infoBO.photos().size()) {
					return infoBO.photos().get(position);
				} else {
					return null;
				}
			}
		}

		public int getItemType(int position) {
			if (infoBO.photos().size() == 0) {
				return 0;
			} else {
				if (position < infoBO.photos().size()) {
					return infoBO.photos().get(position).type;
				} else {
					return 0;
				}
			}
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();

				convertView = mInflater.inflate(
						R.layout.v_grwdy_takephotos_griditem, null);
				initGridItemViewHolder(convertView, position, holder);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			initGridItem(position, holder);

			return convertView;
		}

		private void initGridItemViewHolder(View convertView, int position,
				ViewHolder holder) {
			holder.holderView(convertView.findViewById(R.id.imageView));
			holder.holderView(convertView.findViewById(R.id.imageType));
			holder.holderView(convertView.findViewById(R.id.createTime));
		}

		private void initGridItem(int position, ViewHolder holder) {
			ImageView imageView = (ImageView) holder
					.findViewById(R.id.imageView);
			ImageView imageType = (ImageView) holder
					.findViewById(R.id.imageType);
			TextView createTime = (TextView) holder
					.findViewById(R.id.createTime);

			int itemSize = gridView.getMeasuredWidth() / mColumns;
			PhotosItem photo = (PhotosItem) getItem(position);
			int type = getItemType(position);
			View typeParentView = (View) imageType.getParent();
			if (position == getCount() - 1) {
				typeParentView.setVisibility(View.INVISIBLE);
				imageView.setImageResource(R.drawable.addphoto);
				imageView.setTag(ImageLoaderHandler.ImageViewTag_LoadUnuse,
						true);
			} else if (1 == type) {// 身份证照片
				imageView.setTag(ImageLoaderHandler.ImageViewTag_LoadUnuse,
						false);
				typeParentView.setVisibility(View.VISIBLE);
				createTime.setText("身份证照片");
				imageType
						.setBackgroundResource(R.drawable.phototype_shenfenzheng);
				imgLoad(photo.photo, imageView, itemSize, itemSize);
			} else if (2 == type) {// 表单拍照照片
				imageView.setTag(ImageLoaderHandler.ImageViewTag_LoadUnuse,
						false);
				typeParentView.setVisibility(View.VISIBLE);
				createTime.setText("申请表照片");
				imageType.setBackgroundResource(R.drawable.phototype_biaodan);
				imgLoad(photo.photo, imageView, itemSize, itemSize);
			} else if (3 == type) {// 其他拍照照片
				imageView.setTag(ImageLoaderHandler.ImageViewTag_LoadUnuse,
						false);
				typeParentView.setVisibility(View.VISIBLE);
				createTime.setText("其他附加照片");
				imageType.setBackgroundResource(R.drawable.phototype_qita);
				imgLoad(photo.photo, imageView, itemSize, itemSize);
			}
		}
	}

	class TakePhotoDialog extends Dialog {
		public TakePhotoDialog(Context context) {
			super(context);
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			LayoutParams params = new LayoutParams(
					(int) (XCToolkit.getScreenWidth() * 0.5656f),
					(int) (XCToolkit.getScreenHeight() * 0.4925f));
			View contentView = mInflater.inflate(R.layout.pop_photo, null);
			setContentView(contentView, params);

			// setTitle("拍照选择");

			final GroupTagView photoType = (GroupTagView) findViewById(R.id.photoType);
			final GroupTagView photoFrom = (GroupTagView) findViewById(R.id.photoFrom);
			findViewById(R.id.confirmBtn).setOnClickListener(
					new android.view.View.OnClickListener() {
						@Override
						public void onClick(View v) {
							TakePhotoDialog.this.dismiss();
							takePhotoAction(photoType.getCurrentValue(),
									photoFrom.getCurrentValue());
						}
					});
			findViewById(R.id.closeBtn).setOnClickListener(
					new android.view.View.OnClickListener() {
						@Override
						public void onClick(View v) {
							TakePhotoDialog.this.dismiss();
						}
					});
		}
	}

	class PhotoShowDialog extends Dialog {
		String imageFileName = null;

		public PhotoShowDialog(Context context, String imageFileName) {
			super(context, R.style.AppTheme_Dialog);
			this.imageFileName = imageFileName;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			LayoutParams params = new LayoutParams(XCToolkit.dip2px(700),
					XCToolkit.dip2px(700));
			PZSImageView imageView = new PZSImageView(getActivity());
			imageView.setScaleType(ScaleType.FIT_CENTER);
			setContentView(imageView, params);

			imgLoad(imageFileName, imageView, -1, -1);
		}
	}

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater.inflate(R.layout.fm_grwdy_step1_content_takephotos,
				null);
	}

	int mColumns = 4;
	@ViewInject(R.id.gridView_takephoto)
	GridView gridView;

	@Override
	protected void onInitContentView(View rootView, TakePhotosBO info) {
		downloadPhotos(new AfterDownload() {
			@Override
			public void AfterDownloadAction() {
				initGridView();
			}
		});
	}

	public static void imgLoad(String imageKey, ImageView imageView, int width,
			int height) {
		ImageLoader.load(FileUtils.photoTempDir + File.separator + imageKey,
				imageView, width, height);
	}

	private void initGridView() {
		gridView.setAdapter(adapter = new GridViewAdapter());
		gridView.setOnItemClickListener(this);
		gridView.setOnItemLongClickListener(this);
		gridView.setVerticalSpacing(XCToolkit.dip2px(30));
		mColumns = gridView.getNumColumns();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		String fileName = XCToolkit
				.stringFromDate(new Date(), "yyyyMMddHHmmss") + ".jpg";
		if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
			if (null != data && data.getAction() != null
					&& data.getAction().equals("inline-data")) {
				Bitmap bitmap = (Bitmap) data.getExtras().get("data");
				File file = XCFile.create(FileUtils.savePhotoFile(fileName,
						bitmap));
				if (file.exists()) {
					infoBO.addPhoto(selectedType, fileName);
				} else {
					Log.e("---拍照保存--", "保存照片失败");
				}
				adapter.notifyDataSetChanged();
			} else {
				String filePath = XCSdCardDirUtils.getSDCardDataDir()
						+ File.separator + "photoTemp.jpg";
				File originPhoto = null;
				if (!(originPhoto = new File(filePath)).exists()) {
					return;
				}
				Bitmap bitmap = BitmapFactory.decodeFile(filePath);
				if (null != bitmap) {
					File file = XCFile.create(FileUtils.savePhotoFile(fileName,
							bitmap));
					if (file.exists()) {
						infoBO.addPhoto(selectedType, fileName);
						originPhoto.delete();
					} else {
						Log.e("---拍照保存--", "保存拍照照片失败");
					}
					adapter.notifyDataSetChanged();
				} else {
					Log.e("---照片--", "图片获取失败，请重新拍照");
					XCToolkit.showToast("图片获取失败，请重新拍照");
				}
			}
		} else if (null != data && requestCode == 2
				&& resultCode == Activity.RESULT_OK) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaColumns.DATA };

			Cursor cursor = getActivity().getContentResolver().query(
					selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
			if (null != bitmap) {
				File file = XCFile.create(FileUtils.savePhotoFile(fileName,
						bitmap));
				if (file.exists()) {
					infoBO.addPhoto(selectedType, fileName);
				} else {
					Log.e("---照片保存--", "保存照片失败");
				}
				adapter.notifyDataSetChanged();
			} else {
				Log.e("---照片--", "图片获取失败，请重新选择照片");
				XCToolkit.showToast("图片获取失败，请重新选择照片");
			}
		}
	}

	int selectedType = PhotosItem.OtherPhoto;

	@Override
	public void onItemClick(AdapterView<?> arg0, final View view, int position,
			long id) {
		if (null == adapter.getItem(position)) {// 添加新照片
			new TakePhotoDialog(getActivity()).show();
		} else {// 已有照片，此时弹出展示对话框
			new PhotoShowDialog(getActivity(),
					infoBO.photos().get(position).photo).show();
		}
	}

	// 已添加好的照片，长按会弹出删除提示框。
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, long id) {
		if (null != adapter.getItem(position)) {// 添加新照片
			final AlertDialog.Builder builder = new AlertDialog.Builder(
					getActivity());
			builder.setCancelable(false);
			builder.setMessage("您要删除本照片吗？");
			builder.setPositiveButton("是",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int whichButton) {
							PhotosItem item = infoBO.photos().get(position);
							FileUtils.removePhotoFile(item.photo);
							infoBO.removePhotos(item);
							adapter.notifyDataSetChanged();
						}
					});
			builder.setNegativeButton("否", null);
			builder.show();
		}
		return false;
	}

	private void takePhotoAction(Object photoType, Object photoFrom) {
		String[] types = getResources().getStringArray(R.array.takephotoType);
		String[] from = getResources().getStringArray(R.array.takephotoFrom);

		if (photoFrom.equals(from[0])) {// 来源于相机
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 2);
			// 将拍照的照片存储到文件中
			if (XCSdCardDirUtils.isSdCardExist()) {
				String filePath = XCSdCardDirUtils.getSDCardDataDir();
				File file = new File(filePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				file = new File(filePath + File.separator + "photoTemp.jpg");
				FileUtils.delete(file);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			}
			startActivityForResult(intent, 1);
		} else if (photoFrom.equals(from[1])) {// 来源于相册
			Intent intent = new Intent(Intent.ACTION_PICK,
					Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, 2);
		}
		if (photoType.equals(types[0])) {// 身份证
			selectedType = PhotosItem.CustomPhoto;
		} else if (photoType.equals(types[1])) {// 表单拍照
			selectedType = PhotosItem.FormPhoto;
		} else if (photoType.equals(types[2])) {// 其他
			selectedType = PhotosItem.OtherPhoto;
		}
	}

	@Override
	public TakePhotosBO getValueFromInterfaceView() {
		return infoBO;
	}

	@Override
	public boolean isRequiredFieldInputed(TakePhotosBO info) {
		if (null == info) {
			XCToolkit.showToast("请编辑“影像资料”信息");
			return false;
		} else if (null == info.photos() || info.photos().size() <= 0) {
			XCToolkit.showToast("您必须添加影像资料");
			return false;
		}
		return true;
	}
}
