package com.example.Final.Ingregrador.Back.End.repository.impl;

import com.example.Final.Ingregrador.Back.End.models.Paciente;
import com.example.Final.Ingregrador.Back.End.repository.IDao;
import com.example.Final.Ingregrador.Back.End.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PacienteDao implements IDao<Paciente> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/bd-clinica-odontologica-final;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";


    // REGISTRAR PACIENTE

    @Override
    public Paciente registrar(Paciente paciente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO PACIENTES (NOMBRE, APELLIDO, DOMICILIO, DNI, FECHA_ALTA) VALUES (?,?,?,?,?)");

            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.domicilioToString());
            preparedStatement.setInt(4, paciente.getDni());
            preparedStatement.setDate(4, Util.utilDateToSqlDate(paciente.getFechaAlta()));

            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                paciente.setId(keys.getInt(1));

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
            //logger.error("No se pudo registrar el odontologo", e);
        }


        // logger.info("Odontologo " + odontologo.getNombre() + " " + odontologo.getApellido() + " registrado con exito.");

        return paciente;
    }


    // LISTAR TODOS LOS PACIENTES

    @Override
    public List<Paciente> buscarTodos() {
        //log.debug("Buscando todos los odontologos");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT *  FROM PACIENTES");

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int idPac = result.getInt("id");
                String nombrePac = result.getString("nombre");
                String apellidoPac = result.getString("apellido");
                String domicilio = result.getString("domicilio");
                Integer dniPac = result.getInt("dni");
                Date fechaAltaPac = result.getDate("fechaAlta");

                Paciente paciente = new Paciente();
                paciente.setId(idPac);
                paciente.setNombre(nombrePac);
                paciente.setApellido(apellidoPac);
                //paciente.setDomicilio(domicilio);
                paciente.setDni(dniPac);
                paciente.setFechaAlta(fechaAltaPac);

                pacientes.add(paciente);
            }

            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pacientes;
    }


    // BUSCAR PACIENTE POR ID

    @Override
    public Paciente buscar(Integer id) {
        //log.debug("Buscando al odontologo con id = " + id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer idPac = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String domicilio = result.getString("domicilio");
                Integer dniPac = result.getInt("dni");
                Date fechaAlta = result.getDate("fechaAlta");

                paciente = new Paciente();
                paciente.setId(idPac);
                paciente.setNombre(nombre);
                paciente.setApellido(apellido);
                //paciente.setDomicilio(domicilio);
                paciente.setDni(dniPac);
                paciente.setFechaAlta(fechaAlta);

            }

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            //log.error(throwables);
        }

        return paciente;
    }


    // ACTUALIZAR PACIENTE

    @Override
    public Paciente actualizar(Paciente paciente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE PACIENTES SET nombre = ?, apellido = ?, domicilio = ?, dni = ?, fechaAlta = ? WHERE id = ?");

            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.domicilioToString());
            preparedStatement.setInt(4, paciente.getDni());
            preparedStatement.setDate(5, Util.utilDateToSqlDate(paciente.getFechaAlta()));
            preparedStatement.setInt(6, paciente.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            //log.error(throwables);
        }

        return paciente;
    }


    // ELIMINAR PACIENTE POR ID

    @Override
    public void eliminar(Integer id) {
        //log.debug("Borrando odontologo con id : "+id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM PACIENTES where id = ?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            //log.error(throwables);
        }
    }
}
