package Q1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * Package: JSONPackage
 * File: NewJsonParser.java
 * Author: Martin Castorena Agredano
 * Description: This Java class provides a JSON parser for parsing JSON-formatted 
 * 				strings into Map objects. It supports nested objects, arrays, and 	
 * 				various data types including strings, booleans, integers, and 
 * 				decimals. The parser includes exception handling for handling 
 * 				malformed JSON input gracefully.
 */

public class JsonParser {

	private static int index = 0;

	private static Map<String, Object> result = new HashMap<>();

	private static String jsonString;

	// Method to parse a JSON-formatted string into a Map
	public static Map<String, Object> parse(String json) {
		jsonString = json.trim();

		// Check if the string starts with '{', indicating the beginning of a JSON
		if (jsonString.charAt(index) == '{') {
			index++;

			// Iterate through the top level of each key of the JSON
			// jsonString.length(): Getting the total number of characters inside the JSON
			while (index < jsonString.length() - 2) {

				String key = getKey();
				Object highestMap = new HashMap<>();

//            	Getting the object assigned with that specific key
				highestMap = checkType(key);

				result.put(key, highestMap);

			}
		} else {
			throw new IllegalArgumentException("Input JSON must start with '{'.");
		}

		return result;

	}

	/**
	 * Helper method to handle the parsing of nested JSON objects within an array.
	 * It increments the index to skip the opening bracket of the nested object,
	 * iterates through the characters of the nested object string, extracts keys
	 * and their associated values, and stores them in a Map.
	 *
	 * @param iterateString The string representing the nested JSON object within an
	 *                      array.
	 * @return A Map containing the parsed key-value pairs of the nested object.
	 */
	private static Map<String, Object> innerLoop(String iterateString) {
		// Increment the index to skip the opening bracket of the nested object
		index = index + 1;

		// Initialize a map to store key-value pairs of the nested object
		Map<String, Object> currentMap = new HashMap<>();

		// Iterate through the characters of the nested object string
		while (index < iterateString.length() - 1) {
			// Extract the key for the current key-value pair
			String key = getKey();

			// Check the type of the value associated with the key and parse it
			Object smallMap = checkType(key);

			// Add the key-value pair to the current map
			currentMap.put(key, smallMap);
		}

		// Return the map containing the parsed key-value pairs of the nested object
		return currentMap;
	}

	/**
	 * Method to extract and return the key from the JSON string. It identifies the
	 * opening and closing double quotes of the key, extracts the key from the JSON
	 * string, and updates the index for the next values.
	 *
	 * @return The extracted key from the JSON string.
	 */
	private static String getKey() {
		// Find the index of the opening double quote of the key
		int keyStart = jsonString.indexOf('"', index) + 1;

		// Find the index of the closing double quote of the key
		int keyEnd = jsonString.indexOf('"', keyStart);

		// Extract the key from the JSON string
		String key = jsonString.substring(keyStart, keyEnd);

		// Move the index to the character after the closing double quote of the key
		index = keyEnd + 1;

		// Reference the index for the next values after the key, specifically the colon
		// (':') that separates the key and value
		index = jsonString.indexOf(':', index) + 1;

		// Return the extracted key
		return key;
	}

	/**
	 * Method to handle the parsing of a JSON object enclosed within curly braces '{
	 * }'. It extracts the content of the JSON object, delegates the parsing to the
	 * parseMembers method, and returns a Map representing the key-value pairs of
	 * the parsed JSON object.
	 *
	 * @return A Map representing the key-value pairs of the parsed JSON object.
	 */ // Method to handle the parsing of a JSON object enclosed in curly braces '{ }'
	private static Map<String, Object> CurlyBrace() {
		// Find the index of the closing curly brace ('}') in the JSON string
		int curlyBraceEnd = jsonString.indexOf('}', index);

		// Extract the substring representing the JSON object inside the curly braces
		String bracketValue = (jsonString.substring(index, curlyBraceEnd + 1).trim());

		// Parse the contents of the JSON object using the helper method parseMembers
		Map<String, Object> newestPare = parseMembers(bracketValue);

		// Move the index to the character after the closing curly brace of the JSON
		// object
		index = curlyBraceEnd + 2;

		// Return the parsed Map representing the JSON object
		return newestPare;
	}

