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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SSF_PARAMETRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfParametro.findAll", query = "SELECT s FROM SsfParametro s")
    , @NamedQuery(name = "SsfParametro.findById", query = "SELECT s FROM SsfParametro s WHERE s.id = :id")
    , @NamedQuery(name = "SsfParametro.findByFechCreacion", query = "SELECT s FROM SsfParametro s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfParametro.findByEstado", query = "SELECT s FROM SsfParametro s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfParametro.findByParametro", query = "SELECT s FROM SsfParametro s WHERE s.parametro = :parametro")
    , @NamedQuery(name = "SsfParametro.findByDescripcion", query = "SELECT s FROM SsfParametro s WHERE s.descripcion = :descripcion")})
public class SsfParametro implements Serializable {

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
    @Column(name = "PARAMETRO")
    private String parametro;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idParametro")
    private List<SsfEvaluacionparametro> ssfEvaluacionparametroList;
    @JoinColumn(name = "ID_EVALUACIONTIPO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SsfEvaluaciontipo idEvaluaciontipo;

    public SsfParametro() {
    }

    public SsfParametro(BigDecimal id) {
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

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SsfEvaluacionparametro> getSsfEvaluacionparametroList() {
        return ssfEvaluacionparametroList;
    }

    public void setSsfEvaluacionparametroList(List<SsfEvaluacionparametro> ssfEvaluacionparametroList) {
        this.ssfEvaluacionparametroList = ssfEvaluacionparametroList;
    }

    public SsfEvaluaciontipo getIdEvaluaciontipo() {
        return idEvaluaciontipo;
    }

    public void setIdEvaluaciontipo(SsfEvaluaciontipo idEvaluaciontipo) {
        this.idEvaluaciontipo = idEvaluaciontipo;
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
        if (!(object instanceof SsfParametro)) {
            return false;
        }
        SsfParametro other = (SsfParametro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfParametro[ id=" + id + " ]";
    }

}
