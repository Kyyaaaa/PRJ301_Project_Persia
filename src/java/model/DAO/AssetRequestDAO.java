package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.AssetRequest;
import model.DBContext;

public class AssetRequestDAO extends DBContext {
    
    public AssetRequest create(AssetRequest req) {
        String sql = """
            INSERT INTO AssetRequest
            (asset_id, classroom_id, requested_by, purpose, expected_return_date)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS
            )
        ) {
            ps.setInt(1, req.getAssetId());
            ps.setInt(2, req.getClassroomId());
            ps.setString(3, req.getRequestedBy());

            // nullable
            ps.setObject(4, req.getPurpose());
            ps.setObject(5,
                req.getExpectedReturnDate() == null
                    ? null
                    : new java.sql.Date(req.getExpectedReturnDate().getTime())
            );

            int affected = ps.executeUpdate();

            if (affected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    req.setRequestId(rs.getInt(1));
                    req.setStatus("PENDING");
                    return req;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
