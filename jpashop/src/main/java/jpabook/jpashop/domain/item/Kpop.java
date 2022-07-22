package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("K")
@Getter @Setter
public
class Kpop extends Item {

    private String artist;
    private String manufacturer;


}
