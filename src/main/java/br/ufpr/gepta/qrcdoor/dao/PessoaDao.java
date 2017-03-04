/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.gepta.qrcdoor.dao;

import br.ufpr.gepta.qrcdoor.model.Pessoa;

/**
 *
 * @author mklos
 */
public abstract class PessoaDao  implements DaoI<Pessoa>{

   /* @Override
    public Pessoa findById(Integer id) {
        Query query = getSession().createSQLQuery("delete from Employee where ssn = :ssn");
        query.setString("ssn", ssn);
        query.executeUpdate();
	}

    }

    @Override
    public Pessoa save(Pessoa model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa update(Pessoa model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa listar(Pessoa model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    
}
