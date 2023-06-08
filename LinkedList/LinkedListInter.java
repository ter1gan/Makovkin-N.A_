package LinkedList;

import java.util.function.Predicate;

public interface LinkedListInter {
    void add(int value);
    void add(int idx, int value);
    void remove(int idx);
    void remove(Predicate<Integer> predicate);
    int get(int idx);
    int size ();

}
