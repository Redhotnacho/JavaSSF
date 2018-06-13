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
@Table(name = "SSF_ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfAlumno.findAll", query = "SELECT s FROM SsfAlumno s")
    , @NamedQuery(name = "SsfAlumno.findById", query = "SELECT s FROM SsfAlumno s WHERE s.id = :id")
    , @NamedQuery(name = "SsfAlumno.findByFechCreacion", query = "SELECT s FROM SsfAlumno s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfAlumno.findByEstado", query = "SELECT s FROM SsfAlumno s WHERE s.estado = :estado")})
public class SsfAlumno implements Serializable {

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
    @OneToMany(mappedBy = "idAlumno")
    private List<SsfAlumnocapaempresa> ssfAlumnocapaempresaList;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private SsfEmpresa idEmpresa;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    @OneToOne
    private SsfPersona idPersona;

    public SsfAlumno() {
    }

    public SsfAlumno(BigDecimal id) {
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

    @XmlTransient
    public List<SsfAlumnocapaempresa> getSsfAlumnocapaempresaList() {
        return ssfAlumnocapaempresaList;
    }

    public void setSsfAlumnocapaempresaList(List<SsfAlumnocapaempresa> ssfAlumnocapaempresaList) {
        this.ssfAlumnocapaempresaList = ssfAlumnocapaempresaList;
    }

    public SsfEmpresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(SsfEmpresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public SsfPersona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(SsfPersona idPersona) {
        this.idPersona = idPersona;
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
        if (!(object instanceof SsfAlumno)) {
            return false;
        }
        SsfAlumno other = (SsfAlumno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfAlumno[ id=" + id + " ]";
    }

}
