package Q2;

/*
 * Package: JSONPackage
 * File: TreeSerializer_Q1.java
 * Author: Martin Castorena Agredano
 * Description: 
 * The TreeSerializer_Q2 class implements the TreeSerializer interface for binary tree serialization and deserialization.
 *
 * Serialization:
 *   - The serialize method converts a binary tree into a string representation.
 *   - The serializeHelper method is a recursive helper for the serialize method.
 *   - Uses a HashSet to detect cyclic trees during serialization.
 *
 * Deserialization:
 *   - The deserialize method converts a string representation back into a binary tree.
 *   - The deserializeHelper method is a recursive helper for the deserialize method.
 *
 * Example Usage:
 *   - The main method demonstrates an example usage of the TreeSerializer_Q2 class.
 *   - It creates a binary tree, introduces a cyclic connection, and attempts serialization.
 *   - Handles a RuntimeException if a cyclic tree is detected during serialization.
 *
 * Note: This implementation assumes that the Node class is defined elsewhere in the code.
 */

import java.util.*;

// The TreeSerializer_Q2 class implements the TreeSerializer interface for binary tree serialization and deserialization,
public class TreeSerializer_Q2 implements TreeSerializer {

	// Serialization
	/**
	 * Serializes a binary tree into a string representation.
	 *
	 * @param root The root node of the binary tree to be serialized.
	 * @return A string representing the serialized binary tree.
	 * @throws RuntimeException if a cyclic tree is detected during serialization.
	 */
	public String serialize(Node root) {
		// If the root is null, return "null" as the serialized representation.
		if (root == null) {
			return "null";
		}

		// Use a HashSet to keep track of visited nodes during serialization to detect
		// cyclic trees.
		Set<Node> visited = new HashSet<>();
		// Create a StringBuilder to build the serialized string.
		StringBuilder sb = new StringBuilder();
		// Call the helper method to perform the actual serialization.
		serializeHelper(root, sb, visited);
		// Convert StringBuilder to String and return the result.
		return sb.toString();
	}

	/**
	 * Helper method for serialization.
	 *
	 * @param node    The current node being processed during serialization.
	 * @param sb      The StringBuilder used to build the serialized string.
	 * @param visited A HashSet to keep track of visited nodes during serialization.
	 * @throws RuntimeException if a cyclic tree is detected during serialization.
	 */
	private void serializeHelper(Node node, StringBuilder sb, Set<Node> visited) {
		// If the current node is null, append "null" to the string.
		if (node == null) {
			sb.append("null").append(",");
		} else {
			// If the node has been visited before, throw an exception to indicate a cyclic
			// tree.
			if (visited.contains(node)) {
				throw new RuntimeException("Cyclic tree detected");
			}
			// Mark the node as visited.
			visited.add(node);

			// Append the value of the current node and recurse on the left and right
			// children.
			sb.append(node.num).append(",");
			serializeHelper(node.left, sb, visited);
			serializeHelper(node.right, sb, visited);
		}
	}

	// Deserialization
	/**
	 * Deserializes a string representation into a binary tree.
	 *
	 * @param str The string representing the serialized binary tree.
	 * @return The root node of the deserialized binary tree.
	 */
	public Node deserialize(String str) {
		// Use a queue to process nodes during deserialization.
		Queue<String> nodes = new LinkedList<>(Arrays.asList(str.split(",")));
		// Call the helper method to perform the actual deserialization.
		return deserializeHelper(nodes);
	}

	/**
	 * Helper method for deserialization.
	 *
	 * @param nodes The queue containing nodes to be processed during
	 *              deserialization.
	 * @return The root node of the deserialized binary tree.
	 */
	private Node deserializeHelper(Queue<String> nodes) {
		// Poll the next value from the queue.
		String val = nodes.poll();
		// If the value is "null", return null.
		if (val.equals("null")) {
			return null;
		} else {
			// Otherwise, create a new node with the parsed integer value.
			Node node = new Node();
			node.num = Integer.parseInt(val);
			// Recursively deserialize the left and right children.
			node.left = deserializeHelper(nodes);
			node.right = deserializeHelper(nodes);
			// Return the constructed node.
			return node;
		}
	}

	// Main method for example usage and testing.
	public static void main(String[] args) {
		// Example usage
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);

		// Introduce a cyclic connection
		root.left.left.right = root;

		// Create an instance of TreeSerializer_Q2.
		TreeSerializer_Q2 serializer = new TreeSerializer_Q2();

		try {
			// This should throw a RuntimeException due to the cyclic connection.
			serializer.serialize(root);
		} catch (RuntimeException e) {
			System.out.println("Cyclic tree detected: " + e.getMessage());
		}
	}
}
