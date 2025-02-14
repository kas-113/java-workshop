package models;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
