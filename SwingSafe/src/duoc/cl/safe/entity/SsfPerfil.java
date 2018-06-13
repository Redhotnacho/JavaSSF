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
@Table(name = "SSF_PERFIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfPerfil.findAll", query = "SELECT s FROM SsfPerfil s")
    , @NamedQuery(name = "SsfPerfil.findById", query = "SELECT s FROM SsfPerfil s WHERE s.id = :id")
    , @NamedQuery(name = "SsfPerfil.findByFechCreacion", query = "SELECT s FROM SsfPerfil s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfPerfil.findByEstado", query = "SELECT s FROM SsfPerfil s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfPerfil.findByPerfil", query = "SELECT s FROM SsfPerfil s WHERE s.perfil = :perfil")
    , @NamedQuery(name = "SsfPerfil.findByDescripcion", query = "SELECT s FROM SsfPerfil s WHERE s.descripcion = :descripcion")})
public class SsfPerfil implements Serializable {

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
    @Column(name = "PERFIL")
    private String perfil;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idPerfil")
    private List<SsfPerfilvista> ssfPerfilvistaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfil")
    private List<SsfUsuario> ssfUsuarioList;

    public SsfPerfil() {
    }

    public SsfPerfil(BigDecimal id) {
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SsfPerfilvista> getSsfPerfilvistaList() {
        return ssfPerfilvistaList;
    }

    public void setSsfPerfilvistaList(List<SsfPerfilvista> ssfPerfilvistaList) {
        this.ssfPerfilvistaList = ssfPerfilvistaList;
    }

    @XmlTransient
    public List<SsfUsuario> getSsfUsuarioList() {
        return ssfUsuarioList;
    }

    public void setSsfUsuarioList(List<SsfUsuario> ssfUsuarioList) {
        this.ssfUsuarioList = ssfUsuarioList;
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
        if (!(object instanceof SsfPerfil)) {
            return false;
        }
        SsfPerfil other = (SsfPerfil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfPerfil[ id=" + id + " ]";
    }

}
