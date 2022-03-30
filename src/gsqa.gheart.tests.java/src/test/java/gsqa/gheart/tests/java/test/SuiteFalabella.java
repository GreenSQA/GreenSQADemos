package gsqa.gheart.tests.java.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import gsqa.gheart.sdk.java.helpers.GreenHeartHelper;

@ExtendWith(SuiteFalabellaExtension.class)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class SuiteFalabella extends SuiteFalabellaExtension {

	@DisplayName("VALIDAR_DISPONIBILIDAD_FALABELLA")
	@Test
	public void A_validarDisponibilidadFalabella() {
		driver.navigate().to("https://www.falabella.com/");
		driver.manage().window().maximize();
		GreenHeartHelper.takeEvidenceWithScreenshot("Navegación", "Se navega al sitio web corretamente", driver);
	}

	@DisplayName("BUSQUEDA_FALABELLA")
	@Test
	public void B_busquedaFalabella() throws Exception {
		String text = "Laptop table";
		driver.findElement(By.id("testId-SearchBar-Input")).click();
		driver.findElement(By.id("testId-SearchBar-Input")).sendKeys(text);
		driver.findElement(By.cssSelector("#testId-search-wrapper > div > button")).click();
		Thread.sleep(1000);
		GreenHeartHelper.takeEvidenceWithScreenshot("Búsqueda", "Se realiza la búsqueda de " + text + " correctamente",
				driver);
	}
}
