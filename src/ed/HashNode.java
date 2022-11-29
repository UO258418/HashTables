package ed;

public class HashNode<T> {

    private T value;
    private NodeStatus status;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodeStatus getStatus() {
        return status;
    }

    public void setStatus(NodeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        if (value == null)
            return "null";

        return value.toString();
    }
}
