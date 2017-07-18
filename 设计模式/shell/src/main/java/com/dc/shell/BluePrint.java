package com.dc.shell;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("blueprint")

public class BluePrint implements Serializable {

	private static final long serialVersionUID = 1L;
    	
		private String linkFromPortIdProperty;
		private String linkToPortIdProperty;
		private String modelData;
		private List<Element> nodeDataArray;
		private List<StartBluePrint> linkDataArray;
		private long id;
		private boolean issub; //是否是子流程，组件的流程是子流程，蓝图的流程非子流程
		private String bluePrintId;
		private String op;
		
		public String getLinkFromPortIdProperty() {
			return linkFromPortIdProperty;
		}
		public void setLinkFromPortIdProperty(String linkFromPortIdProperty) {
			this.linkFromPortIdProperty = linkFromPortIdProperty;
		}
		public String getLinkToPortIdProperty() {
			return linkToPortIdProperty;
		}
		public void setLinkToPortIdProperty(String linkToPortIdProperty) {
			this.linkToPortIdProperty = linkToPortIdProperty;
		}
		public String getModelData() {
			return modelData;
		}
		public void setModelData(String modelData) {
			this.modelData = modelData;
		}
		public List<Element> getNodeDataArray() {
			return nodeDataArray;
		}
		public void setNodeDataArray(List<Element> nodeDataArray) {
			this.nodeDataArray = nodeDataArray;
		}
		public List<StartBluePrint> getLinkDataArray() {
			return linkDataArray;
		}
		public void setLinkDataArray(List<StartBluePrint> linkDataArray) {
			this.linkDataArray = linkDataArray;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public boolean isIssub() {
			return issub;
		}
		public void setIssub(boolean issub) {
			this.issub = issub;
		}
		public String getBluePrintId() {
			return bluePrintId;
		}
		public void setBluePrintId(String bluePrintId) {
			this.bluePrintId = bluePrintId;
		}
		public String getOp() {
			return op;
		}
		public void setOp(String op) {
			this.op = op;
		}
}
