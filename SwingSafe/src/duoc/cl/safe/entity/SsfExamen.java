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
@Table(name = "SSF_EXAMEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfExamen.findAll", query = "SELECT s FROM SsfExamen s")
    , @NamedQuery(name = "SsfExamen.findById", query = "SELECT s FROM SsfExamen s WHERE s.id = :id")
    , @NamedQuery(name = "SsfExamen.findByFechCreacion", query = "SELECT s FROM SsfExamen s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfExamen.findByEstado", query = "SELECT s FROM SsfExamen s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfExamen.findByExamen", query = "SELECT s FROM SsfExamen s WHERE s.examen = :examen")
    , @NamedQuery(name = "SsfExamen.findByDescripcion", query = "SELECT s FROM SsfExamen s WHERE s.descripcion = :descripcion")})
public class SsfExamen implements Serializable {

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
    @Column(name = "EXAMEN")
    private String examen;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idExamen")
    private List<SsfFichamedica> ssfFichamedicaList;
    @JoinColumn(name = "ID_EXAMENTIPO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SsfExamentipo idExamentipo;

    public SsfExamen() {
    }

    public SsfExamen(BigDecimal id) {
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

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SsfFichamedica> getSsfFichamedicaList() {
        return ssfFichamedicaList;
    }

    public void setSsfFichamedicaList(List<SsfFichamedica> ssfFichamedicaList) {
        this.ssfFichamedicaList = ssfFichamedicaList;
    }

    public SsfExamentipo getIdExamentipo() {
        return idExamentipo;
    }

    public void setIdExamentipo(SsfExamentipo idExamentipo) {
        this.idExamentipo = idExamentipo;
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
        if (!(object instanceof SsfExamen)) {
            return false;
        }
        SsfExamen other = (SsfExamen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfExamen[ id=" + id + " ]";
    }

}
