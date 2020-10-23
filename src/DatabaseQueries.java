

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DatabaseQueries {

    public static void insertCustomer(Customer customer, Database database) throws SQLException {

        String sql;

        sql = "insert into customer (id, name, surname, birth_date) " +
                " values (customer_seq.nextval, ?, ?, ? )";

        database.setPs(database.getConnection().prepareStatement(sql));

        database.getPs().setString(1, customer.getName());
        database.getPs().setString(2, customer.getSurName());
        database.getPs().setDate(3, Date.valueOf(customer.getBirthDate()));
        database.getPs().executeUpdate();
        database.getConnection().commit();

    }

    public static void selectCustomer(Database database) throws SQLException {

        String sql;

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
    }

    public static  int getCustomerId(Customer customer, Database database) throws SQLException {
        String sql;
        int customerId = -1;

        sql = "select id from customer where name= ? and surname = ? and birth_date = ? ";

        database.setPs(database.getConnection().prepareStatement(sql));

        database.getPs().setString(1, customer.getName());
        database.getPs().setString(2, customer.getSurName());
        database.getPs().setDate(3,Date.valueOf(customer.getBirthDate()));

        database.setRs(database.getPs().executeQuery());

        if (database.getRs().next()) {
            customerId = database.getRs().getInt("id");
            return customerId;
        }
        return customerId;

    }

    public static void insertCredit(Customer customer, Credit credit, Database database) throws SQLException {

        String sql;

        sql = "insert into credit (id, customer_id, home_price, initial_payment, credit_amount, interest_amount,first_payment_date, last_payment_date, action_date)" +
                " values (CREDIT_SEQ.nextval , ?, ?, ?, ?, ?, ?, ?, ? )";

        database.setPs(database.getConnection().prepareStatement(sql));

        database.getPs().setInt(1, DatabaseQueries.getCustomerId(customer,database));
        database.getPs().setBigDecimal(2, credit.getHomePrice());
        database.getPs().setBigDecimal(3, credit.getInitialPayment());
        database.getPs().setBigDecimal(4, credit.getCreditAmount());
        database.getPs().setBigDecimal(5, credit.getInterestAmount());
        database.getPs().setDate(6, Date.valueOf(credit.getFirstPaymentDate()));
        database.getPs().setDate(7, Date.valueOf(credit.getLastPaymentDate()));
        database.getPs().setDate(8, Date.valueOf(credit.getActionDate()));


        database.getPs().executeUpdate();
        database.getConnection().commit();

    }

    public static void selectCredit(Database database) throws SQLException {

        String sql;

        sql = "select id, home_price, initial_payment, credit_amount, interest_amount, " +
                "first_payment_date, last_payment_date, action_date from credit";

        database.setPs(database.getConnection().prepareStatement(sql));
        database.setRs(database.getPs().executeQuery());

        List<Credit> credits = new ArrayList<>();
        while (database.getRs().next()) {
            Credit credit = new Credit();

            credit.setId(database.getRs().getInt("id"));
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
    }

    public static  int getCreditId(Credit credit, Database database) throws SQLException {
        String sql;
        int customerId = -1;

        sql = "select id from credit where home_price= ? and initial_payment = ? and credit_amount = ? and " +
                "interest_amount = ? and first_payment_date = ? and last_payment_date = ? " +
                " and action_date = ?";

        database.setPs(database.getConnection().prepareStatement(sql));
        database.getPs().setBigDecimal(1, credit.getHomePrice());
        database.getPs().setBigDecimal(2, credit.getInitialPayment());
        database.getPs().setBigDecimal(3, credit.getCreditAmount());
        database.getPs().setBigDecimal(4, credit.getInterestAmount());
        database.getPs().setDate(5, Date.valueOf(credit.getFirstPaymentDate()));
        database.getPs().setDate(6, Date.valueOf(credit.getLastPaymentDate()));
        database.getPs().setDate(7, Date.valueOf(credit.getActionDate()));

        database.setRs(database.getPs().executeQuery());

        if (database.getRs().next()) {
            customerId = database.getRs().getInt("id");
            return customerId;
        }
        return customerId;

    }


    public static void insertPaymentPlan(Credit credit, Database database) throws SQLException {

        String sql;

        sql = "insert into monthly_payment (id, credit_id, payment_date , base_amount, interest_amount, total_amount)" +
                " values (MONTHLY_PAYMENT_SEQ.nextval , ?, ?, ?, ?, ?, ? )";

        List<MonthlyPayment> paymentPlan =  credit.monthlyPaymentPlan(credit, database);

        ListIterator<MonthlyPayment> paymentPlanIterator = paymentPlan.listIterator();

        while(paymentPlanIterator.hasNext()){
            database.setPs(database.getConnection().prepareStatement(sql));

            database.getPs().setInt(1, DatabaseQueries.getCreditId(credit,database));
            database.getPs().setDate(2, Date.valueOf(paymentPlanIterator.next().getPaymentDate()));
            database.getPs().setBigDecimal(3, paymentPlanIterator.next().getBaseAmount());
            database.getPs().setBigDecimal(4, paymentPlanIterator.next().getInterestAmount());
            database.getPs().setBigDecimal(5, paymentPlanIterator.next().getTotalAmount());

            database.getPs().executeUpdate();
            database.getConnection().commit();
        }

    }


//    public static void selectPaymentPlan(Customer customer, Database database) throws SQLException {
//
//        String sql;
//
//        sql = "select id, credit_id, payment_date , base_amount, interest_amount, total_amount from monthly_payment";
//
//        database.setPs(database.getConnection().prepareStatement(sql));
//        database.setRs(database.getPs().executeQuery());
//
//        List<MonthlyPayment> paymentPlan= new ArrayList<>();
//
//        while (database.getRs().next()) {
//            Credit credit = new Credit();
//
//            credit.setId(database.getRs().getInt("id"));
//            credit.setHomePrice(database.getRs().getBigDecimal("home_price"));
//            credit.setInitialPayment(database.getRs().getBigDecimal("initial_payment"));
//            credit.setCreditAmount(database.getRs().getBigDecimal("credit_amount"));
//            credit.setInterestAmount(database.getRs().getBigDecimal("interest_amount"));
//            credit.setFirstPaymentDate(database.getRs().getDate("first_payment_date").toLocalDate());
//            credit.setLastPaymentDate(database.getRs().getDate("last_payment_date").toLocalDate());
//            credit.setActionDate(database.getRs().getDate("action_date").toLocalDate());
//
//
//            credits.add(credit);
//
//            database.getConnection().commit();
//
//            System.out.println(credit);
//        }
//    }

}
