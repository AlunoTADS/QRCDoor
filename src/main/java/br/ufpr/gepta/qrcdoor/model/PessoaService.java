/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.gepta.qrcdoor.model;

import java.util.List;

public interface PessoaService {

    Pessoa findById(Integer idPessoa);

    void savePessoa(Pessoa Pessoa);

    void updatePessoa(Pessoa Pessoa);

    void deletePessoaById(Integer idPessoa);

    List findAllPessoas();
}
