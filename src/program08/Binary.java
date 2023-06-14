package program08;

public class Binary extends BitString {
    private BitString bits;

    // Default constructor.
    public Binary() {
        bits = new BitString();
        bits.addBit(BinaryBit.zero);
    }

    /*
     * Encode a non-negative (base 10) number val such into
     * the host's bitstring - used in the Binary(long val) constructor.
     */
    private void encode(long val) {
        while(val > 0) {
            int remainder = (int) (val % 2);

            val /= 2;

            bits.addBit(new BinaryBit(remainder));
        }
    }

    // First specifying constructor.
    public Binary(long val) {
        bits = new BitString();

        this.encode(val);
    }

    // Second specifying constructor.
    public Binary(BitString guest) {
        bits = guest.clone();
    }

    // Clone constructor.
    public Binary(Binary guest) {
        this(guest.bits);
    }


    // Clone method.
    public Binary clone() {
        return new Binary(this);
    }

    /*
     * Return a new Binary number that is the result of adding the
     * host bitstring to the guest bitstring under then assumption
     * that both are representing (base 2) numbers.
     */
    public Binary addition(Binary guest) {
        int position1 = bits.length();
        int position2 = guest.length();
        AbstractBit carry = BinaryBit.zero;
        AbstractBit b1;
        AbstractBit b2;
        AbstractBit sum;
        BitString result = new BitString();

        while(position1 > 0 || position2 > 0) {
            if(position1 == 0) {
                b1 = BinaryBit.zero;
            } else {
                b1 = this.bitAt(position1--);
            }

            if(position2 == 0) {
                b2 = BinaryBit.zero;
            } else {
                b2 = guest.bitAt(position2--);
            }

            sum = b1.addBits(carry, b2);
            carry = b1.carryBit(carry, b2);

            result.addBit(sum);
        }

        if(carry.equals(BinaryBit.one)) {
            result.addBit(carry);
        }

        return new Binary(result);
    }

    public int length() {
        return bits.length();
    }

    /*
     * Overwrote and provided own implemenation of the following 3 methods so that the driver class can receive accurate information.
     * This is because the driver class uses bits.bitsAt and we need to use the variable BitString bit to call
     * the necessary information.
     */

    // Define the method bitAt().
    @Override
    public AbstractBit bitAt(int location) {
        return bits.bitAt(location);
    }

    // Define the method toString().
    @Override
    public String toString() {
        return bits.toString();
    }

    // Define the method equals().
    public boolean equals(Binary guest) {
        return bits.equals(guest.bits);
    }
}

