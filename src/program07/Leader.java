package program07;

public class Leader extends Member {
    private static final int DEFAULT_TERM = 0;
    private int term;

    // Default constructor.
    public Leader() {
        super();
        this.setTerm(DEFAULT_TERM);
    }

    // Specifying constructor.
    public Leader(String name, int ID, int term) {
        super(name, ID);
        this.setTerm(term);
    }

    // Public accessor.
    public int getTerm() {
        return this.term;
    }

    // Protected mutator.
    protected void setTerm(int term) {
        this.term = term;
    }
}
