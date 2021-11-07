package fr.dademo.reader.http.repository.exception;

import lombok.Getter;
import okhttp3.Response;

import javax.annotation.Nonnull;

@SuppressWarnings("java:S1948")
public abstract class BaseHttpQueryException extends RuntimeException {

    private static final long serialVersionUID = 5481320755074086222L;
    @Nonnull
    @Getter
    private final Response queryResponse;

    protected BaseHttpQueryException(@Nonnull Response queryResponse) {
        super();
        this.queryResponse = queryResponse;
    }

    protected BaseHttpQueryException(String message, @Nonnull Response queryResponse) {
        super(message);
        this.queryResponse = queryResponse;
    }

    protected BaseHttpQueryException(String message, BaseHttpQueryException cause) {
        super(message, cause);
        this.queryResponse = cause.getQueryResponse();
    }

    protected BaseHttpQueryException(String message, Throwable cause, @Nonnull Response queryResponse) {
        super(message, cause);
        this.queryResponse = queryResponse;
    }

    protected BaseHttpQueryException(Throwable cause, @Nonnull Response queryResponse) {
        super(cause);
        this.queryResponse = queryResponse;
    }
}
