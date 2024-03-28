
package com.kingz.format.xml.jaxb.manifest;

import com.kingz.utils.TextUtils;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@XmlRootElement(name = "manifest")
public class AndroidManifest {
	public static final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";
	public static final String TOOLS_NAMESPACE = "http://schemas.android.com/tools";

	public static final String ANDROID_ACTION_MAIN = "android.intent.action.MAIN";
	public static final String ANDROID_CATEGORY_DEFAULT = "android.intent.category.DEFAULT";
	public static final String ANDROID_CATEGORY_HOME = "android.intent.category.HOME";
	public static final String ANDROID_CATEGORY_LAUNCHER = "android.intent.category.LAUNCHER";


	public static final String ANDROID_PERMISSION_INTERNET = "android.permission.INTERNET";
	public static final String ANDROID_PERMISSION_ACCESS_WIFI_STATE = "android.permission.ACCESS_WIFI_STATE";
	public static final String ANDROID_PERMISSION_ACCESS_NETWORK_STATE = "android.permission.ACCESS_NETWORK_STATE";
	public static final String ANDROID_PERMISSION_CHANGE_WIFI_STATE = "android.permission.CHANGE_WIFI_STATE";
	public static final String ANDROID_PERMISSION_CHANGE_NETWORK_STATE = "android.permission.CHANGE_NETWORK_STATE";
	public static final String ANDROID_PERMISSION_RECEIVE_BOOT_COMPLETED = "android.permission.RECEIVE_BOOT_COMPLETED";
	public static final String ANDROID_PERMISSION_BLUETOOTH = "android.permission.BLUETOOTH";
	public static final String ANDROID_PERMISSION_KILL_BACKGROUND_PROCESSES = "android.permission.KILL_BACKGROUND_PROCESSES";
	public static final String ANDROID_PERMISSION_READ_LOGS = "android.permission.READ_LOGS";
	public static final String ANDROID_PERMISSION_SYSTEM_ALERT_WINDOW = "android.permission.SYSTEM_ALERT_WINDOW";
	public static final String ANDROID_PERMISSION_MOUNT_UNMOUNT_FILESYSTEMS = "android.permission.MOUNT_UNMOUNT_FILESYSTEMS";

	public static final String ANDROID_SYSTEM_USER_ID = "android.uid.system";


	@XmlAttribute(name = "package", required = true)
	String _packageName;

	//@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
	//int _versionCode;
    //
	//@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
	//String _versionName;

	@XmlAttribute(namespace = ANDROID_NAMESPACE)
	String sharedUserId;

	@XmlAnyAttribute()
	HashMap<QName, String> attributes;

	@XmlElement(name = "uses-sdk")
	SdkInfo usesSdk;

	@XmlElement(name = "instrumentation")
	InstrumentInfo instrumentation;

//	@XmlJavaTypeAdapter(value = PermissionsAdapter.class)
	@XmlElements(@XmlElement(name = "permission"))
	HashSet<Permission> permissions;

//	@XmlJavaTypeAdapter(value = PermissionsAdapter.class)
	@XmlElements(@XmlElement(name = "uses-permission"))
	public HashSet<Permission> usesPermissions;

	@XmlElement(required = true)
	ApplicationInfo application;

	@XmlAnyElement
	Object[] any;

	public String getApplicationAttr(String key){
		if(TextUtils.isEmpty(key)){
			return null;
		}
		if(application.attributes == null){
			return null;
		}
		return application.attributes.get(key);
	}

	public void addApplicationAttr(String key, String value){
		if(TextUtils.isEmpty(key) || TextUtils.isEmpty(value)){
			return;
		}
		if(application.attributes == null){
			return;
		}
		String namespaceURI = "http://schemas.android.com/apk/res/android";
		String localPart = key;
		String prefix = "android";
		QName qName = new QName(namespaceURI, localPart, prefix);
		application.attributes.put(qName, value);
	}

 	public String getPackageName() {
		return _packageName;
	}

	//public int getVersionCode() {
	//	return _versionCode;
	//}
    //
	//public String getVersionName() {
	//	return _versionName;
	//}

	public void addPackageName(String _packageName) {
		this._packageName = _packageName;
	}

	//public void addVersion(int versionCode, String versionName) {
	//	this._versionCode = versionCode;
	//	this._versionName = versionName;
	//}
    //
	//public void addVersionCode(int _versionCode) {
	//	this._versionCode = _versionCode;
	//}
    //
	//public void addVersionName(String _versionName) {
	//	this._versionName = _versionName;
	//}


