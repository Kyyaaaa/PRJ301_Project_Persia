package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.AssetRequest;
import model.DBContext;

public class AssetRequestDAO extends DBContext {
    
    public List<AssetRequest> getAllAssetRequests() {
        String sql = "SELECT * FROM AssetRequest ORDER BY request_date DESC";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            List<AssetRequest> list = new java.util.ArrayList<>();

            while (rs.next()) {
                AssetRequest req = new AssetRequest();

                req.setRequestId(rs.getInt("request_id"));
                req.setAssetId(rs.getInt("asset_id"));
                req.setClassroomId(rs.getInt("classroom_id"));
                req.setRequestedBy(rs.getString("requested_by"));
                req.setPurpose(rs.getString("purpose"));

                req.setRequestDate(rs.getTimestamp("request_date"));
                req.setExpectedReturnDate(rs.getDate("expected_return_date"));

                req.setStatus(rs.getString("status"));
                req.setReviewedBy(rs.getString("reviewed_by"));
                req.setReviewedDate(rs.getTimestamp("reviewed_date"));
                req.setReviewNote(rs.getString("review_note"));

                list.add(req);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<AssetRequest> getAssetRequestsByUsername(String username) {
        String sql = """
            SELECT *
            FROM AssetRequest
            WHERE requested_by = ?
            ORDER BY request_date DESC
        """;

        List<AssetRequest> list = new ArrayList<>();

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AssetRequest r = new AssetRequest(
                    rs.getInt("request_id"),
                    rs.getInt("asset_id"),
                    rs.getInt("classroom_id"),
                    rs.getString("requested_by"),
                    rs.getString("purpose"),
                    rs.getTimestamp("request_date"),
                    rs.getDate("expected_return_date"),
                    rs.getString("status"),
                    rs.getString("reviewed_by"),
                    rs.getTimestamp("reviewed_date"),
                    rs.getString("review_note")
                );
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
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
    
    public boolean delete(String requestId) {
        String sql = """
            DELETE FROM AssetRequest
            WHERE request_id = ?
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, requestId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean isExist(String requestId) {
        String sql = """
            SELECT 1
            FROM AssetRequest
            WHERE request_id = ?
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, Integer.parseInt(requestId));

            ResultSet rs = ps.executeQuery();
            return rs.next(); // có bản ghi => tồn tại

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public AssetRequest edit(
            int requestId,
            String status,
            String reviewedBy,
            Date reviewedDate,
            String reviewNote
    ) {
        String sql = """
            UPDATE AssetRequest
            SET status = ?,
                reviewed_by = ?,
                reviewed_date = ?,
                review_note = ?
            WHERE request_id = ?
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, status);
            ps.setString(2, reviewedBy);

            // reviewed_date có thể null
            if (reviewedDate != null) {
                ps.setTimestamp(3, new java.sql.Timestamp(reviewedDate.getTime()));
            } else {
                ps.setTimestamp(3, null);
            }

            ps.setString(4, reviewNote);
            ps.setInt(5, requestId);

            int affected = ps.executeUpdate();

            if (affected > 0) {
                // Lấy lại bản ghi sau khi update
                String selectSql = """
                    SELECT *
                    FROM AssetRequest
                    WHERE request_id = ?
                """;

                try (PreparedStatement ps2 = con.prepareStatement(selectSql)) {
                    ps2.setInt(1, requestId);
                    ResultSet rs = ps2.executeQuery();

                    if (rs.next()) {
                        return new AssetRequest(
                            rs.getInt("request_id"),
                            rs.getInt("asset_id"),
                            rs.getInt("classroom_id"),
                            rs.getString("requested_by"),
                            rs.getString("purpose"),
                            rs.getTimestamp("request_date"),
                            rs.getDate("expected_return_date"),
                            rs.getString("status"),
                            rs.getString("reviewed_by"),
                            rs.getTimestamp("reviewed_date"),
                            rs.getString("review_note")
                        );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public AssetRequest findById(int requestId) {
        String sql = """
            SELECT *
            FROM AssetRequest
            WHERE request_id = ?
        """;

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new AssetRequest(
                    rs.getInt("request_id"),
                    rs.getInt("asset_id"),
                    rs.getInt("classroom_id"),
                    rs.getString("requested_by"),
                    rs.getString("purpose"),
                    rs.getTimestamp("request_date"),
                    rs.getDate("expected_return_date"),
                    rs.getString("status"),
                    rs.getString("reviewed_by"),
                    rs.getTimestamp("reviewed_date"),
                    rs.getString("review_note")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // không tìm thấy
    }
}
