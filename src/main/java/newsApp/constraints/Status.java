package newsApp.constraints;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {

    SUCCESS(1000, "standard.success-operation"),
    ERROR(1001, "standard.error-operation");

    private final int code;
    private final String message;
}

