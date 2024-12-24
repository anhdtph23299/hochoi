package com.example.demo.entity;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLException;

public class NClobImpl implements NClob {
    private String data;

    public NClobImpl(String data) {
        this.data = data;
    }

    @Override
    public String getSubString(long pos, int length) throws SQLException {
        if (pos < 1 || pos > data.length()) {
            throw new SQLException("Vị trí không hợp lệ.");
        }
        return data.substring((int) pos - 1, Math.min((int) pos - 1 + length, data.length()));
    }

    @Override
    public long length() throws SQLException {
        return data.length();
    }

    @Override
    public long position(String searchstr, long start) throws SQLException {
        if (start < 1 || start > data.length()) {
            throw new SQLException("Vị trí không hợp lệ.");
        }
        int index = data.indexOf(searchstr, (int) start - 1);
        return index == -1 ? -1 : index + 1; // Trả về vị trí bắt đầu (1-based)
    }

    @Override
    public long position(Clob searchstr, long start) throws SQLException {
        return 0;
    }

    @Override
    public int setString(long pos, String str) throws SQLException {
        return 0;
    }

    @Override
    public int setString(long pos, String str, int offset, int len) throws SQLException {
        return 0;
    }


    @Override
    public void free() throws SQLException {
        // Giải phóng tài nguyên nếu cần
        data = null; // Dọn dẹp dữ liệu
    }

    @Override
    public Reader getCharacterStream() throws SQLException {
        return new StringReader(data);
    }

    @Override
    public InputStream getAsciiStream() throws SQLException {
        return null;
    }

    @Override
    public Reader getCharacterStream(long pos, long length) throws SQLException {
        if (pos < 1 || pos > data.length()) {
            throw new SQLException("Vị trí không hợp lệ.");
        }
        return new StringReader(data.substring((int) pos - 1, Math.min((int) pos - 1 + (int) length, data.length())));
    }

    @Override
    public void truncate(long len) throws SQLException {
        if (len < 0 || len > data.length()) {
            throw new SQLException("Độ dài không hợp lệ.");
        }
        data = data.substring(0, (int) len);
    }

    // Phương thức còn lại của interface NClob cần thực hiện (trả về mặc định hoặc ném ngoại lệ)
    @Override
    public String toString() {
        return data;
    }

    // Các phương thức khác không được sử dụng trong ví dụ này




    @Override
    public OutputStream setAsciiStream(long pos) throws SQLException {
        return null;
    }

    @Override
    public Writer setCharacterStream(long pos) throws SQLException {
        return null;
    }


}
