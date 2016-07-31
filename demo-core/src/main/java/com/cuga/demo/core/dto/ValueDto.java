package com.cuga.demo.core.dto;

import java.io.Serializable;

public class ValueDto<T> implements Serializable {
    private T value;

    public ValueDto() {
        // Nothing here
    }

    public ValueDto(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public ValueDto<T> setValue(T value) {
        this.value = value;
        return this;
    }

}
