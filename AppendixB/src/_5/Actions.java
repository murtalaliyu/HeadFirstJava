package _5;

import java.util.ArrayList;
import java.util.List;

public class Actions {
	
	private String usr;
	private List<String> actions;
	
	public Actions(String usr) {
		this.usr = usr;
		actions = new ArrayList<>();
	}
	
	public String getUsr() { return usr; }
	public void setUsr(String usr) { this.usr = usr; }
	
	public List<String> getActions() { return actions; }
	public void setActions(List<String> actions) { this.actions = actions; }
	
	public void addAction(String action) { actions.add(action); }

}
