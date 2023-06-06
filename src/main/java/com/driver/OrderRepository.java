package com.driver;

import java.util.*;

public class OrderRepository {


   private Map<String,Order> orderMap=new HashMap<>();

    private Map<String,DeliveryPartner> partnerMap=new HashMap<>();

    private Map<String, String> partnerPair=new HashMap<>();//assign the order for particular partner
              //order ->partner
    private Map<String, ArrayList<String>> partnerOrderPair=new HashMap<>();

    public void addOrder(Order order) {
        orderMap.put(order.getId(),order);
    }

    public void addPartner(DeliveryPartner partner) {
    partnerMap.put(partner.getId(),partner);
    }



    public Optional<Order> getOrderById(String orderId) {
        if(orderMap.containsKey(orderId)){
            return Optional.of(orderMap.get(orderId));
        }
        return Optional.empty();
    }

    public Optional<DeliveryPartner> getPartnerById(String partnerId) {
        if(partnerMap.containsKey(partnerId)){
            return Optional.of(partnerMap.get(partnerId));
        }
        return Optional.empty();
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        ArrayList<String> orders=partnerOrderPair.getOrDefault(partnerId,new ArrayList<>());
        orders.add(orderId);

        partnerOrderPair.put(partnerId,orders);
    }

    public List<String> getOrderForPartner(String partnerId) {
        return partnerOrderPair.getOrDefault(partnerId,new ArrayList<>());
    }

    public List<String> getOrderByPartnerId(String partnerId) {
        return partnerOrderPair.getOrDefault(partnerId,new ArrayList<>());
    }

    public List<String> getAllOrder() {
        return new ArrayList<>(orderMap.keySet());
    }

    public List<String> getAssignedOrder() {
        return new ArrayList<>(partnerPair.keySet());

    }

    public void deletePartner(String partnerId) {
        partnerMap.remove(partnerId);
        partnerOrderPair.remove(partnerId);
    }

    public void deleteOrder(String id) {
        orderMap.remove(id);
        partnerPair.remove(id);
    }

    public void unAssignedOrder(String id) {
        partnerPair.remove(id);
    }

    public String getPartnerForOrder(String orderId) {
        return  partnerPair.get(orderId);
    }

    public List<String> getAllOrderForPartner(String partnerId) {
        return partnerOrderPair.getOrDefault(partnerId,new ArrayList<>());
    }
}
