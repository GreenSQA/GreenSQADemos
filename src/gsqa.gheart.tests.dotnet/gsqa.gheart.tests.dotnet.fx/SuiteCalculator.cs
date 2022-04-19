using Gsqa.GHeart.Sdk.FX.Helpers;
using Gsqa.GHeart.Sdk.FX.Models;
using NUnit.Framework;
using System;
using System.Threading.Tasks;

namespace gsqa.gheart.tests.dotnet.fx
{
  public class SuiteCalculator
  {
    private static DateTime startTime;

    [OneTimeSetUp]
    public static void BeforeAll()
    {
      GreenHeartHelper.Init(false, "123456", "GSQA", "123456", "GreenHeart", "001", "Suite_Calculator", "1.0");
    }

    [SetUp]
    public void BeforeTest()
    {
      startTime = DateTime.Now;
    }

    [Test]
    public void CalculatorShouldSumTwoNumbers()
    {
      // Arrange
      int num1 = 8;
      int num2 = 2;
      int expectedTotal = 10;
      // Act
      GreenHeartHelper.TakeEvidence("Número 1", num1.ToString());
      GreenHeartHelper.TakeEvidence("Número 2", num2.ToString());
      int actualTotal = num1 + num2;
      // Assert
      Assert.AreEqual(expectedTotal, actualTotal);
      GreenHeartHelper.TakeEvidence("Total", actualTotal.ToString());
    }

    [Test]
    public void CalculatorShouldDivideTwoNumbers()
    {
      // Arrange
      int num1 = 8;
      int num2 = 1;
      int expectedTotal = 8;
      // Act
      GreenHeartHelper.TakeEvidence("Número 1", num1.ToString());
      GreenHeartHelper.TakeEvidence("Número 2", num2.ToString());
      int actualTotal = num1 / num2;
      // Assert
      Assert.AreEqual(expectedTotal, actualTotal);
      GreenHeartHelper.TakeEvidence("Total", actualTotal.ToString());
    }

    [TearDown]
    public async Task AfterTest()
    {
      bool testResult = true;
      string testName = TestContext.CurrentContext.Test.MethodName;
      long testElapsedTime = (long)(DateTime.Now - startTime).TotalMilliseconds;

      if (TestContext.CurrentContext.Result.Message != null)
      {
        string formatedError = System.Text.RegularExpressions.Regex.Replace(TestContext.CurrentContext.Result.Message, @"\t|\n|\r", "");
        GreenHeartHelper.TakeEvidence("Error", formatedError);
        testResult = false;
      }

      switch (testName)
      {
        case "CalculatorShouldSumTwoNumbers":
          {
            await GreenHeartHelper.PublishAsync(testResult, testName, "cgordillo", 3000, 12000, testElapsedTime, 2.5,
                DevEnvironment.DEV, GsqaService.TIA, "GreenHeart");
            break;
          }
        case "CalculatorShouldDivideTwoNumbers":
          {
            await GreenHeartHelper.PublishAsync(testResult, testName, "cgordillo", 2000, 8000, testElapsedTime, 2.0,
                DevEnvironment.DEV, GsqaService.TIA, "GreenHeart");
            break;
          }
      }
    }
  }
}