package br.ufscar.dc.dsw.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

import java.sql.Connection;

public class CriaUsuarios {

    public static void main(String[] args) throws ClassNotFoundException {

        try {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            DataSource ds = JDBCUtil.getDataSource();

            Connection conn = ds.getConnection();

            String userSql = "Insert into Usuario (email, senha, ativo) "
                    + "values (?,?,?)";

            String roleSql = "Insert into Papel (email, nome)"
                    + "values (?,?)";

            //Criando Usuario admin com papel ROLE_ADMIN
            
            PreparedStatement userStatement = conn.prepareStatement(userSql); //administrador
            userStatement.setString(1, "admin@admin");
            userStatement.setString(2, encoder.encode("admin"));
            userStatement.setBoolean(3, true);
            userStatement.execute();

            PreparedStatement roleStatement = conn.prepareStatement(roleSql);
            roleStatement.setString(1, "admin@admin");
            roleStatement.setString(2, "ROLE_ADMIN");
            roleStatement.execute();

            //Criando Usuario user com papel ROLE_USER 
            userStatement = conn.prepareStatement(userSql); //usuario comum
            userStatement.setString(1, "user@user");
            userStatement.setString(2, encoder.encode("user"));
            userStatement.setBoolean(3, true);
            userStatement.execute();

            roleStatement = conn.prepareStatement(roleSql);
            roleStatement.setString(1, "user@user");
            roleStatement.setString(2, "ROLE_USER");
            roleStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}