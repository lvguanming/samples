/**
 * 
 */
package ramp.sample.springjdbctemplate.model;

/**
 * @author Rama Palaniappan
 * @since Jan 8, 2014
 */
public class App {

	private int appId;
	private String name;
	private String packageName;

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String toString() {
		return appId + "-" + name + "-" + packageName;
	}
}
