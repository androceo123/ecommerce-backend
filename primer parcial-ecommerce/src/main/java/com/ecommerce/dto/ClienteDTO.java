package com.ecommerce.dto;

import com.ecommerce.model.Cliente;

public class ClienteDTO {
    public Long id;
    public String nombre;
    public String apellido;
    public String cedula;
    public String email;

    public ClienteDTO(Cliente c) {
        this.id = c.getIdCliente();
        this.nombre = c.getNombre();
        this.apellido = c.getApellido();
        this.cedula = c.getCedula();
        this.email = c.getEmail();
    }
}
