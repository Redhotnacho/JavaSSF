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
import javax.persistence.OneToOne;
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
@Table(name = "SSF_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfUsuario.findAll", query = "SELECT s FROM SsfUsuario s")
    , @NamedQuery(name = "SsfUsuario.findById", query = "SELECT s FROM SsfUsuario s WHERE s.id = :id")
    , @NamedQuery(name = "SsfUsuario.findByFechCreacion", query = "SELECT s FROM SsfUsuario s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfUsuario.findByEstado", query = "SELECT s FROM SsfUsuario s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfUsuario.findByUsername", query = "SELECT s FROM SsfUsuario s WHERE s.username = :username")
    , @NamedQuery(name = "SsfUsuario.findByContrasena", query = "SELECT s FROM SsfUsuario s WHERE s.contrasena = :contrasena")})
public class SsfUsuario implements Serializable {

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
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "CONTRASENA")
    private String contrasena;
    @OneToMany(mappedBy = "idUsuario")
    private List<SsfCapacitacionempresa> ssfCapacitacionempresaList;
    @OneToMany(mappedBy = "idUsuario")
    private List<SsfAtencionmedica> ssfAtencionmedicaList;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private SsfEmpresa idEmpresa;
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SsfPerfil idPerfil;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private SsfPersona idPersona;
    @OneToMany(mappedBy = "idUsuario")
    private List<SsfFichamedica> ssfFichamedicaList;

    public SsfUsuario() {
    }

    public SsfUsuario(BigDecimal id) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @XmlTransient
    public List<SsfCapacitacionempresa> getSsfCapacitacionempresaList() {
        return ssfCapacitacionempresaList;
    }

    public void setSsfCapacitacionempresaList(List<SsfCapacitacionempresa> ssfCapacitacionempresaList) {
        this.ssfCapacitacionempresaList = ssfCapacitacionempresaList;
    }

    @XmlTransient
    public List<SsfAtencionmedica> getSsfAtencionmedicaList() {
        return ssfAtencionmedicaList;
    }

    public void setSsfAtencionmedicaList(List<SsfAtencionmedica> ssfAtencionmedicaList) {
        this.ssfAtencionmedicaList = ssfAtencionmedicaList;
    }

    public SsfEmpresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(SsfEmpresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public SsfPerfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(SsfPerfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    public SsfPersona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(SsfPersona idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public List<SsfFichamedica> getSsfFichamedicaList() {
        return ssfFichamedicaList;
    }

    public void setSsfFichamedicaList(List<SsfFichamedica> ssfFichamedicaList) {
        this.ssfFichamedicaList = ssfFichamedicaList;
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
        if (!(object instanceof SsfUsuario)) {
            return false;
        }
        SsfUsuario other = (SsfUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfUsuario[ id=" + id + " ]";
    }
    
}
