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
@Table(name = "SSF_VISTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsfVista.findAll", query = "SELECT s FROM SsfVista s")
    , @NamedQuery(name = "SsfVista.findById", query = "SELECT s FROM SsfVista s WHERE s.id = :id")
    , @NamedQuery(name = "SsfVista.findByFechCreacion", query = "SELECT s FROM SsfVista s WHERE s.fechCreacion = :fechCreacion")
    , @NamedQuery(name = "SsfVista.findByEstado", query = "SELECT s FROM SsfVista s WHERE s.estado = :estado")
    , @NamedQuery(name = "SsfVista.findByNombre", query = "SELECT s FROM SsfVista s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SsfVista.findByUrl", query = "SELECT s FROM SsfVista s WHERE s.url = :url")})
public class SsfVista implements Serializable {

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
    @Column(name = "URL")
    private String url;
    @OneToMany(mappedBy = "idVista")
    private List<SsfPerfilvista> ssfPerfilvistaList;
    @JoinColumn(name = "ID_MENU", referencedColumnName = "ID")
    @ManyToOne
    private SsfMenu idMenu;

    public SsfVista() {
    }

    public SsfVista(BigDecimal id) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public List<SsfPerfilvista> getSsfPerfilvistaList() {
        return ssfPerfilvistaList;
    }

    public void setSsfPerfilvistaList(List<SsfPerfilvista> ssfPerfilvistaList) {
        this.ssfPerfilvistaList = ssfPerfilvistaList;
    }

    public SsfMenu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(SsfMenu idMenu) {
        this.idMenu = idMenu;
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
        if (!(object instanceof SsfVista)) {
            return false;
        }
        SsfVista other = (SsfVista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "duoc.cl.safe.entity.SsfVista[ id=" + id + " ]";
    }

}
