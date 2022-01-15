package cl.mingeso.evaluacion2.repositories;

import java.util.List;

import cl.mingeso.evaluacion2.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class productRepositoryImp implements productRepository{
    @Autowired
    private Sql2o sql2o;

       @Override
    public int countProduct() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM products";
        try(Connection conn = sql2o.open()){
            total = conn.createQuery(sql).executeScalar(Integer.class);
        }
        return total;
    }
    

    @Override
    public List<Product> getAllProduct() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM products ORDER by Code").executeAndFetch(Product.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public Product createProduct(Product product){
        try(Connection conn = sql2o.open()){
            int idAnterior = conn.createQuery("SELECT COUNT(*) FROM products").executeScalar(Integer.class);
            String sql = "INSERT INTO products (id_product, code, name_product, " +
                            "\"expiration\", category, isVisible, price)"
                    + " VALUES (:id_Product, :code_Product, :name_Product,:expiration_Product, :category_Product, :isVisible, :price_Product)";
            conn.createQuery(sql, true)
                    .addParameter("id_Product", idAnterior + 1)
                    .addParameter("code_Product", product.getCode())
                    .addParameter("name_Product", product.getName())
                    .addParameter("category_Product", product.getCategory())
                    .addParameter("expiration_Product", product.getExpiration())
                    .addParameter("isVisible", product.isVisible())
                    .addParameter("price_Product", product.getPrice())
                    .executeUpdate();
            product.setId(idAnterior + 1);
            return product;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteProductAll() {
        try(Connection conn = sql2o.open()){
            conn.createQuery("TRUNCATE products CASCADE")
                    .executeUpdate();
            return 1;
        }catch(Exception e){
            return 0;
        }
    }

    @Override
    public List<Product> getProduct(int idProduct){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM products WHERE id = '"+ idProduct +"'")
                    .executeAndFetch(Product.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    

    @Override
    public String deleteProduct(int idProduct) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM products WHERE id_product = '"+idProduct+"'").executeUpdate();
            return "Eliminado el producto con id = " + idProduct;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "No se pudo eliminar el producto con id = " + idProduct;
        }
    }
}