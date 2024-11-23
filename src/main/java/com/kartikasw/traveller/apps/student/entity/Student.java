package com.kartikasw.traveller.apps.student.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
    public static final String OBJECT_NAME = "Student";

    public static final String NAME_FIELD = "Name";

    public static final String ADDRESS_FIELD = "Address";

    private String name;

    private String address;
}
