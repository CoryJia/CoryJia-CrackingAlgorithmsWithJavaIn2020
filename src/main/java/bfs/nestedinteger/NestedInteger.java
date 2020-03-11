package bfs.nestedinteger;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class NestedInteger implements NestedInt {
    private Integer value;
    private List<NestedInteger> list;

    public NestedInteger() {
        this.list = new LinkedList<NestedInteger>();
    }

    public NestedInteger(Integer value) {
        this();
        this.value = value;
    }

    @Override
    public boolean isInteger() {
        return this.value != null;
    }

    @Override
    public Integer getInteger() {
        return value;
    }

    @Override
    public void setInteger(int value) {
        this.value = value;
    }


    @Override
    public void add(NestedInteger ni) {
        if (isInteger()) {
            this.list = new LinkedList<NestedInteger>();
            this.list.add(this);
            this.value = null;
        }else {
            this.list.add(ni);
        }
    }

    @Override
    public List<NestedInteger> getList() {
        return this.list;
    }

    @Override
    public String toString() {
        return "NestedInteger{" +
                "value=" + value +
                ", list=" + list.toString() +
                '}';
    }
}
