package com.example.data.user.dto;

import lombok.Value;

import java.io.Serializable;

@Value
public class ChangeUsernameDTO implements Serializable {
    String oldName;
    String newName;
}
