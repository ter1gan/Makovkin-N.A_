import java.sql.*;
import java.util.Date;
import java.util.*;


public class Bank{

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Date date = new Date();
        int choice;

        while (true) {
            System.out.println("\n-------------------");
            System.out.println("BANK    OF     JAVA");
            System.out.println("-------------------\n");
            System.out.println("1. Регистрация.");
            System.out.println("2. Вход в существующий аккаунт.");
            System.out.println("3. Выход.");
            System.out.print("\nВведите свой выбор: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Введите имя: ");
                    String firstName = sc.nextLine();
                    System.out.print("Введите фамилию: ");
                    String lastName = sc.nextLine();
                    System.out.print("Введите адрес: ");
                    String address = sc.nextLine();
                    System.out.print("Введите контактный номер: ");
                    String phone = sc.nextLine();
                    System.out.println("Установите имя пользователя: ");
                    String username = sc.next();
                    System.out.println("Установите пароль:");
                    String password = sc.next();
                    sc.nextLine();
                    Customer customer = new Customer(firstName, lastName, address, phone, username, password, date);
                    AccountOwner.registerCustomer(customer);
                    break;
                case 2:
                    System.out.print("Введите имя пользователя: ");
                    String loginUsername = sc.next();
                    System.out.print("Введите пароль: ");
                    String loginPassword = sc.next();
                    sc.nextLine();

                    Customer loggedInCustomer = AccountOwner.login(loginUsername, loginPassword);
                    BankAccount selectedAccount = new BankAccount();

                    if (choice != 3) {
                        assert loggedInCustomer != null;
                        System.out.println("Добро пожаловать, " + loggedInCustomer.getFullName() + "!");
                        int accountChoice;

                        boolean running = true;
                        while(running) {
                            System.out.println("\n-------------------");
                            System.out.println("BANK ACCOUNT MENU");
                            System.out.println("-------------------\n");
                            System.out.println("1. Внести средства.");
                            System.out.println("2. Вывод средств.");
                            System.out.println("3. Открыть счет.");
                            System.out.println("4. Закрыть счет.");
                            System.out.println("5. Перевести средства.");
                            System.out.println("6. Показать сводную информацию по всем счетам.");
                            System.out.println("7. Проверить баланс.");
                            System.out.println("8. Показывать историю транзакций.");
                            System.out.println("9. Выход из системы.");
                            System.out.print("\nВведите свой выбор: ");
                            accountChoice = sc.nextInt();


                            switch (accountChoice) {

                                case 1:
                                    if (loginUsername != null && loginPassword != null) {
                                        System.out.print("Введите номер счета:");
                                        String accountNumber = sc.next();
                                        System.out.print("Введите сумму: ");
                                        double depositAmount = Double.parseDouble(sc.next());
                                        selectedAccount.deposit(accountNumber, depositAmount);
                                        System.out.println("Средства успешно внесены!");
                                    } else {
                                        System.out.println("Учетная запись не выбрана.");
                                    }
                                    break;

                                case 2:
                                    if (loginUsername != null && loginPassword != null) {
                                        System.out.print("Введите номер счета:");
                                        String accountNumber = sc.next();
                                        System.out.print("Введите сумму: ");
                                        double withdrawAmount = Double.parseDouble(sc.next());
                                        selectedAccount.withdraw(accountNumber, withdrawAmount);
                                        System.out.println("Средства успешно выведены!");
                                    } else {
                                        System.out.println("Счет не выбран.");
                                    }
                                    break;

                                case 3:
                                    System.out.print("Введите номер счета: ");
                                    String accountNumber = sc.next();
                                    BankAccount.openAccount(accountNumber);
                                    break;
                                case 4:
                                    System.out.print("Введите номер счета, который нужно закрыть: ");
                                    String closedAccountNumber = sc.next();
                                    BankAccount.closeAccount(closedAccountNumber);
                                    break;
                                case 5:
                                    System.out.print("Введите номер счета, с которого нужно перевести средства: ");
                                    String fromAccountNumber = sc.next();
                                    System.out.print("Введите номер счета, на который нужно перевести средства: ");
                                    String toAccountNumber = sc.next();
                                    System.out.print("Введите сумму для перевода: ");
                                    double amount = Double.parseDouble(sc.next());
                                    sc.nextLine(); // Считывание символа новой строки
                                    BankAccount.transfer(fromAccountNumber, toAccountNumber, amount);
                                    break;
                                case 6:
                                    BankAccount.generateSummary();
                                    break;
                                case 7:
                                    if (loginUsername != null && loginPassword != null) {
                                        System.out.print("Введите номер счета: ");
                                        String accountNumber1 = sc.next();
                                        System.out.println("Баланс: " + BankAccount.getBalance(accountNumber1));
                                    } else {
                                        System.out.println("Счет не выбран.");
                                    }
                                    break;

                                case 8:
                                    if (loginUsername != null && loginPassword != null) {
                                        TransactionHistory.transactionHistory();
                                    } else {
                                        System.out.println("Счет не выбран.");
                                    }
                                    break;
                                case 9:
                                    running = false;
                                    break;
                                default:
                                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Неверное имя пользователя или пароль. Пожалуйста, попробуйте снова.");
                    }
                case 3:
                    System.exit(0);
            }
        }

    }

}



