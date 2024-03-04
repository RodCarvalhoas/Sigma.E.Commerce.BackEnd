package com.Sigma.SigmaBackEnd.services;

import com.Sigma.SigmaBackEnd.Enums.Role;
import com.Sigma.SigmaBackEnd.model.Category;
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
        Product product2 = new Product(null, "Headset Sem Fio Gamer HyperX Cloud Stinger Core Som Surround 7.1, Drivers 40mm - 4P4F0AA", 369.99, "https://images.kabum.com.br/produtos/fotos/114027/headset-sem-fio-gamer-hyperx-cloud-stinger-core-7-1-hhss1c-ba-bk-g_1597936019_gg.jpg", 4, category2, null);
        Product notebookAcer = new Product(null, "Notebook Gamer Acer Nitro AMD Ryzen 7-5800H, 8GB RAM, GeForce GTX 1650, SSD 1TB, 15.6 Full HD, Windows 11, Preto - AN515-45-R4S3", 3799.99, "https://images.kabum.com.br/produtos/fotos/sync_mirakl/473212/Notebook-Acer-Nitro-5-An515-58-58w3-Intel-Core-I5-12-gen-Linux-Gutta-8GB-512GB-SSD-RTX3050-15-6-Polegadas-FULL-HD_1696961338_gg.jpg", 2, category, null);
        Product ps5 = new Product(null, "Console Playstation 5 Sony, SSD 825GB, Controle sem fio DualSense, Com Mídia Física, Branco - 1214A", 3440.90, "https://images.kabum.com.br/produtos/fotos/238671/console-sony-playstation-5_1634132556_gg.jpg", 8, category, null);
        Product mouseHiperX = new Product(null, "Mouse Sem Fio Gamer HyperX Pulsefire Dart, RGB, 16000DPI - HX-MC006B", 399.99, "https://images.kabum.com.br/produtos/fotos/105010/mouse-sem-fio-gamer-hyperx-pulsefire-dart-rgb-16000dpi-hx-mc006b-_mouse-sem-fio-gamer-hyperx-pulsefire-dart-rgb-16000dpi-hx-mc006b-_1571411835_gg.jpg", 4, category2, null);
        Product teclado = new Product(null, "Teclado Mecânico Gamer Redragon Kumara, Anti-Ghosting, RGB, Switch Outemu Brown, ABNT2, Preto, PT - K552RGB-1 (PT-BROWN)", 199.99, "https://images.kabum.com.br/produtos/fotos/93160/93160_1_1523969668_gg.jpg", 3, category, null);

        UserModel userAdmin = new UserModel(null, "Administrador", "admin@gmail.com", new BCryptPasswordEncoder().encode("senha123"), Role.ADMIN, null);
        UserModel userUser = new UserModel(null, "Usuario", "usuario@gmail.com", new BCryptPasswordEncoder().encode("senha123"), Role.USER, null);

        categoryRepository.saveAll(Arrays.asList(category2, category));
        productRepository.saveAll(Arrays.asList(product2, product, notebookAcer, ps5, mouseHiperX, teclado));
        userModelRepository.saveAll(Arrays.asList(userUser, userAdmin));
    }
}
