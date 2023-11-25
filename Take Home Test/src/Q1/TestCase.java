package Q1;

public class TestCase {
	
			
			public static String jsonString1 = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";

			public static String jsonString2 = "{" +
			            "  \"person\": {" +
			            "    \"name\": \"John Doe\"," +
			            "    \"age\": 30," +
			            "    \"address\": {" +
			            "      \"street\": \"123 Main St\"," +
			            "      \"city\": \"Anytown\"," +
			            "      \"zip\": \"12345\"" +
			            "    }," +
			            "    \"contacts\": [" +
			            "      {" +
			            "        \"type\": \"email\"," +
			            "        \"value\": \"john.doe@example.com\"" +
			            "      }," +
			            "      {" +
			            "        \"type\": \"phone\"," +
			            "        \"value\": \"+1 123-456-7890\"" +
			            "      }" +
			            "    ]" +
			            "  }," +
			            "  \"projects\": [" +
			            "    {" +
			            "      \"title\": \"Project A\"," +
			            "      \"status\": \"ongoing\"," +
			            "      \"team\": [\"Alice\", \"Bob\", \"Charlie\"]" +
			            "    }," +
			            "    {" +
			            "      \"title\": \"Project B\"," +
			            "      \"status\": \"completed\"," +
			            "      \"team\": [\"Alice\", \"David\", \"Eve\"]" +
			            "    }" +
			            "  ]" +
			            "}";

	        
	        public static String jsonString3 = "{" +
	                "\"debug\": \"on\"," +
	                "\"window\": {" +
	                "\"title\": \"sample\"," +
	                "\"isStudent\": false," +
	                "\"size\": 500" +
	                "}" +
	                "}";
	        
	        public static String jsonString4 = "{" +
	                "\"name\": \"John Doe\"," +
	                "\"age\": 30," +
	                "\"isStudent\": false," +
	                "\"address\": {" +
	                "\"street\": \"123 Main St\"," +
	                "\"city\": \"Anytown\"," +
	                "\"zip\": \"12345\"" +
	                "}," +
	                "\"contacts\": [" +
	                "{" +
	                "\"type\": \"email\"," +
	                "\"value\": \"john.doe@example.com\"" +
	                "}," +
	                "{" +
	                "\"type\": \"phone\"," +
	                "\"value\": \"+1 123-456-7890\"" +
	                "}" +
	                "]," +
	                "\"projects\": [" +
	                "{" +
	                "\"title\": \"Project A\"," +
	                "\"status\": \"ongoing\"," +
	                "\"team\": [\"Alice\", \"Bob\", \"Charlie\"]" +
	                "}," +
	                "{" +
	                "\"title\": \"Project B\"," +
	                "\"status\": \"completed\"," +
	                "\"team\": [\"Alice\", \"David\", \"Eve\"]" +
	                "}" +
	                "]" +
	                "}";
	        
//	        Not valid Json
	       public static String jsonString5 = "{"
	    		   + "\"contacts\": [" +
	                "{" +
	                "\"type\": \"email\"," +
	                "\"value\": \"john.doe@example.com\"" +
	                "}," +
	                "{" +
	                "\"type\": \"phone\"," +
	                "\"value\": \"+1 123-456-7890\"" +
	                "}" +
	                "]" + "}"; 
	       
//	  Valid Json
	       public static String jsonString6 ="{" +
	    	        "\"contacts\": [" +
	    	        "{" +
	    	        "\"type\": \"email\"," +
	    	        "\"value\": \"john.doe@example.com\"" +
	    	        "}," +
	    	        "{" +
	    	        "\"type\": \"phone\"," +
	    	        "\"value\": \"+1 123-456-7890\"" +
	    	        "}" +
	    	        "]," +
	    	        "}";


