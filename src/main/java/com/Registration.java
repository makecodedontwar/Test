package com;

import com.persistance.ConnectionFactory;
import com.persistance.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.*;

/**
 * Система должна работать по следующему принципу.
 * Каждый раз, когда новый пользователь хочет зарегистрироваться, он посылает системе запрос name со своим именем.
 * Если данное имя не содержится в базе данных системы, то оно заносится туда и пользователю возвращается ответ OK,
 * подтверждающий успешную регистрацию. Если же на сайте уже присутствует пользователь с именем name,
 * то система формирует новое имя и выдает его пользователю в качестве подсказки,
 * при этом подсказка также добавляется в базу данных. Новое имя формируется по следующему правилу.
 * <p>
 * К name последовательно приписываются числа, начиная с единицы (name1, name2, ...),
 * и среди них находят такое наименьшее i, что namei не содержится в базе данных сайта.
 * <p>
 * Примеры:
 * входные данные:
 * abacaba
 * acaba
 * abacaba
 * acab
 * <p>
 * выходные данные:
 * OK
 * OK
 * abacaba1
 * OK
 */
public class Registration {
    private static Map<String, Integer> db = new HashMap<>();
    private static String template = "%s%d";

    public static void main(String[] args) {
        List<String> in = Arrays.asList(
                "abacaba",
                "acaba",
                "abacaba",
                "acab"
        );
        register(in);

    }

    private static void register(List<String> input) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            Database db = new Database(connection);
            for (String s : input) {
                System.out.println(db.save(s));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
