package program05;

import java.util.*;

public class DynArray {
    private double[] array;
    private int size;
    private int nextIndex;

    public DynArray() {
        this.size = 1;
        this.array = new double[1];
        this.nextIndex = 0;
    }

    public int arraySize() {
        return this.size;
    }

    public int elements() {
        return this.nextIndex;
    }

    // make array a reference to an array that is twice as large
    // and contains the same values for indicies 0 through
    // Only grow if nextIndex reaches size.
    // nextIndex - 1, and adjust size appropriately.
    private void grow() {
        this.array = Arrays.copyOf(array, this.size * 2);
        this.size = this.size * 2;
    }

    // make array a reference to an array that is half as large
    // and contains the same values for indicies 0 through
    // nextIndex - 1, and adjust size appropriately.
    private void shrink() {
        array = Arrays.copyOf(array, size / 2);
        size = size / 2;
    }

    // accessor
    // if 0 <= index < nextIndex
    // return the value of array[index].
    // else
    // return Double.NaN.
    public double at(int index) {
        if ((index >= 0) && (index < this.nextIndex)) {
            return array[index];
        } else {
            return Double.NaN;
        }
    }

    // mutator
    // if 0 <= index <= nextIndex
    // move the nessecary values over one so that value can
    // be incerted at the location index in the array, inserts
    // incerts value at the location index, and adjust nextIndex
    // appropriately.

    // Note a grow() may be neccessary before or after.
    // else
    // do nothing.
    public void insertAt(int index, double value) {
        if (index >= 0 && index <= this.nextIndex) {
            if (this.nextIndex == this.size) {
                this.grow();
            }

            for (int i = this.nextIndex; i > index; i--) {
                this.array[i] = array[i - 1];
            }
        }

        this.array[index] = value;
        this.nextIndex++;
    }

    // mutator
    public void insert(double value) {
        // insert value at location nextIndex.
        if (this.nextIndex == this.size) {
            this.grow();
        }

        this.array[this.nextIndex] = value;
        this.nextIndex++;
    }

    // mutator
    // if 0 <= index < nextIndex
    // move the nessecary values over one as to eliminate
    // the value at the location index in the array, adjust
    // nextIndex appropriately, and return the value that was
    // at the location index .
    // Note: a shrink() may be neccessary before or after.
    // else
    // return Double.NaN.
    public double removeAt(int index) {
        if (index >= 0 && index < this.nextIndex) {
            double value = at(index);

            if (this.nextIndex != 0 && this.nextIndex <= (size / 2)) {
                this.shrink();
            }

            for (int i = index; i < this.nextIndex - 1; i++) {
                this.array[i] = this.array[i + 1];
            }

            this.nextIndex--;

            return value;
        } else {
            return Double.NaN;
        }
    }

    // mutator
    public double remove() {
        // return the removal of the value at location nextIndex-1.
        // System.out.println("nextIndex: " + this.nextIndex);
        // System.out.println("Size: " + this.size);
        double value = at(nextIndex - 1);

        if (this.nextIndex != 0 && this.nextIndex <= (size / 2)) {
            this.shrink();
        }

        --nextIndex;

        // If next index reaches a negative number, reset it to 0
        if (this.nextIndex == -1) {
            this.nextIndex = 0;
        }

        // return the removal of the value at location nextIndex-1.
        return value;
    }

    // accessor
    // prints the values of all occupied locations of the array to
    // the screen
    public void printArray() {
        for (int i = 0; i < this.nextIndex; i++) {
            System.out.printf("\narray.at(%d) = %.1f", i, array[i]);
        }
        System.out.println();
    }
}

