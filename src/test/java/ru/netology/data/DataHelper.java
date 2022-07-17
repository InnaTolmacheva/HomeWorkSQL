package ru.netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataHelper {
    public DataHelper() {

    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @SneakyThrows
    public static String getCode() {
        String codeSQL = "SELECT code FROM auth_codes";
        QueryRunner runner = new QueryRunner();

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass"); // создаем соединение

        ) {

            String code = runner.query(conn, codeSQL, new ScalarHandler<>());
            return code;
        }
    }

    @SneakyThrows
    public static void cleanDataBases() {
        var runner = new QueryRunner();
        String cleanAuthCodes = "DELETE FROM auth_codes;";
        String cleanCardTransactions = "DELETE FROM card_transactions;";
        String cleanCards = "DELETE FROM cards;";
        String cleanUsers = "DELETE FROM users;";

        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {

            runner.update(conn, cleanAuthCodes);
            runner.update(conn, cleanCardTransactions);
            runner.update(conn, cleanCards);
            runner.update(conn, cleanUsers);

        }
    }


}