	       public static String jsonString7 = "{" +
	    		    "  \"string\": \"Hello, JSON!\"," +
	    		    "  \"number\": 42," +
	    		    "  \"boolean\": true," +
	    		    "  \"nullValue\": null," +
	    		    "  \"array\": [1, 2, 3]," +
	    		    "  \"object\": {" +
	    		    "    \"key1\": \"value1\"," +
	    		    "    \"key2\": 42," +
	    		    "    \"key3\": false" +
	    		    "  }," +
	    		    "  \"escapedString\": \"Special characters: \\\" \\\\ \\/ \\b \\f \\n \\r \\t\"," +
	    		    "  \"unicodeString\": \"Unicode characters: \\u0041\\u0042\\u0043\"," +
	    		    "  \"scientificNotation\": 1.23e4" +
	    		    "}";
	       
	       
//	        Not working
	       public static String jsonString8 = 
	    		    "{" +
	    		    "\"contacts\": [" +
	    		    "{" +
	    		    "\"type\": \"email\"," +
	    		    "\"value\": \"john.doe@example.com\"" +
	    		    "}" +
	    		    "]," +
	    		    "\"phone\": {" +
	    		    "\"type\": \"phone\"," +
	    		    "\"value\": \"+1 123-456-7890\"" +
	    		    "}" +
	    		    "}";
	       
	       public static String jsonString9 = 
	    		   "{ " +
	    				    "\"contacts\": {" +
	    				        "\"phone\": {" +
	    				            "\"type\": \"phone\"," +
	    				            "\"value\": \"+1 123-456-7890\"" +
	    				        "}" +
	    				    "}" +
	    				"}";
	       
	       public static String jsonString10 = 
	    		    "{" +
	    		        "\"Time\": {" +
	    		            "\"contacts\": {" +
	    		                "\"phone\": {" +
	    		                    "\"type\": \"phone\"," +
	    		                    "\"value\": \"+1 123-456-7890\"" +
	    		                "}" +
	    		            "}" +
	    		        "}" +
	    		    "}";

	       
	       public static String jsonString11 = "{" +
	    	        "\"phone\": {" +
	    	        "\"type\": \"cellphone\"," +
	    	        "\"value\": \"+1 123-456-7890\"" +
	    	        "}," + // Add a comma here
	    	        "\"home\": {" +
	    	        "\"day\": \"Tuesday\"," +
	    	        "\"money\": \"+2\"" +
	    	        "}" +
	    	        "}";

	       
	       public static String jsonString12 = 
	    		    "{ " +
	    		    "    \"phone\": {" +
	    		    "        \"type\": \"cellphone\"," +
	    		    "        \"value\": \"+1 123-456-7890\","  +
	    		    "        \"street\": \"123 Main St\"," +
	    		    "        \"city\": \"Anytown\"," +
	    		    "        \"zipcode\": \"12345\"," +
	    		    "        \"water\": \"cellphone\"," +
	    		    "        \"gas\": \"+1 123-456-7890\","  +
	    		    "        \"fire\": \"123 Main St\"," +
	    		    "        \"elect\": \"Anytown\"," +
	    		    "        \"community \": \"12345\"" +
	    		    "    }" +
	    		    "}";

	       
	       public static String jsonString13 = "{" +
	    		    "\"person\": {" +
	    		        "\"name\": {" +
	    		            "\"first\": \"John\"," +
	    		            "\"last\": \"Doe\"" +
	    		        "}," +
	    		        "\"contacts\": {" +
	    		            "\"phone\": {" +
	    		                "\"type\": \"mobile\"," +
	    		                "\"number\": \"+1 987-654-3210\"" +
	    		            "}," +
	    		            "\"email\": {" +
	    		                "\"type\": \"work\"," +
	    		                "\"address\": \"john.doe@example.com\"" +
	    		            "}" +
	    		        "}" +
	    		    "}" +
	    		"}";
	       
	       public static String jsonString14 = "{" +
	    		    "\"phone\": {" +
	    	        "\"type\": \"cellphone\"," +
	    	        "\"value\": \"+1 123-456-7890\"" +
	    	    "}" +
	    	"}";


	       

	       public static String jsonString15 = "{" +
	    		    "\"debug\": \"on\"," +
	    		    "\"window\": {" +
	    		        "\"title\": \"sample\"," +
	    		        "\"size\": 500" +
	    		    "}" +
	    		"}";
	       
	       public static String jsonString16 = "{" +
                   "\"temperature\": \"-10\"," +
                   "\"price\": \"-25.5\"" +
                   "}";
	       
	       public static String jsonString17 = "{" +
	    	        "  \"person\": {" +
	    	        "    \"contacts\": [" +
	    	        "      {" +
	    	        "        \"type\": \"email\"," +
	    	        "        \"value\": \"john.doe@example.com\"" +
	    	        "      }" +
	    	        "    ]" +
	    	        "  }" +
	    	        "}";
	       
}
