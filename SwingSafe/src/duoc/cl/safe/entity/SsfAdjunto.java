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
@Table(name = "SSF_ADJUNTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfAdjunto.findAll", query = "SELECT s FROM SsfAdjunto s")
    , @NamedQuery(name = "SsfAdjunto.findById", query = "SELECT s FROM SsfAdjunto s WHERE s.id = :id")
    , @NamedQuery(name = "SsfAdjunto.findByFechCreacion", query = "SELECT s FROM SsfAdjunto s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfAdjunto.findByEstado", query = "SELECT s FROM SsfAdjunto s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfAdjunto.findByAdjunto", query = "SELECT s FROM SsfAdjunto s WHERE s.adjunto = :adjunto")
    , @NamedQuery(name = "SsfAdjunto.findByUrl", query = "SELECT s FROM SsfAdjunto s WHERE s.url = :url")})
public class SsfAdjunto implements Serializable {

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
    @Column(name = "ADJUNTO")
    private String adjunto;
    @Column(name = "URL")
    private String url;
    @JoinColumn(name = "ID_ATENCIONMEDICA", referencedColumnName = "ID")
    @ManyToOne
    private SsfAtencionmedica idAtencionmedica;

    public SsfAdjunto() {
    }

    public SsfAdjunto(BigDecimal id) {
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

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SsfAtencionmedica getIdAtencionmedica() {
        return idAtencionmedica;
    }

    public void setIdAtencionmedica(SsfAtencionmedica idAtencionmedica) {
        this.idAtencionmedica = idAtencionmedica;
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
        if (!(object instanceof SsfAdjunto)) {
            return false;
        }
        SsfAdjunto other = (SsfAdjunto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfAdjunto[ id=" + id + " ]";
    }

}
