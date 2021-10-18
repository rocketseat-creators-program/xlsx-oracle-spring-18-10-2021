package br.com.rocketseat.expert.xlsx.controller;

import br.com.rocketseat.expert.xlsx.model.Customer;
import br.com.rocketseat.expert.xlsx.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer")
    @ResponseBody
    public List<Customer> getAll(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/customer/report/{reportName}")
    public ResponseEntity getCustomerReport(@PathVariable String reportName) throws IOException, InterruptedException {

        byte[] excel = customerService.generateReport(reportName);
        ByteArrayInputStream is = new ByteArrayInputStream(excel);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + reportName + ".xlsx");

        ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
                HttpStatus.OK);

        return response;
    }
}
