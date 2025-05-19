package com.example.creditapp.mappers;

import com.example.creditapp.dtos.CustomerDTO;
import com.example.creditapp.entities.Customer;
import com.example.creditapp.entities.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    
    @Mapping(source = "credits", target = "creditIds", qualifiedByName = "creditsToCreditIds")
    CustomerDTO toDTO(Customer customer);
    
    @Mapping(target = "credits", ignore = true)
    Customer toEntity(CustomerDTO customerDTO);
    
    List<CustomerDTO> toDTOList(List<Customer> customers);
    
    @Named("creditsToCreditIds")
    default List<Long> creditsToCreditIds(List<Credit> credits) {
        if (credits == null) {
            return null;
        }
        return credits.stream()
                .map(Credit::getId)
                .collect(Collectors.toList());
    }
}
