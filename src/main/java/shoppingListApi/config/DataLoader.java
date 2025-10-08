package shoppingListApi.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shoppingListApi.model.Item;
import shoppingListApi.repository.ItemRepository;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ItemRepository repository;

    public DataLoader(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Item>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/items.json");
        List<Item> items = mapper.readValue(inputStream, typeReference);
        repository.saveAll(items);
        System.out.println("Sample data loaded!");
    }
}