	public boolean isPermissionUsed(String permission) {
		if (this.usesPermissions == null) {
			return false;
		}
		return this.usesPermissions.contains(permission);
	}

	public void unusePermission(String permission) {
		if (this.usesPermissions == null) {
			return;
		}
		this.usesPermissions.remove(permission);
	}

	public boolean isPermissionDefined(String permission) {
		if (this.permissions == null) {
			return false;
		}
		return this.permissions.contains(permission);
	}

	public void undefinePermission(String permission) {
		if (this.permissions == null) {
			return;
		}
		this.permissions.remove(permission);
	}

	public void setSharedUserId(String sharedUserId) {
		this.sharedUserId = sharedUserId;
	}

	public void setTargetSdk(String targetSdk){
		if(this.usesSdk == null){
			return;
		}
		System.out.println("[manifest setTargetSdk] targetSdk = "+targetSdk);
		this.usesSdk.targetSdkVersion = targetSdk;
	}

	public AndroidActivity addActivity(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.addActivity(name);
	}

	public AndroidReceiver addReceivere(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.addReceiver(name);
	}

	public AndroidService addService(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.addService(name);
	}

	public AndroidContentProvider addContentProvider(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.addContentProvider(name);
	}

	public AndroidActivity getActivity(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.getActivity(name);
	}

	public List<AndroidActivity> getActivitys() {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.getActivitys();
	}

	public List<AndroidService> getServices() {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.getServices();
	}

	public AndroidService getService(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.getService(name);
	}

	public AndroidContentProvider getContentProvider(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.getContentProvider(name);
	}

	public List<AndroidContentProvider> getContentProviders() {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.getContentProviders();
	}

	public AndroidReceiver getReceiver(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.getReceiver(name);
	}

	public List<AndroidReceiver> getReceivers() {
		ApplicationInfo application = this.application;
		if (application == null) {
			return null;
		}
		return application.getReceivers();
	}

