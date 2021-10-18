package br.com.rocketseat.expert.xlsx.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
public class ReportRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public int executeReportProcedure(String reportName) {
        Query query = entityManager.createNativeQuery("CALL GEN_CUSTOMER_XLSX(:report_name)");
        query.setParameter("report_name", reportName);
        query.executeUpdate();
        return 1;
    }
}
