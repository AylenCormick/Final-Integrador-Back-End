package com.example.Final.Ingregrador.Back.End.services;

import com.example.Final.Ingregrador.Back.End.models.Odontologo;
import com.example.Final.Ingregrador.Back.End.models.Paciente;
import com.example.Final.Ingregrador.Back.End.models.Turno;

import javax.swing.plaf.metal.MetalIconFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorService {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/bd-clinica-odontologica-final;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";


    // REGISTROS

    public Odontologo registrarOdontologo(Odontologo odontologo) {
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

    public Paciente registrarPaciente(Paciente paciente) {
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
            preparedStatement.setDate(5, paciente.getFechaAlta());

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

//    public Turno registrarTurno(Turno turno) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//
//        try {
//            Class.forName(DB_JDBC_DRIVER);
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//
//            preparedStatement = connection.prepareStatement("INSERT INTO TURNOS (NOMBRE, APELLIDO, DOMICILIO, DNI, FECHA_ALTA) VALUES (?,?,?,?,?)");
//
//            preparedStatement.setString(1, paciente.getNombre());
//            preparedStatement.setString(2, paciente.getApellido());
//            preparedStatement.setString(3, paciente.domicilioToString());
//            preparedStatement.setInt(4, paciente.getDni());
//            preparedStatement.setDate(5, paciente.getFechaAlta());
//
//            preparedStatement.executeUpdate();
//
//            ResultSet keys = preparedStatement.getGeneratedKeys();
//            if(keys.next())
//                paciente.setId(keys.getInt(1));
//
//            preparedStatement.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            //logger.error("No se pudo registrar el odontologo", e);
//        }
//
//
//        // logger.info("Odontologo " + odontologo.getNombre() + " " + odontologo.getApellido() + " registrado con exito.");
//
//        return paciente;
//    }

    // LISTAR TODOS

    public List<Odontologo> buscarTodosOdontologos() {
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

    public List<Paciente> buscarTodosPacientes() {
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


    // BUSCAR POR DNI / MATRICULA

    public Odontologo buscarOdontologo(String matricula) {
        //log.debug("Buscando al odontologo con id = " + id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS where matricula = ?");
            preparedStatement.setString(1, matricula);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer id = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String matriculaOdont = result.getString("matricula");

                odontologo = new Odontologo();
                odontologo.setId(id);
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

    public Paciente buscarPaciente(Integer dni) {
        //log.debug("Buscando al odontologo con id = " + id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;

        try {

            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES where dni = ?");
            preparedStatement.setInt(1, dni);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Integer id = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                String domicilio = result.getString("domicilio");
                Integer dniPac = result.getInt("dni");
                Date fechaAlta = result.getDate("fechaAlta");

                paciente = new Paciente();
                paciente.setId(id);
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


    // MODIFICAR

    public Odontologo actualizarOdontologo(Odontologo odontologo) {

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

    public Paciente actualizarPaciente(Paciente paciente) {

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
            preparedStatement.setDate(5, paciente.getFechaAlta());
            preparedStatement.setInt(6, paciente.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            //log.error(throwables);
        }

        return paciente;
    }


    // ELIMINAR

    public void eliminarOdontologo(Integer id) {
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

    public void eliminarPaciente(Integer id) {
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
