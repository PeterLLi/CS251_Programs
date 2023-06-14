package program07;

public class Member extends Person {
    private int uniqueID;
    protected static final int DEFAULT_ID = 1;

    // Default constroctor.
    public Member() {
        super();
        this.setID(DEFAULT_ID);
    }

    // Specifying constructor.
    public Member(String name, int ID) {
        super(name);
        this.setID(ID);
    }

    // Accessor.
    public int getId() {
        return this.uniqueID;
    }

    // Protected mutator.
    protected void setID(int ID) {
        this.uniqueID = ID;
    }
}

