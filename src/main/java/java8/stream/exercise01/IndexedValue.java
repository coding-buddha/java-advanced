package java8.stream.exercise01;

public class IndexedValue<T> {
    private final int index;
    private final T value;

    public IndexedValue(final int index, final T value) {
        this.index = index;
        this.value = value;
    }

    public int index() {return index;}
    public T value() {return value;}

    @Override
    public String toString() {
        return "IndexedValue{" + "index=" + index + ", value=" + value + '}';
    }
}
