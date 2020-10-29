package model;

import database.DatabaseQueries;
import model.Credit;
import model.Customer;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;



public class Calculator {
    public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            Customer customer = new Customer();
            Credit credit = new Credit(customer);

            String input;

            System.out.print("Enter customer's name: ");
            input = scanner.next();
            customer.setName(input);

            System.out.print("Enter customer's surname: ");
            input = scanner.next();
            customer.setSurName(input);

            System.out.print("Enter customer's birth date in yyyy-MM-dd format: ");
            input = scanner.next();
            customer.setBirthDate(LocalDate.parse(input));

            System.out.print("Enter gender of customer => m=male, f=female: ");
            customer.setGender(scanner.next());
            credit.setCreditYear(customer);

            System.out.print("Enter the price of home: ");
            credit.setHomePrice(scanner.nextBigDecimal());



        try {

            if(DatabaseQueries.getCustomerId(customer) != -1 ){
                System.err.println("This person has already have mortgage.");
                System.exit(1);
            }if(Period.between(customer.getBirthDate(), LocalDate.now()).getYears() < 18){
                System.err.println("This person is underage");
                System.exit(1);
            }if(customer.getRestWorkYear() <= 0){
                System.err.println("This person is retired");
                System.exit(1);
            }

            DatabaseQueries.insertCustomer(customer);
            DatabaseQueries.insertCredit(credit);
            DatabaseQueries.insertPaymentPlan(credit);

            System.out.println("model.Customer:");
            DatabaseQueries.selectExactCustomer(customer);
            System.out.println("Mortgage:");
            DatabaseQueries.selectCreditForCustomer(customer);
            System.out.println("Monthly Payment Plan:");
            DatabaseQueries.selectMonthlyPaymentPlanForCustomer(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }


}
