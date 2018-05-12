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
@Table(name = "SSF_CAPACITACIONTIPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfCapacitaciontipo.findAll", query = "SELECT s FROM SsfCapacitaciontipo s")
    , @NamedQuery(name = "SsfCapacitaciontipo.findById", query = "SELECT s FROM SsfCapacitaciontipo s WHERE s.id = :id")
    , @NamedQuery(name = "SsfCapacitaciontipo.findByFechCreacion", query = "SELECT s FROM SsfCapacitaciontipo s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfCapacitaciontipo.findByEstado", query = "SELECT s FROM SsfCapacitaciontipo s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfCapacitaciontipo.findByTipo", query = "SELECT s FROM SsfCapacitaciontipo s WHERE s.tipo = :tipo")
    , @NamedQuery(name = "SsfCapacitaciontipo.findByDescripcion", query = "SELECT s FROM SsfCapacitaciontipo s WHERE s.descripcion = :descripcion")})
public class SsfCapacitaciontipo implements Serializable {

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
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idCapacitaciontipo")
    private List<SsfCapacitacion> ssfCapacitacionList;

    public SsfCapacitaciontipo() {
    }

    public SsfCapacitaciontipo(BigDecimal id) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SsfCapacitacion> getSsfCapacitacionList() {
        return ssfCapacitacionList;
    }

    public void setSsfCapacitacionList(List<SsfCapacitacion> ssfCapacitacionList) {
        this.ssfCapacitacionList = ssfCapacitacionList;
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
        if (!(object instanceof SsfCapacitaciontipo)) {
            return false;
        }
        SsfCapacitaciontipo other = (SsfCapacitaciontipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfCapacitaciontipo[ id=" + id + " ]";
    }
    
}
