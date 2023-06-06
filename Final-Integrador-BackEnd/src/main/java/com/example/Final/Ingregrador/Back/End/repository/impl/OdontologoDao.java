package com.example.Final.Ingregrador.Back.End.repository.impl;

import com.example.Final.Ingregrador.Back.End.models.Odontologo;
import com.example.Final.Ingregrador.Back.End.repository.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDao implements IDao<Odontologo> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/bd-clinica-odontologica-final;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";


    // REGISTRAR ODONTOLOGO

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) VALUES (?,?,?)");

            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getMatricula());

            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                odontologo.setId(keys.getInt(1));

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
            //logger.error("No se pudo registrar el odontologo", e);
        }


        // logger.info("Odontologo " + odontologo.getNombre() + " " + odontologo.getApellido() + " registrado con exito.");

        return odontologo;
    }


    // LISTAR TODOS LOS ODONTOLOGOS

    @Override
    public List<Odontologo> buscarTodos() {
        //log.debug("Buscando todos los odontologos");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT *  FROM ODONTOLOGOS");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int idOdontologo = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String matricula = result.getString("matricula");

                Odontologo odontologo = new Odontologo();
                odontologo.setId(idOdontologo);
                odontologo.setNombre(nombre);
                odontologo.setApellido(apellido);
                odontologo.setMatricula(matricula);

                odontologos.add(odontologo);
            }

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return odontologos;
    }


    // BUSCAR ODONTOLOGO POR ID

    @Override
    public Odontologo buscar(Integer id) {
        //log.debug("Buscando al odontologo con id = " + id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer idOd = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String matriculaOdont = result.getString("matricula");

                odontologo = new Odontologo();
                odontologo.setId(idOd);
                odontologo.setNombre(nombre);
                odontologo.setApellido(apellido);
                odontologo.setMatricula(matriculaOdont);

            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            //log.error(throwables);
        }

        return odontologo;
    }


    // ACTUALIZAR ODONTOLOGO

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE ODONTOLOGOS SET nombre = ?, apellido = ?, matricula = ?  WHERE id = ?");

            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getMatricula());
            preparedStatement.setInt(4, odontologo.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            //log.error(throwables);
        }

        return odontologo;
    }


    // ELIMINAR ODONTOLOGO POR ID

    @Override
    public void eliminar(Integer id) {
        //log.debug("Borrando odontologo con id : "+id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM ODONTOLOGOS where id = ?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            //log.error(throwables);
        }
    }
}
