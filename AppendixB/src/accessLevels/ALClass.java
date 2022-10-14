package accessLevels;

public class ALClass {
	
	public int thisIsPublic;
	protected int thisIsProtected;
	int thisIsDefault;
	private int thisIsPrivate;
	
	/**
	 * public means any code anywhere can access this thing
	 * (by "thing" we mean class, variable, method, 
	 * inner-class, constructor, etc)
	 */
	public int getThisIsPublic() { return thisIsPublic; }
	public void setThisIsPublic(int x) { thisIsPublic = x; }
	
	/**
	 * protected works just like default, EXCEPT it also allows 
	 * subclasses outside the package to inherit the protected thing
	 */
	protected int getThisIsProtected() { return thisIsProtected; }
	protected void setThisIsProtected(int x) { thisIsProtected = x; }
	
	/**
	 * default access means that only code within 
	 * the same package as the class with the default 
	 * thing can access the default thing
	 */
	int getThisIsDefault() { return thisIsDefault; }
	void setThisIsDefault(int x) { thisIsDefault = x; }
	
	/**
	 * private means that only code within the same class can access
	 * the private thing. Keep in mind it means private to the class,
	 * not private to the object. One Dog can see another Dog's object's
	 * private stuff, but a Cat can't see a Dog's privates
	 */
	private int getThisIsPrivate() { return thisIsPrivate; }
	private void setThisIsPrivate(int x) { thisIsPrivate = x; }
	
}
