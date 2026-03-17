package problem2;

import java.util.Objects;

/**
 * Abstract class representing an individual person who is a creator.
 * Stores a separate first name and last name.
 */
public abstract class Person implements Creator {

    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person other = (Person) o;
        return Objects.equals(firstName, other.firstName)
            && Objects.equals(lastName, other.lastName)
            && this.getClass() == other.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), firstName, lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
