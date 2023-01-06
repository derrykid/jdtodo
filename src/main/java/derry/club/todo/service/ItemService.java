package derry.club.todo.service;

import derry.club.todo.entity.Item;
import derry.club.todo.entity.form.ItemForm;
import derry.club.todo.exception.ItemNotFoundException;
import derry.club.todo.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository itemRepository;

    public ItemService(@Autowired ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getUserItems(long userId) {
        return itemRepository.findAllByUserId(userId);
    }

    public List<Item> getUserUnfinishedItems(long userId) {
        return itemRepository.findUnFinishedItemsByUserId(userId);
    }

    public List<Item> getUserFinishedItems(long userId) {
        return itemRepository.findFinishedItemsByUserId(userId);
    }

    public Item createItem(long userId, ItemForm itemForm) {
        logger.info("Create new item: {}", itemForm.getTitle());

        Item item = new Item();
        item.setCompleted(itemForm.isCompleted());
        item.setTitle(itemForm.getTitle());
        item.setUserId(userId);

        return itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(long userId, ItemForm itemForm) {
        logger.info("Update item: {}", itemForm);

        Optional<Item> optionalItem = itemRepository.findById(itemForm.getId());

        if (optionalItem.isEmpty()) {
            throw new ItemNotFoundException(itemForm.getId());
        } else {
            Item item = optionalItem.get();
            item.setTitle(itemForm.getTitle());
            item.setCompleted(itemForm.isCompleted());
            return itemRepository.save(item);
        }
    }

    public void deleteItemById(long userId, ItemForm itemForm) {
        logger.info("Delete User: {}, item id: {}", userId, itemForm.getId());

        itemRepository.deleteById(itemForm.getId());
    }
}
