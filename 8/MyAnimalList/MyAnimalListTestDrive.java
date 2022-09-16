public class MyAnimalListTestDrive {
	public static void main(String[] args) {
		MyAnimalList list = new MyAnimalList();
		Dog dog = new Dog();
		Cat cat = new Cat();
		list.add(dog);
		list.add(cat);

		System.out.println(cat.getClass());
		System.out.println("hash code: " + cat.hashCode());
		System.out.println("cat to string: " + cat.toString());
	}
}
