package com.adn.tokoku.repository.impl;

import com.adn.tokoku.config.AppProperties;
import com.adn.tokoku.dto.ProductListResponseDTO;
import com.adn.tokoku.repository.WishlistQueryRepo;
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
public class WishlistQueryRepoImpl implements WishlistQueryRepo {

    @Autowired
    AppProperties appProperties;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int  count(int userId){
        String sql = "SELECT p.id,p.nama,p.harga,pi.image FROM wishlist w"
                + "JOIN products p ON p.id=w.product_id"
                + "JOIN product_image pi on pi.product_id=p.id"
                + "WHERE PI.is_primary=true AND w.user_id= ?";


        return jdbcTemplate.queryForObject(sql, Integer.class, userId);

    }
    @Override
    public Page<ProductListResponseDTO> findProducts(int userId, Pageable pageable) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.id,p.nama,p.harga,");
        sql.append("'").append(appProperties.getProductStorage()).append("'").append("|| pi.image as image");
        sql.append(" FROM wishlist w"
                + "JOIN products p ON p.id=w.product_id"
                + "JOIN product_image pi on pi.product_id=p.id"
                + "WHERE PI.is_primary=true AND w.user_id= ?");


        sql.append(" ORDER BY p.nama ASC ");
        sql.append(" LIMIT ").append(pageable.getPageSize()).append(" OFFSET ").append(pageable.getOffset());

        List<ProductListResponseDTO> listData = jdbcTemplate.query(sql.toString(), new WishlistQueryRowMapper(),userId);

        return new PageImpl<>(listData, pageable, count(userId));
    }

    private static class WishlistQueryRowMapper implements RowMapper<ProductListResponseDTO> {

        @Override
        public ProductListResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductListResponseDTO dto = new ProductListResponseDTO();
            dto.setId(rs.getInt("id"));
            dto.setNama(rs.getString("nama"));
            dto.setHarga(rs.getDouble("harga"));
            dto.setImage(rs.getString("image"));
            return dto;
        }
    }
}
