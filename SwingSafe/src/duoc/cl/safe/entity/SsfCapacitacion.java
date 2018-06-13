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
@Table(name = "SSF_CAPACITACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfCapacitacion.findAll", query = "SELECT s FROM SsfCapacitacion s")
    , @NamedQuery(name = "SsfCapacitacion.findById", query = "SELECT s FROM SsfCapacitacion s WHERE s.id = :id")
    , @NamedQuery(name = "SsfCapacitacion.findByFechCreacion", query = "SELECT s FROM SsfCapacitacion s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfCapacitacion.findByEstado", query = "SELECT s FROM SsfCapacitacion s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfCapacitacion.findByNombre", query = "SELECT s FROM SsfCapacitacion s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SsfCapacitacion.findByHoras", query = "SELECT s FROM SsfCapacitacion s WHERE s.horas = :horas")
    , @NamedQuery(name = "SsfCapacitacion.findByFechaInicio", query = "SELECT s FROM SsfCapacitacion s WHERE s.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "SsfCapacitacion.findByFechaTermino", query = "SELECT s FROM SsfCapacitacion s WHERE s.fechaTermino = :fechaTermino")})
public class SsfCapacitacion implements Serializable {

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
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "HORAS")
    private Long horas;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_TERMINO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTermino;
    @OneToMany(mappedBy = "idCapacitacion")
    private List<SsfCertificado> ssfCertificadoList;
    @OneToMany(mappedBy = "idCapacitacion")
    private List<SsfCapacitacionempresa> ssfCapacitacionempresaList;
    @JoinColumn(name = "ID_CAPACITACIONTIPO", referencedColumnName = "ID")
    @ManyToOne
    private SsfCapacitaciontipo idCapacitaciontipo;

    public SsfCapacitacion() {
    }

    public SsfCapacitacion(BigDecimal id) {
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

    public Long getHoras() {
        return horas;
    }

    public void setHoras(Long horas) {
        this.horas = horas;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    @XmlTransient
    public List<SsfCertificado> getSsfCertificadoList() {
        return ssfCertificadoList;
    }

    public void setSsfCertificadoList(List<SsfCertificado> ssfCertificadoList) {
        this.ssfCertificadoList = ssfCertificadoList;
    }

    @XmlTransient
    public List<SsfCapacitacionempresa> getSsfCapacitacionempresaList() {
        return ssfCapacitacionempresaList;
    }

    public void setSsfCapacitacionempresaList(List<SsfCapacitacionempresa> ssfCapacitacionempresaList) {
        this.ssfCapacitacionempresaList = ssfCapacitacionempresaList;
    }

    public SsfCapacitaciontipo getIdCapacitaciontipo() {
        return idCapacitaciontipo;
    }

    public void setIdCapacitaciontipo(SsfCapacitaciontipo idCapacitaciontipo) {
        this.idCapacitaciontipo = idCapacitaciontipo;
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
        if (!(object instanceof SsfCapacitacion)) {
            return false;
        }
        SsfCapacitacion other = (SsfCapacitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfCapacitacion[ id=" + id + " ]";
    }

}
