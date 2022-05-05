import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AnimalBase {

    private ArrayList<Animal> animals;
    private PrintStream out;

    public AnimalBase() {
        animals = new ArrayList<>();
    }

    public void start() throws FileNotFoundException {
        UserInterface ui = new UserInterface(this);
        ui.start();
    }

    public static void main(String[] args) throws FileNotFoundException {
        AnimalBase app = new AnimalBase();
        app.start();
    }

    public Iterable<Animal> getAllAnimals() {
        return animals;
    }

    public int getAnimalCount() {
        return animals.size();
    }

    public void sortBy(String sortBy, SortDirection sortDirection) {
        // TODO: Implement sorting!
        System.out.println("TODO: Sort the list of animals by: " + sortBy);
    }

    public void createNewAnimal(String name, String description, String type, int age, double weight) {
        Animal animal = new Animal(name,description,type,age,weight);
        animals.add(animal);
    }

    public boolean deleteAnimal(String name) {
        // find animal with this name
        Animal animal = findAnimalByName(name);
        if(animal == null) {
            return false;
        } else {
            animals.remove(animal);
            return true;
        }
    }

    private Animal findAnimalByName(String name) {
        for(Animal animal : animals) {
            if(animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }


    public void loadDatabase() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("animals.csv"));
        while(fileScanner.hasNext()) {
            String fileLine = fileScanner.nextLine();
            Scanner input =  new Scanner(fileLine).useDelimiter(";").useLocale(Locale.ENGLISH);
            String name = input.next();
            String desc = input.next();
            String type = input.next();
            int age = input.nextInt();
            double weight = input.nextDouble();
            Animal animal = new Animal(name, desc, type, age, weight);
            System.out.println(animal);
            animals.add(animal);
        }

    }

    public void saveDatabase() throws FileNotFoundException {
        out=new PrintStream(new File("animals.csv"));
        for (Animal animal:animals) {
            out.println(animal.animalCSV());

        }
    }

}
