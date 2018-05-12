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
@Table(name = "SSF_EMPRESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfEmpresa.findAll", query = "SELECT s FROM SsfEmpresa s")
    , @NamedQuery(name = "SsfEmpresa.findById", query = "SELECT s FROM SsfEmpresa s WHERE s.id = :id")
    , @NamedQuery(name = "SsfEmpresa.findByFechCreacion", query = "SELECT s FROM SsfEmpresa s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfEmpresa.findByEstado", query = "SELECT s FROM SsfEmpresa s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfEmpresa.findByNombre", query = "SELECT s FROM SsfEmpresa s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SsfEmpresa.findByTelefono", query = "SELECT s FROM SsfEmpresa s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "SsfEmpresa.findByDireccion", query = "SELECT s FROM SsfEmpresa s WHERE s.direccion = :direccion")})
public class SsfEmpresa implements Serializable {

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
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TELEFONO")
    private Long telefono;
    @Column(name = "DIRECCION")
    private String direccion;
    @OneToMany(mappedBy = "idEmpresa")
    private List<SsfEvaluacion> ssfEvaluacionList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<SsfCapacitacionempresa> ssfCapacitacionempresaList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<SsfUsuario> ssfUsuarioList;
    @OneToMany(mappedBy = "idEmpresa")
    private List<SsfAlumno> ssfAlumnoList;

    public SsfEmpresa() {
    }

    public SsfEmpresa(BigDecimal id) {
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

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<SsfEvaluacion> getSsfEvaluacionList() {
        return ssfEvaluacionList;
    }

    public void setSsfEvaluacionList(List<SsfEvaluacion> ssfEvaluacionList) {
        this.ssfEvaluacionList = ssfEvaluacionList;
    }

    @XmlTransient
    public List<SsfCapacitacionempresa> getSsfCapacitacionempresaList() {
        return ssfCapacitacionempresaList;
    }

    public void setSsfCapacitacionempresaList(List<SsfCapacitacionempresa> ssfCapacitacionempresaList) {
        this.ssfCapacitacionempresaList = ssfCapacitacionempresaList;
    }

    @XmlTransient
    public List<SsfUsuario> getSsfUsuarioList() {
        return ssfUsuarioList;
    }

    public void setSsfUsuarioList(List<SsfUsuario> ssfUsuarioList) {
        this.ssfUsuarioList = ssfUsuarioList;
    }

    @XmlTransient
    public List<SsfAlumno> getSsfAlumnoList() {
        return ssfAlumnoList;
    }

    public void setSsfAlumnoList(List<SsfAlumno> ssfAlumnoList) {
        this.ssfAlumnoList = ssfAlumnoList;
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
        if (!(object instanceof SsfEmpresa)) {
            return false;
        }
        SsfEmpresa other = (SsfEmpresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfEmpresa[ id=" + id + " ]";
    }
    
}
