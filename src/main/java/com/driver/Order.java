package com.driver;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.deliveryTime = TimeUtils.convertTime(deliveryTime);
        this.id = id;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }
//  public Order(String id){
//        this.id=id;
//  }
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setDeliveryTime(int deliveryTime) {
//        this.deliveryTime = deliveryTime;
//    }
//    public void setDeliveryTime(String deliveryTime) {
//        this.deliveryTime = convertDeliveryTime(deliveryTime);
//    }
//
//    private int convertDeliveryTime(String deliveryTime) {
//        String[] time = deliveryTime.split(":");
//        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public int getDeliveryTime() {
//        return deliveryTime;
//    }


//    private String convertDeliveryTime(int deliveryTime){
//        int HH = deliveryTime / 60;
//        int MM = deliveryTime % 60;
//        String n1 = String.valueOf(HH);
//        String n2 = String.valueOf(MM);
//        return n1 + ":" + n2;
//    }
  //  public String getDeliveryTimeString() {
//        return convertDeliveryTime(this.deliveryTime);
//    }
}