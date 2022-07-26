package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class KpopForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String artist;
    private String manufacturer;
}
