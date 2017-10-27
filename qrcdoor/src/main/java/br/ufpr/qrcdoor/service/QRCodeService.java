package br.ufpr.qrcdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.QRCode;
import br.ufpr.qrcdoor.exception.ResourceNotFoundException;
import br.ufpr.qrcdoor.repository.QRCodeRepository;

@Service
public class QRCodeService {
	
	@Autowired
	QRCodeRepository qrCodeRepository;
	
	public List<QRCode> findAll() throws Exception {
		List<QRCode> list = this.qrCodeRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public QRCode findOne(Long id) throws Exception {
		QRCode qrCode = this.qrCodeRepository.findOne(id);
		if (qrCode == null) {
			throw new ResourceNotFoundException();
		}
		return qrCode;
	}
	
	public QRCode save(QRCode qrCode) throws Exception {
		return this.qrCodeRepository.saveAndFlush(qrCode);
	}
	
	public void delete(Long id) throws Exception {
		this.qrCodeRepository.delete(id);
	}

}
