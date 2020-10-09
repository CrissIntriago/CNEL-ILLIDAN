package com.example.Model;

public class Persona {
    private String nombre;
    private String apellido;
    private Integer edad;
    private String telefono;
    private String email;
    private String password;
    private String primerNombrePrimerApellido;
    private String fechaNacimiento;
    private Boolean masculino;
    private String ciudad;
    private String identificacion;
    private String user;
    private String rol;

    public Persona() {
    }

    public Persona(String nombre, String apellido, Integer edad, String telefono,
                   String email, String password, String fechaNacimiento, Boolean masculino, String ciudad, String identificacion, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.masculino = masculino;
        this.ciudad = ciudad;
        this.identificacion = identificacion;
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPrimerNombrePrimerApellido() {
        if (this.nombre != null && this.apellido != null) {
            String[] nombre = this.nombre.split(" ");
            String[] apellido = this.apellido.split(" ");
            this.primerNombrePrimerApellido = nombre[0] + " " + apellido[0];
        }
        return primerNombrePrimerApellido;
    }

    public void setPrimerNombrePrimerApellido(String primerNombrePrimerApellido) {
        this.primerNombrePrimerApellido = primerNombrePrimerApellido;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getMasculino() {
        return masculino;
    }

    public void setMasculino(Boolean masculino) {
        this.masculino = masculino;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}
