package Test01;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class Add_To_Category {
    WebDriver driver = new ChromeDriver();

    @BeforeClass
    public void beforeClass() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://cms.anhtester.com/login");
//		Thực hiện login
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("admin@example.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//		Click Add category
        driver.findElement(By.xpath("//span[text()='Products']")).click();
        driver.findElement(By.xpath("//span[text()='Category']")).click();
        driver.findElement(By.xpath("//span[text()='Add New category']")).click();
    }


    @Test
    // Case 2: Không nhập trường nào và thêm mới thất bại
    public void Category_fail_2() throws InterruptedException {
//	Click button save
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//	Verify vẫn đang ở màn Category Information => chưa add thành công
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Category Information']")).isDisplayed());
        Thread.sleep(3 * 1000);

    }

    @Test
// CASE 1: Nhap day du cac truong verify success
    public void Category_Success_1() throws InterruptedException  {
//		Điền Name
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Hao Test 3");
//      Điền Parent Category
        driver.findElement(By.xpath("//div[text()='No Parent']")).click();
        driver.findElement(By.xpath("//li//a//span[text()='Laptop']")).click();
//        new Actions(driver).sendKeys(Keys.ENTER).perform();
//      Điền Ordering Number
        Thread.sleep(2 * 1000);
        driver.findElement(By.xpath("//input[@id='order_level']")).sendKeys("3");
// Điền Type
        driver.findElement(By.xpath("//button[@title='Physical']")).click();
        driver.findElement(By.xpath("//a[@id='bs-select-2-1']")).click();
        Thread.sleep(2* 1000);
//	chọn file ảnh Banner (200x200)
        driver.findElement(By.xpath("(//label[text()='Banner ']//parent::div//following-sibling::div)[1]//div[text()='Choose File']")).click();
        Thread.sleep(2 * 1000);
        driver.findElement(By.xpath("//div[@title='430333983_914461580223005_5432766109724515617_n.jpg']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Add Files']")).click();
        Thread.sleep(2 * 1000);
//	chọn file ảnh Icon (32x32)
        driver.findElement(By.xpath("//div[6]//div[1]//div[1]//div[2]")).click();
        Thread.sleep( 1000);
        driver.findElement(By.xpath("//div[@title='108608841_p0_master1200.jpg']//img[@class='img-fit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Add Files']")).click();
        Thread.sleep(2 * 1000);
// Điền Meta Title
        driver.findElement(By.xpath("//input[@placeholder='Meta Title']")).sendKeys("Meta Title 3");
// Điền  Meta description
        driver.findElement(By.xpath("//textarea[@name='meta_description']")).sendKeys("Meta description 3");
// Điền  Filtering Attributes
        driver.findElement(By.xpath("//div[contains(text(),'Nothing selected')]")).click();
        driver.findElement(By.xpath("//a[@id='bs-select-3-0']")).click();

//	Click button save
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//	Verify hiển thị mess thong bao pass
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-notify='message']")).getText(), "Category has been inserted successfully");
//	Tìm kiếm category vừa tạo thành công
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Hao Test 3");
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        Thread.sleep(2 * 1000);
//	Verify hiển thì category vừa tạo
        Assert.assertTrue(driver.findElement(By.xpath("(//*[text()='Hao Test 3'])[1]")).isDisplayed());
        Thread.sleep(3 * 1000);
    }
/*
@Test
// CASE 3: Nhập các trường không nhập name -> thêm mới thất bại
public void Category_fail_3() throws InterruptedException  {
//  Điền Parent Category
    driver.findElement(By.xpath("//div[text()='No Parent']")).click();
    driver.findElement(By.xpath("//li//a//span[text()='Laptop']")).click();
//      Điền Ordering Number
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//input[@id='order_level']")).sendKeys("3");
    //      Điền Type
    driver.findElement(By.xpath("//button[@title='Physical']")).click();
    driver.findElement(By.xpath("//a[@id='bs-select-2-1']")).click();
    Thread.sleep(3 * 1000);
//		chọn file ảnh Banner (200x200)
    driver.findElement(By.xpath("(//label[text()='Banner ']//parent::div//following-sibling::div)[1]//div[text()='Choose File']")).click();
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//div[@title='430333983_914461580223005_5432766109724515617_n.jpg']")).click();
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//button[text()='Add Files']")).click();
    Thread.sleep(3 * 1000);
//		chọn file ảnh Icon (32x32)
    driver.findElement(By.xpath("//div[6]//div[1]//div[1]//div[2]")).click();
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//div[@title='108608841_p0_master1200.jpg']//img[@class='img-fit']")).click();
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//button[text()='Add Files']")).click();
    Thread.sleep(3 * 1000);
// Điền Meta Title
    driver.findElement(By.xpath("//input[@placeholder='Meta Title']")).sendKeys("Meta Title 3");
// Điền  Meta description
    driver.findElement(By.xpath("//textarea[@name='meta_description']")).sendKeys("Meta description 3");
// Điền  Filtering Attributes
    driver.findElement(By.xpath("//div[contains(text(),'Nothing selected')]")).click();
    driver.findElement(By.xpath("//a[@id='bs-select-3-0']")).click();

//		Click button save
    driver.findElement(By.xpath("//button[@type='submit']")).click();
//		Verify vẫn đang ở màn Category Information => chưa add thành công
    Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Category Information']")).isDisplayed());
}

    @Test
// Case 4: Nhập name = space không thêm mới thành công
public void Category_fail_4() throws InterruptedException  {
//		Điền Name
    driver.findElement(By.xpath("//input[@id='name']")).sendKeys("   ");
//      Điền Parent Category
    driver.findElement(By.xpath("//div[text()='No Parent']")).click();
    driver.findElement(By.xpath("//li//a//span[text()='Laptop']")).click();
//        new Actions(driver).sendKeys(Keys.ENTER).perform();
//      Điền Ordering Number
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//input[@id='order_level']")).sendKeys("3");
    //      Điền Type
    driver.findElement(By.xpath("//button[@title='Physical']")).click();
    driver.findElement(By.xpath("//a[@id='bs-select-2-1']")).click();
    Thread.sleep(3 * 1000);
//		chọn file ảnh Banner (200x200)
    driver.findElement(By.xpath("(//label[text()='Banner ']//parent::div//following-sibling::div)[1]//div[text()='Choose File']")).click();
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//div[@title='430333983_914461580223005_5432766109724515617_n.jpg']")).click();
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//button[text()='Add Files']")).click();
    Thread.sleep(3 * 1000);
//		chọn file ảnh Icon (32x32)
    driver.findElement(By.xpath("//div[6]//div[1]//div[1]//div[2]")).click();
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//div[@title='108608841_p0_master1200.jpg']//img[@class='img-fit']")).click();
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//button[text()='Add Files']")).click();
    Thread.sleep(3 * 1000);
// Điền Meta Title
    driver.findElement(By.xpath("//input[@placeholder='Meta Title']")).sendKeys("Meta Title 3");
// Điền  Meta description
    driver.findElement(By.xpath("//textarea[@name='meta_description']")).sendKeys("Meta description 3");
// Điền  Filtering Attributes
    driver.findElement(By.xpath("//div[contains(text(),'Nothing selected')]")).click();
    driver.findElement(By.xpath("//a[@id='bs-select-3-0']")).click();

//		Click button save
    Thread.sleep(3 * 1000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
//		Verify vẫn đang ở màn Category Information => chưa add thành công
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Category Information']")).isDisplayed());
}

*/


















}

