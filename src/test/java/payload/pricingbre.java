package payload;

public class pricingbre {
	
	
	public static String payload(String roi,String PROCESSING_FEE,String NET_INSURANCE_PENETRATION,

			String PRE_OR_PART_PAYMENT_CHARGES,String FORECLOSURE_CHARGES,String LOAN_AMOUNT) {

			return "{\r\n"

			+ "\r\n"

			+ "    \"ruleid\": \"8777\",\r\n"

			+ "\r\n"

			+ "    \"programName\": \"Pricing BRE\",\r\n"

			+ "\r\n"

			+ "    \"reference_id\": \"LAFID294161430\",\r\n"

			+ "\r\n"

			+ "    \"reference_id_2\": \"\",\r\n"

			+ "\r\n"

			+ "    \"reference_id_3\": \"\",\r\n"

			+ "\r\n"

			+ "    \"application\": {\r\n"

			+ "\r\n"

			+ "        \"COMMERCIAL\": {\r\n"

			+ "\r\n"

			+ "           \"CERSAI\": 0,\r\n"

			+ "\r\n"

			+ "            \"STAMP_DUTY\": \"\",\r\n"

			+ "\r\n"

			+ "            \"PROCESSING_FEE\":0,\r\n"

			+ "\r\n"

			+ "            \"DOCUMENT_CHARGES\": 0,\r\n"

			+ "\r\n"

			+ "            \"FORECLOSURE_CHARGES\": 0,\r\n"

			+ "\r\n"

			+ "            \"LEGAL_TECHNICAL_CHARGES\": 3000,\r\n"

			+ "\r\n"

			+ "            \"NO_OF_PROPERTIES\": 1,\r\n"

			+ "\r\n"

			+ "            \"NET_INSURANCE_PENETRATION\":0,\r\n"

			+ "\r\n"

			+ "            \"PRE_OR_PART_PAYMENT_CHARGES\":0\r\n"

			+ "\r\n"

			+ "        },\r\n"

			+ "\r\n"

			+ "        \"LOAN_DETAILS\": {\r\n"

			+ "\r\n"

			+ "            \"ROI\": 0,\r\n"

			+ "\r\n"

			+ "            \"LOAN_AMOUNT\": \"700000\",\r\n"

			+ "\r\n"

			+ "            \"PROGRAM_CODE\": \"USL-SANJ-ABB\"\r\n"

			+ "\r\n"

			+ "           \r\n"

			+ "\r\n"

			+ "        }\r\n"

			+ "\r\n"

			+ "    }\r\n"

			+ "\r\n"

			+ "}";

			}

}
