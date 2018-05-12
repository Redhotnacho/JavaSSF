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
@Table(name = "SSF_CERTIFICADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfCertificado.findAll", query = "SELECT s FROM SsfCertificado s")
    , @NamedQuery(name = "SsfCertificado.findById", query = "SELECT s FROM SsfCertificado s WHERE s.id = :id")
    , @NamedQuery(name = "SsfCertificado.findByFechCreacion", query = "SELECT s FROM SsfCertificado s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfCertificado.findByEstado", query = "SELECT s FROM SsfCertificado s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfCertificado.findByContenido", query = "SELECT s FROM SsfCertificado s WHERE s.contenido = :contenido")})
public class SsfCertificado implements Serializable {

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
    @Column(name = "CONTENIDO")
    private String contenido;
    @JoinColumn(name = "ID_CAPACITACION", referencedColumnName = "ID")
    @ManyToOne
    private SsfCapacitacion idCapacitacion;
    @OneToMany(mappedBy = "idCertificado")
    private List<SsfAlumnocapaempresa> ssfAlumnocapaempresaList;

    public SsfCertificado() {
    }

    public SsfCertificado(BigDecimal id) {
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public SsfCapacitacion getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(SsfCapacitacion idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    @XmlTransient
    public List<SsfAlumnocapaempresa> getSsfAlumnocapaempresaList() {
        return ssfAlumnocapaempresaList;
    }

    public void setSsfAlumnocapaempresaList(List<SsfAlumnocapaempresa> ssfAlumnocapaempresaList) {
        this.ssfAlumnocapaempresaList = ssfAlumnocapaempresaList;
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
        if (!(object instanceof SsfCertificado)) {
            return false;
        }
        SsfCertificado other = (SsfCertificado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfCertificado[ id=" + id + " ]";
    }
    
}
