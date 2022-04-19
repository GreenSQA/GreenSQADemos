using Gsqa.GHeart.Sdk.FX.Helpers;
using Gsqa.GHeart.Sdk.FX.Models;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;
using System.Threading;
using System.Threading.Tasks;

namespace gsqa.gheart.tests.dotnet.fx
{
  public class SuiteFalabella
  {
    private static IWebDriver driver;
    private static DateTime startTime;

    [OneTimeSetUp]
    public void TestInitialize()
    {
      driver = new ChromeDriver();
      GreenHeartHelper.Init(false, "123456", "GSQA", "123456", "GreenHeart", "001", "Suite_Falabella", "1.0");
    }

    [SetUp]
    public void Start()
    {
      startTime = DateTime.Now;
    }

    [Test]
    public void A_validarDisponibilidadFalabella()
    {
      driver.Navigate().GoToUrl("https://www.falabella.com/");
      driver.Manage().Window.Maximize();
      GreenHeartHelper.TakeEvidenceWithScreenshot("Navegación", "Se navega al sitio web corretamente", driver);
    }

    [Test]
    public void B_busquedaFalabella()
    {
      String text = "Laptop table";
      driver.FindElement(By.Id("testId-SearchBar-Input")).Click();
      driver.FindElement(By.Id("testId-SearchBar-Input")).SendKeys(text);
      driver.FindElement(By.CssSelector("#testId-search-wrapper > div > button")).Click();
      Thread.Sleep(1000);
      GreenHeartHelper.TakeEvidenceWithScreenshot("Búsqueda",
          "Se realiza la búsqueda de " + text + " correctamente", driver);
    }

    [TearDown]
    public async Task PublishToGreenHeartAsync()
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
        case "A_validarDisponibilidadFalabella":
          {
            await GreenHeartHelper.PublishAsync(testResult, testName, "cgordillo", 3000, 12000,
                testElapsedTime, 2.5, DevEnvironment.DEV, GsqaService.TIA,
                "GreenHeart");
            break;
          }
        case "B_busquedaFalabella":
          {
            await GreenHeartHelper.PublishAsync(testResult, testName, "cgordillo", 2000, 8000,
                testElapsedTime, 2.0, DevEnvironment.DEV, GsqaService.TIA,
                "GreenHeart");
            break;
          }
      }
    }

    [OneTimeTearDown]
    public void KillDriver()
    {
      driver.Close();
    }
  }
}
