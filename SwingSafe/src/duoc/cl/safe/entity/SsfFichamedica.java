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
@Table(name = "SSF_FICHAMEDICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfFichamedica.findAll", query = "SELECT s FROM SsfFichamedica s")
    , @NamedQuery(name = "SsfFichamedica.findById", query = "SELECT s FROM SsfFichamedica s WHERE s.id = :id")
    , @NamedQuery(name = "SsfFichamedica.findByFechCreacion", query = "SELECT s FROM SsfFichamedica s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfFichamedica.findByEstado", query = "SELECT s FROM SsfFichamedica s WHERE s.estado = :estado")})
public class SsfFichamedica implements Serializable {

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
    @OneToMany(mappedBy = "idFichamedica")
    private List<SsfFichamedicaatencion> ssfFichamedicaatencionList;
    @JoinColumn(name = "ID_EXAMEN", referencedColumnName = "ID")
    @ManyToOne
    private SsfExamen idExamen;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    @ManyToOne
    private SsfUsuario idUsuario;

    public SsfFichamedica() {
    }

    public SsfFichamedica(BigDecimal id) {
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
    public List<SsfFichamedicaatencion> getSsfFichamedicaatencionList() {
        return ssfFichamedicaatencionList;
    }

    public void setSsfFichamedicaatencionList(List<SsfFichamedicaatencion> ssfFichamedicaatencionList) {
        this.ssfFichamedicaatencionList = ssfFichamedicaatencionList;
    }

    public SsfExamen getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(SsfExamen idExamen) {
        this.idExamen = idExamen;
    }

    public SsfUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SsfUsuario idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof SsfFichamedica)) {
            return false;
        }
        SsfFichamedica other = (SsfFichamedica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfFichamedica[ id=" + id + " ]";
    }
    
}
