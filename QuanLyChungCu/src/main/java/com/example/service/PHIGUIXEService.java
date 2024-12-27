package com.example.service;

import java.util.ArrayList;
import java.util.Map;

import com.example.dao.PHIGUIXEDao;
import com.example.model.PHIGUIXE;

public class PHIGUIXEService {

	private final PHIGUIXEDao phiguixeDao;

	public PHIGUIXEService() {
		this.phiguixeDao = new PHIGUIXEDao();
	}

	public boolean addPHIGUIXE(PHIGUIXE phi) {
		validatePhiGuiXe(phi);
		int result = phiguixeDao.insert(phi);
		System.out.println("Thêm phí gửi xe: " + (result > 0 ? "Thành công" : "Thất bại"));
		return result > 0;
	}

	public boolean updatePHIGUIXE(PHIGUIXE phi) {
		validatePhiGuiXe(phi);
		int result = phiguixeDao.update(phi);
		System.out.println("Cập nhật phí gửi xe: " + (result > 0 ? "Thành công" : "Thất bại"));
		return result > 0;
	}

	public boolean deletePHIGUIXE(int idCanHo, String hanthu) {
		validateHanThu(hanthu);
		boolean isDeleted = phiguixeDao.delete(new PHIGUIXE(idCanHo, 0, 0, hanthu, 0));
		System.out.println("Xóa phí gửi xe: " + (isDeleted ? "Thành công" : "Không tìm thấy phí để xóa"));
		return isDeleted;
	}

	public ArrayList<PHIGUIXE> getAllPHIGUIXE() {
		ArrayList<PHIGUIXE> phiguixeList = phiguixeDao.selectAll();
		if (phiguixeList.isEmpty()) {
			System.out.println("Danh sách phí gửi xe hiện đang trống.");
		}
		return phiguixeList;
	}

	public PHIGUIXE getPHIGUIXEById(int idCanHo, String hanthu) {
		validateHanThu(hanthu);
		PHIGUIXE phi = phiguixeDao.selectById(new PHIGUIXE(idCanHo, 0, 0, hanthu, 0));
		if (phi == null) {
			System.err.println("Không tìm thấy phí gửi xe với IdCanHo: " + idCanHo + " và Hanthu: " + hanthu);
			throw new RuntimeException("Không tìm thấy phí gửi xe.");
		}
		return phi;
	}

	public int applyFeeForAll(int giasoxemay, int giaoto, String hanthu) {
		if (giasoxemay < 0 || giaoto < 0) {
			throw new IllegalArgumentException("Phí xe máy hoặc ô tô không được nhỏ hơn 0.");
		}
		validateHanThu(hanthu);
		int affectedRows = phiguixeDao.applyFeeForAll(giasoxemay, giaoto, hanthu);
		System.out.println("Đã áp dụng phí gửi xe cho " + affectedRows + " căn hộ.");
		return affectedRows;
	}

	public ArrayList<Map<String, Object>> searchPHIGUIXE(String tenChuHo, String sonha, Integer month, Integer year) {
		ArrayList<Map<String, Object>> results = phiguixeDao.searchPHIGUIXE(tenChuHo, sonha, month, year);
		if (results.isEmpty()) {
			System.out.println("Không tìm thấy kết quả phù hợp.");
		} else {
			System.out.println("Tìm thấy " + results.size() + " kết quả phù hợp.");
		}
		return results;
	}

	/**
	 * Phương thức mới: Lấy danh sách phí gửi xe dựa trên tháng và năm.
	 */
	public ArrayList<PHIGUIXE> getPHIGUIXEByMonthAndYear(Integer month, Integer year) {
		if (month == null || year == null) {
			throw new IllegalArgumentException("Tháng và năm không được để trống.");
		}
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("Tháng phải nằm trong khoảng từ 1 đến 12.");
		}
		ArrayList<PHIGUIXE> phiguixeList = phiguixeDao.selectByMonthAndYear(month, year);
		if (phiguixeList.isEmpty()) {
			System.out.println("Không tìm thấy phí gửi xe cho tháng " + month + " và năm " + year);
		}
		return phiguixeList;
	}

	private void validatePhiGuiXe(PHIGUIXE phi) {
		if (phi.getTienxemay() < 0 || phi.getTienxeoto() < 0) {
			throw new IllegalArgumentException("Phí xe máy hoặc xe đạp không được nhỏ hơn 0.");
		}
		validateHanThu(phi.getHanthu());
	}

	private void validateHanThu(String hanthu) {
		if (hanthu == null || hanthu.trim().isEmpty()) {
			throw new IllegalArgumentException("Hạn thu không được để trống.");
		}
		if (!hanthu.matches("\\d{4}-\\d{2}")) {
			throw new IllegalArgumentException("Hạn thu phải đúng định dạng YYYY-MM.");
		}
	}
}
