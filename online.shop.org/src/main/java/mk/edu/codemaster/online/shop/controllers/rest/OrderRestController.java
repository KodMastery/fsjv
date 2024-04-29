package mk.edu.codemaster.online.shop.controllers.rest;

import mk.edu.codemaster.online.shop.entities.dtos.OrderDTO;
import mk.edu.codemaster.online.shop.entities.dtos.OrderItemDTO;
import mk.edu.codemaster.online.shop.mappers.OrderItemMapper;
import mk.edu.codemaster.online.shop.mappers.OrderMapper;
import mk.edu.codemaster.online.shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderRestController {

    @Autowired private OrderService orderService;
    @Autowired private OrderMapper orderMapper;
    @Autowired private OrderItemMapper orderItemMapper;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderMapper.toDTOList(orderService.getAllOrders());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("id") Long id) {
        OrderDTO order = orderMapper.toDTO(orderService.getOrderById(id));
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO order = orderMapper.toDTO(orderService.createOrder(orderMapper.toEntity(orderDTO)));
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable("id") Long id, @RequestBody OrderDTO orderDTO){
        OrderDTO order = orderMapper.toDTO(orderService.updateOrder(id, orderDTO));
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/item")
    public ResponseEntity<OrderItemDTO> addOrderItem(@RequestBody OrderItemDTO orderItemDTO){
        OrderItemDTO orderItem = orderItemMapper.toDTO(orderService.createOrderItem(orderItemMapper.toEntity(orderItemDTO)));
        return ResponseEntity.ok(orderItem);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable("id") Long id){
        orderService.deleteOrderItemById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{orderId}/item/add")
    public ResponseEntity<OrderDTO> addItemToOrder(@PathVariable("orderId") Long orderId, @RequestBody OrderItemDTO orderItemDTO){
        OrderDTO order = orderMapper.toDTO(orderService.addItemInOrder(orderId, orderItemMapper.toEntity(orderItemDTO)));
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{orderId}/item/{itemId}/remove")
    public ResponseEntity<OrderDTO> removeItemToOrder(@PathVariable("orderId") Long orderId, @PathVariable("itemId") Long itemId){
        OrderDTO order = orderMapper.toDTO(orderService.removeItemFromOrder(orderId, itemId));
        return ResponseEntity.ok(order);
    }

    @PostMapping("/item/{itemId}/quantity/add")
    public ResponseEntity<OrderItemDTO> addQuantityToOrderItem(@PathVariable("itemId") Long itemId, @RequestParam("quantity") int quantity){
        OrderItemDTO orderItemDTO = orderItemMapper.toDTO(orderService.updateQuantityOrderItem(itemId, quantity, "+"));
        return ResponseEntity.ok(orderItemDTO);
    }

    @PostMapping("/item/{itemId}/quantity/remove")
    public ResponseEntity<OrderItemDTO> removeQuantityToOrderItem(@PathVariable("itemId") Long itemId, @RequestParam("quantity") int quantity){
        OrderItemDTO orderItemDTO = orderItemMapper.toDTO(orderService.updateQuantityOrderItem(itemId, quantity, "-"));
        return ResponseEntity.ok(orderItemDTO);
    }
}
