package HashTable;

class HNode<K, V> {
    K key;
    V value;
    int hashCode;
    HNode<K, V> next;
    HNode(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}
