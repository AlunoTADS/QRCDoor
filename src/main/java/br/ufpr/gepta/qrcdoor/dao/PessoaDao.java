package br.ufpr.gepta.qrcdoor.dao;

import br.ufpr.gepta.qrcdoor.model.Pessoa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mklos
 */
@Repository("employeeDao")
public class PessoaDao extends Dao<Pessoa> implements DaoI<Pessoa> {

}