package program08;

public class BinaryBit extends AbstractBit{
    public static final BinaryBit zero = new BinaryBit(false);
    public static final BinaryBit one = new BinaryBit(true);

    // Default constructor.
    public BinaryBit() {
        super();
    }

    // Allows construction with a boolean - false -> 0, true -> 1.
    public BinaryBit(boolean bit) {
        super(bit);
    }

    // Allows construction with an int - should be 0 or 1.
    public BinaryBit(int bit) {
        super(bit == 1);
    }

    // Clone constructor.
    public BinaryBit(BinaryBit guest) {
        super(guest);
    }

    /*
     * If we want to call some method and the method needs current
     * class object as an argument, we can use this keyword to pass
     * the current class object instance.
     */
    @Override
    public BinaryBit clone() {
        return new BinaryBit(this);
    }

    // Equals comparator method.
    public boolean equals(BinaryBit guest) {
        return super.equals(guest);
    }

    // Overwritten toString method.
    @Override
    public String toString() {
        return super.toString();
    }

    // Returns the low order bit of adding the host bit to the guest bit
    @Override
    public AbstractBit addBits(AbstractBit guest) {
        if((this.equals(one) && guest.equals(zero)) || (this.equals(zero) && guest.equals(one))) {
            return one;
        } else {
            return zero;
        }
    }

    // Returns the low order bit of adding the host bit, the guest1 bit, and the guest2 bit
    @Override
    public AbstractBit addBits(AbstractBit guest1, AbstractBit guest2) {
        AbstractBit s1 = guest1.addBits(guest2);

        return addBits(s1);
    }

    // Returns the high order bit of adding the host bit to the guest bit
    @Override
    public AbstractBit carryBit(AbstractBit guest) {
        if(this.equals(one) && guest.equals(one)) {
            return one;
        } else {
            return zero;
        }
    }

    // Returns the high order bit of adding the host bit to the guest bit
    @Override
    public AbstractBit carryBit(AbstractBit guest1, AbstractBit guest2) {
        int oneBits = 0;

        if(this.equals(BinaryBit.one)) {
            oneBits++;
        }

        if(guest1.equals(BinaryBit.one)) {
            oneBits++;
        }

        if(guest2.equals(BinaryBit.one)) {
            oneBits++;
        }

        if(oneBits >= 2) {
            return BinaryBit.one;
        } else {
            return BinaryBit.zero;
        }
    }
}