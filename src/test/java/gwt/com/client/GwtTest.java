package gwt.com.client;

import com.google.gwt.junit.client.GWTTestCase;

import gwt.com.shared.FieldVerifier;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class GwtTest extends GWTTestCase {

	/**
	 * Must refer to a valid module that sources this class.
	 */
	public String getModuleName() {
		return "gwt.com.GwtJUnit";
	}

	/**
	 * Tests the FieldVerifier.
	 */
	public void testFieldVerifier() {
		assertFalse(FieldVerifier.isValidNumber(null));
		assertFalse(FieldVerifier.isValidNumber(""));
		assertFalse(FieldVerifier.isValidNumber("0"));
		assertFalse(FieldVerifier.isValidNumber("0.35"));
		assertFalse(FieldVerifier.isValidNumber("-75"));
		assertFalse(FieldVerifier.isValidNumber("1001"));
		assertTrue(FieldVerifier.isValidNumber("57"));
	}

	/**
	 * This test will send a request to the server using the greetServer method in
	 * GreetingService and verify the response.
	 */
	public void testAppService() {
		// Create the service that we will test.

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);
	}

}
