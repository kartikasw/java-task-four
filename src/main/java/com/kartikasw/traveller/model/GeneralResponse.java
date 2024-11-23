package com.kartikasw.traveller.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralResponse<T, U> {
    private T metadata;
    private U data;
}