	/**
	 * Method to iterate through the content of a JSON object enclosed within curly
	 * braces, identifying keys and their associated values.
	 *
	 * @param bracketValue The content of the JSON object enclosed within curly
	 *                     braces.
	 * @return A Map representing the key-value pairs of the parsed JSON object.
	 */
	private static Map<String, Object> parseMembers(String bracketValue) {
		// Initialize a new Map to store the key-value pairs of the JSON object
		// Using LinkedHashMap to maintain the order of insertion
		Map<String, Object> newResult = new LinkedHashMap<>();

		// Calculate the final index by adding the current index to the length of the
		// bracketValue - 1
		int finalLength = index + (bracketValue.length() - 1);

		// Iterate through the content of the JSON object
		while (index < finalLength) {
			// Extract the key using the helper method getKey()
			String gottenKey = getKey();

			// Find the index of the comma ',' separating key-value pairs
			int valueEnd = jsonString.indexOf(',', index);

			// Checks to see if a comma is at the end, if not, then it's the last value
			if (valueEnd == -1) {
				valueEnd = jsonString.indexOf('}', index);
			}

			// Extract the value and trim it
			String value = jsonString.substring(index, valueEnd).trim();

			// Move the index to the character after the comma
			index = valueEnd + 1;

			// Parse the value using the helper method parseType and store the key-value
			// pair in the Map
			newResult.put(gottenKey, parseType(value));

			// Print the intermediate result for debugging purposes
//			System.out.println(newResult);
		}

		// Print the final result after parsing all key-value pairs
//		System.out.println(newResult + " newResult");

		// Return the Map representing the key-value pairs of the JSON object
		return newResult;
	}

