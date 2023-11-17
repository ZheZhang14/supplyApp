package com.project.service;

import com.project.pojo.dto.InventoryDTO;
import com.project.pojo.dto.InventoryVO;
import com.project.pojo.entities.Inventory;

import java.util.List;

public interface InventoryService {
    List<InventoryVO> getAllInventory();

    void updateInventory(InventoryDTO inventoryDTO);
}
