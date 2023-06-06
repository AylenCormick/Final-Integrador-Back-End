package com.example.Final.Ingregrador.Back.End.repository.impl;

import com.example.Final.Ingregrador.Back.End.models.Administrador;
import com.example.Final.Ingregrador.Back.End.models.Odontologo;
import com.example.Final.Ingregrador.Back.End.models.Paciente;

import com.example.Final.Ingregrador.Back.End.repository.IDao;
import com.example.Final.Ingregrador.Back.End.util.Util;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDao implements IDao<Administrador> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/bd-clinica-odontologica-final;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";


    @Override
    public Administrador registrar(Administrador administrador) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO ADMINISTRADORES (NOMBRE, APELLIDO, DOMICILIO, DNI, FECHA_ALTA) VALUES (?,?,?,?,?)");

            preparedStatement.setString(1, administrador.getNombre());
            preparedStatement.setString(2, administrador.getApellido());
            preparedStatement.setString(3, administrador.domicilioToString());
            preparedStatement.setInt(4, administrador.getDni());
            preparedStatement.setDate(4, Util.utilDateToSqlDate(administrador.getFechaAlta()));

            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                administrador.setId(keys.getInt(1));

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
            //logger.error("No se pudo registrar el odontologo", e);
        }


        // logger.info("Odontologo " + odontologo.getNombre() + " " + odontologo.getApellido() + " registrado con exito.");

        return administrador;
    }

    @Override
    public List<Administrador> buscarTodos() {
        //log.debug("Buscando todos los odontologos");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Administrador> administradores = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT *  FROM ADMINISTRADORES");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int idAd = result.getInt("id");
                String nombreAd = result.getString("nombre");
                String apellidoAd = result.getString("apellido");
                String domicilioAd = result.getString("domicilio");
                Integer dniAd = result.getInt("dni");
                Date fechaAltaAd = result.getDate("fechaAlta");

                Administrador administrador = new Administrador();
                administrador.setId(idAd);
                administrador.setNombre(nombreAd);
                administrador.setApellido(apellidoAd);
                //administrador.setDomicilio(domicilioAd);
                administrador.setDni(dniAd);
                administrador.setFechaAlta(fechaAltaAd);

                administradores.add(administrador);
            }

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return administradores;
    }

    @Override
    public Administrador buscar(Integer id) {
        //log.debug("Buscando al odontologo con id = " + id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Administrador administrador = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM ADMINISTRADORES where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int idAd = result.getInt("id");
                String nombreAd = result.getString("nombre");
                String apellidoAd = result.getString("apellido");
                String domicilioAd = result.getString("domicilio");
                Integer dniAd = result.getInt("dni");
                Date fechaAltaAd = result.getDate("fechaAlta");

                administrador = new Administrador();
                administrador.setId(idAd);
                administrador.setNombre(nombreAd);
                administrador.setApellido(apellidoAd);
                //administrador.setDomicilio(domicilioAd);
                administrador.setDni(dniAd);
                administrador.setFechaAlta(fechaAltaAd);

            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            //log.error(throwables);
        }

        return administrador;
    }

    @Override
    public Administrador actualizar(Administrador administrador) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE ADMINISTRADOR SET nombre = ?, apellido = ?, domicilio = ?, dni = ?, fechaAlta = ? WHERE id = ?");

            preparedStatement.setString(1, administrador.getNombre());
            preparedStatement.setString(2, administrador.getApellido());
            preparedStatement.setString(3, administrador.domicilioToString());
            preparedStatement.setInt(4, administrador.getDni());
            preparedStatement.setDate(5, Util.utilDateToSqlDate(administrador.getFechaAlta()));
            preparedStatement.setInt(6, administrador.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            //log.error(throwables);
        }

        return administrador;
    }

    @Override
    public void eliminar(Integer id) {
        //log.debug("Borrando odontologo con id : "+id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM ADMINISTRADORES where id = ?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            //log.error(throwables);
        }
    }
}
