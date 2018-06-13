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
@Table(name = "SSF_EVALUACIONESTADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfEvaluacionestado.findAll", query = "SELECT s FROM SsfEvaluacionestado s")
    , @NamedQuery(name = "SsfEvaluacionestado.findById", query = "SELECT s FROM SsfEvaluacionestado s WHERE s.id = :id")
    , @NamedQuery(name = "SsfEvaluacionestado.findByFechCreacion", query = "SELECT s FROM SsfEvaluacionestado s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfEvaluacionestado.findByEstado", query = "SELECT s FROM SsfEvaluacionestado s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfEvaluacionestado.findByEstadoeval", query = "SELECT s FROM SsfEvaluacionestado s WHERE s.estadoeval = :estadoeval")
    , @NamedQuery(name = "SsfEvaluacionestado.findByDescripcion", query = "SELECT s FROM SsfEvaluacionestado s WHERE s.descripcion = :descripcion")})
public class SsfEvaluacionestado implements Serializable {

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
    @Column(name = "ESTADOEVAL")
    private String estadoeval;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idEvaluacionestado")
    private List<SsfEvaluacion> ssfEvaluacionList;

    public SsfEvaluacionestado() {
    }

    public SsfEvaluacionestado(BigDecimal id) {
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

    public String getEstadoeval() {
        return estadoeval;
    }

    public void setEstadoeval(String estadoeval) {
        this.estadoeval = estadoeval;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SsfEvaluacion> getSsfEvaluacionList() {
        return ssfEvaluacionList;
    }

    public void setSsfEvaluacionList(List<SsfEvaluacion> ssfEvaluacionList) {
        this.ssfEvaluacionList = ssfEvaluacionList;
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
        if (!(object instanceof SsfEvaluacionestado)) {
            return false;
        }
        SsfEvaluacionestado other = (SsfEvaluacionestado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfEvaluacionestado[ id=" + id + " ]";
    }

}
