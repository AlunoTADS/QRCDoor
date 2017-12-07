package br.ufpr.qrcdoor.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
        Connection conn = DataSourceUtils.getConnection(jdbc.getDataSource());
        JasperPrint jasperPrint = JasperFillManager.fillReport(getTemplate(), null, conn);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private InputStream getTemplate() throws IOException {
        return ReportController.class
                .getClassLoader()
                .getResourceAsStream("report/relatorio_acessos_periodo.jasper");

    }

}
