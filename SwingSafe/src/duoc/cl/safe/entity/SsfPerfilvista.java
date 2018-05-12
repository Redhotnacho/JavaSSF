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
@Table(name = "SSF_PERFILVISTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfPerfilvista.findAll", query = "SELECT s FROM SsfPerfilvista s")
    , @NamedQuery(name = "SsfPerfilvista.findById", query = "SELECT s FROM SsfPerfilvista s WHERE s.id = :id")
    , @NamedQuery(name = "SsfPerfilvista.findByFechCreacion", query = "SELECT s FROM SsfPerfilvista s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfPerfilvista.findByEstado", query = "SELECT s FROM SsfPerfilvista s WHERE s.estado = :estado")})
public class SsfPerfilvista implements Serializable {

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
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID")
    @ManyToOne
    private SsfPerfil idPerfil;
    @JoinColumn(name = "ID_VISTA", referencedColumnName = "ID")
    @ManyToOne
    private SsfVista idVista;

    public SsfPerfilvista() {
    }

    public SsfPerfilvista(BigDecimal id) {
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

    public SsfPerfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(SsfPerfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    public SsfVista getIdVista() {
        return idVista;
    }

    public void setIdVista(SsfVista idVista) {
        this.idVista = idVista;
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
        if (!(object instanceof SsfPerfilvista)) {
            return false;
        }
        SsfPerfilvista other = (SsfPerfilvista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfPerfilvista[ id=" + id + " ]";
    }
    
}
