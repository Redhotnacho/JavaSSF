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
@Table(name = "SSF_CAPACITACIONDIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfCapacitaciondia.findAll", query = "SELECT s FROM SsfCapacitaciondia s")
    , @NamedQuery(name = "SsfCapacitaciondia.findById", query = "SELECT s FROM SsfCapacitaciondia s WHERE s.id = :id")
    , @NamedQuery(name = "SsfCapacitaciondia.findByFechCreacion", query = "SELECT s FROM SsfCapacitaciondia s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfCapacitaciondia.findByEstado", query = "SELECT s FROM SsfCapacitaciondia s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfCapacitaciondia.findByDia", query = "SELECT s FROM SsfCapacitaciondia s WHERE s.dia = :dia")
    , @NamedQuery(name = "SsfCapacitaciondia.findByCantidadPresentes", query = "SELECT s FROM SsfCapacitaciondia s WHERE s.cantidadPresentes = :cantidadPresentes")})
public class SsfCapacitaciondia implements Serializable {

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
    @Column(name = "DIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dia;
    @Column(name = "CANTIDAD_PRESENTES")
    private Long cantidadPresentes;
    @JoinColumn(name = "ID_CAPAEMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private SsfCapacitacionempresa idCapaempresa;
    @OneToMany(mappedBy = "idCapacitaciondia")
    private List<SsfAsistencia> ssfAsistenciaList;

    public SsfCapacitaciondia() {
    }

    public SsfCapacitaciondia(BigDecimal id) {
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

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Long getCantidadPresentes() {
        return cantidadPresentes;
    }

    public void setCantidadPresentes(Long cantidadPresentes) {
        this.cantidadPresentes = cantidadPresentes;
    }

    public SsfCapacitacionempresa getIdCapaempresa() {
        return idCapaempresa;
    }

    public void setIdCapaempresa(SsfCapacitacionempresa idCapaempresa) {
        this.idCapaempresa = idCapaempresa;
    }

    @XmlTransient
    public List<SsfAsistencia> getSsfAsistenciaList() {
        return ssfAsistenciaList;
    }

    public void setSsfAsistenciaList(List<SsfAsistencia> ssfAsistenciaList) {
        this.ssfAsistenciaList = ssfAsistenciaList;
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
        if (!(object instanceof SsfCapacitaciondia)) {
            return false;
        }
        SsfCapacitaciondia other = (SsfCapacitaciondia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfCapacitaciondia[ id=" + id + " ]";
    }
    
}
