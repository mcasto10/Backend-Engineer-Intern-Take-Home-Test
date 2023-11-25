package Q2;

// Nested NodeType class representing nodes with generic data type.
public class NodeType<T> {
    NodeType<T> left;
    NodeType<T> right;
    T data;

    public NodeType(T data) {
        this.data = data;
    }
}
