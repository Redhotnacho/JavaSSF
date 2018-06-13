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
@Table(name = "SSF_MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfMenu.findAll", query = "SELECT s FROM SsfMenu s")
    , @NamedQuery(name = "SsfMenu.findById", query = "SELECT s FROM SsfMenu s WHERE s.id = :id")
    , @NamedQuery(name = "SsfMenu.findByFechCreacion", query = "SELECT s FROM SsfMenu s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfMenu.findByEstado", query = "SELECT s FROM SsfMenu s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfMenu.findByNombre", query = "SELECT s FROM SsfMenu s WHERE s.nombre = :nombre")})
public class SsfMenu implements Serializable {

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
    @OneToMany(mappedBy = "idMenu")
    private List<SsfVista> ssfVistaList;

    public SsfMenu() {
    }

    public SsfMenu(BigDecimal id) {
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

    @XmlTransient
    public List<SsfVista> getSsfVistaList() {
        return ssfVistaList;
    }

    public void setSsfVistaList(List<SsfVista> ssfVistaList) {
        this.ssfVistaList = ssfVistaList;
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
        if (!(object instanceof SsfMenu)) {
            return false;
        }
        SsfMenu other = (SsfMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfMenu[ id=" + id + " ]";
    }

}
