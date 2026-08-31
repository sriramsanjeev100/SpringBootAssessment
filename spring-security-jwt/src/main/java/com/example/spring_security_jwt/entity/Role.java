package com.example.spring_security_jwt.entity;

import jakarta.persistence.*;

@Entity
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public Role()
    {

    }

    public Role(RoleEnum name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public RoleEnum getName()
    {
        return name;
    }

    public void setName(RoleEnum name)
    {
        this.name = name;
    }
}