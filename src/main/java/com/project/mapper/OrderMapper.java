package com.project.mapper;

import com.project.pojo.dto.OrderCreatedDTO;
import com.project.pojo.entities.Order;
import com.project.pojo.entities.OrderType;
import com.project.pojo.entities.OrderVVO;
import com.project.pojo.vo.OrderOverviewVO;
import com.project.pojo.vo.OrderVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT o.id, o.total_amount AS totalAmount, o.order_type AS orderType, o.count, o.status, " +
            "o.createtime, o.updatetime, u.contact_name AS contactName, p.product_name AS productName, p.price " +
            "FROM `order` o INNER JOIN user u ON o.user_id = u.id " +
            "INNER JOIN product p ON o.product_id = p.id order by o.updatetime desc")
    List<OrderVVO> getAllOrders();

    @Select("SELECT o.id, o.total_amount AS totalAmount, o.order_type AS orderType, o.count, o.status, " +
            "o.createtime, o.updatetime, u.contact_name AS contactName, p.product_name AS productName, p.price " +
            "FROM `order` o INNER JOIN user u ON o.user_id = u.id " +
            "INNER JOIN product p ON o.product_id = p.id WHERE u.id = #{id} order by o.updatetime desc")
    List<OrderVVO> getOrderByUserId(Integer id);
    @Insert("INSERT INTO `order`(product_id,user_id,total_amount,count,order_type,status) " +
            "VALUES(#{productId}, #{userId}, #{totalAmount}, #{count}, #{orderType},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createOrder(OrderCreatedDTO orderCreatedDTO);

    void updateOrder(Order order);

    @Select("SELECT product_id,count from `order` where id=#{id}")
    OrderVO getOrderById(Integer id);

    @Select("SELECT o.id, o.total_amount AS totalAmount, o.order_type AS orderType, o.count, o.status, " +
            "o.createtime, o.updatetime, u.contact_name AS contactName, p.product_name AS productName, p.price " +
            "FROM `order` o INNER JOIN user u ON o.user_id = u.id " +
            "INNER JOIN product p ON o.product_id = p.id WHERE p.id = #{id} and o.status='Done' and o.order_type = 'Create' order by o.updatetime desc")
    List<OrderVVO> getDoneNormalOrdersByProductId(Integer id);
}

