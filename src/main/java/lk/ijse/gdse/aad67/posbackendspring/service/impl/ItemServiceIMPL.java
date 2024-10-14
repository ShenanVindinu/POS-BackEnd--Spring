package lk.ijse.gdse.aad67.posbackendspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad67.posbackendspring.dao.ItemDAO;
import lk.ijse.gdse.aad67.posbackendspring.dto.ItemDTO;
import lk.ijse.gdse.aad67.posbackendspring.entity.impl.ItemEntity;
import lk.ijse.gdse.aad67.posbackendspring.exception.DataPersistException;
import lk.ijse.gdse.aad67.posbackendspring.service.ItemService;
import lk.ijse.gdse.aad67.posbackendspring.util.AppUtil;
import lk.ijse.gdse.aad67.posbackendspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private Mapping mapping;

    @Autowired
    private ItemDAO itemDAO;


    @Override
    public void saveItems(ItemDTO item) {
        item.setId(AppUtil.generateItemId());
        ItemEntity savedItem = itemDAO.save(mapping.toItemEntity(item));
        if (savedItem == null) {
            throw new DataPersistException("Item not saved");
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return mapping.asItemDTOList(itemDAO.findAll());
    }
}
