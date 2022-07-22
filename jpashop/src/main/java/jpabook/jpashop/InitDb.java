package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Kpop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 종 주문 2개
 * * userA
 * 	 * JPA1 BOOK
 * 	 * JPA2 BOOK
 * * userB
 * 	 * SPRING1 BOOK
 * 	 * SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            System.out.println("Init1" + this.getClass());
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Kpop Kpop1 = createKpop("JPA1 BOOK", "1111", 10000, 100);
            em.persist(Kpop1);

            Kpop Kpop2 = createKpop("JPA2 BOOK", "1111", 20000, 100);
            em.persist(Kpop2);

            OrderItem orderItem1 = OrderItem.createOrderItem(Kpop1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(Kpop2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);

            Kpop Kpop1 = createKpop("동행 remastered LP CD", "김동률", 20000, 200);
            em.persist(Kpop1);

            Kpop Kpop2 = createKpop("꽃갈피", "아이유", 40000, 300);
            em.persist(Kpop2);

            OrderItem orderItem1 = OrderItem.createOrderItem(Kpop1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(Kpop2, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        private Kpop createKpop(String name, String artist, int price, int stockQuantity) {
            Kpop Kpop1 = new Kpop();
            Kpop1.setName(name);
            Kpop1.setArtist(artist);
            Kpop1.setPrice(price);
            Kpop1.setStockQuantity(stockQuantity);
            return Kpop1;
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }
    }
}

