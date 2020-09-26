//import java.sql.*;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * Created by Gulzar Safar on 9/25/2020
// */
//
//public class Test {
//
//    public static void main(String[] args) {
//
//
//        String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
//        String username = "c##gulzarsafar";
//        String password = "helloOracle";
//        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ORCL"; // SID
//
////        String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/orcl.docker.internal";
//
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String input;
//        int count = 0;
//
//        try {
//            Class.forName(jdbcDriver);
//
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
//            connection.setAutoCommit(false);
//
//
//
//            Scanner scanner = new Scanner(System.in);
//            Customer customer = new Customer();
//            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//
//            String sql;
//
//
//            System.out.print("Enter customer's name: ");
//            input = scanner.next();
//            customer.setName(input);
//
//            System.out.print("Enter customer's surname: ");
//            input = scanner.next();
//            customer.setSurName(input);
//
//            System.out.print("Enter customer's birth date in yyyy-MM-dd format: ");
//            input = scanner.next();
//            customer.setBirthDate(LocalDate.parse(input, formatter2));
//
//
//            sql = "insert into customer (id, name, surname, birth_date) " +
//                    " values (customer_seq.nextval, ?, ?, ? )";
//
//
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, customer.getName());
//            ps.setString(2, customer.getSurName());
//            ps.setDate(3, Date.valueOf(customer.getBirthDate()));
//            count = ps.executeUpdate();
//
//            sql = "select id, name, surname, birth_date from customer";
//
//            ps = connection.prepareStatement(sql);
//
//            rs = ps.executeQuery();
//
//
//            List<Customer> customers = new ArrayList<>();
//
//            while (rs.next()) {
//                Customer customer1 = new Customer();
//                customer1.setId(rs.getInt("id"));
//                customer1.setName(rs.getString("name"));
//                customer1.setSurName(rs.getString("surname"));
//                customer1.setBirthDate(rs.getDate("birth_date").toLocalDate());
//
//                customers.add(customer1);
//                System.out.println(customer1);
//
//            }
//            connection.commit();
//
//
//            } catch (SQLException e) {
//                try {
//                    if(connection != null) {
//                        connection.rollback();
//                    }
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//                System.out.println("database ile bagli xeta bas verdi, mesaj = " + e.getMessage());
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//
//                if(ps != null) {
//                    ps.close();
//                }
//
//                if(connection != null) {
//                    connection.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//}
//
//
//
