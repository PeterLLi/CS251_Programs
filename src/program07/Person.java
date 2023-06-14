package program07;

public class Person {
    private String name;
    protected static final String DEFAULT_NAME = "Peter";

    // Default constructor.
    public Person() {
        this(DEFAULT_NAME);
    }

    // Specifying constructor.
    public Person(String name) {
        this.setName(name);
    }

    // Public accessor.
    public String getName(){
        return this.name;
    }

    // Protected mutator.
    protected void setName(String name) {
        this.name = name;
    }
}

