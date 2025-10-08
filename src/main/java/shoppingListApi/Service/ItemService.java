package shoppingListApi.Service;

import org.springframework.stereotype.Service;
import shoppingListApi.model.Item;
import shoppingListApi.repository.ItemRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> listAllItems() {
        return repository.findAll();
    }

    public Item getItemById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found with id: " + id));
    }

    public Item save(Item item) {
        return repository.save(item);
    }

    public Item update(Long id, Item updatedItem) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found with id: " + id));
        item.setName(updatedItem.getName());
        item.setPrice(updatedItem.getPrice());
        item.setQuantity(updatedItem.getQuantity());
        item.setCategory(updatedItem.getCategory());
        return repository.save(item);
    }

    public void delete(Long id) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found with id: " + id));
        repository.delete(item);
    }
}
