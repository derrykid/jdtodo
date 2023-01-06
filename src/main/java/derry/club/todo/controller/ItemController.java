package derry.club.todo.controller;

import derry.club.todo.entity.Item;
import derry.club.todo.entity.form.ItemForm;
import derry.club.todo.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;

    public ItemController(@Autowired ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "/{userId}/items")
    public List<Item> getUserItems(@PathVariable long userId) {
        logger.info("Get items by user id: {}", userId);
        return itemService.getUserItems(userId);
    }

    @GetMapping(path = "/{userId}/items/undone")
    public List<Item> getUnfinishedItems(@PathVariable long userId) {
        logger.info("Get unfinished items by user id: {}", userId);
        return itemService.getUserUnfinishedItems(userId);
    }

    @GetMapping(path = "/{userId}/items/done")
    public List<Item> getFinishedItems(@PathVariable long userId) {
        logger.info("Get finished items by user id: {}", userId);
        return itemService.getUserFinishedItems(userId);
    }

    @PostMapping(path = "/{userId}/item/create")
    public Item createItem(@PathVariable long userId,
                           @RequestBody ItemForm itemForm) {

        logger.info("Create new item for user id: {} with item title: {}",
                                                                    userId, itemForm.getTitle());
        return itemService.createItem(userId, itemForm);
    }

    @PatchMapping(path = "/{userId}/item")
    public Item updateItem(@PathVariable long userId, @RequestBody ItemForm itemForm) {
        logger.info("Update item id: {} for user id: {}", itemForm.getId(), userId);
        return itemService.updateItem(userId, itemForm);
    }

    @DeleteMapping(path = "/{userId}/item")
    public void deleteItem(@PathVariable long userId, @RequestBody ItemForm itemForm){
        logger.info("Delete item id: {}", itemForm.getId());
        itemService.deleteItemById(userId, itemForm);
    }

}
