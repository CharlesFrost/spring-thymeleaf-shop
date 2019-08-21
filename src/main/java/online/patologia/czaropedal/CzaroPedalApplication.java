package online.patologia.czaropedal;

import online.patologia.czaropedal.model.MyUser;
import online.patologia.czaropedal.model.Product;
import online.patologia.czaropedal.repo.ProductRepo;
import online.patologia.czaropedal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CzaroPedalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CzaroPedalApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(ProductRepo productRepo, UserRepo userRepo){
		return args -> {
			productRepo.save(new Product("Apple","Iphone 7",700.00,"https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201606271147",15, "AMPH"));
			productRepo.save(new Product("Apple","Iphone 8",900.00,"https://stat-m5.ms-online.pl/media/cache/gallery/rc/qtvl4toz/images/20/20090187/microsoft-nokia-230-czarny-1.jpg",15,"BENZO"));
			productRepo.save(new Product("Ziułko","Ziułko",420.00,"https://upload.wikimedia.org/wikipedia/commons/2/23/Marijuana-Cannabis-Weed-Bud-Gram.jpg",1500, "WEED"));
			productRepo.save(new Product("Apple","Iphone 7",700.00,"https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201606271147",15, "AMPH"));
			productRepo.save(new Product("Apple","Iphone 8",900.00,"https://stat-m5.ms-online.pl/media/cache/gallery/rc/qtvl4toz/images/20/20090187/microsoft-nokia-230-czarny-1.jpg",15,"BENZO"));
			productRepo.save(new Product("Ziułko","Ziułko",420.00,"https://upload.wikimedia.org/wikipedia/commons/2/23/Marijuana-Cannabis-Weed-Bud-Gram.jpg",1500, "WEED"));
			productRepo.save(new Product("Apple","Iphone 7",700.00,"https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201606271147",15, "AMPH"));
			productRepo.save(new Product("Apple","Iphone 8",900.00,"https://stat-m5.ms-online.pl/media/cache/gallery/rc/qtvl4toz/images/20/20090187/microsoft-nokia-230-czarny-1.jpg",15,"BENZO"));
			productRepo.save(new Product("Ziułko","Ziułko",420.00,"https://upload.wikimedia.org/wikipedia/commons/2/23/Marijuana-Cannabis-Weed-Bud-Gram.jpg",1500, "LSD"));
			productRepo.save(new Product("Apple","Iphone 7",700.00,"https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201606271147",15, "COKE"));
			productRepo.save(new Product("Apple","Iphone 8",900.00,"https://stat-m5.ms-online.pl/media/cache/gallery/rc/qtvl4toz/images/20/20090187/microsoft-nokia-230-czarny-1.jpg",15,"BENZO"));
			productRepo.save(new Product("Ziułko","Ziułko",420.00,"https://upload.wikimedia.org/wikipedia/commons/2/23/Marijuana-Cannabis-Weed-Bud-Gram.jpg",1500, "WEED"));
			productRepo.save(new Product("Apple","Iphone 7",700.00,"https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201606271147",15, "AMPH"));
			productRepo.save(new Product("Apple","Iphone 8",900.00,"https://stat-m5.ms-online.pl/media/cache/gallery/rc/qtvl4toz/images/20/20090187/microsoft-nokia-230-czarny-1.jpg",15,"BENZO"));
			productRepo.save(new Product("Ziułko","Ziułko",420.00,"https://upload.wikimedia.org/wikipedia/commons/2/23/Marijuana-Cannabis-Weed-Bud-Gram.jpg",1500, "WEED"));
			productRepo.save(new Product("Apple","Iphone 7",700.00,"https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201606271147",15, "AMPH"));
			productRepo.save(new Product("Apple","Iphone 8",900.00,"https://stat-m5.ms-online.pl/media/cache/gallery/rc/qtvl4toz/images/20/20090187/microsoft-nokia-230-czarny-1.jpg",15,"BENZO"));
			productRepo.save(new Product("Ziułko","Ziułko",420.00,"https://upload.wikimedia.org/wikipedia/commons/2/23/Marijuana-Cannabis-Weed-Bud-Gram.jpg",1500, "WEED"));
			productRepo.save(new Product("Apple","Iphone 7",700.00,"https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201606271147",15, "AMPH"));
			productRepo.save(new Product("Apple","Iphone 8",900.00,"https://stat-m5.ms-online.pl/media/cache/gallery/rc/qtvl4toz/images/20/20090187/microsoft-nokia-230-czarny-1.jpg",15,"BENZO"));
			productRepo.save(new Product("Ziułko","Ziułko",420.00,"https://upload.wikimedia.org/wikipedia/commons/2/23/Marijuana-Cannabis-Weed-Bud-Gram.jpg",1500, "WEED"));
			productRepo.save(new Product("Apple","Iphone 7",700.00,"https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201606271147",15, "AMPH"));
			productRepo.save(new Product("Apple","Iphone 8",900.00,"https://stat-m5.ms-online.pl/media/cache/gallery/rc/qtvl4toz/images/20/20090187/microsoft-nokia-230-czarny-1.jpg",15,"BENZO"));
			productRepo.save(new Product("Ziułko","Ziułko",420.00,"https://upload.wikimedia.org/wikipedia/commons/2/23/Marijuana-Cannabis-Weed-Bud-Gram.jpg",1500, "WEED"));
			productRepo.save(new Product("Apple","Iphone 7",700.00,"https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201606271147",15, "AMPH"));
			productRepo.save(new Product("Apple","Iphone 8",900.00,"https://stat-m5.ms-online.pl/media/cache/gallery/rc/qtvl4toz/images/20/20090187/microsoft-nokia-230-czarny-1.jpg",15,"BENZO"));
			productRepo.save(new Product("Ziułko","Ziułko",420.00,"https://upload.wikimedia.org/wikipedia/commons/2/23/Marijuana-Cannabis-Weed-Bud-Gram.jpg",1500, "WEED"));

		};
	}
}
