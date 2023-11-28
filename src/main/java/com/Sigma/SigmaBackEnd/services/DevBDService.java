package com.Sigma.SigmaBackEnd.services;

import com.Sigma.SigmaBackEnd.Enums.Role;
import com.Sigma.SigmaBackEnd.model.Category;
import com.Sigma.SigmaBackEnd.model.Favorite;
import com.Sigma.SigmaBackEnd.model.Product;
import com.Sigma.SigmaBackEnd.model.UserModel;
import com.Sigma.SigmaBackEnd.repository.CategoryRepository;
import com.Sigma.SigmaBackEnd.repository.FavoriteRepository;
import com.Sigma.SigmaBackEnd.repository.ProductRepository;
import com.Sigma.SigmaBackEnd.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DevBDService {

    @Autowired
    UserModelRepository userModelRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FavoriteRepository favoriteRepository;


    public void startBDH2() {

        Category category = new Category(null, "COMPUTADORES");
        Category category2 = new Category(null, "PERIFERICOS");

        Product product = new Product(null,
                "PC Gamer Intel Core i5-10400F, GEFORCE RTX 3060 12GB, 16G, SSD M.2 512GB, 750W 80 PLUS, Linux - 19651",
                7099.99,
                "https://images.kabum.com.br/produtos/fotos/337049/pc-gamer-facil-intel-core-i5-10400f-geforce-rtx-3060-12gb-16g-ssd-m-2-512gb-750w-80-plus-linux-19651_1667828753_gg.jpg",
                5,category,
                null);
        Product product2 = new Product(null, "Fone Gamer", 250, "https://m.media-amazon.com/images/I/61D0neZIgNL.__AC_SX300_SY300_QL70_ML2_.jpg", 4, category2, null);

        UserModel userAdmin = new UserModel(null, "Rodrigo", "teste@gmail.com", new BCryptPasswordEncoder().encode("senha123"), Role.ADMIN, null);
        UserModel userUser = new UserModel(null, "Antonio", "teste2@gmail.com", new BCryptPasswordEncoder().encode("senha123"), Role.USER, null);

        Favorite favorite = new Favorite(null, product, userAdmin);

        categoryRepository.saveAll(Arrays.asList(category2, category));
        productRepository.saveAll(Arrays.asList(product2, product));
        userModelRepository.saveAll(Arrays.asList(userUser, userAdmin));
        favoriteRepository.save(favorite);
    }
}