	/**
	 * Determines the next type of character in the JSON string based on the current
	 * index.
	 *
	 * This method identifies the positions of curly braces, square brackets, and
	 * commas, then compares their indices to determine the next type in the JSON
	 * structure.
	 *
	 * @return true if the next type is an opening curly brace or square bracket,
	 *         indicating a nested structure. false if the next type is a comma or
	 *         if the end of the current JSON structure is reached.
	 */
	private static boolean nextType() {
		// Find the index of the next opening curly brace
		int indexOfCurlyBrace = jsonString.indexOf('{', index + 2);

		// Find the index of the next opening square bracket
		int indexOfSquareBracket = jsonString.indexOf('[', index + 2);

		// Find the index of the next comma
		int indexOfComma = jsonString.indexOf(',', index);

		// Check if any of the characters are not found and set their indices to a large
		// value
		indexOfCurlyBrace = (indexOfCurlyBrace == -1) ? Integer.MAX_VALUE : indexOfCurlyBrace;
		indexOfSquareBracket = (indexOfSquareBracket == -1) ? Integer.MAX_VALUE : indexOfSquareBracket;
		indexOfComma = (indexOfComma == -1) ? Integer.MAX_VALUE : indexOfComma;

		// Find the index of the next closing curly brace
		int indexOfEndCurlyBrace = jsonString.indexOf('}', index);

		// Find the index of the next closing square bracket
		int indexOfEndSquareBracket = jsonString.indexOf(']', index);

		// Check if any of the end characters are not found and set their indices to a
		// large value
		indexOfEndCurlyBrace = (indexOfEndCurlyBrace == -1) ? Integer.MAX_VALUE : indexOfEndCurlyBrace;
		indexOfEndSquareBracket = (indexOfEndSquareBracket == -1) ? Integer.MAX_VALUE : indexOfEndSquareBracket;

		// Create lists to find the minimum start and end indices
		List<Integer> start = List.of(indexOfCurlyBrace, indexOfSquareBracket);
		int minStart = Collections.min(start);

		List<Integer> end = List.of(indexOfEndCurlyBrace, indexOfEndSquareBracket);
		int minEnd = Collections.min(end);

		// If the next type is an opening curly brace or square bracket, return true
		// indicating a nested structure. Otherwise, return false.
		if (minStart < minEnd) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check the type of character in the JSON string and parse
	 * accordingly. It determines the type of the character based on its position in
	 * the string, and parses nested JSON objects, arrays, or regular values
	 * accordingly.
	 *
	 * @param key The key associated with the current JSON value.
	 * @return An Object representing the parsed value based on its type.
	 */
	private static Object checkType(String key) {
//		System.out.println(jsonString.charAt(index + 1) + " jsonString");
		int indexOfCurlyBrace = jsonString.indexOf('{', index);
		int indexOfSquareBracket = jsonString.indexOf('[', index);
		int indexOfComma = jsonString.indexOf(',', index);
		// Check if any of the characters are not found
		indexOfCurlyBrace = (indexOfCurlyBrace == -1) ? Integer.MAX_VALUE : indexOfCurlyBrace;
		indexOfSquareBracket = (indexOfSquareBracket == -1) ? Integer.MAX_VALUE : indexOfSquareBracket;
		indexOfComma = (indexOfComma == -1) ? Integer.MAX_VALUE : indexOfComma;

		int indexOfEndCurlyBrace = jsonString.indexOf('}', index);
		int indexOfEndSquareBracket = jsonString.indexOf(']', index);

		indexOfEndCurlyBrace = (indexOfEndCurlyBrace == -1) ? Integer.MAX_VALUE : indexOfEndCurlyBrace;
		indexOfEndSquareBracket = (indexOfEndSquareBracket == -1) ? Integer.MAX_VALUE : indexOfEndSquareBracket;

		List<Integer> start = List.of(indexOfCurlyBrace, indexOfSquareBracket);

		int minStart = Collections.min(start);

		List<Integer> end = List.of(indexOfEndCurlyBrace, indexOfEndSquareBracket);

		int minEnd = Collections.min(end);

	    // Map to store the parsed values (may need refinement in future updates)
		Map<String, Object> returndeMap = new HashMap<>();

		if (indexOfCurlyBrace < indexOfComma || indexOfCurlyBrace < indexOfComma) {
			if (indexOfCurlyBrace > indexOfSquareBracket) {

				/* Determining if the bracket contains nested types */
//				if (nextType()) {
//
//					int curlyBraceEnd = jsonString.indexOf(']', index);
//					String bracketValue = (jsonString.substring(index, curlyBraceEnd + 1).trim());
////            	  
//				} else {
//					Object newBracket = BracketParse();
//
////					System.out.println(newBracket + " newEstMap");
//
//					return newBracket;
//				}

				Object newBracket = BracketParse();
//                    	System.out.println(key);
//                    	System.out.println(newBracket);
				return newBracket;

			}

			else {

	            // Check for inner loops
				if (nextType()) {

					int curlyBraceEnd = jsonString.indexOf('}', index);
					String curlyValue = (jsonString.substring(index, curlyBraceEnd + 1).trim());

//					System.out.println(key);
	                // Passing the string that we want to iterate over
					returndeMap = innerLoop(curlyValue);

				} else {
					
	                // Parse the content inside curly braces as a JSON object\
					Map<String, Object> newCulry = CurlyBrace();
					Object newEstMap = newCulry;

//					System.out.println(newEstMap + " newEstMap");

					return newEstMap;
				}
			}
		}

		else {
			
	        // Handle regular values (non-nested)
			int valueEnd = jsonString.indexOf(',', index);

	        // Checks to see if a comma is at the end, if not then it's the last value
			if (valueEnd == -1) {
				valueEnd = jsonString.indexOf('}', index);
			}

	        // Extract the value and trim it
			String value = jsonString.substring(index, valueEnd).trim();

			index = valueEnd + 1;

			return parseType(value);
		}

		return returndeMap;

	}

	/**
	 * Method to parse the content inside square brackets ('[]') in JSON. It
	 * extracts the content, identifies whether it represents a JSON object or
	 * array, and parses accordingly using CurlyBrace or parseArray methods.
	 *
	 * @return An Object representing the parsed content inside square brackets.
	 */
	private static Object BracketParse() {
		// Initialize an Object to store the parsed content inside square brackets
		Object ListBracket = new HashMap<>();

		// Find the index of the closing square bracket (']') in the JSON string
		int bracketEnd = jsonString.indexOf(']', index);

		// Extract the substring representing the content inside the square brackets
		String bracketValue = (jsonString.substring(index, bracketEnd + 1).trim());

		// Find the index of the opening square bracket ('[') after the current position
		int newStart = jsonString.indexOf('[', index) + 1;

		// Find the indices of the next opening curly brace ('{') and comma (',') after
		// the current position
		int indixOg = jsonString.indexOf('{', newStart);
		int otherCase = jsonString.indexOf(',', newStart);

		// If there is an opening curly brace before a comma, parse it as a JSON object
		// using CurlyBrace
		if (indixOg < otherCase) {
			Map<String, Object> newCulry = CurlyBrace();
			ListBracket = newCulry;
		}
		// If there is a comma before an opening curly brace, parse it as a JSON array
		// using parseArray
		else {
			// Move the index to the character after the closing square bracket
			index = bracketEnd + 1;

			// Parse the content inside the square brackets as a JSON array
			List<Object> ListBracketParsed = parseArray(bracketValue);

			// Store the parsed array in the ListBracket object
			ListBracket = ListBracketParsed;
		}

		// Return the object representing the parsed content inside square brackets
		return ListBracket;
	}

	/**
	 * Method to parse a JSON array into a List of objects. It trims the array
	 * string, removes the square brackets, splits the array into elements, and
	 * recursively parses each element using parseType.
	 *
	 * @param arrayString The string representing the JSON array.
	 * @return A List of objects representing the parsed elements of the JSON array.
	 */
	private static List<Object> parseArray(String arrayString) {
		// Trim the array string and remove the square brackets
		arrayString = arrayString.substring(1, arrayString.length() - 1).trim();

		// Initialize a List to store array elements
		List<Object> array = new ArrayList<>();

		// Split the array string by commas and parse each element
		String[] elements = arrayString.split(",");
		for (String element : elements) {
			// Trim each element and recursively parse it
			array.add(parseType(element.trim()));
		}

		return array;
	}

	/**
	 * Method to parse a JSON value into an Object based on its type. It checks the
	 * type of the JSON value and parses it accordingly, handling strings, booleans,
	 * integers, and decimal numbers.
	 *
	 * @param value The string representation of the JSON value.
	 * @return An Object representing the parsed value.
	 */
	private static Object parseType(String value) {
		// Check if the value is a string enclosed in double quotes
		if (value.startsWith("\"") && value.endsWith("\"")) {
			// If so, remove the quotes and return the string
			return value.substring(1, value.length() - 1);
		}
		// Check if the value is a boolean (either "true" or "false")
		else if (value.equals("true") || value.equals("false")) {
			// Parse and return the boolean value
			return Boolean.parseBoolean(value);
		}
		// Check if the value is an integer (consisting of digits with optional negative
		// sign)
		else if (value.matches("-?\\d+")) {
			// Parse and return the integer value
			return Integer.parseInt(value);
		}
		// Check if the value is a decimal number (integer part with optional fractional
		// part)
		else if (value.matches("-?\\d+(\\.\\d+)?")) {
			// Parse and return the double value
			return Double.parseDouble(value);
		}

		// If none of the above conditions match, return the value as is
		return value;
	}

	// Example usage in the main method
	public static void main(String[] args) {

		// Example JSON-formatted string
		String input = TestCase.jsonString1;
		// Example usage of the JSONParser to parse the string
		Map<String, Object> output = JsonParser.parse(input);

		// Assertions to check the correctness of the parsing
		assert output.get("debug").equals("on");
		assert ((Map<String, Object>) output.get("window")).get("title").equals("sample");
		assert ((Map<String, Object>) output.get("window")).get("size").equals(500);

		// Print a success message along with the parsed Map
		System.out.println("Parsing successful!");
		System.out.println(output);
	}

	// Additional Comments: Areas for Improvement or Future Work

	/*
	 * Areas for Improvement: 1. Improve code readability by breaking down complex
	 * methods into smaller, focused functions. 2. Enhance error handling for
	 * specific cases to provide more informative error messages. 3. Optimizing
	 * performance for large JSON strings or nested structures. 4. Implement more
	 * robust testing, including edge cases and invalid JSON scenarios. 5. Implement
	 * more robust handling for deeply nested objects with different types. 6.
	 * Adjust indexing to resolve infinite loops for large JSON data sets. a
	 */

	/*
	 * Future Work: 1. Explore supporting additional JSON features, such as handling
	 * escape characters. 2. Consider adding support for parsing JSON arrays with
	 * mixed data types. 3. Evaluate opportunities for parallelizing parsing tasks
	 * to improve efficiency. 4. Address challenges related to solving complex
	 * nested structures of mixed types in scenarios involving large JSON data sets.
	 * f
	 */

}
