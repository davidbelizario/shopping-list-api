package shoppingListApi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import shoppingListApi.Service.ItemService;
import shoppingListApi.model.Item;
import shoppingListApi.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {
    @Mock
    private ItemRepository repository;

    @InjectMocks
    private ItemService service;

    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        item1 = new Item(1L, "Rice", BigDecimal.valueOf(5.50), 2, "Food", null, null);
        item2 = new Item(2L, "Milk", BigDecimal.valueOf(3.50), 1, "Dairy", null, null);
    }

    @Test
    void testListAllItems() {
        when(repository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<Item> items = service.listAllItems();
        assertEquals(2, items.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetItemById_Found() {
        when(repository.findById(1L)).thenReturn(Optional.of(item1));

        Item result = service.getItemById(1L);
        assertNotNull(result);
        assertEquals("Rice", result.getName());
    }

    @Test
    void testGetItemById_NotFound() {
        when(repository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.getItemById(3L));
    }

    @Test
    void testSave() {
        when(repository.save(item1)).thenReturn(item1);

        Item saved = service.save(item1);
        assertEquals(item1.getName(), saved.getName());
        verify(repository, times(1)).save(item1);
    }

    @Test
    void testUpdate_Found() {
        Item updated = new Item(null, "Rice Premium", BigDecimal.valueOf(6.00), 3, "Food", null, null);
        when(repository.findById(1L)).thenReturn(Optional.of(item1));
        when(repository.save(any(Item.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Item result = service.update(1L, updated);

        assertNotNull(result);
        assertEquals("Rice Premium", result.getName());
        assertEquals(3, result.getQuantity());
        verify(repository, times(1)).save(item1);
    }

    @Test
    void testUpdate_NotFound() {
        Item item = new Item(null, "Rice Premium", BigDecimal.valueOf(6.00), 3, "Food", null, null);
        when(repository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.update(3L, item));
        verify(repository, never()).save(any());
    }

    @Test
    void testDelete_Found() {
        when(repository.findById(1L)).thenReturn(Optional.of(item1));
        service.delete(1L);
        verify(repository, times(1)).delete(item1);
    }

    @Test
    void testDelete_NotFound() {
        when(repository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.delete(3L));
        verify(repository, never()).delete(any());
    }
}
