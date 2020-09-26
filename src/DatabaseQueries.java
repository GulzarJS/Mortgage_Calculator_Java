/*
 *  Created by Gulzar Safar on 9/26/2020
 */

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueries {

    public static void insertCustomer(Customer customer, Database database) throws SQLException {

        String sql;
        int count = 0;

        sql = "insert into customer (id, name, surname, birth_date) " +
                " values (customer_seq.nextval, ?, ?, ? )";

        database.setPs(database.getConnection().prepareStatement(sql));

        database.getPs().setString(1, customer.getName());
        database.getPs().setString(2, customer.getSurName());
        database.getPs().setDate(3, Date.valueOf(customer.getBirthDate()));
        count = database.getPs().executeUpdate();
        database.getConnection().commit();

    }

    public static void selectCustomer(Database database) throws SQLException {

        String sql;

        sql = "select * from customer ";

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

        sql = "select id from customer where name= ? and surname = ? ";

        database.setPs(database.getConnection().prepareStatement(sql));

        database.getPs().setString(1, customer.getName());
        database.getPs().setString(2, customer.getSurName());

        database.setRs(database.getPs().executeQuery());

        if (database.getRs().next()) {
            customerId = database.getRs().getInt("id");
            return customerId;
        }
        return customerId;

    }

    public static void insertCredit(Customer customer, Credit credit, Database database) throws SQLException {

        String sql;
        int count = 0;

        sql = "insert into credit (id, customer_id, home_price, initial_payment, credit_amount, interest_amount,first_payment_date, last_payment_date, action_date)" +
                " values (CREDIT_SEQ.nextval , ?, ?, ?, ?, ?, ?, ?, ? )";

//        insert into credit (id, customer_id, home_price, initial_payment, credit_amount, interest_amount,first_payment_date, last_payment_date, action_date)
//        values(CREDIT_SEQ.nextval ,23, 200000, 50000, 150000, 12000,to_date('2020-09-26','yyyy-MM-dd' ),to_date('2020-09-26','yyyy-MM-dd' ),to_date('2020-09-26','yyyy-MM-dd' ));
//
        database.setPs(database.getConnection().prepareStatement(sql));

        database.getPs().setInt(1, getCustomerId(customer,database));
        database.getPs().setBigDecimal(2, credit.getHomePrice());
        database.getPs().setBigDecimal(3, credit.getInitialPayment());
        database.getPs().setBigDecimal(4, credit.getCreditAmount());
        database.getPs().setBigDecimal(5, credit.getInterestAmount());
        database.getPs().setDate(6, Date.valueOf(credit.getFirstPaymentDate()));
        database.getPs().setDate(7, Date.valueOf(credit.getLastPaymentDate()));
        database.getPs().setDate(8, Date.valueOf(credit.getActionDate()));


        count = database.getPs().executeUpdate();
        database.getConnection().commit();

    }

    public static void selectCredit(Database database) throws SQLException {

        String sql;

        sql = "select * from credit ";

        database.setPs(database.getConnection().prepareStatement(sql));
        database.setRs(database.getPs().executeQuery());

        List<Credit> credits = new ArrayList<>();
        while (database.getRs().next()) {
            Credit credit = new Credit();

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


}
