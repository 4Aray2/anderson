package collections;

import lombok.ToString;

@ToString
public class ArrayList {
    private int[] array;
    private int length;
    private int size;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int size) {
        this.size = size;
        array = new int[size];
        length = 0;
    }

    public int getLength() {
        return length;
    }

    public int getSize() {
        return size;
    }

    public void put(int value) {
        if (length == size) {
            size *= 2;
            int[] newArr = new int[size];
            System.arraycopy(array, 0, newArr, 0, length);
            array = newArr;
        }
        array[length++] = value;
    }

    public int get(int index) {
        if (0 <= index && index < length) {
            return array[index];
        } else throw new ArrayIndexOutOfBoundsException();
    }

    public void delete(int index) {
        if (0 > index || index >= length) {
            return;
        }

        for (int i = index; i < length; i++) {
            array[i] = array[i + 1];
        }
        length--;
        if (length <= size / 3) {
            size /= 2;
            int[] newArr = new int[size];
            System.arraycopy(array, 0, newArr, 0, length);
            array = newArr;
        }
    }
}
