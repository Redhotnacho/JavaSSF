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
@Table(name = "SSF_ATENCIONMEDICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfAtencionmedica.findAll", query = "SELECT s FROM SsfAtencionmedica s")
    , @NamedQuery(name = "SsfAtencionmedica.findById", query = "SELECT s FROM SsfAtencionmedica s WHERE s.id = :id")
    , @NamedQuery(name = "SsfAtencionmedica.findByFechCreacion", query = "SELECT s FROM SsfAtencionmedica s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfAtencionmedica.findByEstado", query = "SELECT s FROM SsfAtencionmedica s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfAtencionmedica.findByDiagnostico", query = "SELECT s FROM SsfAtencionmedica s WHERE s.diagnostico = :diagnostico")
    , @NamedQuery(name = "SsfAtencionmedica.findByDescripcion", query = "SELECT s FROM SsfAtencionmedica s WHERE s.descripcion = :descripcion")})
public class SsfAtencionmedica implements Serializable {

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
    @Column(name = "DIAGNOSTICO")
    private String diagnostico;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idAtencionmedica")
    private List<SsfFichamedicaatencion> ssfFichamedicaatencionList;
    @JoinColumn(name = "ID_MEDICO", referencedColumnName = "ID")
    @ManyToOne
    private SsfMedico idMedico;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private SsfUsuario idUsuario;
    @OneToMany(mappedBy = "idAtencionmedica")
    private List<SsfAdjunto> ssfAdjuntoList;

    public SsfAtencionmedica() {
    }

    public SsfAtencionmedica(BigDecimal id) {
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

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<SsfFichamedicaatencion> getSsfFichamedicaatencionList() {
        return ssfFichamedicaatencionList;
    }

    public void setSsfFichamedicaatencionList(List<SsfFichamedicaatencion> ssfFichamedicaatencionList) {
        this.ssfFichamedicaatencionList = ssfFichamedicaatencionList;
    }

    public SsfMedico getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(SsfMedico idMedico) {
        this.idMedico = idMedico;
    }

    public SsfUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SsfUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<SsfAdjunto> getSsfAdjuntoList() {
        return ssfAdjuntoList;
    }

    public void setSsfAdjuntoList(List<SsfAdjunto> ssfAdjuntoList) {
        this.ssfAdjuntoList = ssfAdjuntoList;
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
        if (!(object instanceof SsfAtencionmedica)) {
            return false;
        }
        SsfAtencionmedica other = (SsfAtencionmedica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfAtencionmedica[ id=" + id + " ]";
    }
    
}
