package LinkedList;
import java.util.function.Predicate;

public class LinkedList implements LinkedListInter {
    private Node head;
    private int size;

    private static class Node {
        int data;
        Node indicator;

        Node(int data){
            this.data = data;
            indicator = null;
        }

    }
    public void add(int value) {
        Node newNode = new Node(value);
        Node currentNode = head;
        if(head == null) {
            head = newNode;
        } else {
            while (currentNode.indicator != null) {
                currentNode = currentNode.indicator;
            }
            currentNode.indicator = newNode;
        }
        size++;
    }
    public void add(int index, int value){
        Node current = head;
        Node previousNode = null;
        Node newNode = new Node(value);
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Не найден элемент");
        }
        if(index == 0) {
            newNode.indicator = head;
            head = newNode;
        } else {
            for(int i = 0; current != null; i++) {
                if(i == index) {
                    previousNode.indicator = newNode;
                    newNode.indicator = current;
                    break;
                }
                previousNode = current;
                current = current.indicator;
            }

        }
        size++;
    }
    public void remove(int index) {
        Node curNode = head;
        Node previousNode;
        if(index == 0) {
            head = head.indicator;
        } else {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Не найден элемент");
            }
            while(index - 1 > 0) {
                curNode = curNode.indicator;
                index--;
            }
            previousNode = curNode.indicator;
            curNode.indicator = previousNode.indicator;
        }
        size--;
    }

    public void remove(Predicate<Integer> predicate) {
        Node current = head;
        Node prev = null;

        while (current != null) {
            if (predicate.test(current.data)) {
                if (prev == null) {
                    head = current.indicator;

                } else {
                    prev.indicator = current.indicator;

                }
            } else {
                prev = current;
            }
            current = current.indicator;
        }
    }

    public int get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Не найден элемент");
        }
        Node currentNode = head;
        while (index - 1 >= 0) {
            currentNode = currentNode.indicator;
            index--;
        }
        return currentNode.data;
    }
    public static void printLinkedList(LinkedList list) {
        Node сurrentNode = list.head;
        System.out.print("LinkedList: " + "\n");
        while (сurrentNode != null) {
            System.out.print(сurrentNode.data + " ");
            сurrentNode = сurrentNode.indicator;
        }
    }
    public int size() {
        return size();
    }
}
