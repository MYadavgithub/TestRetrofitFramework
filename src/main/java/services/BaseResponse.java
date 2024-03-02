package services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int code) {
        this.statusCode = code;
    }
}
