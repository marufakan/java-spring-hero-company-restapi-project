package com.works.restcontrollers;//package com.works.restcontrollers;//package com.works.restcontrollers;
//
//import com.works.entities.Company;
//import com.works.services.CompanyService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/company")
//public class CompanyRestControllers {
//    final CompanyService companyService;
//
//    public CompanyRestControllers(CompanyService companyService) {
//        this.companyService = companyService;
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity save(@Valid @RequestBody Company company){
//        return companyService.companySave(company);
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity list(){
//        return companyService.companyList();
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity delete(@RequestParam Long cid){
//        return companyService.deleteCompany(cid);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity update( @Valid @RequestBody Company company){
//        return companyService.updateCompany(company);
//    }
//
//}