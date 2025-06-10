package py.edu.fiuni.taller.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import jakarta.json.bind.annotation.JsonbTransient;

@Entity
@Table(name = "detalles_servicio")
public class DetalleServicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion_trabajo")
    private String descripcionTrabajo;

    private double costo;
    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @ManyToMany
    @JoinTable(name = "detalle_repuesto",
        joinColumns = @JoinColumn(name = "detalle_id"),
        inverseJoinColumns = @JoinColumn(name = "repuesto_id"))
    private List<Repuesto> repuestos;

    @ManyToMany
    @JoinTable(name = "detalle_mecanico",
        joinColumns = @JoinColumn(name = "detalle_id"),
        inverseJoinColumns = @JoinColumn(name = "mecanico_id"))
    private List<Mecanico> mecanicos;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo(String descripcionTrabajo) {
        this.descripcionTrabajo = descripcionTrabajo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public List<Repuesto> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<Repuesto> repuestos) {
        this.repuestos = repuestos;
    }

    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(List<Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }
}

