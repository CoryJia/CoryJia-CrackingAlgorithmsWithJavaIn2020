package adt;
/*
 * @author: Cory Jia
 * @email: coryjia@gmail.com
 * @date: 12/6/18 8:06 AM
 */

import java.util.Objects;

public class Interval {
    public int start;
    public int end;

    public Interval() {
        this(0,0);
    }

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return start == interval.start &&
                end == interval.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
