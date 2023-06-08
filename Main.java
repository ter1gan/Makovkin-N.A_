package HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<Object, Integer> map = new HashTable<Object, Integer>();
        map.add(1, 1);
        map.add(true, 2);
        map.add("Hello", 2);
        map.add("Hello", 4);
        map.add(false, 23);
        map.add("o", 21);
        map.add("s", 45);
        map.remove("Hello");
        System.out.println(map.get("Hello"));
        System.out.println(map.get("s"));
        System.out.println(map.get(1));
        System.out.println(map.get(true));
        System.out.println(map.get("t"));
    }
}
