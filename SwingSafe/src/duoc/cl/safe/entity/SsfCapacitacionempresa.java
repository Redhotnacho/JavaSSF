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
@Table(name = "SSF_CAPACITACIONEMPRESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfCapacitacionempresa.findAll", query = "SELECT s FROM SsfCapacitacionempresa s")
    , @NamedQuery(name = "SsfCapacitacionempresa.findById", query = "SELECT s FROM SsfCapacitacionempresa s WHERE s.id = :id")
    , @NamedQuery(name = "SsfCapacitacionempresa.findByFechCreacion", query = "SELECT s FROM SsfCapacitacionempresa s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfCapacitacionempresa.findByEstado", query = "SELECT s FROM SsfCapacitacionempresa s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfCapacitacionempresa.findByCantidadAlumnos", query = "SELECT s FROM SsfCapacitacionempresa s WHERE s.cantidadAlumnos = :cantidadAlumnos")})
public class SsfCapacitacionempresa implements Serializable {

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
    @Column(name = "CANTIDAD_ALUMNOS")
    private Long cantidadAlumnos;
    @OneToMany(mappedBy = "idCapaempresa")
    private List<SsfAlumnocapaempresa> ssfAlumnocapaempresaList;
    @JoinColumn(name = "ID_CAPACITACION", referencedColumnName = "ID")
    @ManyToOne
    private SsfCapacitacion idCapacitacion;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private SsfEmpresa idEmpresa;
    @JoinColumn(name = "ID_ESTADOCAPACITACION", referencedColumnName = "ID")
    @ManyToOne
    private SsfEstadocapaempresa idEstadocapacitacion;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private SsfUsuario idUsuario;
    @OneToMany(mappedBy = "idCapaempresa")
    private List<SsfCapacitaciondia> ssfCapacitaciondiaList;

    public SsfCapacitacionempresa() {
    }

    public SsfCapacitacionempresa(BigDecimal id) {
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

    public Long getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(Long cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    @XmlTransient
    public List<SsfAlumnocapaempresa> getSsfAlumnocapaempresaList() {
        return ssfAlumnocapaempresaList;
    }

    public void setSsfAlumnocapaempresaList(List<SsfAlumnocapaempresa> ssfAlumnocapaempresaList) {
        this.ssfAlumnocapaempresaList = ssfAlumnocapaempresaList;
    }

    public SsfCapacitacion getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(SsfCapacitacion idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public SsfEmpresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(SsfEmpresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public SsfEstadocapaempresa getIdEstadocapacitacion() {
        return idEstadocapacitacion;
    }

    public void setIdEstadocapacitacion(SsfEstadocapaempresa idEstadocapacitacion) {
        this.idEstadocapacitacion = idEstadocapacitacion;
    }

    public SsfUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SsfUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<SsfCapacitaciondia> getSsfCapacitaciondiaList() {
        return ssfCapacitaciondiaList;
    }

    public void setSsfCapacitaciondiaList(List<SsfCapacitaciondia> ssfCapacitaciondiaList) {
        this.ssfCapacitaciondiaList = ssfCapacitaciondiaList;
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
        if (!(object instanceof SsfCapacitacionempresa)) {
            return false;
        }
        SsfCapacitacionempresa other = (SsfCapacitacionempresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfCapacitacionempresa[ id=" + id + " ]";
    }
    
}
