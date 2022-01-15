package cl.mingeso.evaluacion2.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.mingeso.evaluacion2.model.Product;
import cl.mingeso.evaluacion2.repositories.productRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/products")
@CrossOrigin
public class productService {
    /*private final Gson gson;

    productService(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    } */
    private final productRepository productRepository;

    productService(productRepository productRepository){
        this.productRepository = productRepository;
    }   
    
    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productRepository.getAllProduct();
    }

    @PostMapping("/products/create")
    @ResponseBody
    public Product createProduct(@RequestBody Product product ){
        Product result = productRepository.createProduct(product);
        return result;
    }

    
    @DeleteMapping("/products/delete/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable int id){
        return productRepository.deleteProduct(id);
    }


}
