package program08;

public abstract class AbstractBit {
    private boolean bit;
    public abstract AbstractBit clone();
    public abstract AbstractBit addBits(AbstractBit guest);
    public abstract AbstractBit addBits(AbstractBit guest1, AbstractBit guest2);
    public abstract AbstractBit carryBit(AbstractBit guest);
    public abstract AbstractBit carryBit(AbstractBit guest1, AbstractBit guest2);

    protected void setBit(boolean value) {
        this.bit = value;
    }

    public boolean getBit() {
        return this.bit;
    }

    // Default constructor
    public AbstractBit() {
        this(false);
    }

    // Specifying constructor
    public AbstractBit(boolean value) {
        this.setBit(value);
    }

    // Clone constructor
    public AbstractBit(AbstractBit guest) {
        this.bit = guest.bit;
    }

    public boolean equals(AbstractBit guest) {
        return this.bit == guest.bit;
    }

    public String toString() {
        if(this.bit) {
            return "1";
        } else {
            return "0";
        }
    }
}

