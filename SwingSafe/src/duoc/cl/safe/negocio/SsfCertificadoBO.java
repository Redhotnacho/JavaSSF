/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfCertificado;
import duoc.cl.safe.persistencia.SsfCertificadoDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfCertificadoBO {

    private SsfCertificadoDAO certificadoDAO;

    public SsfCertificadoBO() {
        this.certificadoDAO = new SsfCertificadoDAO();
    }

    public boolean add(SsfCertificado certificado) {
        return this.certificadoDAO.add(certificado);
    }

    public boolean update(SsfCertificado certificado) {
        return this.certificadoDAO.update(certificado);
    }

    public boolean remove(int id) {
        return this.certificadoDAO.remove(id);
    }

    public SsfCertificado find(int id) {
        return this.certificadoDAO.find(id);
    }

    public List<SsfCertificado> getAll() {
        return this.certificadoDAO.getAll();
    }

    public SsfCertificado findSP(int id) {
        return this.certificadoDAO.findSP(id);
    }

    public List<SsfCertificado> getAllSP() {
        return this.certificadoDAO.getAllSP();
    }

    public boolean addSP(SsfCertificado certificado) {
        return this.certificadoDAO.addSP(certificado);
    }

    public boolean updateSP(SsfCertificado certificado) {
        return this.certificadoDAO.updateSP(certificado);
    }

    public boolean removeSP(int id) {
        return this.certificadoDAO.removeSP(id);
    }

    public boolean desactivarSP(int id) {
        return this.certificadoDAO.desactivarSP(id);
    }

    public boolean activarSP(int id) {
        return this.certificadoDAO.activarSP(id);
    }

}