	public boolean removeActivity(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return false;
		}
		return application.removeComponent(getActivity(name));
	}

	public boolean removeService(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return false;
		}
		return application.removeComponent(getService(name));
	}

	public boolean removeContentProvider(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return false;
		}
		return application.removeComponent(getContentProvider(name));
	}

	public boolean removeReceiver(String name) {
		ApplicationInfo application = this.application;
		if (application == null) {
			return false;
		}
		return application.removeComponent(getReceiver(name));
	}


	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Permission {
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String name;

		@XmlAnyAttribute()
		HashMap<QName, String> anyAttribute = new HashMap<>();


	}

	public static class SdkInfo {
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String minSdkVersion;

		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String targetSdkVersion;

		@XmlAttribute(namespace = TOOLS_NAMESPACE)
		String overrideLibrary;
	}

	public static class InstrumentInfo {
		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String name;

		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String label;

		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String targetPackage;
	}

	public static class AppUseLibInfo {
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String name;
	}

	static class IntentFilterItem {
		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String name;
	}

	static class IntentFilterAction extends IntentFilterItem {
		public IntentFilterAction(String name) {
			this.name = name;
		}

		public IntentFilterAction() {
		}
	}

	static class IntentFilterCategory extends IntentFilterItem {
		public IntentFilterCategory(String name) {
			this.name = name;
		}

		public IntentFilterCategory() {
		}
	}

	static class IntentFilterData extends IntentFilterItem {

		@XmlAnyAttribute()
		HashMap<QName,String> anyAttribute;
	}

	public void addApplicationMetaData(String name,String value){
		if(TextUtils.isEmpty(name)){
			return;
		}
		if(application.metaData==null){
			application.metaData=new ArrayList<AndroidMetaData>();
		}
		AndroidMetaData data=new AndroidMetaData();
		data.name=name;
		data.value=value;
		application.metaData.add(data);
	}

	private static class ApplicationInfo {
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String name;

		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String label;

		@XmlAnyAttribute()
		HashMap<QName, String> attributes;

		@XmlElement(name = "meta-data")
		ArrayList<AndroidMetaData> metaData;

		@XmlElement(name = "uses-library")
		ArrayList<AppUseLib> usesLib;

		@XmlElements({
			@XmlElement(name = "activity", type = AndroidActivity.class),
			@XmlElement(name = "service", type = AndroidService.class),
			@XmlElement(name = "provider", type = AndroidContentProvider.class),
			@XmlElement(name = "receiver", type = AndroidReceiver.class),
		})
		ArrayList<AndroidComponent> components;

		public <T extends AndroidComponent> T addComponent(String name, Class<T> cls) {
			ArrayList<AndroidComponent> components = this.components;
			if (components == null) {
				this.components = components = new ArrayList<AndroidComponent>();
			}
			try {
				T component = cls.newInstance();
				components.add(component);
				return component;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		}

		public <T extends AndroidComponent> T getComponent(String name, Class<T> cls) {
			ArrayList<AndroidComponent> components = this.components;
			if (components == null) {
				return null;
			}
			for (AndroidComponent component : components) {
				if (!(cls.equals(component.getClass()))) {
					continue;
				}
				if (name.equals(component.name)) {
					return (T) component;
				}
			}
			return null;
		}

		public <T extends AndroidComponent> ArrayList<T> getComponents(Class<T> cls) {
			ArrayList<AndroidComponent> components = this.components;
			if (components == null) {
				return null;
			}
			ArrayList<T> newComps = new ArrayList<T>();
			for (AndroidComponent component : components) {
				if (cls.equals(component.getClass())) {
					newComps.add((T) component);
				}
			}
			return newComps;
		}

		public AndroidActivity getActivity(String name) {
			return getComponent(name, AndroidActivity.class);
		}

		public List<AndroidActivity> getActivitys() {
			return getComponents(AndroidActivity.class);
		}

		public AndroidContentProvider getContentProvider(String name) {
			return getComponent(name, AndroidContentProvider.class);
		}

		public List<AndroidContentProvider> getContentProviders() {
			return getComponents(AndroidContentProvider.class);
		}

		public AndroidReceiver getReceiver(String name) {
			return getComponent(name, AndroidReceiver.class);
		}

		public List<AndroidReceiver> getReceivers() {
			return getComponents(AndroidReceiver.class);
		}

		public AndroidService getService(String name) {
			return getComponent(name, AndroidService.class);
		}

		public List<AndroidService> getServices() {
			return getComponents(AndroidService.class);
		}

		public boolean removeComponent(AndroidComponent component) {
			ArrayList<AndroidComponent> components = this.components;
			if (components == null) {
				return false;
			}
			return components.remove(component);
		}

		public AndroidActivity addActivity(String name) {
			return addComponent(name, AndroidActivity.class);
		}

		public AndroidService addService(String name) {
			return addComponent(name, AndroidService.class);
		}

		public AndroidContentProvider addContentProvider(String name) {
			return addComponent(name, AndroidContentProvider.class);
		}

		public AndroidReceiver addReceiver(String name) {
			return addComponent(name, AndroidReceiver.class);
		}
	}

	private static class AndroidMetaData {
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String name;

		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String value;

		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String resource;

		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String required;
	}

	private static class AppUseLib {
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String name;

		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String required;

		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String value;

		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String resource;
	}

	static class IntentFilter {
		@XmlAnyAttribute()
		HashMap<QName, String> attributes;

		@XmlElements(
			{
				@XmlElement(name = "action", type = IntentFilterAction.class),
				@XmlElement(name = "category", type = IntentFilterCategory.class),
				@XmlElement(name = "data" ,type = IntentFilterData.class)
			}
		)
		ArrayList<IntentFilterItem> intentFilter;

        public void addAction(String name){
        	if(intentFilter == null){
        		intentFilter = new ArrayList<IntentFilterItem>();
        	}
        	intentFilter.add(new IntentFilterAction(name));
        }

        public boolean removeAction(String name){
        	if(intentFilter == null || intentFilter.isEmpty()){
        		return false;
        	}
        	for(IntentFilterItem item : intentFilter){
        		if(item instanceof IntentFilterAction && item.name.equals(name)){
        			intentFilter.remove(item);
        			return true;
        		}
        	}
        	return false;
        }

        public boolean replaceAction(String src, String dst){
        	if(intentFilter == null || intentFilter.isEmpty()){
        		return false;
        	}
        	boolean result = false;
        	for(IntentFilterItem item : intentFilter){
        		if(item instanceof IntentFilterAction && item.name.startsWith(src)){
        			item.name = item.name.replace(src, dst);
        			result = true;
        		}
        	}
        	return result;
        }

        public boolean containsAciton(String name) {
        	if(intentFilter == null || intentFilter.isEmpty()){
        		return false;
        	}
        	for(IntentFilterItem item : intentFilter){
        		if(item instanceof IntentFilterAction && item.name.equals(name)){
        			return true;
        		}
        	}
        	return false;
		}

        public void addCategory(String name){
        	if(intentFilter == null){
        		intentFilter = new ArrayList<IntentFilterItem>();
        	}
        	intentFilter.add(new IntentFilterCategory(name));
        }

        public void addIntentFilterItems(List<IntentFilterItem> items) {
        	if(intentFilter == null){
        		intentFilter = new ArrayList<IntentFilterItem>();
        	}
        	intentFilter.addAll(items);
		}

        public boolean removeCategory(String name){
        	if(intentFilter == null || intentFilter.isEmpty()){
        		return false;
        	}
        	for(IntentFilterItem item : intentFilter){
        		if(item instanceof IntentFilterCategory && item.name.equals(name)){
        			intentFilter.remove(item);
        			return true;
        		}
        	}
        	return false;
        }

        public boolean containsCategory(String name){
        	if(intentFilter == null || intentFilter.isEmpty()){
        		return false;
        	}
        	for(IntentFilterItem item : intentFilter){
        		if(item instanceof IntentFilterCategory && item.name.equals(name)){
        			return true;
        		}
        	}
        	return false;
        }
	}

	private static class AndroidComponent {
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String name;

		@XmlAnyAttribute()
		HashMap<QName, String> attributes;

		@XmlElements(@XmlElement(name = "intent-filter", type = IntentFilter.class))
		ArrayList<IntentFilter> intentFilters;

		@XmlElement(name = "meta-data")
		ArrayList<AndroidMetaData> metaData;

		public boolean replaceAction(String src, String target) {
			if (intentFilters == null || intentFilters.isEmpty()) {
				return false;
			}
			boolean result = false;
			for(IntentFilter filter : intentFilters){
				if(filter.replaceAction(src, target)){
					result = true;
				}
			}
			return result;
		}

		public void addAction(String action) {
			if (intentFilters == null) {
				intentFilters = new ArrayList<IntentFilter>();
				intentFilters.add(new IntentFilter());
			}
			intentFilters.get(0).addAction(action);
		}

		public boolean removeAction(String action) {
			if (intentFilters == null || intentFilters.isEmpty()) {
				return false;
			}
			for(IntentFilter filter : intentFilters){
				if(filter.removeAction(action)){
					return true;
				}
			}
			return false;
		}

		public boolean containsAciton(String name) {
			if (intentFilters == null || intentFilters.isEmpty()) {
				return false;
			}
			for(IntentFilter filter : intentFilters){
				if(filter.containsAciton(name)){
					return true;
				}
			}
			return false;
		}

		public void addCategory(String category) {
			if (intentFilters == null) {
				intentFilters = new ArrayList<IntentFilter>();
				intentFilters.add(new IntentFilter());
			}
			intentFilters.get(0).addCategory(category);
		}

		public boolean removeCategory(String action) {
			if (intentFilters == null || intentFilters.isEmpty()) {
				return false;
			}
			for(IntentFilter filter : intentFilters){
				if(filter.removeCategory(action)){
					return true;
				}
			}
			return false;
		}

		public boolean containsCategory(String name) {
			if (intentFilters == null || intentFilters.isEmpty()) {
				return false;
			}
			for(IntentFilter filter : intentFilters){
				if(filter.containsCategory(name)){
					return true;
				}
			}
			return false;
		}

		public void removeAllItems(){
			if (intentFilters == null || intentFilters.isEmpty()) {
				return;
			}
			intentFilters.clear();
		}

		public List<IntentFilterItem> getAllIntentFilterItem(){
			if (intentFilters == null || intentFilters.isEmpty()) {
				return null;
			}
			return intentFilters.get(0).intentFilter;
		}

		public void addIntentFilterItems(List<IntentFilterItem> items) {
			if (intentFilters == null) {
				intentFilters = new ArrayList<IntentFilter>();
				intentFilters.add(new IntentFilter());
			}
			intentFilters.get(0).addIntentFilterItems(items);
		}

		public void rename(String newName) {
			this.name = newName;
		}
	}

	public static class AndroidActivity extends AndroidComponent {
		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String label;
	}

	static class AndroidContentProvider extends AndroidComponent {
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String authorities;
		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String permission;
		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String readPermission;
		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String writePermission;
		@XmlAttribute(namespace = ANDROID_NAMESPACE)
		String exported;
		@XmlElements(@XmlElement(name = "grant-uri-permission", type = GrantUriProvider.class))
		GrantUriProvider grantUri;
	}

	static class GrantUriProvider{
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String permission;
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String readPermission;
		@XmlAttribute(namespace = ANDROID_NAMESPACE, required = true)
		String writePermission;
	}

	static class AndroidReceiver extends AndroidComponent {
	}

	static class AndroidService extends AndroidComponent {
	}
}
