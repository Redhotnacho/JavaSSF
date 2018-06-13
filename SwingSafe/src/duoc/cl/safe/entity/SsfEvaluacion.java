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
@Table(name = "SSF_EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfEvaluacion.findAll", query = "SELECT s FROM SsfEvaluacion s")
    , @NamedQuery(name = "SsfEvaluacion.findById", query = "SELECT s FROM SsfEvaluacion s WHERE s.id = :id")
    , @NamedQuery(name = "SsfEvaluacion.findByFechCreacion", query = "SELECT s FROM SsfEvaluacion s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfEvaluacion.findByEstado", query = "SELECT s FROM SsfEvaluacion s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfEvaluacion.findByNombre", query = "SELECT s FROM SsfEvaluacion s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SsfEvaluacion.findByFecha", query = "SELECT s FROM SsfEvaluacion s WHERE s.fecha = :fecha")})
public class SsfEvaluacion implements Serializable {

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
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private SsfEmpresa idEmpresa;
    @JoinColumn(name = "ID_EVALUACIONESTADO", referencedColumnName = "ID")
    @ManyToOne
    private SsfEvaluacionestado idEvaluacionestado;
    @JoinColumn(name = "ID_EVALUACIONTIPO", referencedColumnName = "ID")
    @ManyToOne
    private SsfEvaluaciontipo idEvaluaciontipo;
    @OneToMany(mappedBy = "idEvaluacion")
    private List<SsfEvaluacionparametro> ssfEvaluacionparametroList;

    public SsfEvaluacion() {
    }

    public SsfEvaluacion(BigDecimal id) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public SsfEmpresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(SsfEmpresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public SsfEvaluacionestado getIdEvaluacionestado() {
        return idEvaluacionestado;
    }

    public void setIdEvaluacionestado(SsfEvaluacionestado idEvaluacionestado) {
        this.idEvaluacionestado = idEvaluacionestado;
    }

    public SsfEvaluaciontipo getIdEvaluaciontipo() {
        return idEvaluaciontipo;
    }

    public void setIdEvaluaciontipo(SsfEvaluaciontipo idEvaluaciontipo) {
        this.idEvaluaciontipo = idEvaluaciontipo;
    }

    @XmlTransient
    public List<SsfEvaluacionparametro> getSsfEvaluacionparametroList() {
        return ssfEvaluacionparametroList;
    }

    public void setSsfEvaluacionparametroList(List<SsfEvaluacionparametro> ssfEvaluacionparametroList) {
        this.ssfEvaluacionparametroList = ssfEvaluacionparametroList;
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
        if (!(object instanceof SsfEvaluacion)) {
            return false;
        }
        SsfEvaluacion other = (SsfEvaluacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfEvaluacion[ id=" + id + " ]";
    }

}
