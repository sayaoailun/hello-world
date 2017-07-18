package com.dc.shell;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

@Alias("element")
public class Element implements Serializable{
	public static final String RESOURCE = "resource";
	public static final String SERVICE = "service";
	public static final String COMPONENT = "component";
	public static final String WORKPIECE = "workpiece";
	public static final String PLUGIN = "plugin";
	public static final String FLOWCONTROL = "flowcontrol";
	public static final int STATICRESOURCE = 1;
	public static final int CLOUDRESOURCE = 2;
	public static final int DYNAMICRESOURCE = 3;
	private static final long serialVersionUID = 1L;
	
	
    private String text;
    private String des;
    private long key;
    private long group;
    private Boolean isGroup;
    private int pooltype;
    private String uuid;
    private String eleType;//service resource component workpiece plugin flowcontrol
    private long deployId = -1;
    private long startId = -1;
    private long stopId = -1;
    private long destroyId = -1;
    
    private long ins;
    private String resourceVersionId;
    private String config;
    private String _testkey;
    
    private List<Version> versionlist;
    
    private String label;
    private String cluster_id;
    private String pluginName;
    private Map<String,String> params;//插件的参数，发送消息的时候发送
    
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public long getDeployId() {
		return deployId;
	}
	public void setDeployId(long deployId) {
		this.deployId = deployId;
	}
	public long getStartId() {
		return startId;
	}
	public void setStartId(long startId) {
		this.startId = startId;
	}
	public long getStopId() {
		return stopId;
	}
	public void setStopId(long stopId) {
		this.stopId = stopId;
	}
	public long getDestroyId() {
		return destroyId;
	}
	public void setDestroyId(long destroyId) {
		this.destroyId = destroyId;
	}
	public String getEleType() {
		return eleType;
	}
	public void setEleType(String eleType) {
		this.eleType = eleType;
	}
	public int getPooltype() {
		return pooltype;
	}
	public void setPooltype(int pooltype) {
		this.pooltype = pooltype;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public long getIns() {
		return ins;
	}
	public void setIns(long ins) {
		this.ins = ins;
	}
	public long getKey() {
		return key;
	}
	public void setKey(long key) {
		this.key = key;
	}
	public long getGroup() {
		return group;
	}
	public void setGroup(long group) {
		this.group = group;
	}
	public Boolean getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getResourceVersionId() {
		return resourceVersionId;
	}
	public void setResourceVersionId(String resourceVersionId) {
		this.resourceVersionId = resourceVersionId;
	}
	public String getPluginName() {
		return pluginName;
	}
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public List<Version> getVersionlist() {
		return versionlist;
	}
	public void setVersionlist(List<Version> versionlist) {
		this.versionlist = versionlist;
	}
	
	public class Version implements Serializable{
		private static final long serialVersionUID = 1L;
		private long ins;
	    private String resourceVersionId;
	    private String config;
	    private String versionName;
		public long getIns() {
			return ins;
		}
		public void setIns(long ins) {
			this.ins = ins;
		}
		public String getResourceVersionId() {
			return resourceVersionId;
		}
		public void setResourceVersionId(String resourceVersionId) {
			this.resourceVersionId = resourceVersionId;
		}
		public String getConfig() {
			return config;
		}
		public void setConfig(String config) {
			this.config = config;
		}
		public String getVersionName() {
			return versionName;
		}
		public void setVersionName(String versionName) {
			this.versionName = versionName;
		}
	}
	
	@Override
	public String toString() {
		return "key:" + this.getKey()+" name:"+this.getText()+" group:"+this.getGroup();
	}
	public String getClusterId() {
		return cluster_id;
	}
	public void setClusterId(String cluster_id) {
		this.cluster_id = cluster_id;
	}
	public String get_testkey() {
		return _testkey;
	}
	public void set_testkey(String _testkey) {
		this._testkey = _testkey;
	}
	
}
