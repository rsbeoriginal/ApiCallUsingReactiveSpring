package com.example.ApiCallUsingReactiveSpring;

import com.example.ApiCallUsingReactiveSpring.helper.ApiPaths;
import com.example.ApiCallUsingReactiveSpring.helper.WebClientHelper;
import com.example.ApiCallUsingReactiveSpring.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    String url = employeeHost + ApiPaths.EMPLOYEE_PATH + "/" + employeeId;
    return webClientHelper.performGetToMono(URI.create(url), null, EmployeeModel.class);
  }

  @GetMapping
  public Flux<EmployeeModel> getEmployeeById(){
    String url = employeeHost + ApiPaths.EMPLOYEE_PATH;
    return webClientHelper.performGetToFlux(URI.create(url), null, EmployeeModel.class);
  }
}
