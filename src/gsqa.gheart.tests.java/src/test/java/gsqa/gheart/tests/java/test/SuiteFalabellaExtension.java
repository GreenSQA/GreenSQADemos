package gsqa.gheart.tests.java.test;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import gsqa.gheart.sdk.java.helpers.GreenHeartHelper;
import gsqa.gheart.sdk.java.models.DevEnvironment;
import gsqa.gheart.sdk.java.models.GsqaService;

public class SuiteFalabellaExtension
		implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback {
	private static long startTime;
	protected static WebDriver driver;

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Green SQA\\AiMaps\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		GreenHeartHelper.init(false, "123456", "GSQA", "123456", "GreenHeart", "001", "Suite_Falabella", "1.0");
	}

	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		driver.close();
	}

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		startTime = System.currentTimeMillis();
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		Boolean testResult = true;
		if (context.getExecutionException().isPresent()) {
			GreenHeartHelper.takeEvidenceWithScreenshot("Error", context.getExecutionException().toString(), driver);
			testResult = false;
		}

		String testName = context.getDisplayName();
		long testElapsedTime = System.currentTimeMillis() - startTime;
		switch (testName) {
		case ("VALIDAR_DISPONIBILIDAD_FALABELLA"): {
			GreenHeartHelper.publish(testResult, testName, "cgordillo", 3000, 12000, testElapsedTime, 2.5,
					DevEnvironment.DEV, GsqaService.TIA, "GreenHeart");
			break;
		}
		case ("BUSQUEDA_FALABELLA"): {
			GreenHeartHelper.publish(testResult, testName, "cgordillo", 2000, 8000, testElapsedTime, 2.0,
					DevEnvironment.DEV, GsqaService.TIA, "GreenHeart");
			break;
		}
		}
	}
}
