package gsqa.gheart.tests.java.test;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import gsqa.gheart.sdk.java.helpers.GreenHeartHelper;
import gsqa.gheart.sdk.java.models.DevEnvironment;
import gsqa.gheart.sdk.java.models.GsqaService;

public class CalculatorUnitTestExtension implements BeforeEachCallback, AfterEachCallback {
	private static long startTime;

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		startTime = System.currentTimeMillis();
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		Boolean testResult = true;
		if (context.getExecutionException().isPresent()) {
			GreenHeartHelper.takeEvidence("Error", context.getExecutionException().toString());
			testResult = false;
		}

		String testName = context.getDisplayName();
		long testElapsedTime = System.currentTimeMillis() - startTime;
		switch (testName) {
		case ("CALCULATOR_SHOULD_SUM_TWO_NUMBERS"): {
			GreenHeartHelper.publish(testResult, testName, "cgordillo", 3000, 12000, testElapsedTime, 2.5,
					DevEnvironment.DEV, GsqaService.TIA, "GreenHeart");
			break;
		}
		case ("CALCULATOR_SHOULD_DIVIDE_TWO_NUMBERS"): {
			GreenHeartHelper.publish(testResult, testName, "cgordillo", 2000, 8000, testElapsedTime, 2.0,
					DevEnvironment.DEV, GsqaService.TIA, "GreenHeart");
			break;
		}
		}
	}
}
