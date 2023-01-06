package derry.club.todo.config;

import derry.club.todo.entity.User;
import derry.club.todo.entity.Item;
import derry.club.todo.repository.ItemRepository;
import derry.club.todo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MockDB {

    private static final Logger logger = LoggerFactory.getLogger(MockDB.class);

    @Bean
    CommandLineRunner mock(UserRepository userRepository, ItemRepository itemRepository) {

        return args -> {

            User user = new User();
            user.setName("Derry");
            userRepository.save(user);

            Item item1 = new Item();
            item1.setUserId(user.getId());
            item1.setTitle("Drink water");
            item1.setCompleted(false);

            Item item2 = new Item();
            item2.setUserId(user.getId());
            item2.setTitle("Coding");
            item2.setCompleted(false);

            itemRepository.saveAll(List.of(item1, item2));

            user.addItem(item1);
            user.addItem(item2);
            userRepository.save(user);

        };
    }
}
