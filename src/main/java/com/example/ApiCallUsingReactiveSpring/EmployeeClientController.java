package com.example.ApiCallUsingReactiveSpring;

import com.example.ApiCallUsingReactiveSpring.helper.ApiPaths;
import com.example.ApiCallUsingReactiveSpring.helper.WebClientHelper;
import com.example.ApiCallUsingReactiveSpring.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// program to control employee client

import java.net.URI;

/**
 * @author rishi
 */
@RestController
@RequestMapping("/employeeClient")
public class EmployeeClientController {

  @Value("${employee.server.host}")
  private String employeeHost;

  @Autowired
  private WebClientHelper webClientHelper;

  @GetMapping("/{employeeId}")
  public Mono<EmployeeModel> getEmployeeById(@PathVariable("employeeId") String employeeId){
    String url = ApiPaths.getEmployeePath(employeeHost) + "/" + employeeId;
    return webClientHelper.performGetToMono(URI.create(url), null, EmployeeModel.class);
  }

  @GetMapping
  public Flux<EmployeeModel> getEmployeeList(){
    return webClientHelper.performGetToFlux(URI.create(ApiPaths.getEmployeePath(employeeHost)), null, EmployeeModel.class);
  }

  @PostMapping
  public Mono<EmployeeModel> saveEmployee(@RequestBody EmployeeModel employeeModel){
    return webClientHelper.performPostToMono(URI.create(ApiPaths.getEmployeePath(employeeHost)), employeeModel,
        EmployeeModel.class);
  }

  @PutMapping
  public Mono<EmployeeModel> updateEmployee(@RequestBody EmployeeModel employeeModel){
    return webClientHelper
        .performPutToMono(URI.create(ApiPaths.getEmployeePath(employeeHost)), employeeModel,
            EmployeeModel.class);
  }

  @DeleteMapping("/{id}")
  public Mono<EmployeeModel> deleteEmployee(@PathVariable("id") Long employeeId){
    String url = ApiPaths.getEmployeePath(employeeHost) + "/" + employeeId;
    return webClientHelper.performDeleteToMono(URI.create(url),null, EmployeeModel.class);
  }
}
