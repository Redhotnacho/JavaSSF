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
    
    public boolean add(SsfCertificado certificado){
        return this.certificadoDAO.add(certificado);
    }
    
    public boolean update(SsfCertificado certificado){
        return this.certificadoDAO.update(certificado);
    }
    
    public boolean remove(int id){
        return this.certificadoDAO.remove(id);
    }
    
    public SsfCertificado find(int id){
        return this.certificadoDAO.find(id);
    }
    
    public List<SsfCertificado> getAll(){
        return this.certificadoDAO.getAll();
    }
}
