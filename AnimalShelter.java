import java.util.LinkedList;
import java.util.Queue;

// Base class for Animal with a type and arrival order
abstract class Animal {
    private int arrivalOrder;
    protected String type;

    public Animal(int arrivalOrder) {
        this.arrivalOrder = arrivalOrder;
    }

    public int getArrivalOrder() {
        return arrivalOrder;
    }

    public String getType() {
        return type;
    }
}

// Dog class extending Animal
class Dog extends Animal {
    public Dog(int arrivalOrder) {
        super(arrivalOrder);
        this.type = "Dog";
    }
}

// Cat class extending Animal
class Cat extends Animal {
    public Cat(int arrivalOrder) {
        super(arrivalOrder);
        this.type = "Cat";
    }
}

// Animal Shelter class
class AnimalShelter {
    private Queue<Dog> dogs;
    private Queue<Cat> cats;
    public int order; // To track arrival order of animals

    public AnimalShelter() {
        dogs = new LinkedList<>();
        cats = new LinkedList<>();
        order = 0;
    }

    // Enqueue an animal
    public void enqueue(Animal animal) {
        if (animal instanceof Dog) {
            dogs.offer((Dog) animal);
        } else if (animal instanceof Cat) {
            cats.offer((Cat) animal);
        }
    }

    // Dequeue the oldest animal (either dog or cat)
    public Animal dequeueAny() {
        if (dogs.isEmpty() && cats.isEmpty()) {
            return null;
        } else if (dogs.isEmpty()) {
            return dequeueCat();
        } else if (cats.isEmpty()) {
            return dequeueDog();
        } else {
            Dog oldestDog = dogs.peek();
            Cat oldestCat = cats.peek();
            if (oldestDog.getArrivalOrder() < oldestCat.getArrivalOrder()) {
                return dequeueDog();
            } else {
                return dequeueCat();
            }
        }
    }

    // Dequeue the oldest dog
    public Dog dequeueDog() {
        return dogs.poll();
    }

    // Dequeue the oldest cat
    public Cat dequeueCat() {
        return cats.poll();
    }

    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();

        // Enqueue animals
        shelter.enqueue(new Dog(shelter.order++));
        shelter.enqueue(new Cat(shelter.order++));
        shelter.enqueue(new Dog(shelter.order++));

        // Dequeue any animal and print type
        Animal anyAnimal = shelter.dequeueAny();
        if (anyAnimal != null) {
            System.out.println("Dequeue Any: " + anyAnimal.getType());
        } else {
            System.out.println("No animals to dequeue.");
        }

        // Dequeue a dog and print type
        Dog dog = shelter.dequeueDog();
        if (dog != null) {
            System.out.println("Dequeue Dog: " + dog.getType());
        } else {
            System.out.println("No dogs to dequeue.");
        }

        // Dequeue a cat and print type
        Cat cat = shelter.dequeueCat();
        if (cat != null) {
            System.out.println("Dequeue Cat: " + cat.getType());
        } else {
            System.out.println("No cats to dequeue.");
        }
    }
}
