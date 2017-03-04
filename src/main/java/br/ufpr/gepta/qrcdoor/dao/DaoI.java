/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.gepta.qrcdoor.dao;

import br.ufpr.gepta.qrcdoor.model.Model;
import java.util.List;

/**
 *
 * @author mklos
 */
public interface DaoI<T extends Model> {

    T getById(Integer id);

    void persist(T entity);

    void delete(T entity);

    List<T> findAll();
}
