package py.una.pol.web.tarea6.mapper;

import org.apache.ibatis.annotations.Insert;
import py.una.pol.web.tarea6.model.SaleOrder;

import java.util.List;

public interface SaleOrderMapper {
  SaleOrder getSaleOrder(int id);

  List<SaleOrder> getSaleOrders();

  @Insert("INSERT INTO sale_order(item_id, amount, sale_id) VALUES (#{item.id}, #{amount}, #{sale.id})")
  void insertSaleOrder(SaleOrder so);

}
