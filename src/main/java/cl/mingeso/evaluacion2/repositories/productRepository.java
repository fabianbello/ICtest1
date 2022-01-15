package cl.mingeso.evaluacion2.repositories;
import java.util.List;
import cl.mingeso.evaluacion2.model.Product;


public interface productRepository {

    int countProduct();
    List<Product> getAllProduct();
    Product createProduct(Product product);
    int deleteProductAll();
    List<Product> getProduct(int idProduct);
    String deleteProduct(int idProduct);

}

