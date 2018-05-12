/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "SSF_ALUMNOCAPAEMPRESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfAlumnocapaempresa.findAll", query = "SELECT s FROM SsfAlumnocapaempresa s")
    , @NamedQuery(name = "SsfAlumnocapaempresa.findById", query = "SELECT s FROM SsfAlumnocapaempresa s WHERE s.id = :id")
    , @NamedQuery(name = "SsfAlumnocapaempresa.findByFechCreacion", query = "SELECT s FROM SsfAlumnocapaempresa s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfAlumnocapaempresa.findByEstado", query = "SELECT s FROM SsfAlumnocapaempresa s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfAlumnocapaempresa.findByAprobacion", query = "SELECT s FROM SsfAlumnocapaempresa s WHERE s.aprobacion = :aprobacion")})
public class SsfAlumnocapaempresa implements Serializable {

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
    @Column(name = "APROBACION")
    private BigInteger aprobacion;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID")
    @ManyToOne
    private SsfAlumno idAlumno;
    @JoinColumn(name = "ID_CAPAEMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private SsfCapacitacionempresa idCapaempresa;
    @JoinColumn(name = "ID_CERTIFICADO", referencedColumnName = "ID")
    @ManyToOne
    private SsfCertificado idCertificado;

    public SsfAlumnocapaempresa() {
    }

    public SsfAlumnocapaempresa(BigDecimal id) {
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

    public BigInteger getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(BigInteger aprobacion) {
        this.aprobacion = aprobacion;
    }

    public SsfAlumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(SsfAlumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public SsfCapacitacionempresa getIdCapaempresa() {
        return idCapaempresa;
    }

    public void setIdCapaempresa(SsfCapacitacionempresa idCapaempresa) {
        this.idCapaempresa = idCapaempresa;
    }

    public SsfCertificado getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(SsfCertificado idCertificado) {
        this.idCertificado = idCertificado;
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
        if (!(object instanceof SsfAlumnocapaempresa)) {
            return false;
        }
        SsfAlumnocapaempresa other = (SsfAlumnocapaempresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfAlumnocapaempresa[ id=" + id + " ]";
    }
    
}
