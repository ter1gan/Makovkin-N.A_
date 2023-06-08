package LinkedList;

import static LinkedList.LinkedList.printLinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList linList = new LinkedList();
        linList.add(1);
        linList.add(2);
        linList.add(3);
        linList.add(4);
        linList.remove(3);
        linList.remove((x) -> x == 1);
        linList.add(2,5);
        System.out.println(linList.get(0));
        printLinkedList(linList);
    }

}
