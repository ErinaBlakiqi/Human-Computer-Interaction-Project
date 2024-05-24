package services;

import model.dto.DailyRevenueDto;
import repository.AdminRepository;

import java.util.List;

public class AdminService {

    private AdminRepository adminRepository = new AdminRepository();

    public int getTotalOrders() {
        return adminRepository.getTotalOrders();
    }

    public int getPendingOrders() {
        return adminRepository.getPendingOrders();
    }

    public int getCompletedOrders() {
        return adminRepository.getCompletedOrders();
    }

    public double getTotalRevenue() {
        return adminRepository.getTotalRevenue();
    }

    public double getDailyRevenue() {
        return adminRepository.getDailyRevenue();
    }

    public List<DailyRevenueDto> getDailyRevenueData() {
        return adminRepository.getDailyRevenueData();
    }

    public List<DailyRevenueDto> getMonthlyRevenueData() {
        return adminRepository.getMonthlyRevenueData();
    }
}
