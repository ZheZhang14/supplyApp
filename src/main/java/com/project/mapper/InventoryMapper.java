package com.project.mapper;

import com.project.pojo.dto.InventoryDTO;
import com.project.pojo.dto.InventoryVO;
import com.project.pojo.dto.OrderCreatedDTO;
import com.project.pojo.entities.Inventory;
import com.project.pojo.vo.ProductVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InventoryMapper {

    @Insert("insert into inventory (stock,product_id) VALUES (#{stock},#{id})")
    void insertStock(Integer stock,Integer id);

    @Select("select stock from inventory where product_id=#{productId}")
    Integer getstock(Integer productId);

    @Select("select damagedGoods_count from inventory where product_id=#{productId}")
    Integer getDamageCount(Integer productId);

    @Select("select product_id from inventory;")
    List<Integer> getProductId();

    @Select("select * from inventory where product_id=#{id};")
    Inventory getInventoryByProductId(Integer id);

    @Select("SELECT inventory.*, product.product_name " +
            "FROM inventory " +
            "JOIN product ON inventory.product_id = product.id")
    List<InventoryVO> getAll();

    @Insert("INSERT INTO inventory (product_id,stock,expiredGoods_count,damagedGoods_count) " +
            "VALUES(#{productId}, 0, 0, 0);")
    void initInventory(Integer productId);

    void updateCount(InventoryDTO inventoryDTO);

    @Update("UPDATE inventory SET stock = #{totalStock} WHERE id = #{id}")
    void updateById(Integer id, Integer totoalStock);

    @Update("UPDATE inventory SET stock = #{totalStock} WHERE product_id=#{productId}")
    void updateStock(Integer totalCount, Integer productId);
}
