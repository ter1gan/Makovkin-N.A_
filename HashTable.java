package HashTable;


import java.util.ArrayList;
import java.util.Collections;

public class HashTable<K, V> {
    private static int capacity = 20;

    private static double size = 0;
    private ArrayList<HNode<K, V>> nodeArray =
            new ArrayList<>(Collections.nCopies(capacity, null));

    private void resize(){
        capacity += capacity;
        nodeArray = new ArrayList<>(Collections.nCopies(capacity, null));
        ArrayList<HNode<K, V>> arr = nodeArray;
        for(int i = 0; i < arr.size(); i++) {
            HNode<K,V> node = arr.get(i);
            while(node != null){
                add(node.key, node.value);
                node = node.next;
            }
        }

    }

    private int getBucketIndex(K key) {
        int hashFunction = key.hashCode();
        return Math.abs(hashFunction % capacity);
    }


    public V remove(K key) {
        int index = getBucketIndex(key);
        int hashCode = key.hashCode();
        HNode<K, V> base = nodeArray.get(index);
        K block;


        //поиск ключа
        if(base == null){
            return null;
        } else {
            while(base != null) {
                block = base.key;
                if(block.equals(key) && hashCode == base.hashCode){
                    break;
                }
                base = base.next;
            }
            nodeArray.set(index, base.next);

        }
        size--;

        return base.value;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        int hashCode = key.hashCode();

        HNode<K, V> head = nodeArray.get(bucketIndex);
        while(head != null) {
            K headKey = head.key;
            if(headKey.equals(key) && head.hashCode == hashCode){
                return head.value;
            } else {
                head = head.next;
            }
        }
        return null;

    }

    public void add(K key, V value) {
        if(size / capacity >= 0.75){
            resize();
        }
        int bucketIndex = getBucketIndex(key);
        int hashCode = key.hashCode();
        HNode<K, V> newNode = new HNode<>(key, value, hashCode);
        HNode<K, V> base = nodeArray.get(bucketIndex);

        //Присутствует ли ключ сейчас
        while(base != null) {
            K headKey = base.key;
            if(headKey.equals(key) && base.hashCode == hashCode) {
                base.value = value;
                return;
            } else {
                base = base.next;
            }
        }
        size++;


        // Вставить ключ в цепочку
        base = nodeArray.get(bucketIndex);
        newNode.next = base;
        nodeArray.set(bucketIndex, newNode);
    }
}







