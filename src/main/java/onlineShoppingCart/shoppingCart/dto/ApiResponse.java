package onlineShoppingCart.shoppingCart.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse {
    private String message;
    private LocalDateTime timeStamp;

    public ApiResponse(String message) {
        super();
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}