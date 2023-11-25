package Q2;

interface TreeSerializedType<T> {
    String serialize(NodeType<T> root);
    NodeType<T> deserialize(String str, Class<T> type);
}