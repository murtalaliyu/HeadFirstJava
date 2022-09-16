import java.util.List;

public class TestGenerics1 {
  public static void main(String[] args) {
    List<Animal> animals = List.of(new Dog(), new Cat(), new Dog());
    takeAnimals(animals);

    List<Dog> dogs = List.of(new Dog(), new Dog());
    takeAnimals(dogs);

    List<Cat> cats = List.of(new Cat());
    takeAnimals(cats);

    List<Rabbit> rabbits = List.of(new Rabbit());
    takeAnimals(rabbits);
  }

  public static void takeAnimals(List<? extends Animal> animals) {
    for (Animal a : animals) {
      a.eat();
    }
    animals.add(new Cat());
  }
}
