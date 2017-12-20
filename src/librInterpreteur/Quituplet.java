package librInterpreteur;

public class Quituplet<T, U, V, W, Y> {

    private final T first;
    private final U second;
    private final V third;
    private final W fourth;
    private final Y fifth;

    public Quituplet(T first, U second, V third, W fourth, Y fifth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }

    public T getFirst() { return first; }
    public U getSecond() { return second; }
    public V getThird() { return third; }
    public W getFourth() { return fourth; }
    public Y getFifth() { return fifth; }
}