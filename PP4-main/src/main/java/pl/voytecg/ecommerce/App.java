package pl.voytecg.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.voytecg.ecommerce.catalog.ArrayListProductStorage;
import pl.voytecg.ecommerce.catalog.ProductCatalog;
import pl.voytecg.ecommerce.sales.SalesFacade;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("TEST");
        SpringApplication.run(App.class,args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {
        ProductCatalog productCatalog = new ProductCatalog(new ArrayListProductStorage());
        productCatalog.addProduct("Lego set 1", "nice one");
        productCatalog.addProduct("Lego set 2", "nice one");
        productCatalog.addProduct("Lego set 3", "nice one");

        return productCatalog;
    }

    @Bean
    SalesFacade createSales(){
        return new SalesFacade();
    }
}
