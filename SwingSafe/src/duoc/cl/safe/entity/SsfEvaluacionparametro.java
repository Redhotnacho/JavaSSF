/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nacho
 */
@Entity
@Table(name = "SSF_EVALUACIONPARAMETRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfEvaluacionparametro.findAll", query = "SELECT s FROM SsfEvaluacionparametro s")
    , @NamedQuery(name = "SsfEvaluacionparametro.findById", query = "SELECT s FROM SsfEvaluacionparametro s WHERE s.id = :id")
    , @NamedQuery(name = "SsfEvaluacionparametro.findByFechCreacion", query = "SELECT s FROM SsfEvaluacionparametro s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfEvaluacionparametro.findByEstado", query = "SELECT s FROM SsfEvaluacionparametro s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfEvaluacionparametro.findByAprueba", query = "SELECT s FROM SsfEvaluacionparametro s WHERE s.aprueba = :aprueba")
    , @NamedQuery(name = "SsfEvaluacionparametro.findByObservacion", query = "SELECT s FROM SsfEvaluacionparametro s WHERE s.observacion = :observacion")})
public class SsfEvaluacionparametro implements Serializable {

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
    @Column(name = "APRUEBA")
    private Short aprueba;
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "ID_EVALUACION", referencedColumnName = "ID")
    @ManyToOne
    private SsfEvaluacion idEvaluacion;
    @JoinColumn(name = "ID_PARAMETRO", referencedColumnName = "ID")
    @ManyToOne
    private SsfParametro idParametro;

    public SsfEvaluacionparametro() {
    }

    public SsfEvaluacionparametro(BigDecimal id) {
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

    public Short getAprueba() {
        return aprueba;
    }

    public void setAprueba(Short aprueba) {
        this.aprueba = aprueba;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public SsfEvaluacion getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(SsfEvaluacion idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public SsfParametro getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(SsfParametro idParametro) {
        this.idParametro = idParametro;
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
        if (!(object instanceof SsfEvaluacionparametro)) {
            return false;
        }
        SsfEvaluacionparametro other = (SsfEvaluacionparametro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfEvaluacionparametro[ id=" + id + " ]";
    }
    
}
