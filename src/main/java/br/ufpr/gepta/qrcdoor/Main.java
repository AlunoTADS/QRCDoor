package br.ufpr.gepta.qrcdoor;

import br.ufpr.gepta.qrcdoor.dao.PessoaDao;
import br.ufpr.gepta.qrcdoor.model.Pessoa;

/**
 *
 * @author Lucas
 */
public class Main {
    public static void main(String[] args) {
        PessoaDao pd = new PessoaDao();
        for(Pessoa p : pd.findAll()){
            System.out.println(p.toString());
        }
    }
}
