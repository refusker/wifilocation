package hk.org.ha.geek.wifilocation;

import java.util.List;
import java.util.ArrayList;


public class POIBean {

	private String id;
	private String poiName;
	private List<Pair> actions;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPoiName() {
		return poiName;
	}
	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}

	public List<Pair> getActions() {
		return actions;	
	}

	public void setActions(String returnType, String returnValue) {
		Pair pair = new Pair();
		pair.setReturnType(returnType);
		pair.setReturnValue(returnValue);
		List<Pair> newActions = this.actions;
		if(newActions==null) {
			newActions = new ArrayList<Pair>();
		}
		newActions.add(pair);

		this.actions = newActions;
	}

	private class Pair {
		private String returnType;
		private String returnValue;
		
		public String getReturnType() {
			return returnType;
		}
		public void setReturnType(String returnType) {
			this.returnType = returnType;
		}
		public String getReturnValue() {
			return returnValue;
		}
		public void setReturnValue(String returnValue) {
			this.returnValue = returnValue;
		}
	
	}
	
}
