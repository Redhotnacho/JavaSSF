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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nacho
 */
@Entity
@Table(name = "SSF_PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfPersona.findAll", query = "SELECT s FROM SsfPersona s")
    , @NamedQuery(name = "SsfPersona.findById", query = "SELECT s FROM SsfPersona s WHERE s.id = :id")
    , @NamedQuery(name = "SsfPersona.findByRut", query = "SELECT s FROM SsfPersona s WHERE s.rut = :rut")
    , @NamedQuery(name = "SsfPersona.findByNombre", query = "SELECT s FROM SsfPersona s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SsfPersona.findByApPaterno", query = "SELECT s FROM SsfPersona s WHERE s.apPaterno = :apPaterno")
    , @NamedQuery(name = "SsfPersona.findByApMaterno", query = "SELECT s FROM SsfPersona s WHERE s.apMaterno = :apMaterno")
    , @NamedQuery(name = "SsfPersona.findByCorreo", query = "SELECT s FROM SsfPersona s WHERE s.correo = :correo")
    , @NamedQuery(name = "SsfPersona.findByTelefono", query = "SELECT s FROM SsfPersona s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "SsfPersona.findByFechaNac", query = "SELECT s FROM SsfPersona s WHERE s.fechaNac = :fechaNac")
    , @NamedQuery(name = "SsfPersona.findByFechCreacion", query = "SELECT s FROM SsfPersona s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfPersona.findByEstado", query = "SELECT s FROM SsfPersona s WHERE s.estado = :estado")})
public class SsfPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "RUT")
    private String rut;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "AP_PATERNO")
    private String apPaterno;
    @Column(name = "AP_MATERNO")
    private String apMaterno;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "TELEFONO")
    private BigInteger telefono;
    @Column(name = "FECHA_NAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNac;
    @Column(name = "FECH_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechCreacion;
    @Column(name = "ESTADO")
    private Short estado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private SsfUsuario ssfUsuario;
    @OneToOne(mappedBy = "idPersona")
    private SsfAlumno ssfAlumno;

    public SsfPersona() {
    }

    public SsfPersona(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
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

    public SsfUsuario getSsfUsuario() {
        return ssfUsuario;
    }

    public void setSsfUsuario(SsfUsuario ssfUsuario) {
        this.ssfUsuario = ssfUsuario;
    }

    public SsfAlumno getSsfAlumno() {
        return ssfAlumno;
    }

    public void setSsfAlumno(SsfAlumno ssfAlumno) {
        this.ssfAlumno = ssfAlumno;
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
        if (!(object instanceof SsfPersona)) {
            return false;
        }
        SsfPersona other = (SsfPersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfPersona[ id=" + id + " ]";
    }

}
