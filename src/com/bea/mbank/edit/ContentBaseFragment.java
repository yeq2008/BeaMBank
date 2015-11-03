/**
 * 
 */
package com.bea.mbank.edit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.util.FileUtils;
import com.bea.mbank.util.ZipUtil;
import com.bea.remote.BeaRemoteSettings;
import com.bea.remote.models.grwdy.BasicInfoBO;
import com.bea.remote.models.grwdy.BorrowerInforBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import com.bea.remote.models.grwdy.TakePhotosBO;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import xc.android.file.XCFile;
import xc.android.fragment.XCBaseHttpFragment;
import xc.android.remote.json.FileLoader;
import xc.android.remote.json.FileLoader.FileLoaderHandler;
import xc.android.utils.XCToolkit;
import xc.android.widgets.XCProgressDialog;

/**
 * @author cuiyuguo
 *  编辑模块内容区域基类，处理一些统一性操作
 */
public abstract class ContentBaseFragment<T> extends XCBaseHttpFragment {
	/** 
     * 拼接某属性的 get或者set方法 
     * @param fieldName 字段名称 
     * @param methodType    方法类型 
     * @return 方法名称 
     */  
	private String parseMethodName(String fieldName,String methodType) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        return methodType + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);  
    }  
    
    /** 
     * 判断是否存在某属性的 get方法 
     * @param methods   引用方法的数组 
     * @param fieldMethod   方法名称 
     * @return true或者false 
     */  
	private boolean haveMethod(Method[] methods, String fieldMethod) {  
        for (Method met : methods) {  
            if (fieldMethod.equals(met.getName())) {  
                return true;  
            }  
        }  
        return false;  
    } 
	
	//由于将来进行混淆打包，通过类反射会出现问题
	protected abstract Class getInfoClass();
	protected T infoBO = null;
	private Method mfieldSetMethod = null;
	private void initInfoBO() {
//		Type genType = getClass().getGenericSuperclass();   
//		Log.d("====class", genType.toString());
//		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();   
//		Class entityClass =  (Class)params[0];  
		
		Class entityClass = getInfoClass();
		Class<?> cls = homeBO.getClass();          
        // 取出bean里的所有方法     
        Method[] methods = cls.getDeclaredMethods();     
        Field[] fields = cls.getDeclaredFields();  
        for (Field field : fields) { 
        	if (field.getType() == entityClass) {
        		String fieldType = field.getType().getSimpleName();     
                String fieldGetName = parseMethodName(field.getName(),"get");     
                if (!haveMethod(methods, fieldGetName)) {     
                    break;     
                }     
				try {
					Method fieldGetMet = GrwdyHomeBO.class.getMethod(fieldGetName, new Class[] {});
					Object fieldVal = fieldGetMet.invoke(homeBO, new Object[] {});
					String setMetodName = parseMethodName(field.getName(),"set");     
	                if (haveMethod(methods, setMetodName)) { 
	                	Method fieldSetMethod = GrwdyHomeBO.class.getMethod(setMetodName, field.getType());
	                	if (null == fieldVal) {
		                	fieldSetMethod.invoke(homeBO, new Object[] {fieldVal=entityClass.newInstance()}); 
		                	if (fieldVal instanceof BasicInfoBO) {
		                		BasicInfoBO info = (BasicInfoBO)fieldVal;
		                		BorrowerInforBO bo = homeBO.getBorrowerInfor();
		                		if (null != bo) {
		                			info.setLCNum(bo.getMCNum());
		                			info.setLCType(bo.getMCType());
		                			info.setLCC(bo.getMCC());
		                		}
							}
						}
	                	
	                	mfieldSetMethod = fieldSetMethod;
	                } 
					infoBO = (T)fieldVal;
				} catch (Exception e) {
					e.printStackTrace();
				} 
				break;
			}
        }
	}
	
	abstract protected void onInitContentView(View rootView, T info);
	String mOlderDataString = null;
	protected GrwdyHomeBO homeBO = BeaAppSettings.instance().mGrwdyHomeBO;
	@Override
	protected void onInitContentView(View arg0) {
		//从activity中获得本页面数据模型数据
		initInfoBO();
		
		observeMessage(ConstDefined.SavedataWhileChangeAction);
		onInitContentView(arg0, infoBO);
//		T temp = getValueFromInterfaceView();
//		mOlderDataString = XCJsonHelper.toJSONString(temp);
	}
	
	@Override
	public void onReceiveMessage(String msgkey, Object msgObject) {
		if (ConstDefined.SavedataWhileChangeAction.equals(msgkey)) {
			saveData();
		}
	}
    
	abstract public T getValueFromInterfaceView();
	abstract public boolean isRequiredFieldInputed(T info);
	@Override
	public void onDestroy() {
//		XCLog.i("=====destroy", getClass().getName());
		saveData();
		super.onDestroy();
	}
	
	private void saveData() {
		if (homeBO != BeaAppSettings.instance().mGrwdyHomeBO) {
			return;
		}
		//将值回填给activity的GrwdyHomeBO数据对应的模型
		if (null != homeBO && null != mfieldSetMethod) {
			try {
				T valueT = getValueFromInterfaceView();
				if (!homeBO.isRequired(valueT)/* || XCJsonHelper.beanToMap(valueT).toString().equals(mOlderDataString)*/) {
					mfieldSetMethod.invoke(homeBO, new Object[] {null});
				} else {
					mfieldSetMethod.invoke(homeBO, new Object[] {valueT});
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	@Override
	public void onResponsSuccess(int arg0, Object arg1) {}
	
	public interface AfterDownload {
		public void AfterDownloadAction();
	}
	protected void downloadPhotos(final AfterDownload afterAction) {
		TakePhotosBO info = null;
		if (null == homeBO || null == (info=homeBO.getTakePhotos())) {
			return;
		}
		
		//此文件需要从服务器下载,下载完后再调用initGridView()；展示页面
		if (null != info.zipFileUrl && !FileUtils.isPhotoZipExist(info.zipFileUrl)) {
			String url = BeaRemoteSettings.downloadUrl(info.zipFileUrl);
			FileLoader.download(url, FileUtils.photoPath(info.zipFileUrl), new FileLoaderHandler() {
				@Override
				public void onSuccess(String filePath) {
					try {//文件成功下载后解压缩到照片临时目录
						ZipUtil.unZipFolder(filePath, FileUtils.photoTempPath(null));
						if (null != afterAction) {
							afterAction.AfterDownloadAction();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				@Override
				public void onStart() {
					XCProgressDialog.show(getActivity());
				}
				
				@Override
				public void onFinish() {
					XCProgressDialog.dismiss();
				}
				
				@Override
				public void onFailure(Throwable error) {
					XCToolkit.showToast(error.getLocalizedMessage());
				}

				@Override
				public void onProgress(int downSize) {}
			});
		} else if (null != info.zipFileUrl && FileUtils.isPhotoZipExist(info.zipFileUrl)) {
			//本地已经存在此压缩包，将其里面的文件解压到临时文件夹下
			try {
				ZipUtil.unZipFolder(FileUtils.photoPath(info.zipFileUrl), FileUtils.photoTempPath(null));
				if (null != afterAction) {
					afterAction.AfterDownloadAction();
				}
			} catch (Exception e) {
				//压缩包无法解开，可能是文件被损坏，删除重新下载
				XCFile.create(FileUtils.photoDir, FileUtils.getFileName(info.zipFileUrl)).delete();
				downloadPhotos(afterAction);
				e.printStackTrace();
			}
		} else {//这是新编辑的，不存在原有暂存
			if (null != afterAction) {
				afterAction.AfterDownloadAction();
			}
		}
	}
	
	
	/**
	 * flh
	 * 金额类型输入限制
	 */
	public TextWatcher textWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,int after) {
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			String tmp = s.toString();
			if(tmp.length()<1)
				return;
			String first = tmp.substring(0,1);
			Pattern pattern = Pattern.compile("^[1-9]$");
			Matcher m = pattern.matcher(first);
			if(m.matches()){
				int index = tmp.indexOf(".");
				if(-1 != index){
					String xs = tmp.substring(index+1,tmp.length());
					if(xs.length()>2)
						s.replace(index+3, tmp.length(), "");
				}
			} else if("0".equals(first)){
				if(tmp.length()>1){
					String sec = tmp.substring(1,2);
					if(!".".equals(sec)){
						s.replace(1, tmp.length(), "");
					} else {
						if(tmp.length()>4){
							s.replace(4, tmp.length(), "");
						}
					}
				}
			} else{
				s.clear();
			}
		}
	};
}
