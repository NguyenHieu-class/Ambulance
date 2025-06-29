package com.project.Ambulance.repository;

import com.project.Ambulance.model.BrandAmbulance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandAmbulanceRepository extends JpaRepository<BrandAmbulance, Integer> {

    // Tìm theo tên thương hiệu
    BrandAmbulance findByNameBrand(String nameBrand);

    // Lọc theo tên gần đúng, không phân biệt hoa thường
    List<BrandAmbulance> findByNameBrandContainingIgnoreCaseOrderByNameBrandAsc(String nameBrand);

    // Sắp xếp tất cả theo tên
    List<BrandAmbulance> findAllByOrderByNameBrandAsc();

    // Đếm số thương hiệu theo tên gần đúng
    int countByNameBrandContainingIgnoreCase(String nameBrand);

    // Đếm tổng số thương hiệu
    long count();

    // Tìm theo từ khoá gần đúng kết hợp đếm số lượng xe mỗi hãng (tuỳ vào yêu cầu frontend)
    @Query(value = "SELECT b.id_brand, b.name_brand, COUNT(a.id) as ambulance_count FROM brand_ambulance b " +
            "LEFT JOIN ambulance a ON b.id_brand = a.brand_ambulance_id " +
            "WHERE b.name_brand LIKE %:keyword% GROUP BY b.id_brand, b.name_brand ORDER BY b.name_brand ASC", nativeQuery = true)
    List<Object[]> findBrandsWithAmbulanceCount(@Param("keyword") String keyword);
}
