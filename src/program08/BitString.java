package program08;

import java.util.*;
public class BitString {
    // An ordered sequence of bits.
    private ArrayList<AbstractBit> bitString;

    private void setAbstractBitList(ArrayList<AbstractBit> bitList) {
        this.bitString = bitList;
    }

    protected ArrayList<AbstractBit> getAbstractBitList() {
        return this.bitString;
    }

    // Adds a bit to the end of the arrayList of bits.
    public void addBit(AbstractBit bit) {
        this.bitString.add(0, bit);
    }

    // Returns a reference to the bit at location loc in the ArrayList of bits.
    public AbstractBit bitAt(int loc) {
        if(this.isEmpty()) {
            return BinaryBit.zero;
        } else {
            return this.bitString.get(loc - 1);
        }
    }

    // Default constructor.
    public BitString() {
        this.bitString = new ArrayList<AbstractBit>();
    }

    // Specfifying constructor.
    protected BitString(ArrayList<AbstractBit> bitList) {
        this.setAbstractBitList(bitList);
    }

    // Clone constructor
    @SuppressWarnings("unchecked")
    public BitString(BitString guest) {
        this.setAbstractBitList((ArrayList<AbstractBit>) guest.bitString.clone());
    }

    public boolean isEmpty() {
        return this.bitString.size() == 0;
    }

    public int length() {
        return this.bitString.size();
    }

    public BitString clone() {
        return new BitString(this);
    }

    public boolean equals(BitString guest) {
        if(this.length() == guest.length()) {
            for(int i = 1; i <= this.bitString.size(); i++) {
                if(!bitAt(i).equals(guest.bitAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String str = "";

        if(this.isEmpty()) {
            return BinaryBit.zero.toString();
        } else {
            for (AbstractBit bit : this.bitString) {
                str += bit;
            }

            return str;
        }
    }
}

