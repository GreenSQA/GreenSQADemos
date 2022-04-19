using Gsqa.GHeart.Sdk.FX.Helpers;
using Gsqa.GHeart.Sdk.FX.Models;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;
using System.Diagnostics;
using System.Threading;
using System.Threading.Tasks;

namespace gsqa.gheart.tests.dotnet.fx
{
  public class SuiteYoutube
  {
    private static IWebDriver driver;
    private static bool testResult;
    private static Stopwatch testDuration = Stopwatch.StartNew();

    [OneTimeSetUp]
    public void TestInitialize()
    {
      driver = new ChromeDriver();
      GreenHeartHelper.Init(false, "123456", "GSQA", "123456", "GreenHeart", "001", "Suite_Youtube", "1.0");
    }

    [SetUp]
    public void Start()
    {
      testDuration.Restart();
    }

    [Test]
    public void A_validarDisponibilidadYoutube()
    {
      testResult = Navigate();
      Assert.IsTrue(testResult, "Test fail!");
    }

    [Test]
    public void B_busquedaYoutube()
    {
      testResult = Search("GreenSQA");
      Assert.IsTrue(testResult, "Test fail!");
    }

    [TearDown]
    public async Task PublishToGreenHeartAsync()
    {
      string testName = TestContext.CurrentContext.Test.MethodName;

      switch (testName)
      {
        case "A_validarDisponibilidadYoutube":
          {
            await GreenHeartHelper.PublishAsync(testResult, testName, "cgordillo", 3000, 12000,
                testDuration.ElapsedMilliseconds, 2.5, DevEnvironment.DEV, GsqaService.TIA,
                "GreenHeart");
            break;
          }
        case "B_busquedaYoutube":
          {
            await GreenHeartHelper.PublishAsync(testResult, testName, "cgordillo", 2000, 8000,
                testDuration.ElapsedMilliseconds, 2.0, DevEnvironment.DEV, GsqaService.TIA,
                "GreenHeart");
            break;
          }
      }
      testDuration.Reset();
    }

    [OneTimeTearDown]
    public void KillDriver()
    {
      driver.Close();
    }

    private bool Navigate()
    {
      try
      {
        driver.Navigate().GoToUrl("https://www.youtube.com/");
        driver.Manage().Window.Maximize();
        GreenHeartHelper.TakeEvidenceWithScreenshot("Navegación", "Se navega al sitio web corretamente", driver);
        return true;
      }
      catch (Exception ex)
      {
        GreenHeartHelper.TakeEvidenceWithScreenshot("Error", "No se pudo navegar al sitio web " + ex, driver);
        return false;
      }
    }

    private bool Search(string text)
    {
      try
      {
        driver.FindElement(By.XPath("//input[@id = 'search']")).Click();
        driver.FindElement(By.XPath("//input[@id = 'search']")).SendKeys(text);
        driver.FindElement(By.Id("search-icon-legacy")).Click();
        Thread.Sleep(1000);
        GreenHeartHelper.TakeEvidenceWithScreenshot("Búsqueda",
            "Se realiza la búsqueda de " + text + " correctamente", driver);
        return true;
      }
      catch (Exception ex)
      {
        GreenHeartHelper.TakeEvidenceWithScreenshot("Error", "No se pudo realizar la búsqueda correctamente " + ex,
            driver);
        return false;
      }
    }
  }
}
