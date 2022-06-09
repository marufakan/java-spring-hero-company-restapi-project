package com.works.services;//package com.works.services;//package com.works.services;
//
//import com.works.entities.Company;
//import com.works.repostories.CompanyRepository;
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
//public class CompanyService {
//    final CompanyRepository companyRepository;
//
//    public CompanyService(CompanyRepository companyRepository) {
//        this.companyRepository = companyRepository;
//    }
//
//    //post
//    public ResponseEntity<Map<String ,Object>> companySave(Company company){
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("custom","1234556");
//        Company p=companyRepository.save(company);
//
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        hm.put(REnum.status,true);
//        hm.put(REnum.result, p);
//        return new  ResponseEntity(hm,headers, HttpStatus.OK);
//    }
//
//    //list
//    public ResponseEntity<Map<String ,Object>>  companyList(){
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        hm.put(REnum.status,true);
//        hm.put(REnum.result, companyRepository.findAll());
//        return new  ResponseEntity(hm, HttpStatus.OK);
//    }
//
//    //delete
//    public ResponseEntity<Map<String ,Object>> deleteCompany(Long cid ){
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        try {
//            companyRepository.deleteById(cid);
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
//    public ResponseEntity<Map<String ,Object>> updateCompany(Company company){
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        try{
//            Optional<Company> oCompany= companyRepository.findById(company.getCompanyId());
//            if(oCompany.isPresent()){
//                companyRepository.saveAndFlush(company);
//                hm.put(REnum.result, company);
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
