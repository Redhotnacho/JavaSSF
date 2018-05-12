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
@Table(name = "SSF_ESTADOCAPAEMPRESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfEstadocapaempresa.findAll", query = "SELECT s FROM SsfEstadocapaempresa s")
    , @NamedQuery(name = "SsfEstadocapaempresa.findById", query = "SELECT s FROM SsfEstadocapaempresa s WHERE s.id = :id")
    , @NamedQuery(name = "SsfEstadocapaempresa.findByFechCreacion", query = "SELECT s FROM SsfEstadocapaempresa s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfEstadocapaempresa.findByEstado", query = "SELECT s FROM SsfEstadocapaempresa s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfEstadocapaempresa.findByEstadocapaemp", query = "SELECT s FROM SsfEstadocapaempresa s WHERE s.estadocapaemp = :estadocapaemp")
    , @NamedQuery(name = "SsfEstadocapaempresa.findByDescripcion", query = "SELECT s FROM SsfEstadocapaempresa s WHERE s.descripcion = :descripcion")})
public class SsfEstadocapaempresa implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "FECH_CREACION", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechCreacion;
    @Column(name = "ESTADO", insertable = false)
    private Short estado;
    @Column(name = "ESTADOCAPAEMP")
    private String estadocapaemp;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idEstadocapacitacion")
    private List<SsfCapacitacionempresa> ssfCapacitacionempresaList;

    public SsfEstadocapaempresa() {
    }

    public SsfEstadocapaempresa(BigDecimal id) {
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

    public String getEstadocapaemp() {
        return estadocapaemp;
    }

    public void setEstadocapaemp(String estadocapaemp) {
        this.estadocapaemp = estadocapaemp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SsfCapacitacionempresa> getSsfCapacitacionempresaList() {
        return ssfCapacitacionempresaList;
    }

    public void setSsfCapacitacionempresaList(List<SsfCapacitacionempresa> ssfCapacitacionempresaList) {
        this.ssfCapacitacionempresaList = ssfCapacitacionempresaList;
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
        if (!(object instanceof SsfEstadocapaempresa)) {
            return false;
        }
        SsfEstadocapaempresa other = (SsfEstadocapaempresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfEstadocapaempresa[ id=" + id + " ]";
    }
    
}
