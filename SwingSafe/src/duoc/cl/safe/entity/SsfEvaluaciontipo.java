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
@Table(name = "SSF_EVALUACIONTIPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfEvaluaciontipo.findAll", query = "SELECT s FROM SsfEvaluaciontipo s")
    , @NamedQuery(name = "SsfEvaluaciontipo.findById", query = "SELECT s FROM SsfEvaluaciontipo s WHERE s.id = :id")
    , @NamedQuery(name = "SsfEvaluaciontipo.findByFechCreacion", query = "SELECT s FROM SsfEvaluaciontipo s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfEvaluaciontipo.findByEstado", query = "SELECT s FROM SsfEvaluaciontipo s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfEvaluaciontipo.findByTopo", query = "SELECT s FROM SsfEvaluaciontipo s WHERE s.topo = :topo")
    , @NamedQuery(name = "SsfEvaluaciontipo.findByDescripcion", query = "SELECT s FROM SsfEvaluaciontipo s WHERE s.descripcion = :descripcion")})
public class SsfEvaluaciontipo implements Serializable {

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
    @Column(name = "TOPO")
    private String topo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idEvaluaciontipo")
    private List<SsfEvaluacion> ssfEvaluacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvaluaciontipo")
    private List<SsfParametro> ssfParametroList;

    public SsfEvaluaciontipo() {
    }

    public SsfEvaluaciontipo(BigDecimal id) {
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

    public String getTopo() {
        return topo;
    }

    public void setTopo(String topo) {
        this.topo = topo;
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

    @XmlTransient
    public List<SsfParametro> getSsfParametroList() {
        return ssfParametroList;
    }

    public void setSsfParametroList(List<SsfParametro> ssfParametroList) {
        this.ssfParametroList = ssfParametroList;
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
        if (!(object instanceof SsfEvaluaciontipo)) {
            return false;
        }
        SsfEvaluaciontipo other = (SsfEvaluaciontipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfEvaluaciontipo[ id=" + id + " ]";
    }
    
}
