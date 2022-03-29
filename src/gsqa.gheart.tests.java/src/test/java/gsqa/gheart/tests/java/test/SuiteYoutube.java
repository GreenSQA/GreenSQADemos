package gsqa.gheart.tests.java.test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.google.common.base.Stopwatch;

import gsqa.gheart.sdk.java.helpers.GreenHeartHelper;
import gsqa.gheart.sdk.java.models.DevEnvironment;
import gsqa.gheart.sdk.java.models.GsqaService;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class SuiteYoutube {
	private static WebDriver driver;
	private static Boolean testResult;
	private static Stopwatch testDuration = Stopwatch.createUnstarted();

	@BeforeAll
	public static void testInitialize() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Green SQA\\AiMaps\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		GreenHeartHelper.init(false, "123456", "GSQA", "123456", "GreenHeart", "001", "Suite_Youtube", "1.0");
	}

	@AfterAll
	public static void killDriver() {
		driver.close();
	}

	@BeforeEach
	public void start() {
		testDuration.start();
	}

	@AfterEach
	public void publishToGreenHeart(TestInfo testInfo) {
		String testName = testInfo.getDisplayName();
		switch (testName) {
		case ("VALIDAR_DISPONIBILIDAD_YOUTUBE"): {
			GreenHeartHelper.publish(testResult, testName, "cgordillo", 3000, 12000,
					testDuration.elapsed(TimeUnit.MILLISECONDS), 2.5, DevEnvironment.DEV, GsqaService.TIA,
					"GreenHeart");
			break;
		}
		case ("BUSQUEDA_YOUTUBE"): {
			GreenHeartHelper.publish(testResult, testName, "cgordillo", 2000, 8000,
					testDuration.elapsed(TimeUnit.MILLISECONDS), 2.0, DevEnvironment.DEV, GsqaService.TIA,
					"GreenHeart");
			break;
		}
		}
		testDuration.reset();
	}

	@Test
	@DisplayName("VALIDAR_DISPONIBILIDAD_YOUTUBE")
	public void A_validarDisponibilidadYoutube() {
		testResult = navigate();
		assertTrue(testResult, "Test fail!");
	}

	@Test
	@DisplayName("BUSQUEDA_YOUTUBE")
	public void B_busquedaYoutube() {
		testResult = search("GreenSQA");
		assertTrue(testResult, "Test fail!");
	}

	private static Boolean navigate() {
		try {
			driver.navigate().to("https://www.youtube.com/");
			driver.manage().window().maximize();
			GreenHeartHelper.takeEvidenceWithScreenshot("Navegación", "Se navega al sitio web corretamente", driver);
			return true;
		} catch (Exception ex) {
			GreenHeartHelper.takeEvidenceWithScreenshot("Error", "No se pudo navegar al sitio web " + ex, driver);
			return false;
		}
	}

	private static Boolean search(String text) {
		try {
			driver.findElement(By.xpath("//input[@id = 'search']")).click();
			driver.findElement(By.xpath("//input[@id = 'search']")).sendKeys(text);
			driver.findElement(By.id("search-icon-legacy")).click();
			Thread.sleep(1000);
			GreenHeartHelper.takeEvidenceWithScreenshot("Búsqueda",
					"Se realiza la búsqueda de " + text + " correctamente", driver);
			return true;
		} catch (Exception ex) {
			GreenHeartHelper.takeEvidenceWithScreenshot("Error", "No se pudo realizar la búsqueda correctamente " + ex,
					driver);
			return false;
		}
	}
}
