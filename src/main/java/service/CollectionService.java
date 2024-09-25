package service;

import collections.ArrayList;
import collections.HashSet;

public class CollectionService {

    public void arrayTest() {
        ArrayList arrayList = new ArrayList(5);
        arrayList.put(1);
        arrayList.put(2);
        arrayList.put(3);
        arrayList.put(4);
        arrayList.put(5);

        System.out.println(arrayList);

        // doubles size after this put
        arrayList.put(6);
        System.out.println(arrayList);


        arrayList.delete(1);
        arrayList.delete(1);

        // halves size after this delete
        arrayList.delete(1);
        System.out.println(arrayList);

        System.out.println(arrayList.get(0));
//        System.out.println(arrayList.get(-1)); // ArrayIndexOutOfBoundsException
//        System.out.println(arrayList.get(3)); // ArrayIndexOutOfBoundsException
    }

    public void setTest() {
        HashSet set = new HashSet();
        set.put(1);
        set.put(1);
        set.put(2);
        set.put(2);
        set.put(3);
        set.put(4);
        set.put(5);
        set.put(6);
        set.put(6);
        for (Integer integer : set) {
            System.out.print(integer + " ");
        }
        System.out.println();

        System.out.println(set.contains(6));
        System.out.println(set.contains(7));

        set.delete(6);
        System.out.println(set.contains(6));


        for (Integer integer : set) {
            System.out.print(integer + " ");
        }
    }
}
