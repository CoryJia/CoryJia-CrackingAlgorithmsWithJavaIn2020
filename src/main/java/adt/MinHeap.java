package adt;

import java.util.ArrayList;
import java.util.List;

/**
 * build up a min heap
 */
public class MinHeap<T extends Comparable>{
    private List<T> arr;

    public MinHeap(){
        arr = new ArrayList<>();
    }

    public MinHeap(int initialCapacity){
        arr = new ArrayList<>(initialCapacity);
    }

    public MinHeap(List<T> inputArr) {
        //Not to modify the input, deep copy it
        this.arr = new ArrayList<>();
        this.arr.addAll(inputArr);

        heapify();
    }

    private void heapify() {
        //start the last non leaf node to do percolate down
        int lastNonLeaf = parentIdx(arr.size() - 1);

        for (int i = lastNonLeaf; i >= 0; i--) {
            percolateDown(i);
        }
    }

    private int parentIdx(int childIdx){
        return (childIdx - 1) / 2;
    }

    private int leftChild(int parentIdx){
        return parentIdx * 2 + 1;
    }

    private void percolateDown(int idx){
        int size = arr.size();
        int child = leftChild(idx);

        while(child < size){
            if(child + 1 < size && arr.get(child + 1).compareTo(arr.get(child)) > 0){
                child++;
            }

            if(arr.get(idx).compareTo(arr.get(child)) <= 0){
                break;
            }

            swap(idx, child);

            idx = child;
            child = leftChild(idx);
        }
    }

    private void swap(int i, int j){
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    private void percolateUp(int idx){
        if(idx <= 0 || idx >= arr.size()){
            return;
        }

        for(int j = parentIdx(idx); j >= 0 && arr.get(idx).compareTo(arr.get(j)) < 0; idx = j, j = parentIdx(j)){
            swap(idx, j);
        }
    }

    public boolean offer(T val){
        arr.add(val);
        percolateUp(arr.size()  - 1);

        return true;
    }

    public T poll(){
        if(arr.isEmpty()) return null;

        T res = arr.get(0);

        swap(0, arr.size() - 1);
        arr.remove(arr.size() - 1);
        percolateDown(0);

        return res;
    }

    public T peek(){
        return isEmpty()? null : arr.get(0);
    }

    public void update(int idx, T val){
        if(idx < 0 || idx >= arr.size()) return;

        arr.set(idx, val);
        percolateDown(idx);
        percolateUp(idx);
    }

    public  int size(){
        return arr.size();
    }

    public boolean isEmpty(){
        return arr.size() == 0;
    }
}

