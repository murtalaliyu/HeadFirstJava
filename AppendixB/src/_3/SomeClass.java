package _3;

public class SomeClass {
	
	public Member selectedBandMember = Member.KEVIN;
	
	public static void main(String[] args) {
		new SomeClass().someMethod();
	}
	
	// later in the code...
	void someMethod() {
		if (selectedBandMember == Member.KEVIN) {
			// do KEVIN related stuff
			System.out.println("This is Kevin: " + selectedBandMember);
		}
		
		// ---------------------------------------------
		
		Member member = Member.BOB;
		if (member.equals(Member.KEVIN)) System.out.println("Bellloooo!");
		if (member == Member.BOB) System.out.println("Poochy");
		
		switch (member) {
			case BOB: System.out.println("King Bob");
			case STUART: System.out.println("Banana!");
			case KEVIN: System.out.println("Uh... la cucaracha?");
		}
	}

}
