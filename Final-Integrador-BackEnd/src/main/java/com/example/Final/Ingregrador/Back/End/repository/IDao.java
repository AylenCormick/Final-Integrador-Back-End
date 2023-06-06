package com.example.Final.Ingregrador.Back.End.repository;

import java.util.List;

public interface IDao <T> {

    T registrar(T t);

    List<T> buscarTodos();

    T buscar(Integer id);

    T actualizar(T t);

    void eliminar(Integer id);

}
