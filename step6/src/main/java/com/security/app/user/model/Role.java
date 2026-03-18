package com.security.app.user.model;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE,generator = "role_generator")
    @SequenceGenerator(name = "role_generator", initialValue = 3)
    @Column(name = "id")
    private int id;

    public Role(){

    }

    public Role(String s){
        this.roleName=s;
    }

    @Column(name = "role_name")
    private String roleName;

    // Getter and Setter

    public String getRoleName() {
        return roleName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }
}
