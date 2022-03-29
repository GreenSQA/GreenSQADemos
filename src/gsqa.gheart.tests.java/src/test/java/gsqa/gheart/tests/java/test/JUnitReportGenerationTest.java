package gsqa.gheart.tests.java.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * @author TIA cgordillo
 * 
 * Class for generate junit report.
 * https://www.eviltester.com/post/junit/generating-junit-html-reports/
 *
 */
@Suite
@SelectClasses( { SuiteYoutube.class , SuiteFalabella.class } )
public class JUnitReportGenerationTest
{
}
