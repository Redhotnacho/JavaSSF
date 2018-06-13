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
@Table(name = "SSF_MEDICO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfMedico.findAll", query = "SELECT s FROM SsfMedico s")
    , @NamedQuery(name = "SsfMedico.findById", query = "SELECT s FROM SsfMedico s WHERE s.id = :id")
    , @NamedQuery(name = "SsfMedico.findByFechCreacion", query = "SELECT s FROM SsfMedico s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfMedico.findByEstado", query = "SELECT s FROM SsfMedico s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfMedico.findByNombre", query = "SELECT s FROM SsfMedico s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SsfMedico.findByApellidos", query = "SELECT s FROM SsfMedico s WHERE s.apellidos = :apellidos")})
public class SsfMedico implements Serializable {

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
    @Column(name = "APELLIDOS")
    private String apellidos;
    @JoinColumn(name = "ID_CENTROMEDICO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SsfCentromedico idCentromedico;
    @JoinColumn(name = "ID_ESPECIALIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SsfMedicoespecialidad idEspecialidad;
    @OneToMany(mappedBy = "idMedico")
    private List<SsfAtencionmedica> ssfAtencionmedicaList;

    public SsfMedico() {
    }

    public SsfMedico(BigDecimal id) {
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public SsfCentromedico getIdCentromedico() {
        return idCentromedico;
    }

    public void setIdCentromedico(SsfCentromedico idCentromedico) {
        this.idCentromedico = idCentromedico;
    }

    public SsfMedicoespecialidad getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(SsfMedicoespecialidad idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @XmlTransient
    public List<SsfAtencionmedica> getSsfAtencionmedicaList() {
        return ssfAtencionmedicaList;
    }

    public void setSsfAtencionmedicaList(List<SsfAtencionmedica> ssfAtencionmedicaList) {
        this.ssfAtencionmedicaList = ssfAtencionmedicaList;
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
        if (!(object instanceof SsfMedico)) {
            return false;
        }
        SsfMedico other = (SsfMedico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfMedico[ id=" + id + " ]";
    }

}
