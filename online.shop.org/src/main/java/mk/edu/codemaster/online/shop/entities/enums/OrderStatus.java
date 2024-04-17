package mk.edu.codemaster.online.shop.entities.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PENDING("Pending"), TRANSITING("Transiting"), FINISHED("Finished"), CANCELLED("Cancelled");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

}
