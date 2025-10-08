package shoppingListApi.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingListApi.Service.ItemService;
import shoppingListApi.model.Item;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<Item> listAllItems() {
        return service.listAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(
            @PathVariable Long id
    ) {
        Item item = service.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public Item createItem(
            @Valid @RequestBody Item item
    ) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody Item item
    ) {
        Item updatedItem = service.update(id, item);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
