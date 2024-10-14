package lk.ijse.gdse.aad67.posbackendspring.service;

import lk.ijse.gdse.aad67.posbackendspring.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    void saveItems(ItemDTO item);

    List<ItemDTO> getAllItems();

    void deleteItemById(String itemId);
}
