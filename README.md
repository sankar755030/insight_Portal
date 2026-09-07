
# ✅ QA Automation - Insight Portal

This is a Selenium TestNG automation framework for validating workflows on the Insight Portal, specifically focusing on **Adobe eSign**, **Export Control**, and **Forms Management** modules. The project follows best practices like Page Object Model (POM), modular utility design, centralized driver management, and ExtentReports for reporting.

---

## 📁 Project Structure

```
src/
 ├── main/
 │   └── java/
 │       ├── base/          # Core reusable framework base classes
 │       └── pages/         # Page Object Model classes for UI interactions
 └── test/
     └── java/
         ├── base/          # Test-level base logic (e.g. BaseTest)
         ├── drivers/       # Driver binaries (e.g. chromedriver.exe)
         ├── listeners/     # Custom listeners like ExtentReportListener
         ├── pages/         # Page Object test wrappers
         ├── resources/     # Support files like log4j2.xml
         ├── tests/         # Test classes organized by module
         ├── utils/         # Shared utilities (Wait, Screenshot, Constants)
         └── utils1/        # Additional environment-specific utility classes
```

---

## 🧪 Test Reports

After test execution, rich HTML reports are generated under:
```
/test_reports/
 - Esign_Agreement_PDF_Attachment_Positive_Flow.html
 - Esign_Review_and_Sign_Positive_Flow.html
 - Forms_Management_Create_New_Form.html
 - Adding_Esign_Flow_From_Email.html
 - Adobe_ESign_Flow.html
```

📸 Screenshot evidence is stored under:
```
/screenshots/
 - passed_createFormFlow.png
```

---

## 📄 Test Data

Test PDFs used during execution are stored under:
```
/Test_Data/
 - Agreement Info 2025_03.pdf
```

---

## ⚙️ Technologies Used

- Java 17+
- Selenium WebDriver
- TestNG
- ExtentReports
- WebDriverManager (Bonigarcia)
- Maven
- Page Object Model (POM)
- IntelliJ IDEA

---

## 🚀 How to Run Tests

1. **Clone the repo**  
   ```bash
   git clone https://github.com/SankarTechnossus/github-QA_Automation_InsightPortal.git
   ```

2. **Navigate to project root**
   ```bash
   cd github-QA_Automation_InsightPortal
   ```

3. **Run via Maven**
   ```bash
   mvn clean test
   ```

4. **View Report**  
   Open any HTML file under `test_reports/` to view results.

---

## 🧠 Author

**Shankar Venkatesan**  
🔗 _Automation Engineer
📩 Feel free to raise issues, contribute, or fork!

---

## 📌 Future Improvements

- ✅ Integrate CI/CD (GitHub Actions or Jenkins)
- ✅ Parallel execution support via TestNG XML
- ✅ Cross-browser testing setup
- ✅ Docker support for isolated runs
