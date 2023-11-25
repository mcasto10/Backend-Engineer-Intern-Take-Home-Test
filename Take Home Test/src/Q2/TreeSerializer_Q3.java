package Q2;

import java.util.*;

/* In order to support a wide range of data types, we utilize generic types in the binary tree, 
 * as generic types permit the encapsulated data to assume any form. This can be accomplished by 
 * initially converting the Node class into a generic type by introducing a type parameter, T, 
 * into the Node class. 
 * Subsequently, the TreeSerializer interface needs to be modified to align with the updated 
 * Node class, now Node<T>, so we must make the TreeSerializer interface into generic type parameter T.
 * Since we modified Node to utilize the generic Node<T> class and implement the adjusted TreeSerializer<T>
 * interface, we must also modify serialization and deserialization logic to handle the new generic node type. 
 * With these changes, the TreeSerializer implementations become generic and capable of handling binary trees
 * with nodes containing data of any type. The serialized and deserialized forms of the tree maintain the 
 *  desired structure while accommodating a broader range of data types.
 */

/*
 * Package: JSONPackage
 * File: TreeSerializer_Q1.java
 * Author: Martin Castorena Agredano
 * Description: 
 * The TreeSerializer_Q3 class implements the TreeSerializedType interface with support for generic data types.
 * 
 * Serialization:
 *   - The serialize method converts a binary tree into a string representation.
 *   - The serializeHelper method is a recursive helper for the serialize method.
 *   - Uses a HashSet to detect cyclic trees during serialization.
 *   - Throws a RuntimeException if a cyclic connection is detected during serialization.
 *
 * Deserialization:
 *   - The deserialize method converts a string representation back into a binary tree.
 *   - The deserializeHelper method is a recursive helper for the deserialize method.
 *   - Uses a HashSet to detect cyclic trees during deserialization.
 *   - Throws a RuntimeException if a cyclic connection is detected during deserialization.
 *
 * Generic Data Type:
 *   - The TreeSerializer_Q3 class supports generic data types using the TreeSerializedType interface.
 *   - The NodeType class is used to represent nodes with generic data.
 *
 * Example Usage:
 *   - The main method demonstrates an example usage of the TreeSerializer_Q3 class with Integer data type.
 *   - It creates a binary tree, serializes and deserializes it, and prints the results.
 *   - This example does not have a cyclic connection in the tree structure.
 */

// The TreeSerializer_Q3 class implements the TreeSerializedType interface with support for generic data types.
public class TreeSerializer_Q3<T> implements TreeSerializedType<T> {

	// Serialization
	/**
	 * Serializes a binary tree into a string representation.
	 *
	 * @param root The root node of the binary tree to be serialized.
	 * @return A string representing the serialized binary tree.
	 * @throws RuntimeException if a cyclic connection is detected during
	 *                          serialization.
	 */
	public String serialize(NodeType<T> root) {
		// If the root is null, return "null" as the serialized representation.
		if (root == null) {
			return "null";
		}

		// Create a StringBuilder to build the serialized string.
		StringBuilder sb = new StringBuilder();
		// Call the helper method to perform the actual serialization.
		serializeHelper(root, sb, new HashSet<>());
		// Convert StringBuilder to String and return the result.
		return sb.toString();
	}

	/**
	 * Helper method for serialization.
	 *
	 * @param node    The current node being processed during serialization.
	 * @param sb      The StringBuilder used to build the serialized string.
	 * @param visited A HashSet to keep track of visited nodes during serialization.
	 * @throws RuntimeException if a cyclic connection is detected during
	 *                          serialization.
	 */
	private void serializeHelper(NodeType<T> node, StringBuilder sb, Set<NodeType<T>> visited) {
		// If the current node is null, append "null" to the string.
		if (node == null) {
			sb.append("null").append(",");
		} else {
			// If the node has not been visited before, mark it as visited and proceed with
			// serialization.
			if (!visited.contains(node)) {
				visited.add(node);
				// Append the data of the current node and recurse on the left and right
				// children.
				sb.append(node.data).append(",");
				serializeHelper(node.left, sb, visited);
				serializeHelper(node.right, sb, visited);
			} else {
				// If the node has been visited before, throw an exception to indicate a cyclic
				// connection during serialization.
				throw new RuntimeException("Cyclic connection detected while serializing");
			}
		}
	}

	// Deserialization
	/**
	 * Deserializes a string representation into a binary tree.
	 *
	 * @param str  The string representing the serialized binary tree.
	 * @param type The Class object representing the generic data type of the tree.
	 * @return The root node of the deserialized binary tree.
	 * @throws RuntimeException if a cyclic connection is detected during
	 *                          deserialization.
	 */
	public NodeType<T> deserialize(String str, Class<T> type) {
		// Use a queue to process nodes during deserialization.
		Queue<String> nodes = new LinkedList<>(Arrays.asList(str.split(",")));
		// Call the helper method to perform the actual deserialization.
		return deserializeHelper(nodes, new HashSet<>(), type);
	}

	/**
	 * Helper method for deserialization.
	 *
	 * @param nodes   The queue containing nodes to be processed during
	 *                deserialization.
	 * @param visited A HashSet to keep track of visited nodes during
	 *                deserialization.
	 * @param type    The Class object representing the generic data type of the
	 *                tree.
	 * @return The root node of the deserialized binary tree.
	 * @throws RuntimeException if a cyclic connection is detected during
	 *                          deserialization.
	 */
	private NodeType<T> deserializeHelper(Queue<String> nodes, Set<NodeType<T>> visited, Class<T> type) {
		// Poll the next value from the queue.
		String val = nodes.poll();
		// If the value is "null", return null.
		if (val.equals("null")) {
			return null;
		} else {
			// Otherwise, create a new node with the parsed data value.
			NodeType<T> node = new NodeType<>(type.cast(val));

			// If the node has not been visited before, mark it as visited and proceed with
			// deserialization.
			if (!visited.contains(node)) {
				visited.add(node);
				// Recursively deserialize the left and right children.
				node.left = deserializeHelper(nodes, visited, type);
				node.right = deserializeHelper(nodes, visited, type);
				// Return the constructed node.
				return node;
			} else {
				// If the node has been visited before, throw an exception to indicate a cyclic
				// connection during deserialization.
				throw new RuntimeException("Cyclic connection detected while deserializing");
			}
		}
	}

	// Main method for example usage and testing.
	public static void main(String[] args) {
		// Example usage with Integer data type
		NodeType<Integer> root = new NodeType<>(1);
		root.left = new NodeType<>(2);
		root.right = new NodeType<>(3);
		root.left.left = new NodeType<>(4);
		root.left.right = new NodeType<>(5);

		// Create an instance of TreeSerializer_Q3 with Integer data type.
		TreeSerializer_Q3<Integer> serializer = new TreeSerializer_Q3<>();
		// Serialize the tree and print the result.
		String serialized = serializer.serialize(root);
		System.out.println("Serialized Tree: " + serialized);

		// Provide the type explicitly when calling deserialize
		NodeType<Integer> deserialized = serializer.deserialize(serialized, Integer.class);
		System.out.println("Deserialized Tree: " + serializer.serialize(deserialized));
	}

}
