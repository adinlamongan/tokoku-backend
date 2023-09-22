package com.adn.tokoku.repository.impl;

import com.adn.tokoku.config.AppProperties;
import com.adn.tokoku.dto.ProductListResponseDTO;
import com.adn.tokoku.repository.ProductQueryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductQueryRepoImpl implements ProductQueryRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    AppProperties appProperties;

    public int  count(String nama, String color, Number hargaMulai, Number hargaSampai){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> parameters = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(p.id) FROM products p"
                    +" LEFT JOIN product_image pi ON pi.product_id=p.id");
        if (color.length() > 0) {
            sql.append("  JOIN product_color pc ON pc.product_id=p.id ");
        }

        sql.append(" WHERE pi.is_primary =true ");

        if (nama.length() > 0) {
            sql.append(" AND lower(p.nama) like lower( :nama )");
            parameters.put("nama", "%" + nama + "%");
        }

        if (color.length() > 0) {
            sql.append(" AND pc.color=:color");
            parameters.put("color", color);
        }
        if (hargaSampai.intValue() > 0 ) {
            sql.append(" AND harga >=:hargaMulai AND harga <=:hargaSampai");
            parameters.put("hargaMulai", hargaMulai);
            parameters.put("hargaSampai", hargaSampai);
        }
        return namedParameterJdbcTemplate.queryForObject(sql.toString(), parameters, Integer.class);

    }



    @Override
    public Page<ProductListResponseDTO> findProducts(String nama, String color, Number hargaMulai, Number hargaSampai, String orderBy, String direction, Pageable pageable) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> parameters = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.id,p.nama,p.harga,");
        sql.append("'").append(appProperties.getProductStorage()).append("'").append("|| pi.image as image");
        sql.append(" FROM products p"
                +" LEFT JOIN product_image pi ON pi.product_id=p.id");

        if (color.length() > 0) {
            sql.append("  JOIN product_color pc ON pc.product_id=p.id ");
        }

        sql.append(" WHERE pi.is_primary =true ");

        if (nama.length() > 0) {
            sql.append(" AND lower(p.nama) like lower( :nama )");
            parameters.put("nama", "%" + nama + "%");
        }

        if (color.length() > 0) {
            sql.append(" AND pc.color=:color");
            parameters.put("color", color);
        }
        if (hargaSampai.intValue() > 0 ) {
            sql.append(" AND harga >=:hargaMulai AND harga <=:hargaSampai");
            parameters.put("hargaMulai", hargaMulai);
            parameters.put("hargaSampai", hargaSampai);
        }

        sql.append(" ORDER BY ").append(orderBy).append(" ").append(direction);
        sql.append(" LIMIT ").append(pageable.getPageSize()).append(" OFFSET ").append(pageable.getOffset());

        List<ProductListResponseDTO> listData = namedParameterJdbcTemplate.query(sql.toString(),parameters,new ProductQueryRowMapper());

        return new PageImpl<>(listData, pageable, count(nama,color,hargaMulai,hargaSampai));
    }

    private static class ProductQueryRowMapper implements RowMapper<ProductListResponseDTO> {

        @Override
        public ProductListResponseDTO mapRow(ResultSet rs, int rowNum) throws  SQLException {
            ProductListResponseDTO dto = new ProductListResponseDTO();
            dto.setId(rs.getInt("id"));
            dto.setNama(rs.getString("nama"));
            dto.setHarga(rs.getDouble("harga"));
            dto.setImage(rs.getString("image"));
            return dto;
        }
    }
}
