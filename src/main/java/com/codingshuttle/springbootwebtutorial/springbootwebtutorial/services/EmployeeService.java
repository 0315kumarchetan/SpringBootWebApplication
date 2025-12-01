package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper  objectMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper,ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.objectMapper=objectMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeEntity =  employeeRepository.findById(id);
        return employeeEntity.map(employeeEntity1 ->
                modelMapper.map(employeeEntity1,EmployeeDTO.class));
    }

    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities;
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
//        to check if user is admin
//        log something
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId,EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        if(employeeEntity==null)throw new RuntimeException("Partial update Employee not found");
        employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity employeeEntity1 = employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity1,EmployeeDTO.class);

    }

    public boolean deleteById(Long employeeId){
        if(employeeRepository.existsById(employeeId)){
            employeeRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }

   /* public EmployeeDTO partialEmployeeUpdate( Long employeeId, HashMap<String,Object> update){
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        if(employeeEntity==null)throw new RuntimeException("Employee not found");
        update.forEach((field,value)->{
                    Field fieldToBeUpdate = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
                    fieldToBeUpdate.setAccessible(true);
                    ReflectionUtils.setField(fieldToBeUpdate,employeeEntity,value);
                    });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

    }*/

    public EmployeeDTO partialEmployeeUpdate( Long employeeId, HashMap<String,Object> update){
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        if(employeeEntity==null)throw new RuntimeException("Partial update Employee not found");
        JsonMergePatch mergePatch;
        JsonNode mergedJson;
        EmployeeEntity mergedEmployee;
        try {
            JsonNode employeeJson = objectMapper.valueToTree(employeeEntity);
            JsonNode patchJson = objectMapper.valueToTree(update);
            mergePatch = JsonMergePatch.fromJson(patchJson);
            mergedJson = mergePatch.apply(employeeJson);
            try {
                mergedEmployee = objectMapper.treeToValue(mergedJson, EmployeeEntity.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (JsonPatchException e) {
            throw new RuntimeException(e);
        }

        return modelMapper.map(employeeRepository.save(mergedEmployee),EmployeeDTO.class);

    }
}









