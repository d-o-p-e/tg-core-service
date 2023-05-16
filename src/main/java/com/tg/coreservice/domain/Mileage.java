package com.tg.coreservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Mileage {

    @Id
    private Long user_id;

    private Long mileage;

    public Mileage(Long post_id, Long mileage) {
        this.user_id = post_id;
        this.mileage = mileage;
    }

    public void decrement() {
        mileage--;
    }
}
