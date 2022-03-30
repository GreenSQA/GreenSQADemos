package gsqa.gheart.tests.java.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import gsqa.gheart.sdk.java.helpers.GreenHeartHelper;

@ExtendWith(CalculatorUnitTestExtension.class)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class CalculatorUnitTest {

	@BeforeAll
	public static void testInitialize() {
		GreenHeartHelper.init(false, "123456", "GSQA", "123456", "GreenHeart", "001", "Suite_Calculator_Unit_Test",
				"1.0");
	}

	@Test
	@DisplayName("CALCULATOR_SHOULD_SUM_TWO_NUMBERS")
	public void calculatorShouldSumTwoNumbers() {
		// Arrange
		int num1 = 8;
		int num2 = 2;
		int expectedTotal = 10;
		// Act
		GreenHeartHelper.takeEvidence("Número 1", Integer.toString(num1));
		GreenHeartHelper.takeEvidence("Número 2", Integer.toString(num2));
		int actualTotal = num1 + num2;
		// Assert
		assertEquals(expectedTotal, actualTotal);
		GreenHeartHelper.takeEvidence("Total", Integer.toString(actualTotal));
	}

	@Test
	@DisplayName("CALCULATOR_SHOULD_DIVIDE_TWO_NUMBERS")
	public void calculatorShouldDivideTwoNumbers() {
		// Arrange
		int num1 = 8;
		int num2 = 1;
		int expectedTotal = 8;
		// Act
		GreenHeartHelper.takeEvidence("Número 1", Integer.toString(num1));
		GreenHeartHelper.takeEvidence("Número 2", Integer.toString(num2));
		int actualTotal = num1 / num2;
		// Assert
		assertEquals(expectedTotal, actualTotal);
		GreenHeartHelper.takeEvidence("Total", Integer.toString(actualTotal));
	}
}
