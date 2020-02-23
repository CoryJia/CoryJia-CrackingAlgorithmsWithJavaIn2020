package adt;

public class Pair<K,V> {
    public K key;
    public V value;
    int size = 0;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
        size++;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString() {
        return "[key: " + key + ",value: " + value + "]";
    }


    @Override
    public boolean equals(Object obj) {

//        if (this.getClass() != obj.getClass()) return false;
        if (!(obj instanceof Pair)) return false;

        return this.key.equals(((Pair) obj).key) && this.value.equals(((Pair) obj).value);
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }
}
