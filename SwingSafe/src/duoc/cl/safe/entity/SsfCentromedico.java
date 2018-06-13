/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nacho
 */
@Entity
@Table(name = "SSF_CENTROMEDICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfCentromedico.findAll", query = "SELECT s FROM SsfCentromedico s")
    , @NamedQuery(name = "SsfCentromedico.findById", query = "SELECT s FROM SsfCentromedico s WHERE s.id = :id")
    , @NamedQuery(name = "SsfCentromedico.findByFechCreacion", query = "SELECT s FROM SsfCentromedico s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfCentromedico.findByEstado", query = "SELECT s FROM SsfCentromedico s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfCentromedico.findByNombre", query = "SELECT s FROM SsfCentromedico s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SsfCentromedico.findByDireccion", query = "SELECT s FROM SsfCentromedico s WHERE s.direccion = :direccion")})
public class SsfCentromedico implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "FECH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechCreacion;
    @Column(name = "ESTADO")
    private Short estado;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DIRECCION")
    private String direccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCentromedico")
    private List<SsfMedico> ssfMedicoList;

    public SsfCentromedico() {
    }

    public SsfCentromedico(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFechCreacion() {
        return fechCreacion;
    }

    public void setFechCreacion(Date fechCreacion) {
        this.fechCreacion = fechCreacion;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<SsfMedico> getSsfMedicoList() {
        return ssfMedicoList;
    }

    public void setSsfMedicoList(List<SsfMedico> ssfMedicoList) {
        this.ssfMedicoList = ssfMedicoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SsfCentromedico)) {
            return false;
        }
        SsfCentromedico other = (SsfCentromedico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfCentromedico[ id=" + id + " ]";
    }

}
