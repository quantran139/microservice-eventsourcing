package com.example.employeeservice.query.projection;

import com.example.commonservice.model.EmployeeResponseCommonModel;
import com.example.employeeservice.command.data.Employee;
import com.example.employeeservice.command.data.EmployeeRepository;
import com.example.employeeservice.query.model.EmployeeResponseModel;
import com.example.employeeservice.query.queries.GetAllEmployeeQuery;
import com.example.commonservice.query.GetEmployeeDetailQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeProjection {
    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery query){
        List<Employee> listEmployee = employeeRepository.findAllByIsDisciplined(query.getIsDisciplined());
        return listEmployee.stream().map(employee -> {
            EmployeeResponseModel model = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee,model);
            return model;
        }).toList();
    }

    @QueryHandler
    public EmployeeResponseCommonModel handle(GetEmployeeDetailQuery query) throws Exception{
        Employee employee = employeeRepository.findById(query.getId()).orElseThrow(() -> new Exception("Employee not found"));
        EmployeeResponseCommonModel model = new EmployeeResponseCommonModel();
        BeanUtils.copyProperties(employee,model);
        return model;
    }
}
