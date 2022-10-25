package _1;

/**
 * A Java data class, before Java 16
 *
 */
public final class Customer {
	
	private final int id;
	private final String name;
	
	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	
	public boolean equals(Object o) { return false; }
	public int hashCode() { return 1; }
	public String toString() { return null; }

}
