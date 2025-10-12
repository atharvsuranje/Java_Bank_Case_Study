<p align="center">
    <img src="https://raw.githubusercontent.com/PKief/vscode-material-icon-theme/ec559a9f6bfd399b82bb44393651661b08aaf7ba/icons/folder-markdown-open.svg" align="center" width="25%">
</p>

# JAVA_BANK_CASE_STUDY

<p align="center">
	<em>A Banking Management System implemented in Java</em>
</p>

<p align="center">
	<img src="https://img.shields.io/github/license/atharvsuranje/Java_Bank_Case_Study?style=flat-square&logo=opensourceinitiative&logoColor=white&color=0080ff" alt="license">
	<img src="https://img.shields.io/github/last-commit/atharvsuranje/Java_Bank_Case_Study?style=flat-square&logo=git&logoColor=white&color=0080ff" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/atharvsuranje/Java_Bank_Case_Study?style=flat-square&color=0080ff" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/atharvsuranje/Java_Bank_Case_Study?style=flat-square&color=0080ff" alt="repo-language-count">
</p>

---

## 🔗 Table of Contents
- [📍 Overview](#-overview)
- [👾 Features](#-features)
- [📁 Project Structure](#-project-structure)
- [🚀 Getting Started](#-getting-started)
- [🔰 Contributing](#-contributing)
- [🎗 License](#-license)
- [🙌 Acknowledgments](#-acknowledgments)
---

## 📍 Overview
Java Bank Case Study is a console-based banking management system implemented in Java. It supports multiple account types such as **Saving**, **Salary**, **Current**, and **Loan** accounts. Users can perform operations like deposit, withdrawal, fund transfer, and EMI payments. The project also tracks transactions and generates bank reports.

---

## 👾 Features
- Create and manage different types of bank accounts.
- Perform deposits, withdrawals, and fund transfers.
- Automatic handling of interest calculation for loans and salary accounts.
- EMI management for Loan Accounts.
- Freeze and unfreeze accounts based on inactivity.
- View detailed transaction history per account.
- Generate summary reports for the bank branch.

---

## 📁 Project Structure
Java_Bank_Case_Study/
└── src/
├── accounts/       # All account types (BankAccount, Saving, Salary, Current, Loan)
├── business/       # Main testing class (TestBank)
└── helpers/        # Bank operations and Transaction class
<details open>
<summary><b>📂 Project File Index</b></summary>
<br>

| File Path | Description |
|-----------|-------------|
| `src/accounts/BankAccount.java` | Base class for all types of bank accounts. |
| `src/accounts/SavingAccount.java` | Represents Saving Accounts. |
| `src/accounts/SalaryAccount.java` | Represents Salary Accounts. |
| `src/accounts/CurrentAccount.java`| Represents Current Accounts with overdraft limits. |
| `src/accounts/LoanAccount.java` | Represents Loan Accounts with EMI and interest calculation. |
| `src/helpers/Bank.java` | Manages bank operations and report generation. |
| `src/helpers/Transaction.java` | Represents a single transaction. |
| `src/business/TestBank.java` | Main class to test and demonstrate bank operations. |

</details>

---

## 🚀 Getting Started

### ☑️ Prerequisites
- **Java 17** or higher installed on your system.
- Basic understanding of Java OOP concepts.

### ⚙️ Installation
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/atharvsuranje/Java_Bank_Case_Study](https://github.com/atharvsuranje/Java_Bank_Case_Study)
    ```
2.  **Navigate to the project folder:**
    ```bash
    cd Java_Bank_Case_Study
    ```
3.  **Compile the source code:**
    ```bash
    javac -d bin src/helpers/*.java src/accounts/*.java src/business/*.java
    ```

### 🤖 Usage
Run the application from the project's root directory:
```bash
java -cp bin business.TestBank
```
The console will display menu options for creating accounts, depositing, withdrawing, transferring funds, paying EMI, and viewing transactions.
---
🧪 Testing
Currently, testing is performed manually via the TestBank console application. Future versions may include JUnit automated tests.
---
## 🔰 Contributing
Contributions are welcome! Here's how you can help:
---
💬 Discussions: Share insights or ask questions via GitHub Discussions.
---
🐛 Report Issues: Submit bugs or feature requests via GitHub Issues.
---
💡 Pull Requests: Fork the repo, make your changes, and submit a PR.
---
## 🎗 License
This project is licensed under the MIT License. See the LICENSE file for more details.

## 🙌 Acknowledgments
Inspired by academic banking case studies.

Java OOP tutorials and references.

The GitHub community for best practices in project structure.
