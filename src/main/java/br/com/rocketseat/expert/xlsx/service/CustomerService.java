package br.com.rocketseat.expert.xlsx.service;

import br.com.rocketseat.expert.xlsx.model.Customer;
import br.com.rocketseat.expert.xlsx.repository.CustomerRepository;
import br.com.rocketseat.expert.xlsx.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ReportRepository reportRepository;

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public byte[] generateReport(String reportName) throws IOException, InterruptedException {
       reportRepository.executeReportProcedure(reportName);
       File filePath = new File("C:\\projects\\oracle\\excel\\"+ reportName +".xlsx");
       while (!filePath.exists()) {}
       FileInputStream fis = new FileInputStream(filePath);
       return fis.readAllBytes();
    }

}
