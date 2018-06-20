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
@Table(name = "SSF_ASISTENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfAsistencia.findAll", query = "SELECT s FROM SsfAsistencia s")
    , @NamedQuery(name = "SsfAsistencia.findById", query = "SELECT s FROM SsfAsistencia s WHERE s.id = :id")
    , @NamedQuery(name = "SsfAsistencia.findByFechCreacion", query = "SELECT s FROM SsfAsistencia s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfAsistencia.findByEstado", query = "SELECT s FROM SsfAsistencia s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfAsistencia.findByIdAlumcapaempresa", query = "SELECT s FROM SsfAsistencia s WHERE s.idAlumcapaempresa = :idAlumcapaempresa")
    , @NamedQuery(name = "SsfAsistencia.findByAsiste", query = "SELECT s FROM SsfAsistencia s WHERE s.asiste = :asiste")})
public class SsfAsistencia implements Serializable {

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
    @Column(name = "ID_ALUMCAPAEMPRESA")
    private BigInteger idAlumcapaempresa;
    @Column(name = "ASISTE")
    private Short asiste;
    @JoinColumn(name = "ID_CAPACITACIONDIA", referencedColumnName = "ID")
    @ManyToOne
    private SsfCapacitaciondia idCapacitaciondia;

    public SsfAsistencia() {
    }

    public SsfAsistencia(BigDecimal id) {
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

    public BigInteger getIdAlumcapaempresa() {
        return idAlumcapaempresa;
    }

    public void setIdAlumcapaempresa(BigInteger idAlumcapaempresa) {
        this.idAlumcapaempresa = idAlumcapaempresa;
    }

    public Short getAsiste() {
        return asiste;
    }

    public void setAsiste(Short asiste) {
        this.asiste = asiste;
    }

    public SsfCapacitaciondia getIdCapacitaciondia() {
        return idCapacitaciondia;
    }

    public void setIdCapacitaciondia(SsfCapacitaciondia idCapacitaciondia) {
        this.idCapacitaciondia = idCapacitaciondia;
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
        if (!(object instanceof SsfAsistencia)) {
            return false;
        }
        SsfAsistencia other = (SsfAsistencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfAsistencia[ id=" + id + " ]";
    }

}
