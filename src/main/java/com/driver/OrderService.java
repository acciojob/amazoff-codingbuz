package com.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderService {


    private OrderRepository orderRepository;
    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    public void addPartner(String partnerId) {
        DeliveryPartner partner=new DeliveryPartner(partnerId);
        orderRepository.addPartner(partner);
        return;
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {

        Optional<Order> optionalOrder=orderRepository.getOrderById(orderId);
        Optional<DeliveryPartner> optionalDeliveryPartner=orderRepository.getPartnerById(partnerId);

        if(optionalOrder.isEmpty()){
           throw new RuntimeException("order id is not present"+orderId);
        }
        if(optionalDeliveryPartner.isEmpty()){
            throw new RuntimeException("order id is not present"+partnerId);
        }
        DeliveryPartner partner=optionalDeliveryPartner.get();
        partner.setNumberOfOrders(partner.getNumberOfOrders()+1);
        orderRepository.addPartner(partner);
//        DeliveryPartner partner=new DeliveryPartner(partnerId);
//        Order order=new Order(orderId);
          orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId) {
     Optional<Order> optionalOrder=orderRepository.getOrderById(orderId);
     if(optionalOrder.isPresent()){
         return optionalOrder.get();
     }
     throw new RuntimeException("Order not found");
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        Optional<DeliveryPartner> optionalOrder=orderRepository.getPartnerById(partnerId);
        if(optionalOrder.isPresent()){
            return optionalOrder.get();
        }
        throw new RuntimeException("Order not found");
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
//            Optional<DeliveryPartner> optional=orderRepository.getPartnerById(partnerId);
//            if(optional.isPresent()){
//                return optional.get().getNumberOfOrders();
//            }
//            return 0;

        List<String> order= orderRepository.getOrderForPartner(partnerId);
        return order.size();

    }

    public List<String> getOrderByParnerId(String partnerId) {
        return orderRepository.getOrderByPartnerId(partnerId);
    }

    public List<String> getAllOrder() {
        return orderRepository.getAllOrder();
    }

    public Integer getCountOfUnassignedOrders() {
        return orderRepository.getAllOrder().size()- orderRepository.getAssignedOrder().size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
          List<String> orderId=orderRepository.getOrderForPartner(partnerId);
          List<Order> orders=new ArrayList<>();
          for(String id:orderId){
              Order order=orderRepository.getOrderById(id).get();
              if(order.getDeliveryTime()>TimeUtils.convertTime(time)){
                  orders.add(order);
              }
          }
          return orders.size();
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        List<String> orderId=orderRepository.getAllOrderForpartner(partnerId);
        int max=0;
        for(String id:orderId) {
           int deliveryTime=orderRepository.getOrderById(id).get().getDeliveryTime();
           if(deliveryTime>max){
               max=deliveryTime;
           }
        }
        return TimeUtils.convertTime(max);
    }

    public void deletePartnerById(String partnerId) {
        List<String> orderId=orderRepository.getOrderForPartner(partnerId);
        orderRepository.deletePartner(partnerId);
        for (String id:orderId){
            orderRepository.unAssignedOrder(id);
        }
    }

    public void deleteOrderById(String orderId) {
        String partnerId=orderRepository.getPartnerForOrder(orderId);
        orderRepository.deleteOrder(orderId);
        if(Objects.nonNull(partnerId)) {
            List<String> ordersId = orderRepository.getOrderForPartner(orderId);
            ordersId.remove(orderId);
        }
    }
}
