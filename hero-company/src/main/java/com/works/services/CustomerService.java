package com.works.services;//package com.works.services;
//
//import com.works.entities.Customer;
//import com.works.repostories.CustomerRepository;
//import com.works.utils.REnum;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@Service
//public class CustomerService {
//    final CustomerRepository customerRepository;
//
//    public CustomerService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    //post
//    public ResponseEntity<Map<String ,Object>> customerSave(Customer customer){
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("custom","1234556");
//        Customer p=customerRepository.save(customer);
//
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        hm.put(REnum.status,true);
//        hm.put(REnum.result, p);
//        return new  ResponseEntity(hm,headers, HttpStatus.OK);
//    }
//
//    //list
//    public ResponseEntity<Map<String ,Object>>  customerList(){
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        hm.put(REnum.status,true);
//        hm.put(REnum.result, customerRepository.findAll());
//        return new  ResponseEntity(hm, HttpStatus.OK);
//    }
//
//    //delete
//    public ResponseEntity<Map<String ,Object>> deleteCustomer(Long cid ){
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        try {
//            customerRepository.deleteById(cid);
//            hm.put(REnum.status,true);
//            return new  ResponseEntity(hm, HttpStatus.OK);
//        }catch (Exception ex) {
//            hm.put(REnum.status,false);
//            hm.put(REnum.message, ex.getMessage());
//            return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    //update
//    public ResponseEntity<Map<String ,Object>> updateCustomer(Customer customer){
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        try{
//            Optional<Customer> oCustomer = customerRepository.findById(customer.getCid());
//            if(oCustomer.isPresent()){
//                customerRepository.saveAndFlush(customer);
//                hm.put(REnum.result, customer);
//                hm.put(REnum.status, true);
//                return new  ResponseEntity(hm, HttpStatus.OK);
//            }else{
//                hm.put(REnum.status, false);
//                return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
//            }
//        }catch (Exception e){
//            hm.put(REnum.status, false);
//            hm.put(REnum.message, e.getMessage());
//        }
//        return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
//    }
//}
