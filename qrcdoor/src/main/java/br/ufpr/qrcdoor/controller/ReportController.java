package br.ufpr.qrcdoor.controller;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    JdbcTemplate jdbc;

    private Connection conn = DataSourceUtils.getConnection(jdbc.getDataSource());

    @GetMapping("/report")
    public ResponseEntity foto(@RequestParam("dataInicial") Date dataInicial, @RequestParam("dataInicial") Date dataFinal, @RequestParam("pessoa") Long idPessoa, @RequestParam("estrutura") Long idEstrutura) throws Exception {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("dataInicial", dataInicial);
        parameters.put("dataFinal", dataFinal);
        parameters.put("idPessoa", idPessoa);
        parameters.put("idEstrutura", idEstrutura);

        byte[] report = processReport(parameters);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_PDF)
                .body(report);
    }

    private final byte[] processReport(Map<String, Object> parameters) throws Exception {
        final Report report = new Report();
        byte[] data = null;
        return null;
    }

    private class Report {

        private byte[] data;

        public byte[] getData() {
            return data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }

    }

}
