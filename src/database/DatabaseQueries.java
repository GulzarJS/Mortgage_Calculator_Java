package database;

import model.Credit;
import model.Customer;
import model.MonthlyPayment;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DatabaseQueries {

    // Function for inserting information about customer to database
    public static void insertCustomer(Customer customer) throws SQLException {

        Database database = new Database();
        String sql;
        try {
            sql = "insert into customer (id, name, surname, birth_date) " +
                    " values (customer_seq.nextval, ?, ?, ? )";

            database.setPs(database.getConnection().prepareStatement(sql));

            database.getPs().setString(1, customer.getName());
            database.getPs().setString(2, customer.getSurName());
            database.getPs().setDate(3, Date.valueOf(customer.getBirthDate()));
            database.getPs().executeUpdate();
            database.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();

        }
    }



    // Function for select and printing all customers from database
    public static void selectCustomer() throws SQLException {

        Database database = new Database();
        String sql;

        try{
        sql = "select id, name, surname, birth_date from customer ";

        database.setPs(database.getConnection().prepareStatement(sql));
        database.setRs(database.getPs().executeQuery());

        List<Customer> customers = new ArrayList<>();

        while (database.getRs().next()) {
            Customer customer1 = new Customer();

            customer1.setId(database.getRs().getInt("id"));
            customer1.setName(database.getRs().getString("name"));
            customer1.setSurName(database.getRs().getString("surname"));
            customer1.setBirthDate(database.getRs().getDate("birth_date").toLocalDate());


            customers.add(customer1);
            database.getConnection().commit();
            System.out.println(customer1);
        }
        }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.close();

            }

    }

    // Function for select and printing information about given customer from database
    public static void selectExactCustomer(Customer customer) throws SQLException {

        Database database = new Database();
        String sql;

        try{
        sql = "select id, name, surname, birth_date from customer where id = ? ";

        database.setPs(database.getConnection().prepareStatement(sql));

        database.getPs().setInt(1, DatabaseQueries.getCustomerId(customer));

        database.setRs(database.getPs().executeQuery());


        if (database.getRs().next()) {
            Customer customer1 = new Customer();

            customer1.setId(database.getRs().getInt("id"));
            customer1.setName(database.getRs().getString("name"));
            customer1.setSurName(database.getRs().getString("surname"));
            customer1.setBirthDate(database.getRs().getDate("birth_date").toLocalDate());


            database.getConnection().commit();
            System.out.println(customer1);
        }
        }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.close();

            }

    }

    // Function for getting customer Id of given customer from database
    public static  int getCustomerId(Customer customer) throws SQLException {

        Database database = new Database();
        String sql;
        int customerId = -1;

        try {
            sql = "select id from customer where name= ? and surname = ? and birth_date = ? ";

            database.setPs(database.getConnection().prepareStatement(sql));

            database.getPs().setString(1, customer.getName());
            database.getPs().setString(2, customer.getSurName());
            database.getPs().setDate(3, Date.valueOf(customer.getBirthDate()));

            database.setRs(database.getPs().executeQuery());

            if (database.getRs().next()) {
                customerId = database.getRs().getInt("id");
                return customerId;
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();

        }
        return customerId;


    }

    // Function for inserting information about credit to database
    public static void insertCredit(Credit credit) throws SQLException {

        Database database = new Database();
        String sql;

        try {
            sql = "insert into credit (id, customer_id, home_price, initial_payment, credit_amount, interest_amount, first_payment_date, last_payment_date)" +
                    " values (credit_seq.nextval , ?, ?, ?, ?, ?, ?, ? )";


            database.setPs(database.getConnection().prepareStatement(sql));
            database.getPs().setInt(1, DatabaseQueries.getCustomerId(credit.getCustomer()) );
            database.getPs().setBigDecimal(2, credit.getHomePrice());
            database.getPs().setBigDecimal(3, credit.getInitialPayment());
            database.getPs().setBigDecimal(4, credit.getCreditAmount());
            database.getPs().setBigDecimal(5, credit.getInterestAmount());
            database.getPs().setDate(6, Date.valueOf(credit.getFirstPaymentDate()));
            database.getPs().setDate(7, Date.valueOf(credit.getLastPaymentDate()));
            database.getPs().executeUpdate();
            database.getConnection().commit();

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();

        }
    }

    // Function for select and printing all credits from database
    public static void selectCredit() throws SQLException {


        Database database = new Database();
        String sql;

        try{
            sql = "select id, customer_id, home_price, initial_payment, credit_amount, interest_amount, " +
                    "first_payment_date, last_payment_date, action_date from credit";

            database.setPs(database.getConnection().prepareStatement(sql));
            database.setRs(database.getPs().executeQuery());


            List<Credit> credits = new ArrayList<>();

            while (database.getRs().next()) {
                Credit credit = new Credit();

                credit.setId(database.getRs().getInt("id"));
                credit.setCustomerId(database.getRs().getInt("customer_id"));
                credit.setHomePrice(database.getRs().getBigDecimal("home_price"));
                credit.setInitialPayment(database.getRs().getBigDecimal("initial_payment"));
                credit.setCreditAmount(database.getRs().getBigDecimal("credit_amount"));
                credit.setInterestAmount(database.getRs().getBigDecimal("interest_amount"));
                credit.setFirstPaymentDate(database.getRs().getDate("first_payment_date").toLocalDate());
                credit.setLastPaymentDate(database.getRs().getDate("last_payment_date").toLocalDate());
                credit.setActionDate(database.getRs().getDate("action_date").toLocalDate());


                credits.add(credit);
                database.getConnection().commit();
                System.out.println(credit);
            }
        }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.close();

            }

    }

    // Function for select and printing information about credit of given customer from database
    public static void selectCreditForCustomer(Customer customer) throws SQLException {


        Database database = new Database();
        String sql;

        try{
            sql = "select id, customer_id, home_price, initial_payment, credit_amount, interest_amount, " +
                    "first_payment_date, last_payment_date, action_date from credit where customer_id = ?";

            database.setPs(database.getConnection().prepareStatement(sql));

            database.getPs().setInt(1, DatabaseQueries.getCustomerId(customer));

            database.setRs(database.getPs().executeQuery());

            if (database.getRs().next()) {

                Credit credit = new Credit();

                credit.setId(database.getRs().getInt("id"));
                credit.setCustomerId(database.getRs().getInt("customer_id"));
                credit.setHomePrice(database.getRs().getBigDecimal("home_price"));
                credit.setInitialPayment(database.getRs().getBigDecimal("initial_payment"));
                credit.setCreditAmount(database.getRs().getBigDecimal("credit_amount"));
                credit.setInterestAmount(database.getRs().getBigDecimal("interest_amount"));
                credit.setFirstPaymentDate(database.getRs().getDate("first_payment_date").toLocalDate());
                credit.setLastPaymentDate(database.getRs().getDate("last_payment_date").toLocalDate());
                credit.setActionDate(database.getRs().getDate("action_date").toLocalDate());

                database.getConnection().commit();
                System.out.println(credit);

            }

        }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                database.close();

            }

    }


    // Function for getting credit Id of given customer
    public static  int getCreditId(Customer customer) throws SQLException {

        Database database = new Database();
        String sql;
        int creditId = -1;

        try {
            sql = "select id from credit where customer_id = ?";

            database.setPs(database.getConnection().prepareStatement(sql));

            database.getPs().setInt(1,DatabaseQueries.getCustomerId(customer));

            database.setRs(database.getPs().executeQuery());

            if (database.getRs().next()) {
                creditId = database.getRs().getInt("id");
                return creditId;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();

        }
        return creditId;

    }


    // Function for inserting information about monthly payment plan for given credit
    public static void insertPaymentPlan(Credit credit) throws SQLException {

        Database database = new Database();
        String sql;

        try {
            sql = "insert into monthly_payment (id, credit_id, payment_date , base_amount, interest_amount, total_amount)" +
                    " values (MONTHLY_PAYMENT_SEQ.nextval , ?, ?, ?, ?, ? )";

            List<MonthlyPayment> paymentPlan = credit.monthlyPaymentPlan();


            ListIterator<MonthlyPayment> paymentPlanIterator = paymentPlan.listIterator();



            while (paymentPlanIterator.hasNext()) {
                database.setPs(database.getConnection().prepareStatement(sql));

                MonthlyPayment temp = paymentPlanIterator.next();

                database.getPs().setInt(1, DatabaseQueries.getCreditId(credit.getCustomer()));
                database.getPs().setDate(2, Date.valueOf(temp.getPaymentDate()));
                database.getPs().setBigDecimal(3, temp.getBaseAmount());
                database.getPs().setBigDecimal(4, temp.getInterestAmount());
                database.getPs().setBigDecimal(5, temp.getTotalAmount());


            database.getPs().executeUpdate();
            database.getConnection().commit();

            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();

        }
    }

    // Function for select and printing information about monthly payment plan of given customer from database
    public static void selectMonthlyPaymentPlanForCustomer(Customer customer) throws SQLException {


        Database database = new Database();
        String sql;

        try{
            sql = "select id, credit_id, payment_date , base_amount, interest_amount, total_amount " +
                    "from monthly_payment where credit_id = ?";

            database.setPs(database.getConnection().prepareStatement(sql));

            database.getPs().setInt(1, DatabaseQueries.getCreditId(customer));

            database.setRs(database.getPs().executeQuery());

            int counter = 1;
            while (database.getRs().next()) {

                MonthlyPayment payment = new MonthlyPayment();

                payment.setId(database.getRs().getInt("id"));
                payment.setCreditId(database.getRs().getInt("credit_id"));
                payment.setPaymentDate(database.getRs().getDate("payment_date").toLocalDate());
                payment.setBaseAmount(database.getRs().getBigDecimal("base_amount"));
                payment.setInterestAmount(database.getRs().getBigDecimal("interest_amount"));
                payment.setTotalAmount(database.getRs().getBigDecimal("total_amount"));


                database.getConnection().commit();
                System.out.println(counter++ + ". month => " + payment);

            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.close();

        }

    }


}
