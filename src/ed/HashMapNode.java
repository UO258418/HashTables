package ed;

public class HashMapNode<T, V> {

    private T key;
    private V value;
    private NodeStatus status;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
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
