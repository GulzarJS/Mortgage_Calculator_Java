import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Created by Gulzar Safar on 9/25/2020
 */

public class Calculator {
    public static void main(String[] args) {

        // Connection Details
        Database database = null;
        try {

            database = new Database();
            Scanner scanner = new Scanner(System.in);
            Customer customer = new Customer();
            Credit credit = new Credit();
//            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");


//            String sql;
            String input;
//            int count = 0;

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

            System.out.print("Enter the price of home: ");
            credit.setHomePrice(scanner.nextBigDecimal());
            credit.setCreditYear(customer);


            DatabaseQueries.insertCustomer(customer, database);

            System.out.println(DatabaseQueries.getCustomerId(customer,database));

            DatabaseQueries.insertCredit(customer, credit, database);

//            sql = "insert into customer (id, name, surname, birth_date) " +
//                    " values (customer_seq.nextval, ?, ?, ? )";
//
//
//            database.setPs(database.getConnection().prepareStatement(sql));
//
//            database.getPs().setString(1, customer.getName());
//            database.getPs().setString(2, customer.getSurName());
//            database.getPs().setDate(3, Date.valueOf(customer.getBirthDate()));
//            count = database.getPs().executeUpdate();


            DatabaseQueries.selectCustomer(database);
            DatabaseQueries.selectCredit(database);

//            sql = "select * from customer ";
//
//            database.setPs(database.getConnection().prepareStatement(sql));
//            database.setRs(database.getPs().executeQuery());
//
//            List<Customer> customers = new ArrayList<>();
//            while (database.getRs().next()) {
//                Customer customer1 = new Customer();
//
//                customer1.setId(database.getRs().getInt("id"));
//                customer1.setName(database.getRs().getString("name"));
//                customer1.setSurName(database.getRs().getString("surname"));
//                customer1.setBirthDate(database.getRs().getDate("birth_date").toLocalDate());
//
//
//                customers.add(customer1);
//
//                database.getConnection().commit();
//
//                System.out.println(customer1);
//
//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            database.close();
        }


    }


}
