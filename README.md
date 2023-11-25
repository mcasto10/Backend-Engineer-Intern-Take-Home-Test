# Backend-Engineer-Intern-Take-Home-Test

 * JSONParser: A simple Java class for parsing JSON-formatted strings into nested maps and lists.
 *
 * Overview:
 * This class provides a basic implementation of a JSON parser that can handle nested objects and arrays.
 * It extracts key-value pairs from a JSON string and represents them as a Map<String, Object>.
 * Additionally, it supports parsing JSON arrays into List<Object>.
 *
 * Usage:
 * To use the JSONParser, create an instance of the class and call the parse method, passing the JSON string
 * as a parameter. The parsed result will be a Map<String, Object> representing the JSON structure.
 *
 * Example Usage:
 * ```
 * String jsonString = "{ \"key\": \"value\", \"nested\": { \"innerKey\": 42 } }";
 * Map<String, Object> parsedResult = JSONParser.parse(jsonString);
 * System.out.println(parsedResult);
 * ```
 *
