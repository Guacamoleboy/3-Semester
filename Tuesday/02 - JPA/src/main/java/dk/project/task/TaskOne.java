package dk.project.task;

import dk.project.entity.Person;

public class TaskOne {

    // Attributes

    // _________________________________________________

    public void runTaskOne(){

        System.out.println("\n" + "########## TASK 1 ##########");

        // Create Person
        Person person = new Person("John", "Doe", 25);

        // Assert & Edit
        System.out.println(person);
        person.setAge(26);

        // Assert Final
        System.out.println(person.getAge());
        System.out.println("\n");

    }

}