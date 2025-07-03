package com.bd.jpa.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
@Table(name = "tbl_cliente")
public class TblCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcliente")
    private Integer idcliente;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 15, message = "El DNI no puede tener más de 15 caracteres")
    private String dni;

    @NotBlank(message = "El sexo es obligatorio")
    private String sexo;

    private String telf;

    private String nacionalidad;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Correo inválido")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "Mínimo 6 caracteres")
    private String password;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;

    public TblCliente() {
    }

    public TblCliente(Integer idcliente, String nombre, String apellido, String dni, String sexo,
                      String telf, String nacionalidad, String email, String password, String rol) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.sexo = sexo;
        this.telf = telf;
        this.nacionalidad = nacionalidad;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Transient
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
