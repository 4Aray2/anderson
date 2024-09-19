package collections;

import java.util.Iterator;

public class HashSet implements Iterable<Integer> {
    private ListNode[] set;
    private int buckets;

    public HashSet() {
        buckets = 10;
        set = new ListNode[buckets];
        for (int i = 0; i < buckets; i++) {
            set[i] = new ListNode(0);
        }
    }

    public void put(int key) {
        ListNode current = set[getHash(key)];
        while (current.next != null) {
            if (current.next.val == key) return;
            current = current.next;
        }
        current.next = new ListNode(key);
    }

    public void delete(int key) {
        ListNode current = set[getHash(key)];
        while (current.next != null) {
            if (current.next.val == key) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public boolean contains(int key) {
        ListNode current = set[getHash(key)];
        while (current.next != null) {
            if (current.next.val == key) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private int getHash(int key) {
        return key % buckets;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int bucketIndex = 0;
            private ListNode currentNode = set[bucketIndex];

            @Override
            public boolean hasNext() {
                if (currentNode != null && currentNode.next != null) {
                    return true;
                }

                for (int i = bucketIndex + 1; i < buckets; i++) {
                    if (set[i].next != null) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public Integer next() {
                if (currentNode != null && currentNode.next != null) {
                    currentNode = currentNode.next;
                    return currentNode.val;
                }

                bucketIndex++;
                while (bucketIndex < buckets && set[bucketIndex].next == null) {
                    bucketIndex++;
                }

                if (bucketIndex >= buckets) {
                    throw new java.util.NoSuchElementException();
                }

                currentNode = set[bucketIndex].next;
                return currentNode.val;
            }
        };
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
